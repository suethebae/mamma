package shop.mammastore.admin.amember.service;

import static shop.mammastore.common.JdbcUtil.close;
import static shop.mammastore.common.JdbcUtil.commit;
import static shop.mammastore.common.JdbcUtil.getConnection;
import static shop.mammastore.common.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import shop.mammastore.admin.amember.dao.AmemberDao;
import shop.mammastore.admin.vo.AmemberVo;
import shop.mammastore.common.Pagenation;

public class AmemberService {

	// 매니저가 회원 목록을 불러오기
	public ArrayList<AmemberVo> getMberList(Pagenation pagenation, String query) {
		AmemberDao dao = AmemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<AmemberVo> list = dao.getMberList(pagenation, query);
		close(con);
		return list;
	}

	// 매니저가 회원 상세목록을 불러오기
	public AmemberVo getAmemberDetail(int mber_sq) {
		AmemberDao dao = AmemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		AmemberVo AmemberVo = dao.getAmemberDetail(mber_sq); //
		close(con);
		return AmemberVo;
	}

	// 매니저가 회원정보를 수정하기
	public boolean modify(AmemberVo amemberVo) {
		AmemberDao dao = AmemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.modify(amemberVo);
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

	// 매니저가 회원정보를 삭제하기
	public boolean deleteMember(AmemberVo amemberVo) {
		AmemberDao dao = AmemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.deleteMember(amemberVo);
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

	// 페이지네이션
	public int getMemberCount(String query) {
		AmemberDao dao = AmemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.getMemberCount(query);
		close(con);
		return count;
	}

}
