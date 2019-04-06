package com.kakaopay.finance.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class FileReaderUtil {

    public static List<String> getCsvfileInfo(String path){
        List<String> result = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(new File(path)));
            Charset.forName("UTF-8");
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


