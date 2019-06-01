package util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Mysql连接 {

  public static void main(String[] args) {
    String url = "jdbc:mysql://127.0.0.1:3306/tqzooker?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=utf8";
    String userName = "root";
    String password = "soa123456";
    
    //"jdbc:mysql://mysql.abc.com/xyz?" +
//    "useUnicode=true&characterEncoding=utf8",
    Connection conn = null;
    try {
    String name="";
            String age="";
    int id=1;//范例里就给个1，就会返回id为1的user的各种信息
    // 从数据库读name
    try {
        Class.forName("java.sql.Driver");
        conn = DriverManager.getConnection(url,userName,password);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("启动数据库失败");
        }
        Statement stat = conn.createStatement();
//        String tableName = "zk_alarm_info";
        ResultSet rst = stat
            .executeQuery("select column_name as name,column_comment,data_type from information_schema.columns where table_name='zk_alarm_info'");
        if (rst.next()) {
          System.out.println("name>>" + rst.findColumn("name"));
                            name = rst.getString(1);
                            System.out.println("name>" + name);
            age = rst.getString(2);//注意和上面select语句的对应关系，如果有更多的数据需要返回就（3）、（4）……
            System.out.println("age>"+age);
            System.out.println(rst.getString(3));
          }
    } catch (Exception e1) {
        e1.printStackTrace();
        System.out.println("调用失败");
    } finally {
//        DBUtil.close(conn);
    }
}

}
