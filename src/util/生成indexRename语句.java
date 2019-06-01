package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class 生成indexRename语句 {



	public static void main(String[] args) {
		 String filePath = "C:\\Users\\n-340\\Desktop\\sql\\3.txt"; //读取的文件夹路径  
	        try {  
	        	//输出的文件夹路径
	        	FileWriter fw = new FileWriter("C:\\Users\\n-340\\Desktop\\sql\\indexRename.sql", true);
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
	//ALTER INDEX  T_SQQZGZXXB_QZKRKSJ RENAME TO  T_SQQZGZXXB_STRKSJ;
	public static String gen(String[] daima){
		StringBuilder sb = new StringBuilder();
		sb.append("ALTER INDEX "+daima[0]+"_QZKRKSJ RENAME TO  "+daima[0]+"_STRKSJ;\n");
		
		return sb.toString();
	}

}
