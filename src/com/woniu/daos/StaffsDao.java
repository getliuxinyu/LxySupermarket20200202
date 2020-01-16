package com.woniu.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.woniu.entity.PageInfo;
import com.woniu.entity.Staffs;
import com.woniu.tools.ConnectionManager;

/**
 * 员工
 * @author Administrator
 *
 */
public class StaffsDao {
	/**员工登录
	 * 返回
	 * */
	public int isSuccess(String staffName, String staffPwd) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("SELECT * FROM staffs WHERE staff_name=? AND staff_pwd=? and staff_status='启用'");
			pre.setString(1, staffName);
			pre.setString(2, staffPwd);
			ResultSet rs = pre.executeQuery();
			if(rs.next()){
				int staffId = rs.getInt("staff_id");
				return staffId;
			}else{
				return 0;
			}
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}
	/**显示员工*/
	public List<Staffs> selectStaffs() throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("SELECT * FROM staffs");
			ResultSet rs = pre.executeQuery();
			List<Staffs> li = new ArrayList<>();
			while(rs.next()){
				int staffId = rs.getInt("staff_id");
				String staffCode = rs.getString("staff_code");
				String staffName = rs.getString("staff_name");
				String staffPwd = rs.getString("staff_pwd");
				String staffStatus = rs.getString("staff_status");
				String staffFunction = rs.getString("staff_function");
				String staffRemarks = rs.getString("staff_remarks");
				Staffs st = new Staffs(staffId, staffCode, staffName, staffPwd, staffStatus, staffFunction, staffRemarks);
				li.add(st);
			}
			return li;
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}
	/**原密码和旧密码比较*/
	public boolean isExist(String ciphertext, int staffId) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("select * from staffs where staff_id=? and staff_pwd=?");
			pre.setInt(1, staffId);
			pre.setString(2, ciphertext);
			ResultSet rs = pre.executeQuery();
			if(rs.next()){
				return true;
			}else{
				return false;
			}
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}
	/**更改密码
	 * @throws SQLException */
	public void updateStaffs(String newPwd, int staffId) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("update staffs set staff_pwd=? where staff_id=?");
			pre.setString(1, newPwd);
			pre.setInt(2, staffId);
			pre.executeUpdate();
		} finally{
			ConnectionManager.closeConnection(conn);
		}
		
	}
	/**确认
	 * @throws SQLException */
	public void updateById(int staffId) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("update staffs set staff_remarks='已确认' where staff_id=?");
			pre.setInt(1, staffId);
			pre.executeUpdate();
		} finally{
			ConnectionManager.closeConnection(conn);
		}
		
	}
	//重置密码
	public void resetPwd(int staffId) throws SQLException {
		
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("update staffs set staff_pwd='e10adc3949ba59abbe56e057f20f883e' where staff_id=?");
			pre.setInt(1, staffId);
			pre.executeUpdate();
		} finally{
			ConnectionManager.closeConnection(conn);
		}
		
	}
	
	/**启用*/
	public void activeStatus(int staffId, String staffStatus) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("update staffs set staff_status='启用' where staff_id=?");
		
			pre.setInt(1, staffId);
			pre.executeUpdate();
		} finally{
			ConnectionManager.closeConnection(conn);
		}
		
	}
	
	//增加操作
	public void staffsAdd(Staffs sta) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("insert into staffs(staff_code,staff_name,staff_pwd,staff_status,staff_function,staff_remarks)  values(?,?,'e10adc3949ba59abbe56e057f20f883e','启用',?,'未确认')");
			pre.setString(1, sta.getStaffCode());
			pre.setString(2, sta.getStaffName());
			pre.setString(3, sta.getStaffFunction());
			pre.executeUpdate();
		} finally{
			ConnectionManager.closeConnection(conn);
		}
		
	}
	/**修改页面展示
	 * @throws SQLException */
	public Staffs selectById(int staId) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("select * from staffs where staff_id=?");
			pre.setInt(1, staId);
			ResultSet rs = pre.executeQuery();
			Staffs st=null; 
			if(rs.next()){
				int staffId = rs.getInt("staff_id");
				String staffCode = rs.getString("staff_code");
				String staffName = rs.getString("staff_name");
				String staffFunction = rs.getString("staff_function");
				st = new Staffs(staffId, staffCode, staffName, staffFunction);
			}
			return st;
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}
	/**修改员工信息
	 * @throws SQLException */
	public void staffUpdate(Staffs st) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("update staffs set staff_code=?,staff_name=?,staff_function=? where staff_id=?");
			pre.setString(1, st.getStaffCode());
			pre.setString(2, st.getStaffName());
			pre.setString(3, st.getStaffFunction());
			pre.setInt(4, st.getStaffId());
			pre.executeUpdate();
		} finally{
			ConnectionManager.closeConnection(conn);
		}
		
	}
	/**停用*/
	public void InactiveStatus(int staffId, String staffStatus) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("update staffs set staff_status='停用' where staff_id=?");
		
			pre.setInt(1, staffId);
			pre.executeUpdate();
		} finally{
			ConnectionManager.closeConnection(conn);
		}
		
	}
	/**总条目数
	 * @throws SQLException */
	public int selectTotalCount(String staffName, String staffCode) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			String sql="select count(staff_id) counts from staffs where 1=1";
			if(staffName!=null&&!staffName.equals("")){
				sql=sql+" and staff_name like ?";
			}
			if(staffCode!=null&&!staffCode.equals("")){
				sql=sql+" and staff_code like ?";
			}
			PreparedStatement pre = conn.prepareStatement(sql);
			int count=0;
			if(staffName!=null&&!staffName.equals("")){
				count++;
				pre.setString(count, "%"+staffName+"%");
			}
			if(staffCode!=null&&!staffCode.equals("")){
				count++;
				pre.setString(count, "%"+staffCode+"%");
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
	//查找符合分页调价和查询条件的信息
	public List<Staffs> selectStaffs(PageInfo<Staffs> pageInfo, String staffName, String staffCode) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		
		try {
			String sql="select * from staffs where 1=1";
			if(staffName!=null&&!staffName.equals("")){
				sql=sql+" and staff_name like ? ";
			}
			if(staffCode!=null&&!staffCode.equals("")){
				sql=sql+" and staff_code like ? ";
			}
			sql=sql+" limit ?,?";
			PreparedStatement pre = conn.prepareStatement(sql);
			int count=0;
			if(staffName!=null&&!staffName.equals("")){
				count++;
				pre.setString(count, "%"+staffName+"%");
			}
			if(staffCode!=null&&!staffCode.equals("")){
				count++;
				pre.setString(count, "%"+staffCode+"%");
			}
			
			pre.setInt(count+1, (pageInfo.getCurrentPage()-1)*pageInfo.getPageSize());
			pre.setInt(count+2, pageInfo.getPageSize());
			ResultSet rs = pre.executeQuery();
			List<Staffs> li = new ArrayList<>();
			while(rs.next()){
				int staffId = rs.getInt("staff_id");
				String staffsCode = rs.getString("staff_code");
				String staffsName = rs.getString("staff_name");
				String staffPwd = rs.getString("staff_pwd");
				String staffStatus = rs.getString("staff_status");
				String staffFunction = rs.getString("staff_function");
				String staffRemarks = rs.getString("staff_remarks");
				Staffs st = new Staffs(staffId, staffsCode, staffsName, staffPwd, staffStatus, staffFunction, staffRemarks);
				li.add(st);
			}
			return li;
		
		} finally{
			ConnectionManager.closeConnection(conn);
		}
		
	}



}
