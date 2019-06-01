package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class dataxx {
	public static Long oneDay = (long)24 * 60 * 60 * 1000;		//姣忎竴澶╂壒閲忔墽琛屼竴娆�
	public static Long initDelay = (long)0 * 1000;				//寤惰繜0鍒嗛挓寮�濮嬫墽琛�
	public static String jsonPath = "C:/Users/abc/Desktop/json";//json鏂囦欢澶瑰湴鍧�
	public static String dataxPath = "E:/datax/bin/datax.py";	//datax鐨刾ython鏂囦欢鍦板潃
	public static void main(String[] args) {
		//瀹氭椂浠诲姟
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(new Runnable(){

			@Override
			public void run() {
				exeDatax();
			}
			
		}, initDelay, oneDay, TimeUnit.MILLISECONDS);
        
    }
	//鎵归噺鎵цdatax
	public static void exeDatax(){
		try {
            System.out.println("------------------start----------------------");
            String[] str = getFileName(jsonPath);
            for (String name : str) {
    			String windowcmd = "python "+dataxPath+" "+jsonPath+"/"+name;
                System.out.println(windowcmd);
                Process pr = Runtime.getRuntime().exec(windowcmd);
                BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                String line = null;
                while ((line = in.readLine()) != null) {
                	System.out.println(line);     
                }
                in.close();
                pr.waitFor();
    		}
            System.out.println("----------------end------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	//鑾峰彇鏂囦欢澶逛笅鎵�鏈� json 鏂囦欢鍚�
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
