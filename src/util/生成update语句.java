package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class 生成update语句 {



	public static void main(String[] args) {
		 String filePath = "C:\\Users\\n-340\\Desktop\\sql\\3.txt"; //读取的文件夹路径  
	        try {  
	        	//输出的文件夹路径
	        	FileWriter fw = new FileWriter("C:\\Users\\n-340\\Desktop\\update.sql", true);
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
	// UPDATE STUDENTS
//	SET    
//    EMPLOYEE_ID_FK       = '3789'  ,
//    GENDER_CODE          = 'F'  ,
//    MARITAL_STATUS_CODE  = 'M' 
	public static String gen(String[] daima){
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE "+daima[0]+"\n"
				+ "SET \n" + daima[2] + "='6'");
		for(int i = 3 ; i < 5 ; i++){
			if(!"".equals(daima[i]) && daima[i].endsWith("SJ")){
				sb.append("," + daima[i] + "= to_date('30-12-1899 01:00:00', 'dd-mm-yyyy hh24:mi:ss')");
			}else if (!"".equals(daima[i])){
				sb.append("," + daima[i] + " = '6'");
			}
		}
		sb.append("\nwhere "+ daima[1] +" = '5';\n\n");
		
		
		return sb.toString();
	}

}
