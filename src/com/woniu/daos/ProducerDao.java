package com.woniu.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.woniu.entity.GoodsType;
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
}
