package shop.mammastore.admin.amember.service;

import static shop.mammastore.common.JdbcUtil.close;
import static shop.mammastore.common.JdbcUtil.commit;
import static shop.mammastore.common.JdbcUtil.getConnection;
import static shop.mammastore.common.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import shop.mammastore.admin.amember.dao.AmemberDao;
import shop.mammastore.admin.vo.AmemberVo;

public class AmemberService {
	
	public boolean modify(AmemberVo amemberVo) {
		AmemberDao dao = AmemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.modify(amemberVo);
		boolean isSuccess = true;
		if(count > 0) {
			commit(con);
		}else {
			rollback(con);
			isSuccess = false;
		}
		close(con);
		return isSuccess;
	}


	public boolean deleteMember(AmemberVo amemberVo) {
		AmemberDao dao = AmemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.deleteMember(amemberVo);
		boolean isSuccess = true;
		if(count > 0) {
			commit(con);
		}else {
			rollback(con);
			isSuccess = false;
		}
		close(con);
		return isSuccess;
	}
	public ArrayList<AmemberVo> getMberList() {
		AmemberDao dao = AmemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<AmemberVo> list = dao.getMberList();
		close(con);
		return list;
	}
	public AmemberVo getDetailMber(int mber_sq) {
		AmemberDao dao = AmemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		AmemberVo amemberVo = dao.getDetailMber(mber_sq);
		close(con);
		return amemberVo;
	}
}
