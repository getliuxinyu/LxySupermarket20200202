package com.woniu.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.woniu.entity.Goods;
import com.woniu.entity.OutStock;
import com.woniu.entity.PageInfo;
import com.woniu.entity.Staffs;
import com.woniu.tools.ConnectionManager;

/**出库单*/
public class OutStockDao {
	/**总条目数
	 * @throws SQLException */
	public int getTotalCount(String outsCode) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			String sql="select count(outstock_id) counts from outstock where 1=1";
			
			if(outsCode!=null&&!outsCode.equals("")){
				sql=sql+" and outstock_code like ?";
			}
			PreparedStatement pre = conn.prepareStatement(sql);
			int count=0;
			
			if(outsCode!=null&&!outsCode.equals("")){
				count++;
				pre.setString(count, "%"+outsCode+"%");
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
	/**查找符合分页的信息
	 * @throws SQLException */
	public List<OutStock> selectOutStock(String outsCode, PageInfo<OutStock> pageInfo) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			String sql="select outstock_id,outs.goods_id,outs.staff_id,outstock_code,outstock_date,outstock_count,outstock_status,outstock_remarks,goods_name,staff_name from outstock outs LEFT join goods gs on outs.goods_id=gs.goods_id join staffs s on outs.staff_id=s.staff_id where 1=1";
			
			if(outsCode!=null&&!outsCode.equals("")){
				sql=sql+" and outstock_code like ?";
			}
			sql = sql + " limit ?,?";
			PreparedStatement pre = conn.prepareStatement(sql);
			int count=0;
			
			if(outsCode!=null&&!outsCode.equals("")){
				count++;
				pre.setString(count, "%"+outsCode+"%");
			}
			pre.setInt(count+1, (pageInfo.getCurrentPage()-1)*pageInfo.getPageSize());
			pre.setInt(count+2, pageInfo.getPageSize());
			ResultSet rs = pre.executeQuery();
			List<OutStock> li = new ArrayList<OutStock>();
			while(rs.next()){
				int outstockId = rs.getInt("outstock_id");
				int goodsId = rs.getInt("goods_id");
				int staffId = rs.getInt("staff_id");
				String outstockCode = rs.getString("outstock_code");
				Date outstockDate = rs.getDate("outstock_date");
				int outstockCount = rs.getInt("outstock_count");
				String outstockStatus = rs.getString("outstock_status");
				String outstockRemarks = rs.getString("outstock_remarks");
				String goodsName = rs.getString("goods_name");
				String staffName = rs.getString("staff_name");
				Goods goods = new Goods();
				goods.setGoodsName(goodsName);
				Staffs staff = new Staffs();
				staff.setStaffName(staffName);
				OutStock outs = new OutStock(outstockId, goodsId, staffId, outstockCode, outstockDate, outstockCount, outstockStatus, outstockRemarks);
				outs.setGoods(goods);
				outs.setStaff(staff);
				li.add(outs);
			}
			return li;
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}
	/**增加
	 * @throws SQLException */
	public void addOutStock(OutStock outs) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("INSERT into outstock(goods_id,staff_id,outstock_code,outstock_date,outstock_count,outstock_status,outstock_remarks)  values(?,?,?,now(),?,?,'未确认')");
			pre.setInt(1, outs.getGoodsId());
			pre.setInt(2, outs.getStaffId());
			pre.setString(3, outs.getOutstockCode());
			pre.setInt(4, outs.getOutstockCount());
			pre.setString(5, outs.getOutstockStatus());
			pre.executeUpdate();
		} finally{
			ConnectionManager.closeConnection(conn);
		}
		
	}
	/**确认
	 * @throws SQLException */
	public void confirmOuts(OutStock outs) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			//添加事务
			conn.setAutoCommit(false);
			PreparedStatement pre = conn.prepareStatement("update outstock set outstock_remarks='确认' where outstock_id=?");
			pre.setInt(1, outs.getOutstockId());
			pre.executeUpdate();
			pre = conn.prepareStatement("update goods set goods_count=goods_count-?  where goods_id=?");
			pre.setInt(1, outs.getOutstockCount());
			pre.setInt(2, outs.getGoodsId());
			pre.executeUpdate();
			conn.commit();
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}
	/**修改页面展示
	 * @throws SQLException */
	public OutStock selectById(int outsId) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			//添加事务
		
			PreparedStatement pre = conn.prepareStatement("select * from outstock where outstock_id=?");
			pre.setInt(1, outsId);
			ResultSet rs = pre.executeQuery();
			OutStock outs = null;
			if(rs.next()){
				int outstockId = rs.getInt("outstock_id");
				int goodsId = rs.getInt("goods_id");
				int staffId = rs.getInt("staff_id");
				String outstockCode = rs.getString("outstock_code");
				int outstockCount = rs.getInt("outstock_count");
				String outstockStatus = rs.getString("outstock_status");
				outs = new OutStock(goodsId, outstockCode, outstockCount, outstockStatus);
				outs.setOutstockId(outstockId);
			}
			return outs;
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}
	/**修改
	 * @throws SQLException */
	public void updateOuts(OutStock outs) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("update outstock set goods_id=?,outstock_code=?,outstock_count=?,outstock_status=? where outstock_id=?");
			pre.setInt(1, outs.getGoodsId());
			pre.setString(2, outs.getOutstockCode());
			pre.setInt(3, outs.getOutstockCount());
			pre.setString(4, outs.getOutstockStatus());
			pre.setInt(5, outs.getOutstockId());
			pre.executeUpdate();
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}
	/**删除
	 * @throws SQLException */
	public void deleteOuts(int outstockId) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("delete from outstock where outstock_id=?");
			pre.setInt(1, outstockId);
			pre.executeUpdate();
		} finally{
			ConnectionManager.closeConnection(conn);
		}
		
	}

}
