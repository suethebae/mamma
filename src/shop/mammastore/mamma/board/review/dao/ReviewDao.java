package shop.mammastore.mamma.board.review.dao;

import static shop.mammastore.common.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import shop.mammastore.common.Pagenation;
import shop.mammastore.common.Parser;
import shop.mammastore.mamma.vo.ReviewNameVo;
import shop.mammastore.mamma.vo.ReviewVo;

public class ReviewDao {

	private Connection con;

	private ReviewDao() {

	}

	private static class LazyHolder {
		private static final ReviewDao INSTANCE = new ReviewDao();
	}

	public static ReviewDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	// 리뷰 글 등록
	public int register(ReviewVo reviewVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement(
					"insert into inf_review_tb (sj,cntnt,mber_sq,itm_sq,order_cd,evl) values(?,?,?,?,?,?)");
			pstmt.setString(1, reviewVo.getSj());
			pstmt.setString(2, reviewVo.getCntnt());
			pstmt.setInt(3, reviewVo.getMber_sq());
			pstmt.setInt(4, reviewVo.getItm_sq());
			pstmt.setString(5, reviewVo.getOrder_cd());
			pstmt.setInt(6, reviewVo.getEvl());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	// 리뷰 목록 불러오기
	public ArrayList<ReviewVo> getReviewList(int mber_sq, Pagenation pagenation) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ReviewVo> list = new ArrayList<ReviewVo>();
		try {
			pstmt = con.prepareStatement(
					"select review_sq, itm_sq, order_cd, sj, cntnt, date_format(dttm,'%Y-%m-%d %H:%i') as dttm, evl from inf_review_tb WHERE mber_sq=? and del_fl=0 limit ?,?");
			pstmt.setInt(1, mber_sq);
			pstmt.setInt(2, pagenation.getStartArticleNumber());
			pstmt.setInt(3, pagenation.getARTICLE_COUNT_PER_PAGE());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReviewVo reviewVo = new ReviewVo();
				reviewVo.setReview_sq(rs.getInt("review_sq"));
				reviewVo.setItm_sq(rs.getInt("itm_sq"));
				reviewVo.setOrder_cd(rs.getString("order_cd"));
				reviewVo.setSj(rs.getString("sj"));
				reviewVo.setCntnt(rs.getString("cntnt"));
				reviewVo.setDttm(rs.getString("dttm"));
				reviewVo.setEvl(rs.getInt("evl"));
				list.add(reviewVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public ArrayList<ReviewNameVo> getReviewPreview(int itm_sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ReviewNameVo> list = new ArrayList<ReviewNameVo>();
		try {
			pstmt = con.prepareStatement(
					"select review_sq, itm_sq, id, evl, sj, cntnt, date_format(i.dttm,'%Y-%m-%d %H:%i') as dttm from inf_review_tb i inner join inf_mber_tb i1 on i.mber_sq=i1.mber_sq where i.itm_sq=? and i.del_fl=false");
			pstmt.setInt(1, itm_sq);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReviewNameVo reviewVo = new ReviewNameVo();
				reviewVo.setReview_sq(rs.getInt("review_sq"));
				reviewVo.setItm_sq(rs.getInt("itm_sq"));
				reviewVo.setId(rs.getString("id"));
				reviewVo.setEvl(rs.getInt("evl"));
				reviewVo.setSj(rs.getString("sj"));
				reviewVo.setCntnt(Parser.chgToHTML(rs.getString("cntnt")));
				reviewVo.setDttm(rs.getString("dttm"));
				list.add(reviewVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	// 주문코드
	public ArrayList<ReviewVo> getOrderCode(int mber_sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null; // DB의 결과문(쿼리값)을 받아와야함. 우선 빈값으로 설정하자.
		ArrayList<ReviewVo> list = new ArrayList<ReviewVo>();
		try {
			pstmt = con.prepareStatement("select itm_sq from inf_order_tb WHERE mber_sq=?");
			pstmt.setInt(1, mber_sq);
			rs = pstmt.executeQuery();
			while (rs.next()) { // 다음줄이 null(false) 될떄까지 반복실행
				ReviewVo reviewVo = new ReviewVo();
				reviewVo.setItm_sq(rs.getInt("itm_sq"));
				list.add(reviewVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	// 리뷰 상세내용 가져오기
	public ReviewVo getReviewDetail(int review_sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null; // DB의 결과문(쿼리값)을 받아와야함. 우선 빈값으로 설정하자.
		ReviewVo reviewVo = null;
		try {
			pstmt = con.prepareStatement(
					"select i.review_sq, i1.mber_sq,date_format(i.dttm,'%Y-%m-%d %H:%i') as dttm, i.sj,"
					+ "i.order_cd, i.cntnt, i.evl from inf_review_tb i LEFT JOIN inf_mber_tb i1 on i.mber_sq=i1.mber_sq where i.review_sq=?");
			pstmt.setInt(1, review_sq);
			rs = pstmt.executeQuery();
			while (rs.next()) { // 다음줄이 null(false) 될떄까지 반복실행
				reviewVo = new ReviewVo();
				reviewVo.setReview_sq(rs.getInt("i.review_sq"));
				reviewVo.setMber_sq(rs.getInt("i1.mber_sq"));
				reviewVo.setDttm(rs.getString("dttm"));
				reviewVo.setSj(rs.getString("i.sj"));
				reviewVo.setOrder_cd(rs.getString("i.order_cd"));
				reviewVo.setCntnt(rs.getString("i.cntnt"));
				reviewVo.setEvl(rs.getInt("i.evl"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return reviewVo;
	}

	// 리뷰 수정
	public int modify(ReviewVo reviewVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement(
					"update inf_review_tb set sj=?, cntnt=?, order_cd=?, evl=? where review_sq=? and del_fl=0");
			pstmt.setString(1, reviewVo.getSj());
			pstmt.setString(2, reviewVo.getCntnt());
			pstmt.setString(3, reviewVo.getOrder_cd());
			pstmt.setInt(4, reviewVo.getEvl());
			pstmt.setInt(5, reviewVo.getReview_sq());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	// 리뷰 글 삭제
	public int deleteReview(ReviewVo reviewVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("update inf_review_tb set del_fl=1 where review_sq=? and del_fl=0");
			pstmt.setInt(1, reviewVo.getReview_sq());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public int reviewWriteOff(ReviewVo reviewVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("update inf_review_tb set sttus_fl=0 where itm_sq=? and del_fl=0");
			pstmt.setInt(1, reviewVo.getItm_sq());
			count = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	// 페이지네이션
	public int getReviewCount(int mber_sq) {
		PreparedStatement pstmt = null; // 쿼리문 작성할 메소드
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement(
					"select count(review_sq) from inf_review_tb WHERE mber_sq=? and del_fl=0");
			pstmt.setInt(1, mber_sq);
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
