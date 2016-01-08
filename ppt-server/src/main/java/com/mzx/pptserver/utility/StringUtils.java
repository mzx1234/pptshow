package com.mzx.pptserver.utility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public abstract class StringUtils {
    /**
     * 用特殊字符分隔字符窜
     * @param content
     * @param splitChars
     * @param includeEmptyElement   包含空的字符串
     * @return
     */
    public static List<String> splitBySpecialChars(String content, String splitChars, boolean includeEmptyElement) {
        List<String> texts = new ArrayList<String>();
        int index = 0;
        StringBuffer sb = new StringBuffer();
        while (index < content.length()) {
            char c = content.charAt(index++);
            if (splitChars.indexOf(c) != -1) {
                if (includeEmptyElement || sb.length() > 0) {
                    String text = sb.toString().trim();
                    if (includeEmptyElement || text.length() > 0) {
                        texts.add(text);
                    }
                    sb.setLength(0);
                }
            } else {
                sb.append(c);
            }
        }
        if (includeEmptyElement || sb.length() > 0) {
            texts.add(sb.toString());
        }
        return texts;
    }

    public static List<String> splitBySpecialChars(String content, String splitChars) {
        return splitBySpecialChars(content, splitChars, false);
    }

    /**
     * 忽略掉脚本中的注释，只支持单行注释，特定注释标志之后的部分就全为注释，但是这个标志出现在引号和括号之内被认为是无效的
     * @param script
     * @return	2个元素的字符窜数组，第一个元素是内容，第二个元素是注释,如果不存在则返回空
     */
    public static String[] resolveComment(String content, String mark, char yinhao, char[] kuohao) {
        int index = 0;
        boolean inYinhao = false;
        int kuohaoDeep = 0;
        char[] markChars = mark.toCharArray();
        int markDeep = 0;
        while (index < content.length()) {
            char c = content.charAt(index++);
            if (c == yinhao && kuohaoDeep > 0) {
                throw new RuntimeException("语法错误：括号内部不能有引号" + content);
            }
            if (!inYinhao && kuohaoDeep == 0 && c == markChars[markDeep]) {
                markDeep++;
                if (markDeep == markChars.length) {
                    return new String[] { content.substring(0, index - markDeep), content.substring(index) };
                }
            } else {
                if (c == yinhao) {
                    inYinhao = !inYinhao;
                } else if (!inYinhao && c == kuohao[0]) {
                    kuohaoDeep++;
                } else if (!inYinhao && c == kuohao[1]) {
                    if (kuohaoDeep > 0) {
                        kuohaoDeep--;
                    }
                }
            }
        }
        //内容中不含注释
        return new String[] { content, "" };
    }

    public static String[] resolveComment(String content) {
        return resolveComment(content, "//", '"', "{}".toCharArray());
    }

    public static String ignoreComment(String content, String mark, char yinhao, char[] kuohao) {
        return resolveComment(content, mark, yinhao, kuohao)[0];
    }

    public static String ignoreComment(String content) {
        return resolveComment(content, "//", '"', "{}".toCharArray())[0];
    }

    public static String getComment(String content, String mark, char yinhao, char[] kuohao) {
        return resolveComment(content, mark, yinhao, kuohao)[1];
    }

    public static String getComment(String content) {
        return resolveComment(content, "//", '"', "{}".toCharArray())[1];
    }

    /**
     * 用特殊字符分隔字符窜，但特殊字符不能在引号之内
     * @param content
     * @param splitChars
     * @param yinhao
     * @return
     */
    public static List<String> splitBySpecialCharsOutOfYinhao(String content, String splitChars, char yinhao) {
        List<String> texts = new ArrayList<String>();
        int index = 0;
        boolean inYinhao = false;
        StringBuffer sb = new StringBuffer();
        while (index < content.length()) {
            char c = content.charAt(index++);
            if (c == yinhao || splitChars.indexOf(c) != -1 && !inYinhao) {
                if (sb.length() > 0) {
                    String text = sb.toString().trim();
                    if (text.length() > 0) {
                        texts.add(text);
                    }
                    sb.setLength(0);
                }
                if (c == yinhao) {
                    inYinhao = !inYinhao;
                }
            } else {
                sb.append(c);
            }
        }
        if (sb.length() > 0) {
            String text = sb.toString().trim();
            if (text.length() > 0) {
                texts.add(text);
            }
        }
        return texts;
    }

    /**
     * 用特殊字符分隔字符窜，但特殊字符不能在引号和有效的括号之内
     * 注意目前有效括号范围内带引号被认为是一种语法错误，这个限制是为了处理起来简单一些 //TODO
     * @param content
     * @param splitChars
     * @param yinhao
     * @param kuohao
     * @return
     */
    public static List<String> splitBySpecialCharsOutOfYinhaoAndKuohao(String content, String splitChars, char yinhao, char[] kuohao,
            boolean splitByYinhao) {
        List<String> texts = new ArrayList<String>();
        int index = 0;
        boolean inYinhao = false;
        int kuohaoDeep = 0;
        StringBuffer sb = new StringBuffer();
        while (index < content.length()) {
            char c = content.charAt(index++);
            //			if (c == yinhao && kuohaoDeep > 0) {
            //				throw new RuntimeException("语法错误：括号内部不能有引号" + content);
            //			}
            boolean newText = splitByYinhao && c == yinhao || !inYinhao && splitChars.indexOf(c) != -1;
            newText = newText && kuohaoDeep == 0;
            newText = newText && sb.length() > 0;
            if (newText) {
                String text = sb.toString().trim();
                if (text.length() > 0) {
                    if (text.charAt(0) == yinhao) {
                        text = text.substring(1);
                    }
                    if (text.charAt(text.length() - 1) == yinhao) {
                        text = text.substring(0, text.length() - 1);
                    }
                    texts.add(text);
                }
                sb.setLength(0);
            }
            if (!inYinhao && kuohaoDeep == 0 && splitChars.indexOf(c) != -1) {
                continue;
            }
            if (kuohaoDeep == 0 && c == yinhao) {
                inYinhao = !inYinhao;
            } else {
                if (!inYinhao && c == kuohao[0]) {
                    kuohaoDeep++;
                } else if (!inYinhao && c == kuohao[1]) {
                    if (kuohaoDeep > 0) {
                        kuohaoDeep--;
                    }
                }
                sb.append(c);
            }
        }
        if (sb.length() > 0) {
            String text = sb.toString().trim();
            if (text.length() > 0) {
                if (text.charAt(0) == yinhao) {
                    text = text.substring(1);
                }
                if (text.charAt(text.length() - 1) == yinhao) {
                    text = text.substring(0, text.length() - 1);
                }
                texts.add(text);
            }
        }
        return texts;
    }

    public static List<String> splitBySpecialCharsOutOfYinhaoAndKuohao(String content, String splitChars, char yinhao, char[] kuohao) {
        return splitBySpecialCharsOutOfYinhaoAndKuohao(content, splitChars, yinhao, kuohao, true);
    }

    public static List<String> splitBySpecialCharsOutOfYinhaoAndKuohao(String content, String splitChars) {
        return splitBySpecialCharsOutOfYinhaoAndKuohao(content, splitChars, '"', "{}".toCharArray());
    }

    public static List<String> splitBySpecialCharsAndYinhaoAndKuohao(String content, String splitChars, char yinhao, char[] kuohao) {
        List<String> texts = new ArrayList<String>();
        int index = 0;
        boolean inYinhao = false;
        int kuohaoDeep = 0;
        StringBuffer sb = new StringBuffer();
        while (index < content.length()) {
            char c = content.charAt(index);
            boolean newText = sb.length() > 0
                    && !inYinhao
                    && kuohaoDeep == 0
                    && (c == yinhao || content.charAt(index - 1) == yinhao || c == kuohao[0] || content.charAt(index - 1) == kuohao[1]
                            || splitChars.indexOf(c) != -1 || splitChars.indexOf(content.charAt(index - 1)) != -1);
            if (newText) {
                String text = sb.toString().trim();
                if (text.length() > 0) {
                    texts.add(text);
                }
                sb.setLength(0);
            }
            if (c == yinhao) {
                inYinhao = !inYinhao;
            } else if (!inYinhao) {
                if (c == kuohao[0]) {
                    kuohaoDeep++;
                } else if (c == kuohao[1]) {
                    kuohaoDeep--;
                }
            }
            sb.append(c);
            index++;
        }
        if (sb.length() > 0) {
            String text = sb.toString().trim();
            if (text.length() > 0) {
                texts.add(text);
            }
        }
        return texts;
    }

    public static List<String> splitBySpecialCharsAndYinhaoAndKuohao(String content, String splitChars) {
        return splitBySpecialCharsAndYinhaoAndKuohao(content, splitChars, '\"', "{}".toCharArray());
    }

    /**
     * 用逗号分开字符窜，但需要考虑括号，字符窜有可能被一个最外层的括号围住，此时需要先去掉最外层的括号
     * 这个方法用来处理数组，必须注意的是，括号里面不能带引号。
     * @param text
     * @return
     */
    public static String[] splitByKuohao(String text) {
        text = text.trim();
        if (text.charAt(0) == '{') {
            //去掉最外层的括号
            //如果第一个括号的对应括号在最后一个位置，则表示第一个括号是最外层的。
            //{xx,yy},{xx},{{},{}}	第一个括号不是最外层
            //{ {xx,yy},{xx},{{},{}} }	第一个括号是最外层,这种括号就需要先去掉。
            int index = 0;
            int deep = 0;
            while (index < text.length()) {
                char c = text.charAt(index++);
                if (c == '{') {
                    deep++;
                } else if (c == '}') {
                    deep--;
                    if (deep == 0) {
                        if (index == text.length()) {
                            //如果第一个括号的对应括号在最后一个位置
                            text = text.substring(1, text.length() - 1);
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        if (text.indexOf('{') == -1) {
            if (text.length() == 0) {
                return new String[0];
            }
            return text.split(",");
        } else {
            List<String> list = new ArrayList<String>();
            StringBuffer sb = new StringBuffer();
            int index = 0;
            int deep = 0;
            while (index < text.length()) {
                char c = text.charAt(index++);
                if (c == ',') {
                    if (deep == 0) {
                        if (sb.length() > 0) {
                            String sub = sb.toString().trim();
                            if (sub.length() > 0) {
                                list.add(sub);
                            }
                            sb.setLength(0);
                        }
                    } else {
                        sb.append(c);
                    }
                } else {
                    if (c == '{') {
                        deep++;
                    } else if (c == '}') {
                        if (deep > 0) {
                            deep--;
                        }
                    }
                    sb.append(c);
                }
            }
            if (sb.length() > 0) {
                String sub = sb.toString().trim();
                if (sub.length() > 0) {
                    list.add(sub);
                }
            }
            return list.toArray(new String[list.size()]);
        }
    }

    public static String getVarString(String source, Object... vars) {
        for (int i = 0; i * 2 < vars.length; i++) {
            String var = (String) vars[2 * i];
            String value = vars[2 * i + 1] == null ? "NULL" : vars[2 * i + 1].toString();
            source = replace(source, "${"+var+"}", value);
//            value = resolveReg(value);
//            source = source.replaceAll("[$][{]" + resolveVarName(var) + "[}]", value);
        }
        return source;
    }

    public static String replace(String source, String oldValue, String newValue) {
        StringBuffer sb = new StringBuffer();
        int idx = 0;
        int fitLen = 0;
        char[] buf = source.toCharArray();
        char[] oldValueBuf = oldValue.toCharArray();
        while (idx < buf.length) {
            char c = buf[idx];
            if (c == oldValueBuf[fitLen]) {
                fitLen++;
                if (fitLen == oldValueBuf.length) {
                    sb.append(newValue);
                    fitLen = 0;
                }
            } else {
                idx -= fitLen;
                fitLen = 0;
                sb.append(buf[idx]);
            }
            idx++;
        }
        return sb.toString();
    }

//    public static String resolveVarName(String var) {
//        StringBuffer sb = new StringBuffer();
//        for (char c : var.toCharArray()) {
//            if (SpecialVarChars.indexOf(c) != -1) {
//                sb.append('[').append(c).append(']');
//            } else {
//                sb.append(c);
//            }
//        }
//        return sb.toString();
//    }
//
//    public static String resolveReg(String replaceMent) {
//        StringBuffer sb = new StringBuffer();
//        for (char c : replaceMent.toCharArray()) {
//            if (SpecialRegChars.indexOf(c) != -1) {
//                sb.append('\\').append(c);
//            } else {
//                sb.append(c);
//            }
//        }
//        return sb.toString();
//    }
//
//    private static String SpecialVarChars = "${}[].*";
//    private static String SpecialRegChars = "\\$";
    
    /**
     * 目前仅支持String、Collection、Map，其他类型仅判断是否为null
     * @param obj
     * @return 对象是否为空，空则返回true
     */
    public static boolean isEmpty(Object obj){
    	boolean result = true;
    	if(obj != null){
    		if(obj instanceof String){
    			return obj.toString().trim().length()<=0;
    		}else if(obj instanceof Collection){
    			return ((Collection)obj).isEmpty();
    		}else if(obj instanceof Map){
    			return ((Map)obj).isEmpty();
    		}else{
    			result = false;
    		}
    	}
    	return result;
    }

}
