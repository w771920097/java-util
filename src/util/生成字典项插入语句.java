package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class 生成字典项插入语句 {

	public static void main(String[] args) {

		String sql = "select domain_name,system_sensitive,prefix,create_date, update_date from uc_sys_property_domain";
		
		Connection conn = DatabaseUtil.getConnection();
		
		try {
			PreparedStatement pStemt = conn.prepareStatement(sql);
			pStemt.executeQuery();
			ResultSet rs = pStemt.getResultSet();
			
			while(rs.next()){
				String domain_name  = rs.getString("domain_name");
				String system_sensitive = rs.getString("system_sensitive");
				long prefix = rs.getLong("prefix");
				
				System.out.println("insert into uc_sys_property_domain(domain_name,system_sensitive,prefix,create_date, update_date) "
						+ "values('"+domain_name+"',"+system_sensitive+","+prefix+",now(),null);");
				
//				insert into uc_sys_property_dict (property_domain_id, internal_id, display_seq, display_name, simple_pinyin, full_pinyin,create_date, update_date,unique_code)
//				values ((select id from uc_sys_property_domain a where a.domain_name = '探测目标类型'), 0, 2, 'http', 'http', 'http',now(), null,20090002);

				String exSql = "select property_domain_id, internal_id, display_seq, display_name, simple_pinyin, full_pinyin,create_date, update_date,unique_code "
						+ "from uc_sys_property_dict "
						+ "where property_domain_id in "
						+ "(select id from uc_sys_property_domain a where a.domain_name = '"+domain_name+"')";
				
				PreparedStatement pStemt2 = conn.prepareStatement(exSql);
				pStemt2.executeQuery();
				ResultSet rs2 = pStemt2.getResultSet();
				int display_seq = 0;
				while(rs2.next()){
					long internal_id = rs2.getLong("internal_id");
					String display_name = rs2.getString("display_name");
					String simple_pinyin = rs2.getString("simple_pinyin");
					String full_pinyin = rs2.getString("full_pinyin");
					long unique_code = rs2.getLong("unique_code");
					
					System.out.println("insert into uc_sys_property_dict (property_domain_id, internal_id, display_seq, display_name, simple_pinyin, full_pinyin,create_date, update_date,unique_code)\n" + 
							"values ((select id from uc_sys_property_domain a where a.domain_name = '"+domain_name+"'), "+internal_id+", "+display_seq+", '"+display_name+"', '"+simple_pinyin+"', '"+full_pinyin+"',now(), null,"+unique_code+");");
					display_seq ++;
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
