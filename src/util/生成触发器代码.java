package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class 生成触发器代码 {

	public static void main(String[] args) {
		//��ȡ���ļ�·��
		 String filePath = "C:\\Users\\n-340\\Desktop\\����Ϣ.txt";   
	        try {  
	        	//������ļ�·��
	        	FileWriter fw = new FileWriter("C:\\Users\\n-340\\Desktop\\����������.sql", true);
	            BufferedWriter bw = new BufferedWriter(fw);
	            try (BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8")))//��������ȡ�ļ�  
	            {  
	                String temp = null;  
	                while ((temp = bufReader.readLine()) != null) { 
	                	String[] daima = temp.split(",");
	                	if(daima.length>1){
		                    System.out.print(1);  
		    	            bw.append(generate(daima));// �����е��ļ�������ַ���
		                    
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
	/**
	 * ���ɴ���һ����Ĵ�������sql
	 * @param daima  �������ֶ���
	 * @return
	 */
	public static String generate(String[] daima){
		StringBuilder sb = new StringBuilder();
		sb.append("create or replace trigger tri_"+daima[0]+"_add\n"
						+ "  after insert on " + daima[0] + "\n"
						+ "  for each row\n"
						+ "begin\n"
						+ "  insert into " + daima[0] + "@wer\n"
								+ "(" + daima[1]);
		for(int i = 2 ; i < daima.length ; i++){
			if(!"QZKRKSJ".equals(daima[i])&& !"QZKXGSJ".equals(daima[i]) && !"".equals(daima[i])){
				sb.append(", "+ daima[i]);
			}
		}
		sb.append(")\n"
				+ "    values\n(:new." + daima[1]);
		for(int i = 2 ; i < daima.length ; i++){

			if(!"QZKRKSJ".equals(daima[i])&& !"QZKXGSJ".equals(daima[i]) && !"".equals(daima[i])){

				sb.append(",:new."+ daima[i]);
			}
		}
		sb.append(");\nend tri_" + daima[0] + "_add;\n"
								+ "/\n"
								+ "alter trigger tri_" + daima[0] + "_add enable;\n");
		
		sb.append("create or replace trigger tri_"+daima[0]+"_upd\n"
				+ "  after update on " + daima[0] + "\n"
				+ "  for each row\n"
				+ "declare\n  v_time varchar2(20);\n"
				+ "begin\nselect to_char(sysdate,'yyyymmddhh24miss') into v_time from dual@wer;\n"
				+ "   update " + daima[0] + "@wer set\n");
		for(int i = 2 ; i < daima.length ; i++){
			if(!"QZKRKSJ".equals(daima[i])&& !"QZKXGSJ".equals(daima[i]) && !"".equals(daima[i])){
				sb.append( daima[i] + "=:new."+ daima[i] + ",");
			}
		}
		sb.append("\nSTXGSJ=v_time\n"
				+ "    where " + daima[1] + "=:new."+ daima[1] + ";\n"
						+ "end tri_" + daima[0] + "_upd;\n"
								+ "/\n"
								+ "alter trigger tri_" + daima[0] + "_upd enable;\n\n");
		
		return sb.toString();
	}

}
