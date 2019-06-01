package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class  生成触发器代码2 {
	public static List<Prefecture> list = new ArrayList<Prefecture>();

	public static void main(String[] args) { 
		init();
		String filePath = "C:\\Users\\n-340\\Desktop\\sql\\3.txt"; //��ȡ���ļ���·��  
        try {  
        	//������ļ���·��
        	FileWriter fw = new FileWriter("C:\\Users\\n-340\\Desktop\\sql\\ȫ������2.sql", true);
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
	public static String generate(String[] daima){
		StringBuilder sb = new StringBuilder();
		sb.append("create or replace trigger TRIG_"+daima[0]+"_ADD\n"
				+ "   after insert on "+daima[0]+"\n"
						+ "   for each row\n"
						+ "begin\n");
		for (int j = 0; j < list.size(); j++) {
			if(j == 0){
				sb.append("	if :NEW.sjly_dsdm  like '"+list.get(j).getCode()+"%' then\n");
			}else{
				sb.append("	ELSIF :NEW.sjly_dsdm  like '"+list.get(j).getCode()+"%' then\n");
			}
			sb.append("		insert into "+daima[0]+list.get(j).getDblink()+"\n"
							+ "		(" + daima[1]);
			for(int i = 2 ; i < daima.length ; i++){
				if(!"QZKRKSJ".equals(daima[i])&& !"QZKXGSJ".equals(daima[i]) && !"".equals(daima[i])){
					sb.append(", "+ daima[i]);
				}
			}
			sb.append(")\n"
					+ "    	values\n		(:new." + daima[1]);
			for(int i = 2 ; i < daima.length ; i++){

				if(!"QZKRKSJ".equals(daima[i])&& !"QZKXGSJ".equals(daima[i]) && !"".equals(daima[i])){

					sb.append(",:new."+ daima[i]);
				}
			}
			sb.append(");\n");
		}

		sb.append("	end if; \n"
				+ "end TRIG_"+daima[0]+"_ADD;\n"
				+ "/\n"
				+ "alter trigger TRIG_"+daima[0]+"_ADD enable;\n\n");
		return sb.toString();
	}
	public static void init(){
		
		Prefecture pf = new Prefecture("������","@sgajnq","3201");
		list.add(pf);
		pf = new Prefecture("������","@sgaczs","3204");
		list.add(pf);
		pf = new Prefecture("����","@sgazjs","3211");
		list.add(pf);
		pf = new Prefecture("������","@sgayzs","3210");
		list.add(pf);
		pf = new Prefecture("̩����","@sgatzs","3212");
		list.add(pf);
		pf = new Prefecture("�γ���","@sgaycs","3209");
		list.add(pf);
		pf = new Prefecture("��Ǩ��","@sgasqs","3213");
		list.add(pf);
		pf = new Prefecture("������","@sgaxzs","3203");
		list.add(pf);
		pf = new Prefecture("���Ƹ���","@sgalygs","3207");
		list.add(pf);
		pf = new Prefecture("������","@sgawxs","3202");
		list.add(pf);
		pf = new Prefecture("��ͨ��","@sgants","3206");
		list.add(pf);
		pf = new Prefecture("������","@sgahas","3208");
		list.add(pf);
		 
	}

}
//�ؼ���
class Prefecture{
	String name ;
	String dblink;
	String code;
	public Prefecture(String name,String dblink,String code) {
		this.name = name;
		this.dblink = dblink;
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDblink() {
		return dblink;
	}
	public void setDblink(String dblink) {
		this.dblink = dblink;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
