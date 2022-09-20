package com.ricemonitoring.util;


import org.springframework.stereotype.Component;

//将字符串分割再合并，用于模糊匹配
public class SplitString {
    public static String setSplitString(String str){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <str.length(); i++) {
            if(!sb.toString().equals("")){
                sb.append(",");  //插入一个，符号
            }
            sb.append(str.charAt(i));
        }
        String keyword = sb.toString();
        keyword = keyword.replaceAll(",", "%"); //将所有的，符号转换为%
        return keyword;
    }

}
