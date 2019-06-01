package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class 数据连接测试 {

  public static void main(String[] args) {
    String tableName = "zk_alarm_info";
    // jdbc:mysql://localhost:3306/tqzooker?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=utf8
    String url = "jdbc:mysql://127.0.0.1:3306/tqzooker?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=utf8";
    String userName = "root";
    String password = "soa123456";
    // 执行sql 获取metadata信息
    // SELECT TABLE_NAME,TABLE_COMMENT FROM information_schema.TABLES WHERE
    // table_schema='database名';
    String sql = "select * from " + tableName + " where 1=2 ";
    // String sql =
    // "SELECT TABLE_NAME,TABLE_COMMENT FROM information_schema.TABLES WHERE table_schema='"+tableName+"';";

    Connection connection = null;
    PreparedStatement pStatement = null;
    ResultSet rSet = null;
    // List<DataMetaType> metaTypes = new ArrayList<>();
    try {
      connection = DriverManager.getConnection(url, userName, password);
      pStatement = connection.prepareStatement(sql);
      rSet = pStatement.executeQuery();
      ResultSetMetaData rs = rSet.getMetaData();
      System.out.println(rs);
      for (int i = 0; i < rs.getColumnCount(); i++) {
        System.out.print(rs.getColumnName(i + 1) + "\t");
        System.out.print(rs.getColumnTypeName(i + 1) + "\t");
        System.out.println(rs.getColumnType(i + 1));
        /*
         * DataMetaType metaType = new DataMetaType();
         * metaType.setColumName(rs.getColumnName(i + 1));
         * metaType.setColumnType(rs.getColumnTypeName(i + 1));
         * metaType.setDataType(rs.getColumnType(i + 1));
         * metaTypes.add(metaType);
         */
      }
    } catch (Exception e) {
      e.printStackTrace();
      // throw new ServiceValidationException("获取数据库字段信息出错", e);
    } finally {
      if (connection != null) {
        try {
          connection.close();
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
    // return metaTypes;
  }
}
