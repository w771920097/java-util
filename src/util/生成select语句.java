package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class 生成select语句 {


	 
	public static void main(String[] args) {
		 String filePath = "C:\\Users\\n-340\\Desktop\\sql\\3.txt"; //读取的文件夹路径  
	        try {  
	        	//输出的文件夹路径
	        	FileWriter fw = new FileWriter("C:\\Users\\n-340\\Desktop\\sql\\select.sql", true);
	            BufferedWriter bw = new BufferedWriter(fw);
	            try (BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8")))//数据流读取文件  
	            {  
	                String temp = null;  
	                while ((temp = bufReader.readLine()) != null) { 
	                	String[] daima = temp.split(",");
	                	if(daima.length>1){
		                    System.out.print(1);  
		    	            bw.append(gen(daima));// 往已有的文件上添加字符串
		                    
	                	}
	                }  
	                bufReader.close();
		            bw.close();
		            fw.close();
	            }  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  

	}
	// SELECT * FROM oeb_file WHERE ID = 23;
	public static String gen(String[] daima){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM "+daima[0]+" WHERE " + daima[1] + "='5' ; \n");
		return sb.toString();
	}



}
