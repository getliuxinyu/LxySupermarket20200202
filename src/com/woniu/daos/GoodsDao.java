package com.woniu.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.woniu.entity.Goods;
import com.woniu.entity.GoodsType;
import com.woniu.entity.PageInfo;
import com.woniu.entity.Producer;
import com.woniu.tools.ConnectionManager;

/**
 * goods类连接数据库
 * @author Administrator
 *
 */
public class GoodsDao {
	
	/**查询所有商品信息*/
	public List<Goods> selectGoods() throws SQLException {
		// TODO Auto-generated method stub
				Connection conn = ConnectionManager.getConnections();
				try {
					PreparedStatement pre = conn.prepareStatement("SELECT goods_id,goods_code,goods_name,goods_date,goods_price,goods_count,goods_img,goods_remarks,producer_name,goodstype_name FROM goods g JOIN producer pro ON g.`producer_id` = pro.`producer_id` JOIN goodstype gs ON g.`goodstype_id` = gs.`goodstype_id` ORDER BY goods_id");
					ResultSet rs = pre.executeQuery();
					List<Goods> li = new ArrayList<>();
					while(rs.next()){
						int goodsId = rs.getInt("goods_id");
						String goodsCode = rs.getString("goods_code");
						String goodsName=rs.getString("goods_name");
						float goodsPrice=rs.getFloat("goods_price");
						int goodsCount = rs.getInt("goods_count");
						Date goodsData =rs.getDate("goods_date");
						String goodsImg=rs.getString("goods_img");
						String goodsRemarks=rs.getString("goods_remarks");
						String proName = rs.getString("producer_name");
						String gsName = rs.getString("goodstype_name");
						GoodsType gt = new GoodsType();
						gt.setGsname(gsName);
						Producer pro = new Producer();
						pro.setProName(proName);
						Goods gs = new Goods(goodsId, goodsCode, goodsName, goodsData, goodsPrice, goodsCount, goodsImg, goodsRemarks, pro, gt);
						li.add(gs);
					}
					return li;
				} finally{
					ConnectionManager.closeConnection(conn);
				}
	}
	/**增加商品信息
	 * @throws SQLException */
	public void goodsAdd(Goods gs) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("INSERT goods(goodstype_id,producer_id,goods_code,goods_name,goods_date,goods_price,goods_count,goods_img,goods_remarks) VALUE (?,?,?,?,NOW(),?,?,?,?)");
			pre.setInt(1, gs.getGsdId());
			pre.setInt(2, gs.getPoodsId());
			pre.setString(3, gs.getGoodsCode());
			pre.setString(4, gs.getGoodsName());
			pre.setFloat(5, gs.getGoodsPrice());
			pre.setInt(6, gs.getGoodsCount());
			pre.setString(7, gs.getGoodsImg());
			pre.setString(8, gs.getGoodsRemarks());
			pre.executeUpdate();
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}
	/**得到符合查询条件的总条目数
	 * @param cusId */
	public Map<String, Integer> getTotalCount(String goodsName, int cusId) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			String sql = "select count(goods_id) counts from goods where 1=1";
			if(goodsName!=null&&!goodsName.equals("")){
				sql=sql+" and goods_name like ?";
			}
			PreparedStatement pre = conn.prepareStatement(sql);
			int count=0;
			if(goodsName!=null&&!goodsName.equals("")){
				count++;
				pre.setString(count, "%"+goodsName+"%");
			}
			
			ResultSet rs = pre.executeQuery();
			int totalCount=0;
			if(rs.next()){
				totalCount =  rs.getInt("counts");
			}
			
			
			//查询顾客购物车记录
			pre = conn.prepareStatement("SELECT SUM(shopping_count) counts FROM shoppings WHERE customer_id = ? GROUP BY customer_id");
			pre.setInt(1, cusId);
			
			ResultSet re = pre.executeQuery();
			int cusCount = 0;
			if(re.next()){
				cusCount = re.getInt("counts");
			}
		
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("total", totalCount);
			map.put("count", cusCount);
 			
			
			return map;
		}finally{
			ConnectionManager.closeConnection(conn);
		}
		
	}
	/**查询符合查询条件及分页信息的数据
	 * @throws SQLException */
	public List<Goods> goodsList(PageInfo<Goods> pageInfo, String goodsName) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			String sql = "SELECT goods_id,goods_code,goods_name,goods_date,goods_price,goods_count,goods_img,goods_remarks,producer_name,goodstype_name,g.goodstype_id,g.producer_id FROM goods g JOIN producer pro ON g.`producer_id` = pro.`producer_id` JOIN goodstype gs ON g.`goodstype_id` = gs.`goodstype_id` WHERE 1=1 ";
			if(goodsName!=null&&!goodsName.equals("")){
				sql=sql+" and goods_name like ?";
			}
			
			sql = sql+" limit ?,?";
			PreparedStatement pre = conn.prepareStatement(sql);
			int count=0;
			if(goodsName!=null&&!goodsName.equals("")){
				count++;
				pre.setString(count, "%"+goodsName+"%");
			}
			
			pre.setInt(count+1, (pageInfo.getCurrentPage()-1)*pageInfo.getPageSize());
			pre.setInt(count+2, pageInfo.getPageSize());
			
			ResultSet rs = pre.executeQuery();
			List<Goods> li = new ArrayList<>();
			while(rs.next()){
				int goodsId = rs.getInt("goods_id");
				String goodsCode = rs.getString("goods_code");
				String goodName=rs.getString("goods_name");
				float goodsPrice=rs.getFloat("goods_price");
				int goodsCount = rs.getInt("goods_count");
				Date goodsData =rs.getDate("goods_date");
				String goodsImg=rs.getString("goods_img");
				String goodsRemarks=rs.getString("goods_remarks");
				String proName = rs.getString("producer_name");
				String gsName = rs.getString("goodstype_name");
				int goodsTypeId= rs.getInt("goodstype_id");
				int producerId = rs.getInt("producer_id");
				GoodsType gt = new GoodsType();
				gt.setGsname(gsName);
				gt.setGsid(goodsTypeId);
				Producer pro = new Producer();
				pro.setProId(producerId);
				pro.setProName(proName);
				Goods gs = new Goods(goodsId, goodsCode, goodName, goodsData, goodsPrice, goodsCount, goodsImg, goodsRemarks, pro, gt);
				li.add(gs);
			}
			return li;
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}
	//确认信息
	public void confirmGoods(int goodsId) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("update goods set goods_remarks='已确认' where goods_id=?");
			pre.setInt(1, goodsId);
			pre.executeUpdate();
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}
	/**修改信息展示
	 * @throws SQLException */
	public Map<String,Object> selectById(int gdsId) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("select * from goods where goods_id=?");
			pre.setInt(1, gdsId);
			ResultSet re = pre.executeQuery();
			Goods gs=null;
			if(re.next()){
				int goodsId = re.getInt("goods_id");
				int gsdId = re.getInt("goodstype_id");
				int poodsId = re.getInt("producer_id");
				String goodsCode = re.getString("goods_code");
				String goodsName = re.getString("goods_name");
				float goodsPrice = re.getFloat("goods_price");
				int goodsCount = re.getInt("goods_count");
				String goodsImg = re.getString("goods_img");
				String goodsRemarks = re.getString("goods_remarks");
				gs = new Goods(goodsId, goodsCode, goodsName, goodsPrice, goodsCount, goodsImg, goodsRemarks, poodsId, gsdId);
			}
			
			//查找类型厂商
			GoodsTypeDao gtd = new GoodsTypeDao();
			List<GoodsType> gli = gtd.selectGoodsType();
			//查找生产厂商
			ProducerDao pd = new ProducerDao();
			List<Producer> pli = pd.selectProducer();
			Map<String, Object> map = new HashMap<>();
			map.put("goods", gs);
			map.put("goodstypeArr", gli);
			map.put("producerArr", pli);
			return map;
		} finally{
			ConnectionManager.closeConnection(conn);
		}
		
	}
	/**修改商品信息
	 * @throws SQLException */
	public void updateGoods(Goods gs) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("update goods set goods_code=?,goods_name=?,goods_price=?,goods_count=?,goods_img=?,goodstype_id=?,producer_id=?  where goods_id=?");
			pre.setString(1, gs.getGoodsCode());
			pre.setString(2, gs.getGoodsName());
			pre.setFloat(3, gs.getGoodsPrice());
			pre.setInt(4, gs.getGoodsCount());
			pre.setString(5, gs.getGoodsImg());
			pre.setInt(6, gs.getGsdId());
			pre.setInt(7, gs.getPoodsId());
			pre.setInt(8, gs.getGoodsId());
			pre.executeUpdate();
			
		} finally{
			ConnectionManager.closeConnection(conn);
		}
		
	}
	/**删除商品
	 * @throws SQLException */
	public void deleteGoods(int goodsId) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("delete from goods where goods_id=?");
			pre.setInt(1, goodsId);
			pre.executeUpdate();
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}
	/**条件查询总条目数
	 * @throws SQLException */
	public int getTotalCount(String goodsName, String goodsCode) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			String sql = "SELECT COUNT(g.`goods_id`) counts FROM goods g LEFT JOIN goodstype gt ON g.`goodstype_id`=gt.`goodstype_id` JOIN producer p ON g.`producer_id`=p.`producer_id` WHERE 1=1 ";
			if(goodsName!=null&&!goodsName.equals("")){
				sql = sql+" and goods_name like ?";
			}
			if(goodsCode!=null&&!goodsCode.equals("")){
				sql = sql+" and goods_code like ?";
			}
			PreparedStatement pre = conn.prepareStatement(sql);
			int count = 0;
			if(goodsName!=null&&!goodsName.equals("")){
				count++;
				pre.setString(count, "%"+goodsName+"%");
			}
			if(goodsCode!=null&&!goodsCode.equals("")){
				count++;
				pre.setString(count, "%"+goodsCode+"%");
			}
			
			ResultSet rs = pre.executeQuery();
			int totalCount = 0;
			if(rs.next()){
				totalCount = rs.getInt("counts");
			}
			return totalCount;
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}
	/**查询所有符合查询条件的分页信息
	 * @throws SQLException */
	public List<Goods> selectGoods(String goodsCode, String goodsName, PageInfo<Goods> pageInfo) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			String sql = "SELECT goods_id,goods_code,goods_name,goods_date,goods_price,goods_count,goods_img,goods_remarks,producer_name,goodstype_name FROM goods g JOIN producer pro ON g.`producer_id` = pro.`producer_id` JOIN goodstype gs ON g.`goodstype_id` = gs.`goodstype_id` where 1=1 ";
			if(goodsName!=null&&!goodsName.equals("")){
				sql = sql+" and goods_name like ?";
			}
			if(goodsCode!=null&&!goodsCode.equals("")){
				sql = sql+" and goods_code like ?";
			}
			sql = sql+" limit ?,?";
			PreparedStatement pre = conn.prepareStatement(sql);
			int count = 0;
			if(goodsName!=null&&!goodsName.equals("")){
				count++;
				pre.setString(count, "%"+goodsName+"%");
			}
			if(goodsCode!=null&&!goodsCode.equals("")){
				count++;
				pre.setString(count, "%"+goodsCode+"%");
			}
			pre.setInt(count+1, (pageInfo.getCurrentPage()-1)*pageInfo.getPageSize());
			pre.setInt(count+2, pageInfo.getPageSize());
			ResultSet rs = pre.executeQuery();
			List<Goods> li = new ArrayList<>();
			while(rs.next()){
				int goodsId = rs.getInt("goods_id");
				String goodCode = rs.getString("goods_code");
				String goodName=rs.getString("goods_name");
				float goodsPrice=rs.getFloat("goods_price");
				int goodsCount = rs.getInt("goods_count");
				Date goodsData =rs.getDate("goods_date");
				String goodsImg=rs.getString("goods_img");
				String goodsRemarks=rs.getString("goods_remarks");
				String proName = rs.getString("producer_name");
				String gsName = rs.getString("goodstype_name");
				GoodsType gt = new GoodsType();
				gt.setGsname(gsName);
				Producer pro = new Producer();
				pro.setProName(proName);
				Goods gs = new Goods(goodsId, goodCode, goodName, goodsData, goodsPrice, goodsCount, goodsImg, goodsRemarks, pro, gt);
				li.add(gs);
			}
			return li;
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}

}
