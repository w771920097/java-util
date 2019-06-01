package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTest {

//	private static final String URL = "jdbc:mysql://localhost:3306/tqzooker?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=utf8";
	private static final String URL = "jdbc:mysql://localhost:3306/deploy?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=utf8";
	private static final String USER_NAME = "root";
	private static final String PASS_WORD = "soa123456";
//	private static final String OMP_DATABASE_NAME = Constant.OMP_DATABASE_NAME;
	private static final String SQL = "select * from deploy_config_machine";
	
	public static void main(String[] args) {
		System.out.println("ac");
		testResultSet();
	}
	
    public static void testResultSet(){
        //获取id=2的customers数据表的记录，并打印
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            //1.获取Connection
            connection = DriverManager.getConnection(URL, USER_NAME, PASS_WORD);
            //2.获取Statement
            statement = connection.createStatement();
            //3.准备Sql
            //4.执行查询，得到ResultSet
            rs = statement.executeQuery(SQL);
            //5.处理ResultSet
            while(rs.next()){
                //rs.get+数据库中对应的类型+(数据库中对应的列别名)
                int id = rs.getInt("ID");
                String name = rs.getString("name");
                
                System.out.println(id);
                System.out.println(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            //6.关闭数据库相应的资源
            if(connection != null) {
                try {
                	connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(rs != null) {
                try {
                	rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(statement != null) {
                try {
                	statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
