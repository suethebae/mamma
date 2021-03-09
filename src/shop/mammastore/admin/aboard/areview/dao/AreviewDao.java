package shop.mammastore.admin.aboard.areview.dao;

import static shop.mammastore.common.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import shop.mammastore.admin.vo.AreviewNameVo;
import shop.mammastore.admin.vo.AreviewVo;

public class AreviewDao {

	private Connection con;

	private AreviewDao() {

	}

	private static class LazyHolder {
		private static final AreviewDao INSTANCE = new AreviewDao();
	}

	public static AreviewDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	// 리뷰 목록 불러오기
	public ArrayList<AreviewNameVo> getReviewList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null; // DB의 결과문(쿼리값)을 받아와야함. 우선 빈값으로 설정하자.
		ArrayList<AreviewNameVo> list = new ArrayList<AreviewNameVo>();
		try {
			pstmt = con.prepareStatement("select * from inf_review_tb i "
					+ "LEFT JOIN inf_mber_tb i1 "
					+ "ON i.mber_sq=i1.mber_sq "
					+ "where i.del_fl=0");
			rs = pstmt.executeQuery();
			while (rs.next()) { // 다음줄이 null(false) 될떄까지 반복실행
				AreviewNameVo areviewVo = new AreviewNameVo();
				areviewVo.setMber_sq(rs.getInt("i1.mber_sq"));
				areviewVo.setItm_sq(rs.getInt("i.itm_sq"));
				areviewVo.setId(rs.getString("i1.id")); // mber_tb 조인
				areviewVo.setReview_sq(rs.getInt("i.review_sq"));
				areviewVo.setSj(rs.getString("i.sj"));
				areviewVo.setDttm(rs.getString("i.dttm"));
				list.add(areviewVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	// 리뷰 내용 가져오기
	public AreviewVo getReviewDetail(int review_sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null; // DB의 결과문(쿼리값)을 받아와야함. 우선 빈값으로 설정하자.
		AreviewVo areviewVo = null;
		try {
			pstmt = con.prepareStatement("select * from inf_review_tb where review_sq=? and del_fl=0");
			pstmt.setInt(1, review_sq);
			rs = pstmt.executeQuery();
			while (rs.next()) { // 다음줄이 null(false) 될떄까지 반복실행
				areviewVo = new AreviewVo();
				areviewVo.setReview_sq(rs.getInt("review_sq"));
				areviewVo.setMngr_sq(rs.getInt("mber_sq"));
				areviewVo.setSj(rs.getString("sj"));
				areviewVo.setDttm(rs.getString("dttm"));
				areviewVo.setCntnt(rs.getString("cntnt"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return areviewVo;
	}

	// 리뷰 게시글 삭제
	public int deleteReview(AreviewVo areviewVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("update inf_review_tb set del_fl=1 where review_sq=? and del_fl=0");
			pstmt.setInt(1, areviewVo.getReview_sq());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

}
