package com.woniu.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.woniu.entity.Orders;
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
		System.out.println(dateStr+rdStr);
		return dateStr+rdStr;
	}
	
}
