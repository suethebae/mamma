package shop.mammastore.mamma.home.service;

import static shop.mammastore.common.JdbcUtil.close;
import static shop.mammastore.common.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import shop.mammastore.admin.vo.AitemVo;
import shop.mammastore.mamma.home.dao.HomeDao;

public class HomeService {
	// 새로 나온 상품 4개 가지고 오기
	public ArrayList<AitemVo> getNewItem() {
		HomeDao dao = HomeDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<AitemVo> list = dao.getNewItem();
		close(con);
		return list;
	}

	// 잘팔리는 상품 4개 sq 가지고 오기
	public ArrayList<Integer> getHitItem_sq() {
		HomeDao dao = HomeDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<Integer> list = dao.getHitItem_sq();
		close(con);
		return list;
	}

	// 잘팔리는 상품 4개 가지고 오기
	public ArrayList<AitemVo> getHitItem(ArrayList<Integer> SqList) {
		HomeDao dao = HomeDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<AitemVo> list = dao.getHitItem(SqList);
		close(con);
		return list;
	}

}
