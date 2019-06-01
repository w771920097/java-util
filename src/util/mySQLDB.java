package util;

public class mySQLDB {

	public static void main(String[] args) {
		//(null,'2018-07-09 09:49:52',NULL,'host',10,3,'72',49,1),
//		(null,'2018-07-09 09:49:52',NULL,'host',10,2,'空',115,1),
//		(null,'2018-07-09 09:49:52',NULL,'host',10,4,'191',81,1),
//		(null,'2018-07-09 09:49:52',NULL,'java',22,5,'13227',63,1),
		String sql = "(null,'2018-07-%d','2018-07-%d 09:49:52',NULL,1,'cpu','CPU1分钟内连续1次平均值','72',49,1),%n"
				+ "(null,'2018-07-%d','2018-07-%d 09:49:52',NULL,1,'内存','内存使用率10分钟内连续1次平均值','空',115,1),%n"
				+ "(null,'2018-07-%d','2018-07-%d 09:49:52',NULL,1,'进程','进程个数1分钟内连续1次平均值','191',81,1),%n"
				+ "(null,'2018-07-%d','2018-07-%d 09:49:52',NULL,2,'加载class','加载class数量1分钟内连续1次平均值','191',81,1),%n";
		//

		for (int i = 1; i < 16; i++) {
			System.out.printf(sql,i,i+1,i,i+1,i,i+1,i,i+1);
		}
		String name = "盖伦";
		int kill = 8;
		String title = "超神";
		
		String sentenceFormat = "%s 在进行了连续 %d 次击杀后， 获得了 %s 的称号%n";
		//格式化输出，%s表示字符串，%d表示数字，%n表示换号
		System.out.printf(sentenceFormat, name, kill, title);
		System.out.format(sentenceFormat, name, kill, title);
		//format和printf能够达到一模一样的效果
		


	}

}
