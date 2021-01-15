package shop.mammastore.mamma.board.help.dao;

import static shop.mammastore.common.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import shop.mammastore.mamma.vo.HelpVo;

public class HelpDao {

	private Connection con;

	private HelpDao() {

	}

	private static class LazyHolder {
		private static final HelpDao INSTANCE = new HelpDao();
	}

	public static HelpDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	// 글 등록
	public int register(HelpVo helpVo) {
		PreparedStatement pstmt = null; // 쿼리문 작성할 메소드
		int count = 0;
		try {
			pstmt = con.prepareStatement(
					"insert into inf_help_tb(mber_sq, sj, cntnt, order_cd, answer) values(?, ?, ?, ?,'')");
			pstmt.setInt(1, helpVo.getMber_sq());
			pstmt.setString(2, helpVo.getSj());
			pstmt.setString(3, helpVo.getCntnt());
			pstmt.setString(4, helpVo.getOrder_cd());
			count = pstmt.executeUpdate(); // 데이터가 정확히 입력되었으면 카운트가 올라감.

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	// 1:1 문의 리스트
	public ArrayList<HelpVo> getHelpList(int mber_sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null; // DB의 결과문(쿼리값)을 받아와야함. 우선 빈값으로 설정하자.
		ArrayList<HelpVo> list = new ArrayList<HelpVo>();
		try {
			pstmt = con.prepareStatement(
					"select help_sq, sj, i1.dttm, i.mber_sq, id from inf_help_tb i1 left join inf_mber_tb i on i1.mber_sq=i.mber_sq where i.mber_sq=?");
			pstmt.setInt(1, mber_sq);
			rs = pstmt.executeQuery();

			while (rs.next()) { // 다음줄이 null(false) 될떄까지 반복실행
				HelpVo helpVo = new HelpVo();
				helpVo.setHelp_sq(rs.getInt("i1.help_sq"));
				helpVo.setSj(rs.getString("i1.sj"));
				helpVo.setMber_sq(rs.getInt("i.mber_sq"));
				helpVo.setId(rs.getString("i.id"));
				helpVo.setDttm(rs.getString("i1.dttm"));
				list.add(helpVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}

	// 1:1 문의 상세보기
	public HelpVo getHelpDetail(int help_sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null; // DB의 결과문(쿼리값)을 받아와야함. 우선 빈값으로 설정하자.
		HelpVo helpVo = null;
		try {
			pstmt = con.prepareStatement(
					"select * from inf_help_tb i LEFT JOIN inf_mber_tb i1 on i.mber_sq=i1.mber_sq where i.help_sq=?");
			pstmt.setInt(1, help_sq);
			rs = pstmt.executeQuery();
			while (rs.next()) { // 다음줄이 null(false) 될떄까지 반복실행
				helpVo = new HelpVo();
				helpVo.setHelp_sq(rs.getInt("i.help_sq"));
				helpVo.setMber_sq(rs.getInt("i1.mber_sq"));
				helpVo.setId(rs.getString("i1.id"));
				helpVo.setAnswer(rs.getString("i.answer"));
				helpVo.setCntnt(rs.getString("i.cntnt"));
				helpVo.setSj(rs.getString("i.sj"));
				helpVo.setDttm(rs.getString("i.dttm"));
				helpVo.setPhone(rs.getString("i1.phone"));
				helpVo.setEmail(rs.getString("i1.email"));
				helpVo.setOrder_cd(rs.getString("i.order_cd"));
				helpVo.setAnswer_fl(rs.getBoolean("i.answer_fl"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return helpVo;
	}

	// 1:1 문의 수정
	public int modify(HelpVo helpVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con
					.prepareStatement("update inf_help_tb set sj=?, cntnt=?, order_cd=? where help_sq=? and del_fl=0");
			pstmt.setString(1, helpVo.getSj());
			pstmt.setString(2, helpVo.getCntnt());
			pstmt.setString(3, helpVo.getOrder_cd());
			pstmt.setInt(4, helpVo.getHelp_sq());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	// 1:1 문의 삭제
	public int deleteHelp(HelpVo helpVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("delete from inf_help_tb where help_sq=? and del_fl=0");
			pstmt.setInt(1, helpVo.getHelp_sq());
			count = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	// 주문코드
	public ArrayList<HelpVo> getOrderCode(int mber_sq) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null; // DB의 결과문(쿼리값)을 받아와야함. 우선 빈값으로 설정하자.
		ArrayList<HelpVo> list = new ArrayList<HelpVo>();
		try {
			pstmt = con.prepareStatement("select order_code from inf_order_tb WHERE mber_sq=?");
			pstmt.setInt(1, mber_sq);
			rs = pstmt.executeQuery();
			while (rs.next()) { // 다음줄이 null(false) 될떄까지 반복실행
				HelpVo helpVo = new HelpVo();
				helpVo.setOrder_cd(rs.getString("order_code"));
				list.add(helpVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}
}
