package com.woniu.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.woniu.entity.GoodsType;
import com.woniu.entity.PageInfo;
import com.woniu.entity.Producer;
import com.woniu.tools.ConnectionManager;



/**
 * 生产厂商
 * @author Administrator
 *
 */
public class ProducerDao {
	public List<Producer> selectProducer() throws SQLException{
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("select * from producer");
			ResultSet rs = pre.executeQuery();
			List<Producer> li = new ArrayList<Producer>();
			while(rs.next()){
				int proId = rs.getInt("producer_id");
				String proCode = rs.getString("producer_code");
				String proName = rs.getString("producer_name");
				String proPhone = rs.getString("producer_phone");
				String proAddress = rs.getString("producer_address");
				String proRemarks = rs.getString("producer_remarks");
				Producer p = new Producer(proId, proCode, proName, proPhone, proAddress, proRemarks);
				li.add(p);
			}
			return li;
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}
	/**总条目数
	 * @throws SQLException */
	public int getTotalCount(String pCode, String pName) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			String sql = "select count(producer_id) counts from producer where 1=1";
			if(pCode!=null&&!pCode.equals("")){
				sql = sql + " and producer_code like ?";
			}
			if(pName!=null&&!pName.equals("")){
				sql = sql + " and producer_name like ?";
			}
			PreparedStatement pre = conn.prepareStatement(sql);
			int count = 0;
			if(pCode!=null&&!pCode.equals("")){
				count++;
				pre.setString(count, "%"+pCode+"%");
			}
			if(pName!=null&&!pName.equals("")){
				count++;
				pre.setString(count, "%"+pName+"%");
			}
			ResultSet rs = pre.executeQuery();
			int totalCount=0;
			if(rs.next()){
				totalCount = rs.getInt("counts");
			}
			return totalCount;
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}
	/**分页显示信息
	 * @throws SQLException */
	public List<Producer> selectProducer(String pCode, String pName, PageInfo<Producer> pageInfo) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			String sql = "select * from producer where 1=1";
			if(pCode!=null&&!pCode.equals("")){
				sql = sql + " and producer_code like ?";
			}
			if(pName!=null&&!pName.equals("")){
				sql = sql + " and producer_name like ?";
			}
			sql = sql + " limit ?,?";
			PreparedStatement pre = conn.prepareStatement(sql);
			int count = 0;
			if(pCode!=null&&!pCode.equals("")){
				count++;
				pre.setString(count, "%"+pCode+"%");
			}
			if(pName!=null&&!pName.equals("")){
				count++;
				pre.setString(count, "%"+pName+"%");
			}
			
			pre.setInt(count+1, (pageInfo.getCurrentPage()-1)*pageInfo.getPageSize());
			pre.setInt(count+2, pageInfo.getPageSize());
			ResultSet rs = pre.executeQuery();
			List<Producer> li = new ArrayList<>();
			while(rs.next()){
				int proId = rs.getInt("producer_id");
				String proCode = rs.getString("producer_code");
				String proName = rs.getString("producer_name");
				String proPhone = rs.getString("producer_phone");
				String proAddress = rs.getString("producer_address");
				String proRemarks = rs.getString("producer_remarks");
				Producer p = new Producer(proId, proCode, proName, proPhone, proAddress, proRemarks);
				li.add(p);
			}
			return li;
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}
	/**增加
	 * @throws SQLException */
	public void addProducer(Producer pro) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("insert into producer(producer_code,producer_name,producer_phone,producer_address) values(?,?,?,?)");
			pre.setString(1, pro.getProCode());
			pre.setString(2, pro.getProName());
			pre.setString(3, pro.getProPhone());
			pre.setString(4, pro.getProAddress());
			
			pre.executeUpdate();
		} finally{
			ConnectionManager.closeConnection(conn);
		}
		
	}
	/**删除
	 * @throws SQLException */
	public void deleteProducer(int pId) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("delete from producer where producer_id=?");
			pre.setInt(1, pId);
			
			pre.executeUpdate();
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}
	/**修改页面展示
	 * @throws SQLException */
	public Producer selectById(int pId) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("select * from producer where producer_id=?");
			pre.setInt(1, pId);
			ResultSet rs = pre.executeQuery();
			Producer pro = null;
			if(rs.next()){
				int proId = rs.getInt("producer_id");
				String proCode = rs.getString("producer_code");
				String proName = rs.getString("producer_name");
				String proPhone = rs.getString("producer_phone");
				String proAddress = rs.getString("producer_address");
				String proRemarks = rs.getString("producer_remarks");
			    pro = new Producer(proId, proCode, proName, proPhone, proAddress, proRemarks);
				
			}
			return pro;
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}
	/**修改
	 * @throws SQLException */
	public void updateProducer(Producer pro) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("update producer set producer_code=?,producer_name=?,producer_phone=?,producer_address=? where producer_id=?");
			pre.setString(1, pro.getProCode());
			pre.setString(2, pro.getProName());
			pre.setString(3, pro.getProPhone());
			pre.setString(4, pro.getProAddress());
			pre.setInt(5, pro.getProId());
			pre.executeUpdate();
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}
}
