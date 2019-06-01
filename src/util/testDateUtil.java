package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class testDateUtil {

	public static void main(String args[]){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		
		Date date =null;
		try {
			date = format.parse("2018-7-22");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		//n为推迟的周数，1本周，-1向前推迟一周，2下周，依次类推
		int n = -1;
		String monday;
		cal.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
		monday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		System.out.println(monday);
		cal.add(Calendar.DATE, n*7);
		//想周几，这里就传几Calendar.MONDAY（TUESDAY...）
		cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
		monday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		System.out.println(monday);
		
		
		String firstDay;
		String lastDay;
		
        
        //获取前月的第一天
        Calendar   cal_1=Calendar.getInstance();//获取当前日期 
        cal_1.add(Calendar.MONTH, -1);
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        
        firstDay = format.format(cal_1.getTime());
        System.out.println("-----1------firstDay:"+firstDay);
        //获取前月的最后一天
        Calendar cale = Calendar.getInstance();   
        cale.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天 
        lastDay = format.format(cale.getTime());
        System.out.println("-----2------lastDay:"+lastDay);
          
		}
	
	
}
