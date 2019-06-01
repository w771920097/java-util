package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class qianzhijiT_SQMYJQXXB {

        private static String[] codeStr = {  ".1.9.11.", ".1.13.4."};
        public static Long oneDay = (long) 240 * 60 * 60 * 1000;// per 10 days
        public static Long initDelay = 5000L;// (long) 60 * 1000;
        public static String jsonPath = "/home/admin/datax/json/qianzhijiNew/his/";
        public static String dataxPath = "/home/admin/datax/bin/datax.py";

        public static void main(String[] args) {
                ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
                service.scheduleAtFixedRate(new Runnable() {

                        @Override
                        public void run() {
                                exeDatax();
                        }

                }, initDelay, oneDay, TimeUnit.MILLISECONDS);

        }

        public static void exeDatax() {
                String localUrl = "jdbc:oracle:thin:@192.168.197.71:1521:jsgridhw";
                String localUsername = "jsgrid";
                String localPassword = "jsgrid_2014";

                //String remoteUrl="jdbc:oracle:thin:@10.172.0.13:1521:orcl";
                //String remoteUsername="zzdj";
                //String remotePassword="zzdj2018$%";
                String remoteUrl;
                String remoteUsername;
                String remotePassword;
                try {
                        String seq = UUID.randomUUID().toString();
                        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
                                        + "---------" + seq
                                        + "-----------------------------start-----------------------------");
                        String[] str = getFileName(jsonPath);
						
						for(String code : codeStr){
                                        if(code.equals(".1.9.11.")){//yancheng  dafeng                                                                                              
                                                remoteUrl = "jdbc:oracle:thin:@10.172.0.14:1521:orcl";
                                                remoteUsername = "shzl_yc_tq";
                                                remotePassword = "shzl_yc20180306";
                                        }else {//suqian  siyang                                                                                          
                                                remoteUrl = "jdbc:oracle:thin:@10.172.0.15:1521:orcl.microsoft.com";
                                                remoteUsername = "shzl_sq_tq";
                                                remotePassword = "shzl_sq20180305";
                                        }

                                for (String name : str) {
                                        String windowcmd = "python " + dataxPath + "  -p\"-Dorgcode=" + code
                                                        + "  -DlocalUrl=" + localUrl + "  -DlocalUsername=" + localUsername
                                                        + "  -DlocalPassword=" + localPassword + "  -DremoteUrl=" + remoteUrl
                                                        + "  -DremoteUsername=" + remoteUsername + "  -DremotePassword="
                                                        + remotePassword + "\" " + jsonPath + name;
                                        System.out.println(windowcmd);
                                        String[] cmd = { "/bin/sh", "-c",windowcmd};
                                        Process pr = Runtime.getRuntime().exec(cmd);
                                        BufferedReader in = new BufferedReader(
                                                        new InputStreamReader(pr.getInputStream()));
                                        String line = null;
                                        while ((line = in.readLine()) != null) {
                                                //if (line.contains("ERROR") || line.contains("\"exception\":\"ORA-")||line.contains("读出记录总数")||line.contains("读写失败总数")) {
                                                        System.out.println(line);
                                                //}
                                        }
                                        in.close();
                                        pr.waitFor();
                                }
						}

                        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
                                        + "---------" + seq
                                        + "-----------------------------end-----------------------------");
                } catch (Exception e) {
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                }
        }

        public static String[] getFileName(String path) {
                File file = new File(path);
                String[] fileName = file.list(new FilenameFilter() {

                        @Override
                        public boolean accept(File dir, String name) {
                                if (name.equals("T_SQMYJQXXB.json")) {
                                        return true;
                                }
                                return false;
                        }
                });
                return fileName;
        }

}