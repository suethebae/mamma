package shop.mammastore.mamma.board.notice.service;

import static shop.mammastore.common.JdbcUtil.close;
import static shop.mammastore.common.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import shop.mammastore.admin.aboard.anotice.dao.AnoticeDao;
import shop.mammastore.admin.vo.AnoticeVo;
import shop.mammastore.common.Pagenation;

public class NoticeService {

	// 공지사항 목록 불러오기
	public ArrayList<AnoticeVo> getNoticeList(Pagenation pagenation) {
		AnoticeDao dao = AnoticeDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<AnoticeVo> list = dao.getNoticeList(pagenation);
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
	
	// 공지사항 글 갯수 가지고 오기
	public int getNoticeCount() {
		AnoticeDao dao = AnoticeDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.getNoticeCount();
		close(con);
		return count;
	}
}
