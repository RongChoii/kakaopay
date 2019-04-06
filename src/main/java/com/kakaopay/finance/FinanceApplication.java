package com.kakaopay.finance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinanceApplication {

	public static void main(String[] args) {

		SpringApplication.run(FinanceApplication.class, args);
//
//		//반환용 리스트
//		List<List<String>> ret = new ArrayList<List<String>>();
//		BufferedReader br = null;
//
//		try{
//		//	br = Files.newBufferedReader(Paths.get("C:\\Users\\rong\\Desktop\\test.csv"));
//		//	br = Files.newBufferedReader(Paths.get("C:\\Users\\rong\\Desktop\\test2.csv"));
//			br = new BufferedReader(new FileReader(new File("C:\\Users\\rong\\Desktop\\test2.csv")));
//		  Charset.forName("UTF-8");
//		  String line = "";
//
//		  while((line = br.readLine()) != null){
//			  //CSV 1행을 저장하는 리스트
//			  List<String> tmpList = new ArrayList<String>();
//			  String array[] = line.split(",");
//			  // Arrays.asList : 배열에서 리스트 반환
//			  tmpList = Arrays.asList(array);
//			  System.out.println(tmpList);
//			  ret.add(tmpList);
//		  }
//		}catch(FileNotFoundException e){
//		  e.printStackTrace();
//		}catch(IOException e){
//		  e.printStackTrace();
//		}finally{
//		  try{
//			  if(br != null){
//				  br.close();
//			  }
//		  }catch(IOException e){
//			  e.printStackTrace();
//		  }
//		}
	}

}
