
package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class excel {
	public static void main(String[] args) {
		String filePath = "C:\\Users\\n-340\\Desktop\\sql\\5.txt"; //读取的文件夹路径  
        try {  
        	//输出的文件夹路径
            try (BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8")))//数据流读取文件  
            {  
                String temp = null;  
                String sh = null;
                while ((temp = bufReader.readLine()) != null) { 
                	String[] daima = temp.split(",");
                	if(daima[0].startsWith("T_")){
                		sh = daima[0];
                	}
                	if(daima.length>1){
	    	            Gexcel(sh, daima);
                	}
                }  
                bufReader.close();
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
	public static void Gexcel(String sh,String[] lists) throws IOException{
		// 第一步，创建一个webbook，对应一个Excel文件
				HSSFWorkbook wb = new HSSFWorkbook();
				// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
				if(lists[0].startsWith("T_")){
					HSSFSheet sheet = wb.createSheet(lists[0]);
					// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
					// 第四步，写入实体数据 实际应用中这些数据从数据库得到，
					for(int i = 1 ; i < lists.length ; i++){
						sheet.createRow((int) i).createCell((short) 0).setCellValue(lists[i]);
					}
				}else{
					for(int i = 0 ; i < lists.length ; i++){
						HSSFSheet sheet = wb.createSheet(sh);
						sheet.createRow((int) i + 1).createCell((short) 5).setCellValue(lists[i + 1]);
					}
				}
				// 第六步，将文件存到指定位置
				String path = "C:\\Users\\n-340\\Desktop\\sql\\data3.xls";
				FileOutputStream fout = null;
				try
				{
					fout = new FileOutputStream(path);
				}	
				catch (Exception e)
				{
					e.printStackTrace();
				}finally{
					fout.close();
					wb.close();
				}
	}
	public String gen (){
		
		return null;
	}
}
