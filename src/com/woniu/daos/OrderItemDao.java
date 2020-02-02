package com.woniu.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.woniu.entity.Customers;
import com.woniu.entity.Goods;
import com.woniu.entity.OrderItem;
import com.woniu.entity.Orders;
import com.woniu.entity.PageInfo;
import com.woniu.tools.ConnectionManager;

/**
 * 订单明细
 * @author very
 *
 */
public class OrderItemDao {

	public int getTotalCount(String odCode) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			String sql="select count(orderItem_id) counts from orderitem where 1=1";
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

	public List<OrderItem> selectOdItem(String odCode, PageInfo<OrderItem> pageInfo) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			String sql="SELECT orderItem_id,order_code,orderItem_price,orderItem_count,o.goods_id,goods_name FROM `orderitem` o LEFT JOIN goods g on o.goods_id=g.goods_id where 1=1";
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
			List<OrderItem> li = new ArrayList<OrderItem>();
			while(rs.next()){
				int orderItemId = rs.getInt("orderItem_id");
				String orderCode = rs.getString("order_code");
				float orderItemPrice = rs.getFloat("orderItem_price");
				int orderItemCount = rs.getInt("orderItem_count");
				int goodsId = rs.getInt("goods_id");
				String goodsName = rs.getString("goods_name");
				Goods goods = new Goods();
				goods.setGoodsName(goodsName);
				OrderItem oid = new OrderItem(orderItemId, goodsId, orderCode, orderItemPrice, orderItemCount, goods);
				li.add(oid);
			}
			return li;
		
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}
	
	
}
