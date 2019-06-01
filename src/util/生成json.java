package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class 生成json {
	
	public static String readerName = "oraclereader"; 
	public static String readerURL = "jdbc:oracle:thin:@192.168.1.101:1521:orcl";
	public static String readerUserName = "username";
	public static String readerPassWord = "password";
	
	public static String writerName = "oraclewriter";
	public static String writerURL = "jdbc:oracle:thin:@192.168.1.100:1521:orcl";
	public static String writerUserName = "username";
	public static String writerPassWord = "password";

	public static final int isColumn = 1;
	public static final int isSelect = 2;

	public static void main(String[] args) {
		String filePath = "C:\\数据转换语句-DATAX.sql"; //读取的文件夹路径  
        try {  
        	BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));//数据流读取文件  
            
                String temp = null;	//用于存储每行字符串  
                StringBuilder select = new StringBuilder();	
                StringBuilder column = new StringBuilder();
                int flag = isColumn;
                //读取每行字符串
                while ((temp = bufReader.readLine()) != null) { 
                	temp = temp.trim();
                	if(temp.startsWith("insert")){
            			flag = isColumn;
            		}else if(temp.startsWith("select")){
            			flag = isSelect;
            		}
                	if(flag == isSelect){
                		select.append(" "+temp);
                	}else if(flag == isColumn){
                		column.append(temp);
                	}
                	if (temp.endsWith(";")){
                		String fileName = column.substring(column.indexOf("into ") + 5, column.indexOf("("));
                		fileName = fileName.trim();
                		String[] columns = column.substring(column.indexOf("(") + 1, column.lastIndexOf(")")).split(",");
                		String sele = select.toString();
                		//去掉select语句后的';'
                		sele = sele.substring(0,sele.length() - 1);
                		
                		//输出的文件夹路径
                    	FileWriter fw = new FileWriter("C:\\Users\\n-340\\Desktop\\json\\"+fileName+".json", true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        // 往json文件上添加字符串
                    	bw.append(gen(sele, columns , fileName)); 
        	            bw.close();
        	            fw.close();

        	            //清空两个StringBuilder
                		select.delete( 0, select.length() );
                		column.delete( 0, column.length() );
                		flag = isColumn;
                	}
                }  
                bufReader.close();
              
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
	/**
	 * 
	 * @param select	select语句
	 * @param columns	插入的字段
	 * @param tableName	插入的表名
	 * @return		json文件的内容
	 */
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
				+ "                    \"name\": \""+readerName+"\",\n"
				+ "                    \"parameter\": {\n"
				+ "                        \"username\": \""+readerUserName+"\",\n"
				+ "                        \"password\": \""+readerPassWord+"\",\n"
				+ "                        \"connection\": [\n"
				+ "                            {\n"
				+ "                                \"querySql\": [\n"
				+ "									\""+select + "\"\n"
				+ "                                   ],\n"
				+ "                                \"jdbcUrl\": [\n"
				+ "                                    \""+readerURL+"\"\n"
				+ "                                ]\n"
				+ "                            }\n"
				+ "                        ]\n"
				+ "                    }\n"
				+ "                },\n"
				+ "			\"writer\": {\n"
				+ "				\"name\": \""+writerName+"\",\n"
				+ "				\"parameter\": {\n"
				+ "					\"column\": [");
		sb.append("\""+columns[0]+"\"");
		for (int i = 1 ; i < columns.length;i++) {
			sb.append(",\""+columns[i]+"\"");
		}
		sb.append("],\n"
				+ "					\"connection\": [{\n"
				+ "						\"jdbcUrl\": \""+writerURL+"\",\n"
				+ "						\"table\": [\""+tableName+"\"]\n"
				+ "					}],\n"
				+ "					\"password\": \""+writerPassWord+"\",\n"
				+ "					\"username\": \""+writerUserName+"\"\n"
				+ "				}\n"
				+ "			}\n"
				+ "		}]\n"
				+ "	}\n"
				+ "}");
		return sb.toString();
	}

}