package com.kakaopay.finance.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileReaderUtil {

    public static List<String> getCsvfileInfo(String path){
        List<String> result = new ArrayList<>();
        try{
//            BufferedReader br = new BufferedReader(new FileReader(new File(path)));
////            Charset.forName("UTF-8");
//            Charset.forName("EUC-KR");
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "EUC-KR"));

            String line ;

            while((line = br.readLine()) != null)
                result.add(line);

            if(br != null){
                br.close();
            }
            return result;
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}


