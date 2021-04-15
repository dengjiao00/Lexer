package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.Output;
import utils.DBUtils;

public class OutputDao {
	// 向数据库中添加信息的方法addOutput()
			public void addOutput(Output output) throws Exception {
				Connection conn = null;
				PreparedStatement pstm = null;
				try {
					conn = DBUtils.getConnection();
					String sql = "Insert into output(name_out) values(?) ";
					pstm = conn.prepareStatement(sql);
					pstm.setString(1, output.getName_out());
					pstm.executeUpdate();
				}catch (Exception e) {
					e.printStackTrace();
				} 
				finally {
					DBUtils.free(null, pstm, conn);
				}
			}
	//根据id查询output的方法findOut()
	public Output findOut(String name_out) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Output output = null;
		try {
			conn = DBUtils.getConnection();
			String sql = "select * from output where name_out= ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,name_out);
			rs = ps.executeQuery();
			if (rs.next()) {
				output = new Output();
				output.setId(rs.getInt(1));
				output.setName_out(rs.getString(2));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}  
		finally {
			DBUtils.free(rs, ps, conn);
		}
		return output;
	}
	//查询全部output的方法QueryAll()
	public ArrayList<Output> QueryAll() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Output> inputs = new ArrayList<Output>();
		try {
			conn = DBUtils.getConnection();
			String sql = "select * from output";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Output output = new Output();
				output.setId(rs.getInt(1));
				output.setName_out(rs.getString(2));
				inputs.add(output);
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
