package shop.mammastore.mamma.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import shop.mammastore.common.BCrypt;
import shop.mammastore.mamma.vo.MemberVo;

import static shop.mammastore.common.JdbcUtil.close;

public class MemberDao {

	private Connection con;

	private MemberDao() {

	}

	private static class LazyHolder {
		private static final MemberDao INSTANCE = new MemberDao();
	}

	public static MemberDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	public int register(MemberVo vo) {
		PreparedStatement pstmt = null; // 쿼리문 작성할 메소드
		int count = 0;
		try {
			pstmt = con.prepareStatement(
					"insert into inf_mber_tb(id, pwd, name, email, phone, magre_fl, pagre_fl) values(?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getPhone());
			pstmt.setBoolean(6, vo.isMagre_fl());
			pstmt.setBoolean(7, vo.isPagre_fl());

			count = pstmt.executeUpdate(); // 데이터가 정확히 입력되었으면 카운트가 올라감.

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
}
