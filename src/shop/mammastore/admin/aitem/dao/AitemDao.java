package shop.mammastore.admin.aitem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import shop.mammastore.admin.vo.AitemVo;
import shop.mammastore.admin.vo.AmanagerVo;

import static shop.mammastore.common.JdbcUtil.close;

public class AitemDao {

	private Connection con;

	private AitemDao() {

	}

	private static class LazyHolder {
		private static final AitemDao INSTANCE = new AitemDao();
	}

	public static AitemDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	public int register(AitemVo aitemVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("insert into inf_itm_tb (nm,pc,stock,cntnt) values(?,?,?,?)");
			pstmt.setString(1, aitemVo.getNm());
			pstmt.setInt(2, aitemVo.getPc());
			pstmt.setInt(3, aitemVo.getStock());
			pstmt.setString(4, aitemVo.getCntnt());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
}
