package shop.mammastore.admin.amember.dao;

import static shop.mammastore.common.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import shop.mammastore.admin.vo.AmemberVo;

public class AmemberDao {

	private Connection con;

	private AmemberDao() {

	}

	private static class LazyHolder {
		private static final AmemberDao INSTANCE = new AmemberDao();
	}

	public static AmemberDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	/*
	 * // 매니저 로그인 정보 public AmemberVo getLoginInfo(String id) { PreparedStatement
	 * pstmt = null; ResultSet rs = null; // DB의 결과문(쿼리값)을 받아와야함. 우선 빈값으로 설정하자.
	 * AmemberVo amemberVo = null; try { pstmt = con.
	 * prepareStatement("select mngr_sq, id, pwd from inf_mngr_tb where id=? and del_fl=0"
	 * ); pstmt.setString(1, id); rs = pstmt.executeQuery(); while (rs.next()) { //
	 * 다음줄이 null(false) 될떄까지 반복실행 amemberVo = new AmemberVo();
	 * amemberVo.setMngr_sq(rs.getInt("mngr_sq"));
	 * amemberVo.setId(rs.getString("id")); amemberVo.setPwd(rs.getString("pwd")); }
	 * } catch (Exception e) { e.printStackTrace(); } finally { close(pstmt);
	 * close(rs); } return amemberVo; }
	 */

	// 매니저가 회원 목록 가져오기
	public ArrayList<AmemberVo> getMberList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null; // DB의 결과문(쿼리값)을 받아와야함. 우선 빈값으로 설정하자.
		ArrayList<AmemberVo> list = new ArrayList<AmemberVo>();
		try {
			pstmt = con.prepareStatement("select * from inf_mber_tb where del_fl=0");
			rs = pstmt.executeQuery();
			while (rs.next()) { // 다음줄이 null(false) 될떄까지 반복실행
				AmemberVo amemberVo = new AmemberVo();
				amemberVo.setMber_sq(rs.getInt("mber_sq"));
				amemberVo.setId(rs.getString("id"));
				amemberVo.setNm(rs.getString("nm"));
				amemberVo.setPhone(rs.getString("phone"));
				amemberVo.setEmail(rs.getString("email"));
				amemberVo.setDttm(rs.getString("dttm"));
				list.add(amemberVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}

	// 매니저가 회원 상세정보 불러오기
	public AmemberVo getAmemberDetail(int mber_sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AmemberVo amemberVo = null;
		try {
			pstmt = con.prepareStatement("select * from inf_mber_tb where mber_sq=? and del_fl=0");
			pstmt.setInt(1, mber_sq);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				amemberVo = new AmemberVo();
				amemberVo.setMber_sq(rs.getInt("mber_sq"));
				amemberVo.setId(rs.getString("id"));
				amemberVo.setNm(rs.getString("nm"));
				amemberVo.setPhone(rs.getString("phone"));
				amemberVo.setEmail(rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return amemberVo;
	}


	// 매니저가 회원정보 수정
	public int modify(AmemberVo amemberVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("update inf_mber_tb set nm=?, email=?, phone=? where mber_sq=? and del_fl=0");
			pstmt.setString(1, amemberVo.getNm());
			pstmt.setString(2, amemberVo.getEmail());
			pstmt.setString(3, amemberVo.getPhone());
			pstmt.setInt(4, amemberVo.getMber_sq());
			count = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	// 매니저가 회원정보 삭제
	public int deleteMember(AmemberVo amemberVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {									
				pstmt = con.prepareStatement("update inf_mber_tb set del_fl=1 where mber_sq=?"); 
				pstmt.setInt(1, amemberVo.getMber_sq());
				count = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	

}
