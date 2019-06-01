package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class 生成insert语句 {



	public static void main(String[] args) {
		 String filePath = "C:\\Users\\n-340\\Desktop\\sql\\3.txt"; //读取的文件夹路径  
	        try {  
	        	//输出的文件夹路径
	        	FileWriter fw = new FileWriter("C:\\Users\\n-340\\Desktop\\sql\\insert3.sql", true);
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
	// insert into tablename () values ();    to_date('30-12-1899 01:00:00', 'dd-mm-yyyy hh24:mi:ss')
	public static String gen(String[] daima){
		StringBuilder sb = new StringBuilder();
		sb.append("insert into "+daima[0]+"\n"
						+ "  ( " + daima[1]);
		for(int i = 2 ; i < daima.length ; i++){
			if(!"".equals(daima[i])){
				sb.append(", "+ daima[i]);
			}
		}
		sb.append(")\n"
				+ "    values\n( '5'");
		for(int i = 2 ; i < daima.length ; i++){

			if(!"".equals(daima[i]) && (daima[i].endsWith("SJ") || daima[i].endsWith("RQ"))){
				sb.append(",to_date('30-12-1899 01:00:00', 'dd-mm-yyyy hh24:mi:ss')");
			}else if(!"".equals(daima[i]) && (daima[i].endsWith("SJLY_DSDM")) ){
				sb.append(",'12345'");
			}else if (!"".equals(daima[i])){
				sb.append(",'5'");
			}
		}
		sb.append(");\n\n");
		
		
		return sb.toString();
	}

}
