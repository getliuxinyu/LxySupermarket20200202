package com.woniu.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.woniu.entity.Customers;
import com.woniu.entity.Orders;
import com.woniu.entity.PageInfo;
import com.woniu.tools.ConnectionManager;

/**
 * 订单表
 * @author Administrator
 *
 */
public class OrdersDao {

	public void confirmOrder(String shIdArr, Orders order) throws SQLException {
Connection conn = ConnectionManager.getConnections();
		
		try {
			//添加事务
			conn.setAutoCommit(false);
			
			//订单表添加数据
			PreparedStatement pre = conn.prepareStatement("insert into orders(order_code,customer_id,order_price,order_date,order_status,order_remarks) values(?,?,?,now(),'未支付',?)");
			//生成编号随机数
			String orderCode=randomCode();
			pre.setString(1,orderCode);
			
			pre.setInt(2, order.getCustomerId());
			pre.setFloat(3, order.getOrderPrice());
			pre.setString(4, order.getOrderRemarks());
			pre.executeUpdate();
			
			//订单明细表批量插入数据
			String shopId="("+shIdArr+")";
			String sql = "INSERT INTO orderitem(goods_id,order_code,orderItem_price,orderItem_count) SELECT sp.goods_id,"+orderCode+",goods_price,shopping_count FROM  shoppings sp LEFT JOIN goods gs ON sp.goods_id=gs.goods_id WHERE shopping_id IN "+shopId;
			pre = conn.prepareStatement(sql);
			pre.executeUpdate();
			
			//删除购物车表数据
			pre = conn.prepareStatement("delete from shoppings where shopping_id in "+shopId);
			pre.executeUpdate();
			
			//提交
			conn.commit();
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}

	private String randomCode() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddhhmmss");
		String dateStr = sdf.format(date);
		Random random = new Random();
		int rdStr = random.nextInt(10000);
		return dateStr+rdStr;
	}
	/**
	 * 总条目数
	 * @param odCode
	 * @return
	 * @throws SQLException 
	 */
	public int getTotalCount(String odCode) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			String sql="select count(order_code) counts from orders where 1=1";
			if(odCode!=null&&!odCode.equals("")){
				sql=sql+" and order_code like ?";
			}
			PreparedStatement pre = conn.prepareStatement(sql);
			int count=0;
			if(odCode!=null&&!odCode.equals("")){
				count++;
				pre.setString(count, "%"+odCode+"%");
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

	public List<Orders> selectOrders(String odCode, PageInfo<Orders> pageInfo) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			String sql="select order_code,order_date,order_price,order_status,order_remarks,od.customer_id,customer_name from orders od LEFT JOIN customers cus on od.customer_id=cus.customer_id where 1=1";
			if(odCode!=null&&!odCode.equals("")){
				sql=sql+" and order_code like ?";
			}
			sql = sql + " limit ?,?";
			PreparedStatement pre = conn.prepareStatement(sql);
			int count=0;
			if(odCode!=null&&!odCode.equals("")){
				count++;
				pre.setString(count, "%"+odCode+"%");
			}
			pre.setInt(count+1, (pageInfo.getCurrentPage()-1)*pageInfo.getPageSize());
			pre.setInt(count+2, pageInfo.getPageSize());
			ResultSet rs = pre.executeQuery();
			List<Orders> li = new ArrayList<Orders>();
			while(rs.next()){
				String orderCode = rs.getString("order_code");
				Date orderDate = rs.getDate("order_date");
				float orderPrice = rs.getFloat("order_price");
				String orderStatus = rs.getString("order_status");
				String orderRemarks = rs.getString("order_remarks");
				int customerId = rs.getInt("customer_id");
				String customerName = rs.getString("customer_name");
				Orders od = new Orders(orderCode, customerId, orderDate, orderPrice, orderRemarks, orderStatus);
				Customers cus = new Customers();
				cus.setCustomerName(customerName);
				od.setCus(cus);
				li.add(od);
			}
			return li;
		
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}
	
}
