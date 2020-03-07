package com.woniuxy.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 加载驱动，创建连接对象,释放连接对象
 * @author Bruse
 *
 */
public class DriverManage {
//	private final static String DRIVER_URL = "com.mysql.cj.jdbc.Driver";
//	private final static String CONN_URl = "jdbc:mysql://localhost:3306/test20191212?characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
//	private final static String USER_NAME = "root";
//	private final static String USER_PASSWORD = "123456789";
	
	private static DruidDataSource dataSource;
	
	
	static {
		try {
			//获得javaWeb项目中的配置文件
			String path = DriverManage.class.getClassLoader().getResource("/").getPath();
			//创建输入流对象，加载properties配置文件
			// /H:/woniu/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/JAVAWEB1224/WEB-INF/classes/
			//System.out.println(path);
			InputStream input = new FileInputStream(new File(path+ File.separator + "jdbc.properties"));
			Properties pro = new Properties();
			pro.load(input);
			String driver = pro.getProperty("driver");
			String conn_url = pro.getProperty("url");
			String user_name = pro.getProperty("user");
			String password = pro.getProperty("password");
			int maxActive = Integer.parseInt(pro.getProperty("maxActive")); 
			int minIdle = Integer.parseInt(pro.getProperty("minIdle")); 
			int maxWait = Integer.parseInt(pro.getProperty("maxWait"));
			
			//创建连接池对象
			dataSource = new DruidDataSource();
			dataSource.setDriverClassName(driver);
			dataSource.setUrl(conn_url);
			dataSource.setUsername(user_name);
			dataSource.setPassword(password);
			dataSource.setMaxActive(maxActive);
			dataSource.setMinIdle(minIdle);
			dataSource.setMaxWait(maxWait);
			
			//活动连接数到达上限的补救措施，让连接池试图去回收超时的连接
			dataSource.setRemoveAbandonedTimeout(10);
			dataSource.setRemoveAbandoned(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			//System.out.println("活动的连接数量" + dataSource.getActiveCount());
			return conn;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	//释放连接对象
	public static void closeConnection(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
