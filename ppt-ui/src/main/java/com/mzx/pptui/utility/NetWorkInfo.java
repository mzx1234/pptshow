package com.mzx.pptui.utility;



public class NetWorkInfo {
     
	String subnet;  
    String ip;
    public NetWorkInfo(String ip, String subnet)
    {
    	this.ip=ip;
    	this.subnet=subnet;
    }
          
      
     /**
      * 根据本机IP和子网掩码，计算子网广播地址 
     * @return String
     */
    public String getBroadcastAddress() {  
         String[] ips = ip.split("\\.");   
         String[] subnets = subnet.split("\\.");  
         StringBuffer sb = new StringBuffer();  
         for(int i = 0; i < ips.length; i++) {  
             ips[i] = String.valueOf((~Integer.parseInt(subnets[i]))|(Integer.parseInt(ips[i])));  
             sb.append(turnToStr(Integer.parseInt(ips[i])));  
             if(i != (ips.length-1))  
                 sb.append(".");  
         }      
//         System.out.println(turnToIp(sb.toString()));
         return turnToIp(sb.toString());  
    }  
  
     
     /** 
      * 把带符号整形转换为二进制 
      * @param num 
      * @return 
      */  
    private String turnToStr(int num) {  
        String str = "";  
        str = Integer.toBinaryString(num);            
        int len = 8 - str.length();  
        // 如果二进制数据少于8位,在前面补零.  
        for (int i = 0; i < len; i++) {  
            str = "0" + str;  
        }  
        //如果num为负数，转为二进制的结果有32位，如1111 1111 1111 1111 1111 1111 1101 1110  
        //则只取最后的8位.  
        if (len < 0)  
            str = str.substring(24, 32);  
        return str;  
    }      
      
    /** 
     * 把二进制形式的ip，转换为十进制形式的ip 
     * @param str 
     * @return 
     */  
    private String turnToIp(String str){  
        String[] ips = str.split("\\.");  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < ips.length; i++) {  
            sb.append(turnToInt(ips[i]));  
            sb.append(".");  
        }            
        sb.deleteCharAt(sb.length() - 1);  
        return sb.toString();  
    }  
  
    /** 
     * 把二进制转换为十进制 
     * @param str 
     * @return 
     */  
    private int turnToInt(String str){  
        int total = 0;  
        int top = str.length();  
        for (int i = 0; i < str.length(); i++) {  
            String h = String.valueOf(str.charAt(i));  
            top--;  
            total += ((int) Math.pow(2, top)) * (Integer.parseInt(h));  
        }  
        return total;  
    }
    
    
    
} 