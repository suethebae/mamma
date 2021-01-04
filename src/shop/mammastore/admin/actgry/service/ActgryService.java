package shop.mammastore.admin.actgry.service;

import static shop.mammastore.common.JdbcUtil.close;
import static shop.mammastore.common.JdbcUtil.commit;
import static shop.mammastore.common.JdbcUtil.getConnection;
import static shop.mammastore.common.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import shop.mammastore.admin.actgry.dao.ActgryDao;
import shop.mammastore.admin.amanager.dao.AmanagerDao;
import shop.mammastore.admin.vo.ActgryVo;
import shop.mammastore.admin.vo.AmanagerVo;

public class ActgryService {

	public AmanagerVo getLoginInfo(String id) {
		AmanagerDao dao = AmanagerDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		AmanagerVo amanagerVo = dao.getLoginInfo(id);
		close(con);
		return amanagerVo;
	}

	// 카테고리 등록
	public boolean register(ActgryVo actgryVo) {
		ActgryDao dao = ActgryDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.register(actgryVo);
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

	// 카테고리 리스트 불러오기
	public ArrayList<ActgryVo> getCtgryList() {
		ActgryDao dao = ActgryDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<ActgryVo> list = dao.getCtgryList();
		close(con);
		return list;
	}
	
	// 카테고리 삭제
	public boolean delete(ActgryVo actgryVo) {
		ActgryDao dao = ActgryDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.delete(actgryVo);
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
	// 카테고리 수정 이동
	public ActgryVo detail(int ctgry_sq) {
		ActgryDao dao = ActgryDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ActgryVo actgryVo = dao.detail(ctgry_sq);
		close(con);
		return actgryVo;
	}

	// 카테고리 수정 진행
	public boolean modify(ActgryVo actgryVo) {
		ActgryDao dao = ActgryDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.modify(actgryVo);
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
