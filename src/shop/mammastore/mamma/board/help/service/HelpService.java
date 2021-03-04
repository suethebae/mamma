package shop.mammastore.mamma.board.help.service;

import static shop.mammastore.common.JdbcUtil.close;
import static shop.mammastore.common.JdbcUtil.commit;
import static shop.mammastore.common.JdbcUtil.getConnection;
import static shop.mammastore.common.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import shop.mammastore.common.Pagenation;
import shop.mammastore.mamma.board.help.dao.HelpDao;
import shop.mammastore.mamma.vo.HelpVo;

public class HelpService {
	// 1:1 문의 등록
	public boolean register(HelpVo helpVo) {
		HelpDao dao = HelpDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.register(helpVo);
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

	// 1:1 문의 리스트 불러오기
	public ArrayList<HelpVo> getHelpList(int mber_sq, Pagenation pagenation) {
		HelpDao dao = HelpDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<HelpVo> list = dao.getHelpList(mber_sq, pagenation);
		close(con);
		return list;
	}

	// 1:1 문의 상세 보기
	public HelpVo getHelpDetail(int help_sq) {
		HelpDao dao = HelpDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		HelpVo helpVo = dao.getHelpDetail(help_sq);
		close(con);
		return helpVo;
	}

	// 1:1 문의 수정
	public boolean modify(HelpVo helpVo) {
		HelpDao dao = HelpDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.modify(helpVo);
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

	// 1:1 문의 삭제
	public boolean deleteHelp(HelpVo helpVo) {
		HelpDao dao = HelpDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.deleteHelp(helpVo);
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

	// 주문코드
	public ArrayList<HelpVo> getOrderCode(int mber_sq) {
		HelpDao dao = HelpDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<HelpVo> list = dao.getOrderCode(mber_sq);
		close(con);
		return list;
	}

	// 페이지네이션
	public int getHelpCount(int mber_sq) {
		HelpDao dao = HelpDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.getHelpCount(mber_sq);
		close(con);
		return count;
	}
}
