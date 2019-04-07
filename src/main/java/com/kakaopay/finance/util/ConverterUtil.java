package com.kakaopay.finance.util;

import com.kakaopay.finance.model.file.FileDto;

import java.util.*;
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
                System.out.println(target);
            });
        }};
    }

    public static Map convertFromStringToMapFormat(String param) {
        return new HashMap(){{
            List<String> list = Arrays.asList(param.split(","));

            for(int i=0; i<list.size(); i++){
                put(FileDto.class.getDeclaredFields()[i+1].getName(), list.get(i));
//                System.out.println(FileDto.class.getDeclaredFields()[i+1].getName() + "/" +  list.get(i));
            }
        }};

    }
}