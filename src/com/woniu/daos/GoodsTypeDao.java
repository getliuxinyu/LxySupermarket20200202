package com.woniu.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.woniu.entity.GoodsType;
import com.woniu.entity.PageInfo;
import com.woniu.tools.ConnectionManager;

/**
 * 商品类型
 * @author Administrator
 *
 */
public class GoodsTypeDao {
	public List<GoodsType> selectGoodsType() throws SQLException{
		Connection conn = ConnectionManager.getConnections();
		try {
			PreparedStatement pre = conn.prepareStatement("select * from goodstype");
			ResultSet rs = pre.executeQuery();
			List<GoodsType> li = new ArrayList<GoodsType>();
			while(rs.next()){
				int gsId = rs.getInt("goodstype_id");
				String gsCode = rs.getString("goodstype_code");
				String gsName = rs.getString("goodstype_name");
				String gsRemarks = rs.getString("goodstype_remarks");
				GoodsType gt = new GoodsType(gsId, gsCode, gsName, gsRemarks);
				li.add(gt);
			}
			return li;
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}

	public int getTotalCount(String goodsTypeCode, String goodsTypeName) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			String sql = "select count(goodstype_id) counts from goodstype where 1=1";
			if(goodsTypeCode!=null&&!goodsTypeCode.equals("")){
				sql = sql + " and goodstype_code like ?";
			}
			if(goodsTypeName!=null&&!goodsTypeName.equals("")){
				sql = sql + " and goodstype_name like ?";
			}
			PreparedStatement pre = conn.prepareStatement(sql);
			int count =0;
			if(goodsTypeCode!=null&&!goodsTypeCode.equals("")){
				count++;
				pre.setString(count, goodsTypeCode);
			}
			if(goodsTypeName!=null&&!goodsTypeName.equals("")){
				count++;
				pre.setString(count, goodsTypeName);
			}
			ResultSet re = pre.executeQuery();
			int totalCount=0;
			if(re.next()){
				totalCount = re.getInt("counts");
			}
			return totalCount;
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}

	public List<GoodsType> selectGoodsType(String goodsTypeCode, String goodsTypeName, PageInfo<GoodsType> pageInfo) throws SQLException {
		Connection conn = ConnectionManager.getConnections();
		try {
			String sql = "select * from goodstype where 1=1";
			if(goodsTypeCode!=null&&!goodsTypeCode.equals("")){
				sql = sql + " and goodstype_code like ?";
			}
			if(goodsTypeName!=null&&!goodsTypeName.equals("")){
				sql = sql + " and goodstype_name like ?";
			}
			sql = sql + " limit ?,?";
			PreparedStatement pre = conn.prepareStatement(sql);
			int count =0;
			if(goodsTypeCode!=null&&!goodsTypeCode.equals("")){
				count++;
				pre.setString(count, "%"+goodsTypeCode+"%");
			}
			if(goodsTypeName!=null&&!goodsTypeName.equals("")){
				count++;
				pre.setString(count, "%"+goodsTypeName+"%");
			}
			
			pre.setInt(count+1, (pageInfo.getCurrentPage()-1)*pageInfo.getPageSize());
			pre.setInt(count+2, pageInfo.getPageSize());
			ResultSet re = pre.executeQuery();
			List<GoodsType> li = new ArrayList<>();
			while(re.next()){
				int gsId = re.getInt("goodstype_id");
				String gsCode = re.getString("goodstype_code");
				String gsName = re.getString("goodstype_name");
				String gsRemarks = re.getString("goodstype_remarks");
				GoodsType gt = new GoodsType(gsId, gsCode, gsName, gsRemarks);
				li.add(gt);
			}
			return li;
		} finally{
			ConnectionManager.closeConnection(conn);
		}
	}
}
