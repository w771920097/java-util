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

public class qianzhiji210 {

//		private static String[] codeStr = { ".1.1.8.", ".1.12.8.", ".1.6.1.", ".1.2.7.", ".1.3.5.",
//				".1.10.4.", ".1.9.11.", ".1.14.3.", ".1.13.4.", ".1.7.1.", ".1.4.1.", ".1.8.21." };
	private static String[] codeStr = { ".1.2.9."};
	//private static String[] codeStr = { ".1.6.1." };
		public static Long oneDay = (long) 3 * 1000;// per 3 	hours
	//public static Long oneDay = (long) 60 * 1000;// one miniate
	public static Long initDelay = 1000L;// (long) 60 * 1000;
	public static String jsonPath = "C:/Users/n-340/Desktop/test/";
	//public static String jsonPath = "/home/admin/datax/json/qianzhiji/public/";
	public static String dataxPath = "E:/workspace/datax/bin/datax.py";
//	public static String dataxPath = "/home/admin/datax/bin/datax.py";
	public static String dateParam ;
	
	public static void main(String[] args) {
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				long curren = System.currentTimeMillis();
		        curren -= oneDay;//per 3 hours
				dateParam = new SimpleDateFormat("yyyy/MM/dd-hh:mm:ss").format(new Date(curren));
				exeDatax();
			}

		}, initDelay, oneDay, TimeUnit.MILLISECONDS);

	}

	public static void exeDatax() {
				/*String localUrl = "jdbc:oracle:thin:@192.168.197.71:1521:jsgridhw";
				String localUsername = "jsgrid";
				String localPassword = "jsgrid_2014";*/
		String localUrl = "jdbc:oracle:thin:@192.168.1.222:1521:tianque";
		String localUsername = "jsgrid";
		String localPassword = "jsgrid";
		
		/*String remoteUrl="jdbc:oracle:thin:@10.172.0.13:1521:orcl";
		String remoteUsername="zzdj";
		String remotePassword="zzdj2018$%";*/
		String remoteUrl="jdbc:oracle:thin:@tianque.oicp.net:51521:tianque";
		String remoteUsername="jsgrid";
		String remotePassword="jsgrid";
		try {
			String seq = UUID.randomUUID().toString();
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
					+ "---------" + seq
					+ "-----------------------------start-----------------------------");
			String[] str = getFileName(jsonPath);
			for (String code : codeStr) {
				
				for (String name : str) {
					String windowcmd = "python " + dataxPath + "  -p\"-Dorgcode=" + code
							+ "  -DlocalUrl=" + localUrl + "  -DlocalUsername=" + localUsername
							+ "  -DlocalPassword=" + localPassword + "  -DremoteUrl=" + remoteUrl
							+ "  -DremoteUsername=" + remoteUsername + "  -DremotePassword="+ remotePassword
							+ "  -DdateParam=" + dateParam	+ "\" " + jsonPath + name;
					System.out.println(windowcmd);
//					String[] cmd = { "/bin/sh", "-c",windowcmd};
//					Process pr = Runtime.getRuntime().exec(cmd);
					Process pr = Runtime.getRuntime().exec(windowcmd);
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
				if (name.endsWith(".json")) {
					return true;
				}
				return false;
			}
		});
		return fileName;
	}

}
