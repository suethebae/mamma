package shop.mammastore.admin.amanager.dao;

import static shop.mammastore.common.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import shop.mammastore.admin.vo.AmanagerVo;

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

	// 최고관리자 있는지 여부 확인
	public int isSManager() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("select count(*) from inf_mngr_tb where author=1 and del_fl=0");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return count;
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

	// 최고관리자 등록
	public int asregister(AmanagerVo amanagerVo) {
		PreparedStatement pstmt = null; // 쿼리문 작성할 메소드
		int count = 0;
		try {
			pstmt = con.prepareStatement(
					"insert into inf_mngr_tb(id, pwd, nm, email, phone, author) values(?, ?, ?, ?, ?, 1)");
			pstmt.setString(1, amanagerVo.getId());
			pstmt.setString(2, amanagerVo.getPwd());
			pstmt.setString(3, amanagerVo.getNm());
			pstmt.setString(4, amanagerVo.getEmail());
			pstmt.setString(5, amanagerVo.getPhone());
			count = pstmt.executeUpdate(); // 데이터가 정확히 입력되었으면 카운트가 올라감.

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	//관리자 목록 가져오기
	public ArrayList<AmanagerVo> getMngrList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null; // DB의 결과문(쿼리값)을 받아와야함. 우선 빈값으로 설정하자.
		ArrayList<AmanagerVo> list = new ArrayList<AmanagerVo>();
		try {
			pstmt = con.prepareStatement("select * from inf_mngr_tb where del_fl=0");
			rs = pstmt.executeQuery();
			while (rs.next()) { // 다음줄이 null(false) 될떄까지 반복실행
				AmanagerVo amanagerVo = new AmanagerVo();
				amanagerVo.setMngr_sq(rs.getInt("mngr_sq"));
				amanagerVo.setAuthor(rs.getBoolean("author"));
				amanagerVo.setDttm(rs.getString("dttm"));
				amanagerVo.setId(rs.getString("id"));
				amanagerVo.setNm(rs.getString("nm"));
				amanagerVo.setEmail(rs.getString("email"));
				amanagerVo.setPhone(rs.getString("phone"));
				list.add(amanagerVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}

	// 관리자 회원가입
	public int aregister(AmanagerVo amanagerVo) {
		PreparedStatement pstmt = null; // 쿼리문 작성할 메소드
		int count = 0;
		try {
			pstmt = con.prepareStatement("insert into inf_mngr_tb(id, pwd, nm, email, phone) values(?, ?, ?, ?, ?)");
			pstmt.setString(1, amanagerVo.getId());
			pstmt.setString(2, amanagerVo.getPwd());
			pstmt.setString(3, amanagerVo.getNm());
			pstmt.setString(4, amanagerVo.getEmail());
			pstmt.setString(5, amanagerVo.getPhone());
			count = pstmt.executeUpdate(); // 데이터가 정확히 입력되었으면 카운트가 올라감.

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	// 회원정보 수정 이동
	public AmanagerVo getDetailMngr(int mngr_sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null; // DB의 결과문(쿼리값)을 받아와야함. 우선 빈값으로 설정하자.
		AmanagerVo amanagerVo = null;
		try {
			pstmt = con.prepareStatement("select * from inf_mngr_tb where mngr_sq=? and del_fl=0");
			pstmt.setInt(1, mngr_sq);
			;
			rs = pstmt.executeQuery();
			while (rs.next()) { // 다음줄이 null(false) 될떄까지 반복실행
				amanagerVo = new AmanagerVo();
				amanagerVo.setMngr_sq(rs.getInt("mngr_sq"));
				amanagerVo.setId(rs.getString("id"));
				amanagerVo.setAuthor(rs.getBoolean("author"));
				amanagerVo.setDttm(rs.getString("dttm"));
				amanagerVo.setEmail(rs.getString("email"));
				amanagerVo.setNm(rs.getString("nm"));
				amanagerVo.setPhone(rs.getString("phone"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return amanagerVo;
	}

	// 관리자 회원 정보 수정 절차//////
	public int modify(AmanagerVo amanagerVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			if (amanagerVo.getPwd() == null || amanagerVo.getPwd().equals("")) {
				pstmt = con
						.prepareStatement("update inf_mngr_tb set email=?, phone=?, nm=? where mngr_sq=? and del_fl=0");
				pstmt.setString(1, amanagerVo.getEmail());
				pstmt.setString(2, amanagerVo.getPhone());
				pstmt.setString(3, amanagerVo.getNm());
				pstmt.setInt(4, amanagerVo.getMngr_sq());
			} else {
				pstmt = con.prepareStatement(
						"update inf_mngr_tb set pwd=?, email=?, phone=?,nm=? where mngr_sq=? and del_fl=0");
				pstmt.setString(1, amanagerVo.getPwd());
				pstmt.setString(2, amanagerVo.getEmail());
				pstmt.setString(3, amanagerVo.getPhone());
				pstmt.setString(4, amanagerVo.getNm());
				pstmt.setInt(5, amanagerVo.getMngr_sq());
			}
			count = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	// 관리자 회원 탈퇴
	public int leave(AmanagerVo amanagerVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("update inf_mngr_tb set del_fl=1 where mngr_sq=? and del_fl=0");
			pstmt.setInt(1, amanagerVo.getMngr_sq());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
}
