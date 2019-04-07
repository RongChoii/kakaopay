package com.kakaopay.finance.util;

public class RegexpUtil {

    public static String getRegexp(int length){
        String dq = "\"";
        String postfix = "[0-9]{3}";
        String regex;
        String result = "";
        for(int i=1; i<=length; i++){
            if(i >= 4){
                regex = "[0-9]{"+(i%3)+"},";
                result += dq + regex + postfix + "," + postfix + dq;
            } else {
                regex = "[0-9]{"+i+"},";
                result += dq + regex + postfix + dq;
            }

            if(i != length) {
                result += "|";
            }
        }
        return result;
    }

}