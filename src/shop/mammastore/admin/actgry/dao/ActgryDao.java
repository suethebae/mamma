package shop.mammastore.admin.actgry.dao;

import static shop.mammastore.common.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import shop.mammastore.admin.vo.ActgryVo;
import shop.mammastore.admin.vo.AmanagerVo;
import shop.mammastore.mamma.vo.MemberVo;

public class ActgryDao {

	private Connection con;

	private ActgryDao() {

	}

	private static class LazyHolder {
		private static final ActgryDao INSTANCE = new ActgryDao();
	}

	public static ActgryDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	// 카테고리 등록
	public int register(ActgryVo actgryVo) {
		PreparedStatement pstmt = null; // 쿼리문 작성할 메소드
		int count = 0;
		try {
			pstmt = con.prepareStatement("insert into inf_ctgry_tb(nm) values(?)");
			pstmt.setString(1, actgryVo.getNm());
			count = pstmt.executeUpdate(); // 데이터가 정확히 입력되었으면 카운트가 올라감.

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	// 카테고리 목록 가져오기
	public ArrayList<ActgryVo> getCtgryList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null; // DB의 결과문(쿼리값)을 받아와야함. 우선 빈값으로 설정하자.
		ArrayList<ActgryVo> list = new ArrayList<ActgryVo>();
		try {
			pstmt = con.prepareStatement("select ctgry_sq,nm from inf_ctgry_tb where del_fl=0");
			rs = pstmt.executeQuery();
			while (rs.next()) { // 다음줄이 null(false) 될떄까지 반복실행
				ActgryVo actgryVo = new ActgryVo();
				actgryVo.setCtgry_sq(rs.getInt("ctgry_sq"));
				actgryVo.setNm(rs.getString("nm"));
				list.add(actgryVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}

	// 카테고리 삭제
	public int delete(ActgryVo actgryVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("update inf_ctgry_tb set del_fl=1 where ctgry_sq=? and del_fl=0");
			pstmt.setInt(1, actgryVo.getCtgry_sq());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	// 카테고리 수정 이동
	public ActgryVo detail(int ctgry_sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null; // DB의 결과문(쿼리값)을 받아와야함. 우선 빈값으로 설정하자.
		ActgryVo actgryVo = null;
		try {
			pstmt = con.prepareStatement("select * from inf_ctgry_tb where ctgry_sq=? and del_fl=0");
			pstmt.setInt(1, ctgry_sq);
			;
			rs = pstmt.executeQuery();
			while (rs.next()) { // 다음줄이 null(false) 될떄까지 반복실행
				actgryVo = new ActgryVo();
				actgryVo.setCtgry_sq(rs.getInt("ctgry_sq"));
				actgryVo.setNm(rs.getString("nm"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return actgryVo;
	}

	// 카테고리 수정 진행
	public int modify(ActgryVo actgryVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("update inf_ctgry_tb set nm=? where ctgry_sq=? and del_fl=0");
			pstmt.setString(1, actgryVo.getNm());	
			pstmt.setInt(2, actgryVo.getCtgry_sq());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

}
