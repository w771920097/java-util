package util;

import java.util.Scanner;

public class 生成notnull {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while(true){
			System.out.println("ÊäÈë:");
			String daima = scan.next();
			System.out.println("Êä³ö£º");
			System.out.println(gen(daima));
			
		}
	}
	public static String gen(String daima){
		StringBuilder sb = new StringBuilder();
		sb.append("nvl("+daima+",'null')");
		
		return sb.toString();
	}

}
