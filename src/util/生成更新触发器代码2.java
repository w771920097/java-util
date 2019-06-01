package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 生成更新触发器代码2 {
	public static List<Prefecture> list = new ArrayList<Prefecture>();

	public static void main(String[] args) { 
		init();
		String filePath = "C:\\Users\\n-340\\Desktop\\sql\\3.txt"; //读取的文件夹路径  
        try {  
        	//输出的文件夹路径
        	FileWriter fw = new FileWriter("C:\\Users\\n-340\\Desktop\\sql\\更新触发器2.sql", true);
            BufferedWriter bw = new BufferedWriter(fw);
            try (BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8")))//数据流读取文件  
            {  
                String temp = null;  
                while ((temp = bufReader.readLine()) != null) { 
                	String[] daima = temp.split(",");
                	if(daima.length>1){
	                    System.out.print(1);  
	    	            bw.append(generate(daima));// 往已有的文件上添加字符串
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
	public static String generate(String[] daima){
		StringBuilder sb = new StringBuilder();
		sb.append("create or replace trigger TRIG_"+daima[0]+"_UPD\n"
				+ "   after update on "+daima[0]+"\n"
						+ "   for each row\n"
						+ "begin\n"
						+ "declare\n  v_time varchar2(20);\n");
		for (int j = 0; j < list.size(); j++) {
			if(j == 0){
				sb.append("	IF :NEW.sjly_dsdm  like '"+list.get(j).getCode()+"%' then\n");
			}else{
				sb.append("	ELSIF :NEW.sjly_dsdm  like '"+list.get(j).getCode()+"%' then\n");
			}
			sb.append("		select to_char(sysdate,'yyyymmddhh24miss') into v_time from dual"+list.get(j).getDblink()+";\n"
					+ "		   update "+daima[0]+list.get(j).getDblink()+" set\n		");
			for(int i = 2 ; i < daima.length ; i++){
				if(!"QZKRKSJ".equals(daima[i])&& !"QZKXGSJ".equals(daima[i]) && !"".equals(daima[i])){
					sb.append( daima[i] + "=:new."+ daima[i] + ",");
				}
			}
			sb.append("\n		STXGSJ=v_time\n"
					+ "    where " + daima[1] + "=:new."+ daima[1] + ";\n");
		}

		sb.append("	END IF; \n"
				+ "END TRIG_"+daima[0]+"_UPD;\n"
				+ "/\n"
				+ "alter trigger TRIG_"+daima[0]+"_UPD enable;\n\n");
		return sb.toString();
	}
	public static void init(){
		
		Prefecture pf = new Prefecture("江宁区","@sgajnq","3201");
		list.add(pf);
		pf = new Prefecture("常州市","@sgaczs","3204");
		list.add(pf);
		pf = new Prefecture("镇江市","@sgazjs","3211");
		list.add(pf);
		pf = new Prefecture("扬州市","@sgayzs","3210");
		list.add(pf);
		pf = new Prefecture("泰州市","@sgatzs","3212");
		list.add(pf);
		pf = new Prefecture("盐城市","@sgaycs","3209");
		list.add(pf);
		pf = new Prefecture("宿迁市","@sgasqs","3213");
		list.add(pf);
		pf = new Prefecture("徐州市","@sgaxzs","3203");
		list.add(pf);
		pf = new Prefecture("连云港市","@sgalygs","3207");
		list.add(pf);
		pf = new Prefecture("无锡市","@sgawxs","3202");
		list.add(pf);
		pf = new Prefecture("南通市","@sgants","3206");
		list.add(pf);
		pf = new Prefecture("淮安市","@sgahas","3208");
		list.add(pf);
		 
	}

}