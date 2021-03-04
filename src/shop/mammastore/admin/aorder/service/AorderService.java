package shop.mammastore.admin.aorder.service;

import static shop.mammastore.common.JdbcUtil.close;
import static shop.mammastore.common.JdbcUtil.commit;
import static shop.mammastore.common.JdbcUtil.getConnection;
import static shop.mammastore.common.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import shop.mammastore.admin.aorder.dao.AorderDao;
import shop.mammastore.common.Pagenation;
import shop.mammastore.mamma.vo.OrderListVo;

public class AorderService {
	
	//주문 목록 불러오기
	public ArrayList<OrderListVo> getOrderList(Pagenation pagenation, String query){
		AorderDao dao = AorderDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<OrderListVo> list = dao.getOrderList(pagenation, query); //
		close(con);
		return list;
	}

	// 주문 취소, 구매확정
	public boolean changeSttus(String order_sq[], int sttus) {
		AorderDao dao = AorderDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.changeSttus(order_sq, sttus);
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
	
	//페이지네이션
	public int getOrderCount(String query) {
		AorderDao dao = AorderDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.getOrderCount(query);
		close(con);
		return count;
	}
}
