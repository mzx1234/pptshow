package com.mzx.pptui.expand;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.*;

/**
 * spring加载properties文件
 * <p/>
 * 这里将properties暴露出来供项目业务使用
 *
 * @author jackson.mo
 */
public class CustomizedPropertyConfigurer extends PropertyPlaceholderConfigurer {

    private static Map<String, String> propertiesMap;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
            throws BeansException {

        super.processProperties(beanFactoryToProcess, props);
        propertiesMap = new HashMap<String, String>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            propertiesMap.put(keyStr, value);
        }
    }

    /**
     * 获取属性值
     *
     * @param name
     * @return
     */
    public static String getPropertyValue(String name) {
        return propertiesMap.get(name);
    }

    /**
     * 获取属性值，并解析成英文逗号隔开的字符串列表
     *
     * @param name
     * @return
     */
    public static List<String> getPropertyList(String name) {
        List<String> returnValue = new ArrayList<String>();
        String propertyValue = getPropertyValue(name);
        if(propertyValue != null) {
            String[] segments = propertyValue.split(",");
            for(String segment : segments) {
                returnValue.add(segment.trim());
            }
        }
        return returnValue;
    }

}
