package shop.mammastore.mamma.cart.service;

import static shop.mammastore.common.JdbcUtil.close;
import static shop.mammastore.common.JdbcUtil.commit;
import static shop.mammastore.common.JdbcUtil.getConnection;
import static shop.mammastore.common.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import shop.mammastore.mamma.cart.dao.CartDao;
import shop.mammastore.mamma.member.dao.MemberDao;
import shop.mammastore.mamma.vo.CartListVo;
import shop.mammastore.mamma.vo.CartVo;
import shop.mammastore.mamma.vo.MemberVo;

public class CartService {
	//장바구니 등록
	public boolean add(CartVo cartVo) {
		CartDao dao = CartDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.add(cartVo);
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
	
	//카트 리스트 불러오기
	public ArrayList<CartListVo> getCartList(int imber_sq) {
		CartDao dao = CartDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<CartListVo> list = dao.getCartList(imber_sq); //
		close(con);
		return list;
	}
	
	public MemberVo findId(String query) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		MemberVo memberVo = dao.findId(query); //
		close(con);
		return memberVo;
	}
	
	public MemberVo findPwd(MemberVo memberVo) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		MemberVo vo = dao.findPwd(memberVo); //
		close(con);
		return vo;
	}
	
	public boolean setPwd(MemberVo memberVo) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.setPwd(memberVo);
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
	
	public boolean registerHistory(MemberVo memberVo) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.registerHistory(memberVo);
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

	public MemberVo getUserData(int mber_sq) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		MemberVo memberVo = dao.getUserData(mber_sq); //
		close(con);
		return memberVo;
	}
	
	public MemberVo myInfo() {
		return null;
	}

	public boolean modify(MemberVo memberVo) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.modify(memberVo);
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

	public MemberVo getMemberLoginInfo(String mber_sq) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		MemberVo vo = dao.getMemberLoginInfo(mber_sq);
		close(con);
		return vo;
	}

	public boolean leaveMember(MemberVo memberVo) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.leaveMember(memberVo);
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
