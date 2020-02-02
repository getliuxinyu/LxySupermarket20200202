package com.woniu.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.woniu.entity.Customers;
import com.woniu.entity.PageInfo;
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
	/**总条目数
	 * @throws SQLException */
	public int selectTotalCount(String cusName) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			String sql="select count(customer_id) counts from customers where 1=1";
			if(cusName!=null&&!cusName.equals("")){
				sql=sql+" and customer_name like ?";
			}
			PreparedStatement pre = conn.prepareStatement(sql);
			int count=0;
			if(cusName!=null&&!cusName.equals("")){
				count++;
				pre.setString(count, "%"+cusName+"%");
			}
			
			ResultSet rs = pre.executeQuery();
			int totalCount=0;
			if(rs.next()){
				totalCount =rs.getInt("counts");
			}
			return totalCount;
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}
	/**分页显示
	 * @throws SQLException */
	public List<Customers> selectCustomers(String cusName, PageInfo<Customers> pageInfo) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			String sql="select * from customers where 1=1";
			if(cusName!=null&&!cusName.equals("")){
				sql=sql+" and customer_name like ?";
			}
			sql = sql + " limit ?,?";
			PreparedStatement pre = conn.prepareStatement(sql);
			int count=0;
			if(cusName!=null&&!cusName.equals("")){
				count++;
				pre.setString(count, "%"+cusName+"%");
			}
			pre.setInt(count+1, (pageInfo.getCurrentPage()-1)*pageInfo.getPageSize());
			pre.setInt(count+2, pageInfo.getPageSize());
			ResultSet rs = pre.executeQuery();
			List<Customers> li = new ArrayList<Customers>();
			while(rs.next()){
				int customerId = rs.getInt("customer_id");
				String customerName = rs.getString("customer_name");
				String customerPwd = rs.getString("customer_pwd");
				String customerPhone = rs.getString("customer_phone");
				String customerAddress = rs.getString("customer_address");
				String customerStatus = rs.getString("customer_status");
				Customers cs = new Customers(customerId, customerName, customerPwd, customerPhone, customerAddress, customerStatus);
				li.add(cs);		
			}
			return li;
		
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}
	/**修改信息展示
	 * @throws SQLException */
	public Customers selectById(int cusId) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("select * from customers where customer_id=? and customer_status='活跃'");
			pre.setInt(1, cusId);
			ResultSet rs = pre.executeQuery();
			Customers cs = null;
			if(rs.next()){
				int customerId=rs.getInt("customer_id");
				String customerName = rs.getString("customer_name");
				String customerPwd = rs.getString("customer_pwd");
				String customerPhone = rs.getString("customer_phone");
				String customerAddress = rs.getString("customer_address");
				String customerStatus = rs.getString("customer_status");
				cs = new Customers(customerId, customerName, customerPwd, customerPhone, customerAddress, customerStatus);
			}
			return cs;
			
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}
	/**修改信息
	 * @throws SQLException */
	public void updateCustomer(Customers cs) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("update customers set customer_name=?,customer_phone=?,customer_address=?  where customer_id=? and customer_status='活跃'");
			pre.setString(1, cs.getCustomerName());
			pre.setString(2, cs.getCustomerPhone());
			pre.setString(3, cs.getCustomerAddress());
			pre.setInt(4, cs.getCustomerId());
			pre.executeUpdate();
		} finally{
			ConnectionManager.closeConnection(conn);
		}
		
	}
	/**停用
	 * @throws SQLException */
	public void updateCustomer(int customerId) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("update customers set customer_status='不活跃' where customer_id=?");
			pre.setInt(1, customerId);
			pre.executeUpdate();
		} finally{
			ConnectionManager.closeConnection(conn);
		}
		
	}
	/**重置密码
	 * @throws SQLException */
	public void resetCustomer(int customerId) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("update customers set customer_pwd='123456' where customer_id=?");
			pre.setInt(1, customerId);
			pre.executeUpdate();
		} finally{
			ConnectionManager.closeConnection(conn);
		}
		
	}
	/**活跃
	 * @throws SQLException */
	public void activeCustomer(int customerId) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("update customers set customer_status='活跃' where customer_id=?");
			pre.setInt(1, customerId);
			pre.executeUpdate();
		} finally{
			ConnectionManager.closeConnection(conn);
		}
		
	}
	/**
	 * 注册用户名检测
	 * @param cusName
	 * @return
	 * @throws SQLException 
	 */
	public boolean isExist(String cusName) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("select * from customers where customer_name=?");
			pre.setString(1, cusName);
			ResultSet re = pre.executeQuery();
			return re.next();
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}
	public void addCus(Customers cus) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("insert into customers(customer_name,customer_pwd,customer_phone,customer_address,customer_status) values(?,?,?,?,'活跃')");
			pre.setString(1, cus.getCustomerName());
			pre.setString(2, cus.getCustomerPwd());
			pre.setString(3, cus.getCustomerPhone());
			pre.setString(4, cus.getCustomerAddress());
			pre.executeUpdate();
		} finally{
			ConnectionManager.closeConnection(conn);
		}
		
	}

}
