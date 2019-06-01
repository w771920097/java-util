package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class 批量执行datax{
	//12������
	private static String[] codeStr ={".1.1.8.",".1.12.8.",".1.6.1.",".1.2.7.",".1.3.5.",".1.10.4.",".1.9.11.",".1.14.3.",".1.13.4.",".1.7.1.",".1.4.1.",".1.8.21."};
	public static Long oneDay = (long)24 * 60 * 60 * 1000;//һ��
	public static Long initDelay = (long)0 * 1000;//0����
	public static String jsonPath = "C:/Users/n-340/Desktop/json";
	public static String dataxPath = "E:/workspace/datax/bin/datax.py";
	public static void main(String[] args) {
		//��ʱ����
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(new Runnable(){

			@Override
			public void run() {
				exeDatax();
			}
			
		}, initDelay, oneDay, TimeUnit.MILLISECONDS);
        
    }
	//ִ��datax
	public static void exeDatax(){
		try {
            System.out.println("start");
            String[] str = getFileName(jsonPath);
            for(String code : codeStr){
	            for (String name : str) {
	    			String windowcmd = "python "+dataxPath+"  �Cp\"-Dorgcode="+code+"\" "+jsonPath+"/"+name;
	                System.out.println(windowcmd);
	                Process pr = Runtime.getRuntime().exec(windowcmd);
	                BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
	                String line = null;
	                while ((line = in.readLine()) != null) {
	                	//if (line.contains("ERROR") || line.contains("\"exception\":\"ORA-")||line.contains("������¼����")||line.contains("��дʧ������")) {     
                        System.out.println(line);                                                                                            
                        //} 
	                }
	                in.close();
	                pr.waitFor();
	    		}
            }
            System.out.println("end");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	//��ȡ�ļ��������� json �ļ���
	public static String[] getFileName(String path) {
        File file = new File(path);
        String[] fileName = file.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                        if (name.endsWith(".json")) {
                                return true;
                        }
                        return false;
                }
        });
        return fileName;
}

}
