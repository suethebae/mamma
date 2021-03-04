package shop.mammastore.mamma.board.review.service;

import static shop.mammastore.common.JdbcUtil.close;
import static shop.mammastore.common.JdbcUtil.commit;
import static shop.mammastore.common.JdbcUtil.getConnection;
import static shop.mammastore.common.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import shop.mammastore.common.Pagenation;
import shop.mammastore.mamma.board.review.dao.ReviewDao;
import shop.mammastore.mamma.vo.ReviewNameVo;
import shop.mammastore.mamma.vo.ReviewVo;

public class ReviewService {

	// 리뷰 등록
	public boolean register(ReviewVo reviewVo) {
		ReviewDao dao = ReviewDao.getInstance();
		Connection con = getConnection();
		boolean isSuccess = false;
		dao.setConnection(con);
		if (dao.register(reviewVo) > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
			isSuccess = false;
		}
		close(con);

		return isSuccess;
	}

	// 리뷰 목록 불러오기
	public ArrayList<ReviewVo> getReviewList(int mber_sq, Pagenation pagenation) {
		ReviewDao dao = ReviewDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<ReviewVo> list = dao.getReviewList(mber_sq, pagenation);
		//
		close(con);
		return list;
	}
	
	public ArrayList<ReviewNameVo> getReviewPreview(int itm_sq) {
		ReviewDao dao = ReviewDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<ReviewNameVo> list = dao.getReviewPreview(itm_sq);
		//
		close(con);
		return list;
	}
		
		
	// 리뷰 글 불러오기
	public ReviewVo getReviewDetail(int review_sq) {
		ReviewDao dao = ReviewDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ReviewVo reviewVo = dao.getReviewDetail(review_sq);
		close(con);
		return reviewVo;
	}

	// 리뷰 글 수정
	public boolean modify(ReviewVo reviewVo) {
		ReviewDao dao = ReviewDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.modify(reviewVo);
		boolean isSuccess = true;
		if (count > 0) {
			commit(con);
		} else {
			rollback(con);
			isSuccess = false;
		}
		close(con);
		return isSuccess;
	}

	// 리뷰 글 삭제
	public boolean deleteReview(ReviewVo reviewVo) {
		ReviewDao dao = ReviewDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.deleteReview(reviewVo);
		boolean isSuccess = true;
		if (count > 0) {
			commit(con);
		} else {
			rollback(con);
			isSuccess = false;
		}
		close(con);
		return isSuccess;
	}

	public boolean reviewWriteOff(ReviewVo reviewVo) {
		ReviewDao dao = ReviewDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.reviewWriteOff(reviewVo);
		boolean isSuccess = true;
		if (count > 0) {
			commit(con);
		} else {
			rollback(con);
			isSuccess = false;
		}
		close(con);
		return isSuccess;
	}
	
	//리뷰 글 갯수 가지고 오기
	public int getReviewCount(int mber_sq) {
		ReviewDao dao = ReviewDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.getReviewCount(mber_sq);
		close(con);
		return count;
	}
}
