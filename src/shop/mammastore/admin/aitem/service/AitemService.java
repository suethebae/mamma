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
			isSuccess=false;
		}
		close(con);
		
		return isSuccess;
	}
	
	public ArrayList<AitemVo> getItemList() {
		AitemDao dao = AitemDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<AitemVo> list = dao.getItemList();
		close(con);
		return list;
	}
	
	public AitemVo getItemDetail(int itm_sq) {
		AitemDao dao = AitemDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		AitemVo aitemVo = dao.getItemDetail(itm_sq); 
		close(con);
		return aitemVo;
	}
	
	public boolean modify(AitemVo aitemVo) {
		AitemDao dao = AitemDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.modify(aitemVo);
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
	
	public boolean deleteItem(AitemVo aitemVo) {
		AitemDao dao = AitemDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.deleteItem(aitemVo);
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
	
	public boolean saleOn(AitemVo aitemVo) {
		AitemDao dao = AitemDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.saleOn(aitemVo);
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
	public boolean saleOff(AitemVo aitemVo) {
		AitemDao dao = AitemDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.saleOff(aitemVo);
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

	
}
