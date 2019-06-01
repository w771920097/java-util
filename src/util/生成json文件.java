package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class 生成json文件 {

	public static void main(String[] args) {
		String filePath = "C:\\Users\\n-340\\Desktop\\全要素网格通数据转换语句-DATAX.sql"; //读取的文件夹路径  
        try {  
        	//输出的文件夹路径
            try (BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8")))//数据流读取文件  
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
                		select.append(" "+temp);
                	}else if(flag == 1){
                		column.append(temp);
                	}
                	if (temp.endsWith(";")){
                		String fileName = column.substring(column.indexOf("into ") + 5, column.indexOf("("));
                		fileName = fileName.trim();
                		String[] columns = column.substring(column.indexOf("(") + 1, column.lastIndexOf(")")).split(",");
                		if(select.indexOf("like ") + 5 < select.lastIndexOf(" and")){
                    		select.replace(select.indexOf("like ") + 5, select.lastIndexOf(" and"), "'.1.8.21.%'");
                		}else {
                			System.out.println("fileName:"+fileName+"'.1.8.21.%'设置错误");
                		}
                		String sele = select.toString();
                		sele = sele.substring(0,sele.length() - 1);
                		try {
                        	//输出的文件夹路径
                        	FileWriter fw = new FileWriter("C:\\Users\\n-340\\Desktop\\json\\"+fileName+".json", true);
                            BufferedWriter bw = new BufferedWriter(fw);
                            	bw.append(gen(sele, columns , fileName));// 往已有的文件上添加字符串 
                	            bw.close();
                	            fw.close();
                        } catch (Exception e) {  
                            e.printStackTrace();  
                        }
                		

                		select.delete( 0, select.length() );
                		column.delete( 0, column.length() );
                		flag = 1;
                	}
                }  
                bufReader.close();
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
	public static String gen (String select,String[] columns,String tableName){
		StringBuilder sb = new StringBuilder();
		sb.append("{\n"
				+ "    \"job\": {\n"
				+ "        \"setting\": {\n"
				+ "            \"speed\": {\n"
				+ "                \"channel\":2\n"
				+ "            }\n"
				+ "		},\n"
				+ "        \"content\": [\n"
				+ "            {\n"
				+ "                \"reader\": {\n"
				+ "                    \"name\": \"oraclereader\",\n"
				+ "                    \"parameter\": {\n"
				+ "                        \"username\": \"jsgrid\",\n"
				+ "                        \"password\": \"jsgrid\",\n"
				+ "                        \"connection\": [\n"
				+ "                            {\n"
				+ "                                \"querySql\": [\n"
				+ "									\""+select + "\"\n"
				+ "                                   ],\n"
				+ "                                \"jdbcUrl\": [\n"
				+ "                                    \"jdbc:oracle:thin:@192.168.1.222:1521:tianque\"\n"
				+ "                                ]\n"
				+ "                            }\n"
				+ "                        ]\n"
				+ "                    }\n"
				+ "                },\n"
				+ "			\"writer\": {\n"
				+ "				\"name\": \"oraclewriter\",\n"
				+ "				\"parameter\": {\n"
				+ "					\"column\": [");
		sb.append("\""+columns[0]+"\"");
		for (int i = 1 ; i < columns.length;i++) {
			sb.append(",\""+columns[i]+"\"");
		}
		sb.append("],\n"
				+ "					\"connection\": [{\n"
				+ "						\"jdbcUrl\": \"jdbc:oracle:thin:@tianque.oicp.net:51521:tianque\",\n"
				+ "						\"table\": [\""+tableName+"\"]\n"
				+ "					}],\n"
				+ "					\"password\": \"jsgrid\",\n"
				+ "					\"username\": \"jsgrid\"\n"
				+ "				}\n"
				+ "			}\n"
				+ "		}]\n"
				+ "	}\n"
				+ "}");
		return sb.toString();
	}

}
