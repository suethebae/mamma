package shop.mammastore.admin.aboard.ahelp.service;

import static shop.mammastore.common.JdbcUtil.close;
import static shop.mammastore.common.JdbcUtil.commit;
import static shop.mammastore.common.JdbcUtil.getConnection;
import static shop.mammastore.common.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import shop.mammastore.admin.aboard.ahelp.dao.HelpDao;
import shop.mammastore.admin.vo.AhelpVo;

public class HelpService {

	//문의 리스트 불러오기
	public ArrayList<AhelpVo> getHelpList() {
		HelpDao dao = HelpDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<AhelpVo> list = dao.getHelpList();
		close(con);
		return list;
	}
  //문의 상세 리스트 불러오기
	public AhelpVo getHelpDetail(int help_sq) {
		HelpDao dao = HelpDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		AhelpVo ahelpVo = dao.getHelpDetail(help_sq);
		close(con);
		return ahelpVo;
	}
	//답변 등록
	public boolean register(AhelpVo ahelpVo) {
		HelpDao dao = HelpDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.register(ahelpVo);
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
	//1:1 문의 수정
	public boolean modify(AhelpVo ahelpVo) {
		HelpDao dao = HelpDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.modify(ahelpVo);
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
	
	//1:1 문의 삭제
	public boolean deleteHelp(AhelpVo ahelpVo) {
		HelpDao dao = HelpDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.deleteHelp(ahelpVo);
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
