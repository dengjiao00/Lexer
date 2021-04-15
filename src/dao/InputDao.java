package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.Input;
import utils.DBUtils;

public class InputDao {
	//根据id查询input的方法findIn()
	public Input findIn(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Input input = null;
		try {
			conn = DBUtils.getConnection();
			String sql = "select * from input where id=? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				input = new Input();
				input.setId(rs.getInt(1));
				input.setName_in(rs.getString(2));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}  
		finally {
			DBUtils.free(rs, ps, conn);
		}
		return input;
	}
	//查询全部input的方法QueryAll()
	public ArrayList<Input> QueryAll() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Input> inputs = new ArrayList<Input>();
		try {
			conn = DBUtils.getConnection();
			String sql = "select * from input";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Input input = new Input();
				input.setId(rs.getInt(1));
				input.setName_in(rs.getString(2));
				inputs.add(input);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			DBUtils.free(rs, ps, conn);
		}
		return inputs;
	}
}
