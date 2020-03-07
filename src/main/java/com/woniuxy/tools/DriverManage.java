package com.woniuxy.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * �����������������Ӷ���,�ͷ����Ӷ���
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
			//���javaWeb��Ŀ�е������ļ�
			String path = DriverManage.class.getClassLoader().getResource("/").getPath();
			//�������������󣬼���properties�����ļ�
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
			
			//�������ӳض���
			dataSource = new DruidDataSource();
			dataSource.setDriverClassName(driver);
			dataSource.setUrl(conn_url);
			dataSource.setUsername(user_name);
			dataSource.setPassword(password);
			dataSource.setMaxActive(maxActive);
			dataSource.setMinIdle(minIdle);
			dataSource.setMaxWait(maxWait);
			
			//��������������޵Ĳ��ȴ�ʩ�������ӳ���ͼȥ���ճ�ʱ������
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
			//System.out.println("�����������" + dataSource.getActiveCount());
			return conn;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	//�ͷ����Ӷ���
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
