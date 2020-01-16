package com.woniu.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.woniu.entity.Goods;
import com.woniu.entity.Shoppings;
import com.woniu.tools.ConnectionManager;

/**
 * 购物车数据库连接
 * @author Administrator
 *
 */
public class ShoppingsDao {
	/**添加购物车*/
	public int selectShopping(Shoppings shop) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			String sql="";
			//判断该用户的购物车内是否存在该商品
			boolean isExist = isExistShopping(shop);
			if(isExist){
				sql=sql+"UPDATE shoppings SET shopping_count=shopping_count+1 WHERE goods_id=? AND customer_id=?";
			}else{
				sql=sql+"INSERT INTO shoppings(goods_id,customer_id,shopping_count,shopping_date) VALUES(?,?,1,NOW()) ";
			}
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setInt(1, shop.getGoodsId());
			pre.setInt(2, shop.getCustomerId());
			pre.executeUpdate();
			
			//查询顾客的所有购物车记录并显示
			pre = conn.prepareStatement("SELECT SUM(shopping_count) counts FROM shoppings WHERE customer_id = ? GROUP BY customer_id");
			pre.setInt(1, shop.getCustomerId());
			ResultSet re = pre.executeQuery();
			int cusCount = 0;
			if(re.next()){
				cusCount = re.getInt("counts");
			}
			return cusCount;
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}
	/**判断该用户购物车中是否有该商品*/
	private boolean isExistShopping(Shoppings shop) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("select * from shoppings where goods_id=? and customer_id=?");
			pre.setInt(1, shop.getGoodsId());
			pre.setInt(2, shop.getCustomerId());
			ResultSet rs = pre.executeQuery();
			//结果集是否存在
			return rs.next();
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}
	
	/**加载显示购物车*/
	public List<Shoppings> selectById(int customerId) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			String sql = "SELECT shopping_id,shopping_count,shopping_date,goods_count,goods_name,goods_price,goods_img,sh.goods_id FROM shoppings sh LEFT JOIN goods g ON sh.goods_id=g.goods_id WHERE customer_id=?";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setInt(1, customerId);
			ResultSet rs = pre.executeQuery();
			List<Shoppings> li = new ArrayList<>();
			while(rs.next()){
				int shoppingId = rs.getInt("shopping_id");
				int shoppingCount = rs.getInt("shopping_count");
				Date shoppingDate = rs.getDate("shopping_date");
				
				int goodsCount = rs.getInt("goods_count");
				float goodsPrice = rs.getFloat("goods_price");
				String goodsName = rs.getString("goods_name");
				String goodsImg = rs.getString("goods_img");
				int goodsId = rs.getInt("goods_id");
				Goods gs = new Goods(goodsId, goodsName, goodsPrice, goodsCount, goodsImg);
				
				Shoppings shop = new Shoppings(shoppingId, shoppingCount, shoppingDate,gs);
				li.add(shop);
			}
			
			return li;
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}
	/***更改购物车数量*/
	public void updateCount(Shoppings shop) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = ConnectionManager.getConnections();
		PreparedStatement pre;
		try {
			pre = conn.prepareStatement("update shoppings set shopping_count=? where shopping_id=?");
			pre.setInt(1, shop.getShoppingCount());
			pre.setInt(2, shop.getShoppingId());
			pre.executeUpdate();
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}
	
	/**查找确认提交订单页面信息
	 * @throws SQLException 
	 * 
	 * */
	public List<Shoppings> shoppingConfirm(String shId) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			String str="("+shId+")";
			String sql="SELECT shopping_id,shopping_count,shopping_date,goods_count,goods_name,goods_price,goods_count,goods_img,sh.goods_id FROM shoppings sh LEFT JOIN goods g ON sh.goods_id=g.goods_id WHERE shopping_id IN "+str;
			PreparedStatement pre = conn.prepareStatement(sql);
			
			ResultSet rs = pre.executeQuery();
			List<Shoppings> li = new ArrayList<>();
			while(rs.next()){
				int shoppingId = rs.getInt("shopping_id");
				int shoppingCount = rs.getInt("shopping_count");
				Date shoppingDate = rs.getDate("shopping_date");
				
				int goodsCount = rs.getInt("goods_count");
				float goodsPrice = rs.getFloat("goods_price");
				String goodsName = rs.getString("goods_name");
				String goodsImg = rs.getString("goods_img");
				int goodsId = rs.getInt("goods_id");
				Goods gs = new Goods(goodsId, goodsName, goodsPrice, goodsCount, goodsImg);
				
				Shoppings shop = new Shoppings(shoppingId, shoppingCount, shoppingDate,gs);
				li.add(shop);
			}
			return li;
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}
	/**清空购物车
	 * @throws SQLException */
	public void cleanShopping() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("delete  from shoppings");
			pre.executeUpdate();
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}

}
