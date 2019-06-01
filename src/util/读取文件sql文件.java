package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class 读取文件sql文件 {
	
	static int n = 1;

	public static void main(String[] args) {
		String filePath = "C:\\Users\\n-340\\Desktop\\7.sql"; //��ȡ���ļ���·��  
        try {  
        	//������ļ���·��
        	FileWriter fw = new FileWriter("C:\\Users\\n-340\\Desktop\\113.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            try (BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8")))//��������ȡ�ļ�  
            {  
                String temp = null;  
                StringBuilder select = new StringBuilder();
                StringBuilder column = new StringBuilder();
                int flag = 1;
                while ((temp = bufReader.readLine()) != null) { 
                	temp = temp.trim();
                	if(temp.startsWith("insert")){
            			flag = 1;
            		}else if(temp.startsWith("select")){
            			flag = 2;
            		}
                	if(flag == 2){
                		select.append(temp);
                	}else if(flag == 1){
                		column.append(temp);
                	}
                	if (temp.endsWith(";")){
//                		System.out.println("column:"+column.toString());
//                		System.out.println("select:"+select.toString());
                		String fileName = column.substring(column.indexOf("into ") + 5, column.indexOf("("));
                		System.out.println(n+":fileName:"+ fileName);
                		String[] columns = column.substring(column.indexOf("(") + 1, column.lastIndexOf(")")).split(",");
                		System.out.println(n+":columns:"+ columns);
                		select.delete( 0, select.length() );
                		column.delete( 0, column.length() );
                		n++;
                		
                		
                		
                		flag = 1;
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

}
