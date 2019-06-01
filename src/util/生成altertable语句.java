package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class 生成altertable语句 {



	public static void main(String[] args) {
		 String filePath = "C:\\Users\\n-340\\Desktop\\sql\\3.txt"; //读取的文件夹路径  
	        try {  
	        	//输出的文件夹路径
	        	FileWriter fw = new FileWriter("C:\\Users\\n-340\\Desktop\\sql\\alterAdd.sql", true);
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
	// 字段的重命名   	alter table 表名 rename  column  列名 to 新列名
	// 增加字段    		alter table 表名 add (字段名 字段类型 默认值 是否为空);
	/*comment on column T_SQRWHDXXB.strksj
	  is '省厅入库时间';
	  comment on column T_SQRWHDXXB.stxgsj
	    is '省厅修改时间';*/
	public static String gen(String[] daima){
		StringBuilder sb = new StringBuilder();
		sb.append("alter table "+daima[0]+" add  (qzkrksj      VARCHAR2(14) default to_char(sysdate,'yyyymmddhh24miss')) ;	\n");
		sb.append("alter table "+daima[0]+" add  (qzkxgsj      VARCHAR2(14) default to_char(sysdate,'yyyymmddhh24miss')) ;  \n");
		sb.append("comment on column "+daima[0]+".qzkrksj \n"
				+ "is '前置库入库时间';\n"
				+ "comment on column "+daima[0]+".qzkxgsj\n"
				+ "is '前置库修改时间';\n");
		sb.append("alter table "+daima[0]+" add  (garksj      VARCHAR2(14) default to_char(sysdate,'yyyymmddhh24miss')) ;	\n");
		sb.append("alter table "+daima[0]+" add  (gaxgsj      VARCHAR2(14) default to_char(sysdate,'yyyymmddhh24miss')) ;  \n");
		sb.append("comment on column "+daima[0]+".garksj \n"
				+ "is '公安入库时间';\n"
				+ "comment on column "+daima[0]+".gaxgsj\n"
				+ "is '公安修改时间';\n\n");
		
		
		return sb.toString();
	}

}
