package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class 生成indexCreate语句 {



	public static void main(String[] args) {
		 String filePath = "C:\\Users\\n-340\\Desktop\\sql\\3.txt"; //读取的文件夹路径  
	        try {  
	        	//输出的文件夹路径
	        	FileWriter fw = new FileWriter("C:\\Users\\n-340\\Desktop\\sql\\index.sql", true);
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
	/*create index T_SQRWHDXXB_strksj on T_SQRWHDXXB (SUBSTR(strksj,1,8))
	  tablespace SHZL_HADATA
	  pctfree 10
	  initrans 2
	  maxtrans 255
	  storage
	  (
	    initial 64K
	    minextents 1
	    maxextents unlimited
	  );*/
	public static String gen(String[] daima){
		StringBuilder sb = new StringBuilder();
		sb.append("create index "+daima[0]+"_strksj	 on "+daima[0]+" (SUBSTR(strksj,1,8))\n"
				+ "tablespace SHZL_HADATA\n"
				+ "pctfree 10\n"
				+ "initrans 2\n"
				+ " maxtrans 255\n"
				+ "storage\n"
				+ "(\n"
				+ "initial 64K\n"
				+ "minextents 1\n"
				+ "maxextents unlimited\n"
				+ ");\n\n");
		
		return sb.toString();
	}

}
