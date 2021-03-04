package shop.mammastore.admin.aboard.ahelp.dao;

import static shop.mammastore.common.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import shop.mammastore.admin.vo.AhelpVo;
import shop.mammastore.common.Pagenation;

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

	// 관리자
	// 1:1 문의 목록 보기 리스트
	public ArrayList<AhelpVo> getHelpList(Pagenation pagenation, String query) {
		PreparedStatement pstmt = null;
		ResultSet rs = null; // DB의 결과문(쿼리값)을 받아와야함. 우선 빈값으로 설정하자.
		ArrayList<AhelpVo> list = new ArrayList<AhelpVo>();
		try {
			pstmt = con.prepareStatement(
					"select help_sq, sj, i.mber_sq, id, answer_fl, date_format(i1.dttm,'%Y-%m-%d %H:%i') as dttm, order_cd from inf_help_tb i1 inner join inf_mber_tb i on i1.mber_sq=i.mber_sq"
							+ query + " order by i1.dttm desc limit ?,?");
			pstmt.setInt(1, pagenation.getStartArticleNumber());
			pstmt.setInt(2, pagenation.getARTICLE_COUNT_PER_PAGE());
			rs = pstmt.executeQuery();
			while (rs.next()) { // 다음줄이 null(false) 될떄까지 반복실행
				AhelpVo ahelpVo = new AhelpVo();
				ahelpVo.setHelp_sq(rs.getInt("i1.help_sq"));
				ahelpVo.setSj(rs.getString("i1.sj"));
				ahelpVo.setMber_sq(rs.getInt("i.mber_sq"));
				ahelpVo.setId(rs.getString("i.id"));
				ahelpVo.setAnswer_fl(rs.getBoolean("i1.answer_fl"));
				ahelpVo.setDttm(rs.getString("dttm"));
				ahelpVo.setOrder_cd(rs.getString("i1.order_cd"));
				list.add(ahelpVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}

	// 1:1문의 상세보기
	public AhelpVo getHelpDetail(int help_sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null; // DB의 결과문(쿼리값)을 받아와야함. 우선 빈값으로 설정하자.
		AhelpVo ahelpVo = null;
		try {
			pstmt = con.prepareStatement(
					"select i1.mber_sq, i1.id, i.answer,i.help_sq, i.cntnt, i.sj, i.answer_fl, i.order_cd,"
							+ "date_format(i.dttm,'%Y-%m-%d %H:%i') as dttm, i1.phone, i1.email "
							+ "from inf_help_tb i LEFT JOIN inf_mber_tb i1 on i.mber_sq=i1.mber_sq "
							+ "where i.help_sq=?");
			pstmt.setInt(1, help_sq);
			rs = pstmt.executeQuery();
			while (rs.next()) { // 다음줄이 null(false) 될떄까지 반복실행
				ahelpVo = new AhelpVo();
				ahelpVo.setMber_sq(rs.getInt("i1.mber_sq"));
				ahelpVo.setId(rs.getString("i1.id"));
				ahelpVo.setAnswer(rs.getString("i.answer"));
				ahelpVo.setHelp_sq(rs.getInt("i.help_sq"));
				ahelpVo.setCntnt(rs.getString("i.cntnt"));
				ahelpVo.setSj(rs.getString("i.sj"));
				ahelpVo.setAnswer_fl(rs.getBoolean("i.answer_fl"));
				ahelpVo.setOrder_cd(rs.getString("i.order_cd"));
				ahelpVo.setDttm(rs.getString("dttm"));
				ahelpVo.setPhone(rs.getString("i1.phone"));
				ahelpVo.setEmail(rs.getString("i1.email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return ahelpVo;
	}

	// 답변 등록
	public int register(AhelpVo ahelpVo) {
		PreparedStatement pstmt = null; // 쿼리문 작성할 메소드
		int count = 0;
		try {
			pstmt = con
					.prepareStatement("update inf_help_tb set answer=?, answer_fl=true where help_sq=? and del_fl=0");
			pstmt.setString(1, ahelpVo.getAnswer());
			pstmt.setInt(2, ahelpVo.getHelp_sq());
			count = pstmt.executeUpdate(); // 데이터가 정확히 입력되었으면 카운트가 올라감.

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	// 1:1 문의 수정
	public int modify(AhelpVo ahelpVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con
					.prepareStatement("update inf_help_tb set sj=?, cntnt=? where help_sq=? and del_fl=0");
			pstmt.setString(1, ahelpVo.getSj());
			pstmt.setString(2, ahelpVo.getCntnt());
			pstmt.setInt(3, ahelpVo.getHelp_sq());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	// 1:1 문의 삭제
	public int deleteHelp(AhelpVo ahelpVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("delete from inf_help_tb where help_sq=? and del_fl=0");
			pstmt.setInt(1, ahelpVo.getHelp_sq());
			count = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	// 페이지네이션
	public int getHelpCount(String query) {
		PreparedStatement pstmt = null; // 쿼리문 작성할 메소드
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement(
					"select count(help_sq) from inf_help_tb i1 inner join inf_mber_tb i on i1.mber_sq=i.mber_sq"
							+ query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
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
}
