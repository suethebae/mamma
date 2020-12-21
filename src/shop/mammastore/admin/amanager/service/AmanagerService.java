package shop.mammastore.admin.amanager.service;

import static shop.mammastore.common.JdbcUtil.close;
import static shop.mammastore.common.JdbcUtil.commit;
import static shop.mammastore.common.JdbcUtil.getConnection;
import static shop.mammastore.common.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import shop.mammastore.admin.amanager.dao.AmanagerDao;
import shop.mammastore.admin.vo.AitemVo;
import shop.mammastore.admin.vo.AmanagerVo;


public class AmanagerService {

	public AmanagerVo getLoginInfo(String id) {
		AmanagerDao dao = AmanagerDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		AmanagerVo amanagerVo = dao.getLoginInfo(id); 
		close(con);
		return amanagerVo;
	}
	
	//�ְ� ������ �ִ� �� Ȯ��
	public boolean isSManager() {
		AmanagerDao dao = AmanagerDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.isSManager();
		boolean isSManager = false;
		if (count > 0) {
			isSManager = true;
		}
		close(con);
		return isSManager;
	}
	//�ְ� ������ ���
	public boolean asregister(AmanagerVo amanagerVo) {
		AmanagerDao dao = AmanagerDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.asregister(amanagerVo);
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
	//������ ����Ʈ �ҷ�����
	public ArrayList<AmanagerVo> getMngrList() {
		AmanagerDao dao = AmanagerDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<AmanagerVo> list = dao.getMngrList();
		close(con);
		return list;
	}
	//������ ȸ������
	public boolean aregister(AmanagerVo amanagerVo) {
		AmanagerDao dao = AmanagerDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.aregister(amanagerVo);
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
	//������ ȸ������ ���� �̵�
	public AmanagerVo getDetailMngr(int mngr_sq) {
		AmanagerDao dao = AmanagerDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		AmanagerVo amanagerVo = dao.getDetailMngr(mngr_sq); 
		close(con);
		return amanagerVo;
	}

	}

