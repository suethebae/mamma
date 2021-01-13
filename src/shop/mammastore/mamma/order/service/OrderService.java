package shop.mammastore.mamma.order.service;

import static shop.mammastore.common.JdbcUtil.close;
import static shop.mammastore.common.JdbcUtil.commit;
import static shop.mammastore.common.JdbcUtil.getConnection;
import static shop.mammastore.common.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import shop.mammastore.mamma.order.dao.OrderDao;
import shop.mammastore.mamma.vo.AdresVo;
import shop.mammastore.mamma.vo.CartListVo;
import shop.mammastore.mamma.vo.CartVo;
import shop.mammastore.mamma.vo.OrderItemListVo;
import shop.mammastore.mamma.vo.OrderListVo;
import shop.mammastore.mamma.vo.OrderVo;

public class OrderService {
	//cart_sq에 맞는 아이템 데이터 리스트 형태로 가지고 오기
	public ArrayList<CartListVo> getItemList(String cart_sq[]){
		OrderDao dao = OrderDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<CartListVo> list = dao.getItemList(cart_sq); //
		close(con);
		return list;
	}
	
	//cart_sq에 맞는 itm_sq,itm_cnt 리스트 형태로 가지고 오기
	public ArrayList<CartVo> getItem_sq(String cart_sq[]){
		OrderDao dao = OrderDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<CartVo> list = dao.getItem_sq(cart_sq); //
		close(con);
		return list;
	}
	
	//inf_order_tb와 inf_orderdetail_tb에 값 삽입
	public boolean register(OrderVo orderVo, ArrayList<CartVo> item) {
		OrderDao dao = OrderDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.register(orderVo,item);
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
	//inf_adres_tb에 기본 주소지 등록 된것 제거
	public boolean deleteAdresBase(int mber_sq) {
		OrderDao dao = OrderDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.deleteAdresBase(mber_sq);
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
	//inf_adres_tb에 주소 등록
	public boolean registerAdres(AdresVo adresVo) {
		OrderDao dao = OrderDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.registerAdres(adresVo);
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
	//사용된 장바구니 목록 삭제
	public boolean deleteCart(String cart_sq[]) {
		OrderDao dao = OrderDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.deleteCart(cart_sq);
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
	
	//주문 목록 불러오기
	public ArrayList<OrderListVo> getOrderList(int mber_sq){
		OrderDao dao = OrderDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<OrderListVo> list = dao.getOrderList(mber_sq); //
		close(con);
		return list;
	}
	
	//주문 취소, 구매확정
	public boolean changeSttus(OrderVo orderVo) {
		OrderDao dao = OrderDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.changeSttus(orderVo);
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
	//1개 주문 order
	public boolean registerOne(OrderVo orderVo) {
		OrderDao dao = OrderDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.registerOne(orderVo);
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
	//1개 주문 orderDetail
	public boolean registerDetailOne(OrderItemListVo orderDetailVo) {
		OrderDao dao = OrderDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.registerDetailOne(orderDetailVo);
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
