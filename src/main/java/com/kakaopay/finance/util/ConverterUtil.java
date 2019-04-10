package com.kakaopay.finance.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConverterUtil {

    public static List<String> convertToBasicFormat(List<String> param) {
        return new ArrayList<String>(){{
            param.forEach(target -> {
                Matcher matcher = Pattern.compile(RegexpUtil.getRegexp(6)).matcher(target);
                String group;
                while(matcher.find()) {
                    group = matcher.group();
                    target = target.replaceAll(group, group.replaceAll(",", ""));
                }
                add(target.replaceAll("\"", ""));
                System.out.println("@@@@@@ target : " + target);
            });
        }};
    }

}