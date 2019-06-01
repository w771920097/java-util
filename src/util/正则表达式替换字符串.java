package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 正则表达式替换字符串 {
	

	public static void main(String[] args) { 
		String sql = "jdbc:mysql://localhost:3306/taper?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=utf8";
//		sql.replaceFirst("/[a-z]*?", "/deploy?");

		String nam = "/deploy?";
		Pattern pattern = Pattern.compile("/[a-z]*\\?"); //去掉空格符合换行符     
        Matcher matcher = pattern.matcher(sql);    
        System.out.println(matcher.matches());
		
        String result = matcher.replaceAll(nam);    
		
		System.out.println("result>" + result);
	}

}
