package com.woniu.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;



/**
 * 数据库连接池管理器
 * @author Administrator
 *
 */
public class ConnectionManager {
	
		//创建连接池源
		private static DruidDataSource dataSource;
	static{
		//获得配置文件绝对路径
		try {
			String path = ConnectionManager.class.getClassLoader().getResource("/").getPath();
			InputStream reader = new FileInputStream(new File(path+File.separator+"jdbc.properties"));
			//加载文件
			Properties pro = new Properties();
			pro.load(reader);
			
			//读取配置文件信息
			String dirver = pro.getProperty("dirver");
			String driver = pro.getProperty("driver");
			String url = pro.getProperty("url");
			String user = pro.getProperty("user");
			String password = pro.getProperty("password");
			int maxActive = Integer.parseInt(pro.getProperty("maxActive"));
			int minIdle = Integer.parseInt(pro.getProperty("minIdle"));
			int maxWait = Integer.parseInt(pro.getProperty("maxWait"));
			
			//创建连接池对象
			dataSource = new DruidDataSource();
			
			//设置属性
			dataSource.setDriverClassName(driver);
			dataSource.setUrl(url);
			dataSource.setUsername(user);
			dataSource.setPassword(password);
			dataSource.setMaxActive(maxActive);
			dataSource.setMinIdle(minIdle);
			dataSource.setMaxWait(maxWait);
			
			dataSource.setRemoveAbandonedTimeout(10);
			dataSource.setRemoveAbandoned(true);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//从连接池中获取连接
	public static Connection getConnections(){
		try {
			Connection conn = dataSource.getConnection();
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	//关闭连接，将连接放回连接池
	public static void closeConnection(Connection conn){
		try {
			if(conn!=null&&!conn.isClosed()){
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
