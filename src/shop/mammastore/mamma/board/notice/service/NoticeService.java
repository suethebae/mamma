package shop.mammastore.mamma.board.notice.service;

import static shop.mammastore.common.JdbcUtil.close;
import static shop.mammastore.common.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import shop.mammastore.admin.aboard.anotice.dao.AnoticeDao;
import shop.mammastore.admin.vo.AnoticeVo;

public class NoticeService {

	// anoticeDao에서 정보를 다 가져옴.
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

}
