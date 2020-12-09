package shop.mammastore.mamma.member.service;

import java.sql.Connection;

import shop.mammastore.mamma.member.dao.MemberDao;
import shop.mammastore.mamma.vo.MemberVo;

import static shop.mammastore.common.JdbcUtil.*;

public class MemberService {
	public boolean register(MemberVo memberVo) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.register(memberVo);
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
