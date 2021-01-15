package shop.mammastore.admin.aboard.anotice.service;

import static shop.mammastore.common.JdbcUtil.close;
import static shop.mammastore.common.JdbcUtil.commit;
import static shop.mammastore.common.JdbcUtil.getConnection;
import static shop.mammastore.common.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import shop.mammastore.admin.aboard.anotice.dao.AnoticeDao;
import shop.mammastore.admin.vo.AnoticeVo;

public class AnoticeService {

	// 공지사항 등록
	public boolean register(AnoticeVo anoticeVo) {
		AnoticeDao dao = AnoticeDao.getInstance();
		Connection con = getConnection();
		boolean isSuccess = false;
		dao.setConnection(con);
		if (dao.register(anoticeVo) > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
			isSuccess = false;
		}
		close(con);

		return isSuccess;
	}

	// 공지사항 목록 불러오기
	public ArrayList<AnoticeVo> getNoticeList() {
		AnoticeDao dao = AnoticeDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<AnoticeVo> list = dao.getNoticeList();
		return list;
	}

	// 공지사항 글 불러오기
	public AnoticeVo getNoticeDetail(int notice_sq) {
		AnoticeDao dao = AnoticeDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		AnoticeVo anoticeVo = dao.getNoticeDetail(notice_sq);
		close(con);
		return anoticeVo;
	}

	// 공지사항 수정
	public boolean modify(AnoticeVo anoticeVo) {
		AnoticeDao dao = AnoticeDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.modify(anoticeVo);
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

	// 공지사항 글 삭제
	public boolean deleteNotice(AnoticeVo anoticeVo) {
		AnoticeDao dao = AnoticeDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.deleteNotice(anoticeVo);
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
