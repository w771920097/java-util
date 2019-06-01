package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class 读取文件 {
	
	static int n = 1;

	public static void main(String[] args) {
//	  dlg.txt
		String filePath = "C:\\Users\\n-340\\Desktop\\computerRoomInformationDlg.jsp"; //��ȡ���ļ���·��  
        try {  
        	//������ļ���·��
        	FileWriter fw = new FileWriter("C:\\Users\\n-340\\Desktop\\112.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            try (BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8")))//��������ȡ�ļ�  
            {
                String temp = null;  
                while ((temp = bufReader.readLine()) != null) {
                	temp = temp.replace("\"", "\\\"").replace("\t", "\\t").replace("    ", "\\t");
                	temp = temp + "\\n";
                	temp = "\t\tsb.append(\"" + temp + "\");";
                	System.out.println(temp);
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
