package shop.mammastore.mamma.cart.service;

import static shop.mammastore.common.JdbcUtil.close;
import static shop.mammastore.common.JdbcUtil.commit;
import static shop.mammastore.common.JdbcUtil.getConnection;
import static shop.mammastore.common.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import shop.mammastore.mamma.cart.dao.CartDao;
import shop.mammastore.mamma.vo.CartListVo;
import shop.mammastore.mamma.vo.CartVo;

public class CartService {
	// 장바구니 등록
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

	// 장바구니 호출
	public CartListVo getTempDetail(int cart_sq) {
		CartDao dao = CartDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		CartListVo list = dao.getTempDetail(cart_sq);
		close(con);
		return list;
	}

	// 장바구니에 담긴 물품 갯수 수정
	public boolean modify(CartVo cartVo) {
		CartDao dao = CartDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.modify(cartVo);
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
	public boolean delete(int cart_sq) {
		CartDao dao = CartDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.delete(cart_sq);
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
	public boolean deleteAll(int mber_sq) {
		CartDao dao = CartDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.deleteAll(mber_sq);
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


}
