package util;

public class StringTest {

	public static void main(String[] args) {
		//db0:keys=142305,expires=0,avg_ttl=0
		String str = "db0:keys=142305,expires=0,avg_ttl=0";
		
		String str1 = str.substring(0,str.indexOf(":"));
		
		
		System.out.println("str1>>"+str1);
		
		String str2 = str.substring(str.indexOf("="),str.indexOf(","));
		
		System.out.println("str2>>"+str2);

	}

}
