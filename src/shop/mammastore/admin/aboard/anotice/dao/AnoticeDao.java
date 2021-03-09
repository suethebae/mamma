package shop.mammastore.admin.aboard.anotice.dao;

import static shop.mammastore.common.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import shop.mammastore.admin.vo.AnoticeVo;
import shop.mammastore.common.Pagenation;

public class AnoticeDao {

	private Connection con;

	private AnoticeDao() {

	}

	private static class LazyHolder {
		private static final AnoticeDao INSTANCE = new AnoticeDao();
	}

	public static AnoticeDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	// 공지사항 게시글 등록
	public int register(AnoticeVo anoticeVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("insert into inf_ntbd_tb (sj,cntnt,mngr_sq,notice_sq) values(?,?,?,?)");
			pstmt.setString(1, anoticeVo.getSj());
			pstmt.setString(2, anoticeVo.getCntnt());
			pstmt.setInt(3, anoticeVo.getMngr_sq());
			pstmt.setInt(4, anoticeVo.getNotice_sq());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	// 공지사항 목록 불러오기
	public ArrayList<AnoticeVo> getNoticeList(Pagenation pagenation) {
		PreparedStatement pstmt = null;
		ResultSet rs = null; // DB의 결과문(쿼리값)을 받아와야함. 우선 빈값으로 설정하자.
		ArrayList<AnoticeVo> list = new ArrayList<AnoticeVo>();
		try {
			pstmt = con.prepareStatement("select mngr_sq, notice_sq, cntnt, sj, date_format(dttm,'%Y-%m-%d %H:%i') as dttm from inf_ntbd_tb where del_fl=0 limit ?,?");
			pstmt.setInt(1, pagenation.getStartArticleNumber());
			pstmt.setInt(2, pagenation.getARTICLE_COUNT_PER_PAGE());
			rs = pstmt.executeQuery();
			while (rs.next()) { // 다음줄이 null(false) 될떄까지 반복실행
				AnoticeVo anoticeVo = new AnoticeVo();
				anoticeVo.setMngr_sq(rs.getInt("mngr_sq"));
				anoticeVo.setNotice_sq(rs.getInt("notice_sq"));
				anoticeVo.setSj(rs.getString("sj"));
				anoticeVo.setDttm(rs.getString("dttm"));
				list.add(anoticeVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	// 공지사항 내용 가져오기
	public AnoticeVo getNoticeDetail(int notice_sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null; // DB의 결과문(쿼리값)을 받아와야함. 우선 빈값으로 설정하자.
		AnoticeVo anoticeVo = null;
		try {
			pstmt = con.prepareStatement("select notice_sq, mngr_sq, sj, date_format(dttm,'%Y-%m-%d %H:%i') as dttm, cntnt from inf_ntbd_tb where notice_sq=? and del_fl=0");
			pstmt.setInt(1, notice_sq);
			rs = pstmt.executeQuery();
			while (rs.next()) { // 다음줄이 null(false) 될떄까지 반복실행
				anoticeVo = new AnoticeVo();
				anoticeVo.setNotice_sq(rs.getInt("notice_sq"));
				anoticeVo.setMngr_sq(rs.getInt("mngr_sq"));
				anoticeVo.setSj(rs.getString("sj"));
				anoticeVo.setDttm(rs.getString("dttm"));
				anoticeVo.setCntnt(rs.getString("cntnt"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return anoticeVo;
	}

	// 공지사항 수정
	public int modify(AnoticeVo anoticeVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con
					.prepareStatement("update inf_ntbd_tb set sj=?, cntnt=?, Mngr_sq=? where notice_sq=? and del_fl=0");
			pstmt.setString(1, anoticeVo.getSj());
			pstmt.setString(2, anoticeVo.getCntnt());
			pstmt.setInt(3, anoticeVo.getMngr_sq());
			pstmt.setInt(4, anoticeVo.getNotice_sq());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	// 공지사항 게시글 삭제
	public int deleteNotice(AnoticeVo anoticeVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("update inf_ntbd_tb set del_fl=1 where notice_sq=? and del_fl=0");
			pstmt.setInt(1, anoticeVo.getNotice_sq());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	// 페이지네이션
	public int getNoticeCount() {
		PreparedStatement pstmt = null; // 쿼리문 작성할 메소드
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("select count(notice_sq) from inf_ntbd_tb where del_fl=0");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return count;
	}
}
