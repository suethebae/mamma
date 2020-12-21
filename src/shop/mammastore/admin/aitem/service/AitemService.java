package shop.mammastore.admin.aitem.service;

import static shop.mammastore.common.JdbcUtil.close;
import static shop.mammastore.common.JdbcUtil.getConnection;
import static shop.mammastore.common.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import shop.mammastore.admin.aitem.dao.AitemDao;
import shop.mammastore.admin.amanager.dao.AmanagerDao;
import shop.mammastore.admin.vo.AitemVo;
import shop.mammastore.admin.vo.AmanagerVo;


public class AitemService {

	public boolean register(AitemVo aitemVo) {
		AitemDao dao = AitemDao.getInstance();
		Connection con = getConnection();
		boolean isSuccess = false;
		dao.setConnection(con);
		if(dao.register(aitemVo)>0) {
			commit(con);
			isSuccess=true;
		}
		else {
			rollback(con);
		}
		close(con);
		

		return isSuccess;
	}
	public ArrayList<AitemVo> getAitemList() {
		AitemDao dao = AitemDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<AitemVo> list = dao.getAitemList();
		close(con);
		return list;
	}
}
