package shop.mammastore.ajax.service;

import java.sql.Connection;

import shop.mammastore.admin.vo.AmanagerVo;
import shop.mammastore.ajax.dao.AjaxDao;
import shop.mammastore.mamma.vo.MemberVo;

import static shop.mammastore.common.JdbcUtil.*;

public class AjaxService {
	public boolean checkId(MemberVo memberVo) {
		AjaxDao dao = AjaxDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.checkId(memberVo);
		boolean isDuplicate = false;
		if(count!=0) {
			isDuplicate = true;
		}
		close(con);
		return isDuplicate;
	}
	public boolean checkEmail(MemberVo memberVo) {
		AjaxDao dao = AjaxDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.checkEmail(memberVo);
		boolean isDuplicate = false;
		if(count!=0) {
			isDuplicate = true;
		}
		close(con);
		return isDuplicate;
	}
	public boolean checkPhone(MemberVo memberVo) {
		AjaxDao dao = AjaxDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.checkPhone(memberVo);
		boolean isDuplicate = false;
		if(count!=0) {
			isDuplicate = true;
		}
		close(con);
		return isDuplicate;
	}
	public boolean checkAId(AmanagerVo amanagerVo) {
		AjaxDao dao = AjaxDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.checkAId(amanagerVo);
		boolean isDuplicate = false;
		if(count!=0) {
			isDuplicate = true;
		}
		close(con);
		return isDuplicate;
	}
	public boolean checkAEmail(AmanagerVo amanagerVo) {
		AjaxDao dao = AjaxDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.checkAEmail(amanagerVo);
		boolean isDuplicate = false;
		if(count!=0) {
			isDuplicate = true;
		}
		close(con);
		return isDuplicate;
	}
	public boolean checkAPhone(AmanagerVo amanagerVo) {
		AjaxDao dao = AjaxDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.checkAPhone(amanagerVo);
		boolean isDuplicate = false;
		if(count!=0) {
			isDuplicate = true;
		}
		close(con);
		return isDuplicate;
	}
	
}
