package com.woniu.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.woniu.entity.Goods;
import com.woniu.entity.InStock;
import com.woniu.entity.PageInfo;
import com.woniu.entity.Staffs;
import com.woniu.tools.ConnectionManager;

/**
 * 入库表
 * */

public class InStockDao {
	/**总条目数
	 * @throws SQLException */
	public int getTotalCount(String inCode) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			String sql="select count(instock_id) counts from instock where 1=1";
			
			if(inCode!=null&&!inCode.equals("")){
				sql=sql+" and instock_code like ?";
			}
			PreparedStatement pre = conn.prepareStatement(sql);
			int count=0;
			
			if(inCode!=null&&!inCode.equals("")){
				count++;
				pre.setString(count, "%"+inCode+"%");
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
	/**分页信息
	 * @throws SQLException */
	public List<InStock> selectInStock(String inCode, PageInfo<InStock> pageInfo) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			String sql="select instock_id,ins.goods_id,ins.staff_id,instock_code,instock_date,instock_count,instock_status,instock_remarks,goods_name,staff_name from instock ins LEFT join goods gs on ins.goods_id=gs.goods_id join staffs s on ins.staff_id=s.staff_id where 1=1";
			
			if(inCode!=null&&!inCode.equals("")){
				sql=sql+" and instock_code like ?";
			}
			sql = sql + " limit ?,?";
			PreparedStatement pre = conn.prepareStatement(sql);
			int count=0;
			
			if(inCode!=null&&!inCode.equals("")){
				count++;
				pre.setString(count, "%"+inCode+"%");
			}
			pre.setInt(count+1, (pageInfo.getCurrentPage()-1)*pageInfo.getPageSize());
			pre.setInt(count+2, pageInfo.getPageSize());
			ResultSet rs = pre.executeQuery();
			List<InStock> li = new ArrayList<InStock>();
			while(rs.next()){
				int instockId = rs.getInt("instock_id");
				int goodsId = rs.getInt("goods_id");
				int staffId = rs.getInt("staff_id");
				String instockCode = rs.getString("instock_code");
				Date instockDate = rs.getDate("instock_date");
				int instockCount = rs.getInt("instock_count");
				String instockStatus = rs.getString("instock_status");
				String instockRemarks = rs.getString("instock_remarks");
				String goodsName = rs.getString("goods_name");
				String staffName = rs.getString("staff_name");
				Goods goods = new Goods();
				goods.setGoodsName(goodsName);
				Staffs staff = new Staffs();
				staff.setStaffName(staffName);
				InStock in = new InStock(instockId, goodsId, staffId, instockCode, instockDate, instockStatus, instockCount, instockRemarks);
				in.setGoods(goods);
				in.setStaff(staff);
				li.add(in);
			}
			return li;
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}
	/**增
	 * @throws SQLException */
	public void addInStock(InStock in) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("INSERT into instock(goods_id,staff_id,instock_code,instock_date,instock_count,instock_status,instock_remarks)  values(?,?,?,now(),?,?,'未确认')");
			pre.setInt(1, in.getGoodsId());
			pre.setInt(2, in.getStaffId());
			pre.setString(3, in.getInstockCode());
			pre.setInt(4, in.getInstockCount());
			pre.setString(5, in.getInstockStatus());
			pre.executeUpdate();
		} finally{
			ConnectionManager.closeConnection(conn);
		}
		
	}
	/**
	 * 确认
	 * @param in
	 * @throws SQLException 
	 */
	public void confirmInStock(InStock in) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			conn.setAutoCommit(false);
			PreparedStatement pre = conn.prepareStatement("update instock set instock_remarks='确认' where instock_id=?");
			pre.setInt(1, in.getInstockId());
			pre.executeUpdate();
			pre = conn.prepareStatement("update goods set goods_count=goods_count+? where goods_id=?");
			pre.setInt(1, in.getInstockCount());
			pre.setInt(2, in.getGoodsId());
			pre.executeUpdate();
			conn.commit();
		} finally{
			ConnectionManager.closeConnection(conn);
		}
		
	}
	/**
	 * 删除
	 * @param inId
	 * @throws SQLException 
	 */
	public void deleteInStock(int inId) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("delete from instock where instock_id=?");
			pre.setInt(1, inId);
			pre.executeUpdate();
		} finally{
			ConnectionManager.closeConnection(conn);
		}
		
	}
	/**
	 * 修改页面展示
	 * @param inId
	 * @return
	 * @throws SQLException 
	 */
	public InStock selectById(int inId) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("select * from instock where instock_id=?");
			pre.setInt(1, inId);
			ResultSet re = pre.executeQuery();
			InStock in = null;
			if(re.next()){
				int instockId = re.getInt("instock_id");
				String instockCode = re.getString("instock_code");
				String instockStatus = re.getString("instock_status");
				int instockCount = re.getInt("instock_count");
				in = new InStock(instockId, instockCode, instockStatus, instockCount);
			}
			return in;
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}
	/**
	 * 修改
	 * @param in
	 * @throws SQLException 
	 */
	public void updateInStock(InStock in) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("update instock set goods_id=?,instock_code=?,instock_status=?,instock_count=? where instock_id=?");
			pre.setInt(1, in.getGoodsId());
			pre.setString(2, in.getInstockCode());
			pre.setString(3, in.getInstockStatus());
			pre.setInt(4, in.getInstockCount());
			pre.setInt(5, in.getInstockId());
			pre.executeUpdate();
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}

}
