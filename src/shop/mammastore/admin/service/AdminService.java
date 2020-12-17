package shop.mammastore.admin.service;

import java.sql.Connection;

import shop.mammastore.admin.dao.AdminDao;
import shop.mammastore.admin.vo.AdminVo;

import static shop.mammastore.common.JdbcUtil.*;

public class AdminService {

	public AdminVo getLoginInfo(String id) {
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		AdminVo adminVo = dao.getLoginInfo(id); 
		close(con);
		return adminVo;
	}
	
}
