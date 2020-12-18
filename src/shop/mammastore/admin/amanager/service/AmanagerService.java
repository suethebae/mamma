package shop.mammastore.admin.amanager.service;

import java.sql.Connection;

import shop.mammastore.admin.amanager.dao.AmanagerDao;
import shop.mammastore.admin.vo.AmanagerVo;

import static shop.mammastore.common.JdbcUtil.*;

public class AmanagerService {

	public AmanagerVo getLoginInfo(String id) {
		AmanagerDao dao = AmanagerDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		AmanagerVo amanagerVo = dao.getLoginInfo(id); 
		close(con);
		return amanagerVo;
	}
	
}
