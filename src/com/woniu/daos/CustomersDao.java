package com.woniu.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.woniu.tools.ConnectionManager;

/**
 * 顾客实体类
 * @author Administrator
 *
 */
public class CustomersDao {
	/**顾客登录
	 * 返回顾客id，后面添加购物车要用
	 * */
	public int isExist(String customerName, String customerPwd) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("select * from customers where customer_name=? and customer_pwd=? and customer_status='活跃'");
			pre.setString(1, customerName);
			pre.setString(2, customerPwd);
			ResultSet rs = pre.executeQuery();
			int customerId = 0;
			if(rs.next()){
				customerId=rs.getInt("customer_id");
			}
			return customerId;
			
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}

}
