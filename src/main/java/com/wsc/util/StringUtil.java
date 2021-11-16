package com.wsc.util;

/**
 * @author wsc
 * @date 2021/5/2
 */
public class StringUtil {
    public static boolean isEmpty(String s){
        if (s.isEmpty() || s == null){
            return true;
        }
        return false;
    }
    public static boolean isNotEmpty(String s){
        if (s != ""  && s != null){
            return true;
        }
        return false;
    }
}
