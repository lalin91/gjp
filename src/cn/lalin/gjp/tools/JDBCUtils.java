package cn.lalin.gjp.tools;
/**
 * 获取数据库连接的工具类
 * 实现连接池，dbcp连接池
 */
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class JDBCUtils {
	//创建BasicDataSource对象
	private static BasicDataSource datasource = new BasicDataSource();
	//静态代码块实现必要的参数设置
	static {
		datasource.setDriverClassName("com.mysql.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://localhost:3306/gjp?characterEncoding=UTF-8");
		datasource.setUsername("root");
		datasource.setPassword("root");
		datasource.setMaxActive(10);
		datasource.setMaxIdle(5);
		datasource.setMinIdle(2);
		datasource.setInitialSize(10);
	}
	public static DataSource getDataSource(){
		return datasource;
	}
}
