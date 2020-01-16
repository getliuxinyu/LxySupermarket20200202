package com.woniu.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.woniu.entity.GoodsType;
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
}
