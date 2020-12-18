package shop.mammastore.admin.amanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import shop.mammastore.admin.vo.AmanagerVo;

import static shop.mammastore.common.JdbcUtil.close;

public class AmanagerDao {

	private Connection con;

	private AmanagerDao() {

	}

	private static class LazyHolder {
		private static final AmanagerDao INSTANCE = new AmanagerDao();
	}

	public static AmanagerDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	public AmanagerVo getLoginInfo(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null; // DB의 결과문(쿼리값)을 받아와야함. 우선 빈값으로 설정하자.
		AmanagerVo amanagerVo = null;
		try {
			pstmt = con.prepareStatement("select mngr_sq, id, pwd from inf_mngr_tb where id=? and del_fl=0");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) { // 다음줄이 null(false) 될떄까지 반복실행
				amanagerVo = new AmanagerVo();
				amanagerVo.setMngr_sq(rs.getInt("mngr_sq"));
				amanagerVo.setId(rs.getString("id"));
				amanagerVo.setPwd(rs.getString("pwd"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return amanagerVo;
	}
}
