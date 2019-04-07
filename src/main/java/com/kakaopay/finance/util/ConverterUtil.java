package com.kakaopay.finance.util;

import com.kakaopay.finance.model.file.FileDto;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConverterUtil {
    public static List<String> insertCsv(List<String> param) {
        try {

            ArrayList<String> result = new ArrayList<>();
            param.forEach(s ->
            {
                /*
                 *   금액 안 쉼표 제거
                 *   ex) "1,234" -> "1234"
                 */
                boolean hasDoubleQuotation = false;
                StringBuffer resultLine = new StringBuffer();
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == '\"' && !hasDoubleQuotation) {
                        hasDoubleQuotation = true;
                        resultLine.append(s.charAt(i));
                        continue;
                    }

                    if (hasDoubleQuotation) {
                        if (s.charAt(i) == '\"') {
                            hasDoubleQuotation = false;
                        }
                        if (s.charAt(i) == ',') {
                            continue;
                        }
                    }
                    resultLine.append(s.charAt(i));
                }
                /*
                 *   쌍 따옴표 제거
                 *   ex) "1234" -> 1234
                 */
                resultLine = new StringBuffer(resultLine.toString().replaceAll(Pattern.quote("\""), ""));

                /*
                 *   쉼표로 split 하여 배열에 입력
                 *   ex) 1234,5678 -> [1234, 5678]
                 */
                String[] arr = resultLine.toString().split(Pattern.quote(","));
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = arr[i].replaceAll("\"", "");

                    /*
                     *   TODO Data save in DB(각 컬럼 명 정의하여 데이터 입력)
                     */

                }

                String row = String.join(",", arr);
                /*
                 *   Test output
                 */
                result.add(row);
                System.out.println("row >>>" + row);

            });
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> convertToBasicFormat(List<String> param) {
        return new ArrayList<String>(){{
            param.forEach(target -> {
                Matcher matcher = Pattern.compile("\"\\d,\\d\\d\\d|\"").matcher(target);
                String group;
                while(matcher.find()) {
                    group = matcher.group();
                    target = target.replaceAll(group, group.replaceAll(",", ""));
                }
                add(target.replaceAll("\"", ""));
            });
        }};
    }

    public static Map convertFromStringToMapFormat(String param) {
        return new HashMap(){{
            List<String> list = Arrays.asList(param.split(","));

            for(int i=0; i<list.size(); i++){
                put(FileDto.class.getDeclaredFields()[i].getName(), list.get(i));
            }
        }};

    }


    public static void main(String[] args) {
        System.out.println(convertFromStringToMapFormat("2011,7,5258,2296,1973,1213,2,573,1778,233,1062").toString());

    }
}