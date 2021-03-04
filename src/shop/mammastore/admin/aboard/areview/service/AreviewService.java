package shop.mammastore.admin.aboard.areview.service;

import static shop.mammastore.common.JdbcUtil.close;
import static shop.mammastore.common.JdbcUtil.commit;
import static shop.mammastore.common.JdbcUtil.getConnection;
import static shop.mammastore.common.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import shop.mammastore.admin.aboard.areview.dao.AreviewDao;
import shop.mammastore.admin.vo.AreviewNameVo;
import shop.mammastore.admin.vo.AreviewVo;

public class AreviewService {

	// 리뷰 목록 불러오기
	public ArrayList<AreviewNameVo> getReviewList() {
		AreviewDao dao = AreviewDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<AreviewNameVo> list = dao.getReviewList();
		//
		close(con);
		return list;
	}

	// 리뷰 글 불러오기
	public AreviewVo getReviewDetail(int review_sq) {
		AreviewDao dao = AreviewDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		AreviewVo areviewVo = dao.getReviewDetail(review_sq);
		close(con);
		return areviewVo;
	}

	// 리뷰 글 삭제
	public boolean deleteReview(AreviewVo areviewVo) {
		AreviewDao dao = AreviewDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.deleteReview(areviewVo);
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

}
