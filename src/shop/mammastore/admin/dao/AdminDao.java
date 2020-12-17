package shop.mammastore.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import shop.mammastore.admin.vo.AdminVo;

import static shop.mammastore.common.JdbcUtil.close;

public class AdminDao {

	private Connection con;

	private AdminDao() {

	}

	private static class LazyHolder {
		private static final AdminDao INSTANCE = new AdminDao();
	}

	public static AdminDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	public AdminVo getLoginInfo(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null; // DB의 결과문(쿼리값)을 받아와야함. 우선 빈값으로 설정하자.
		AdminVo adminVo = null;
		try {
			pstmt = con.prepareStatement("select admin_sq, id, pwd from inf_admin_tb where id=? and del_fl=0");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) { // 다음줄이 null(false) 될떄까지 반복실행
				adminVo = new AdminVo();
				adminVo.setAdmin_sq(rs.getInt("admin_sq"));
				adminVo.setId(rs.getString("id"));
				adminVo.setPwd(rs.getString("pwd"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return adminVo;
	}
}
