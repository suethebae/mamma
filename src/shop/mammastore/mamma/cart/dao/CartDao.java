package shop.mammastore.mamma.cart.dao;

import static shop.mammastore.common.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import shop.mammastore.mamma.vo.CartListVo;
import shop.mammastore.mamma.vo.CartVo;
import shop.mammastore.mamma.vo.MemberVo;

public class CartDao {

	private Connection con;

	private CartDao() {

	}

	private static class LazyHolder {
		private static final CartDao INSTANCE = new CartDao();
	}

	public static CartDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	// 장바구니 등록
	public int add(CartVo cartVo) {
		PreparedStatement pstmt = null; // 쿼리문 작성할 메소드
		int count = 0;
		try {
			pstmt = con.prepareStatement("insert into inf_cart_tb(mber_sq, itm_sq, itm_cnt) values(?, ?, ?)");
			pstmt.setInt(1, cartVo.getMber_sq());
			pstmt.setInt(2, cartVo.getItm_sq());
			pstmt.setInt(3, cartVo.getItm_cnt());
			count = pstmt.executeUpdate(); // 데이터가 정확히 입력되었으면 카운트가 올라감.
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	// 장바구니 리스트 불러오기
	public ArrayList<CartListVo> getCartList(int imber_sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null; // DB의 결과문(쿼리값)을 받아와야함. 우선 빈값으로 설정하자.
		ArrayList<CartListVo> list = new ArrayList<CartListVo>();
		try {
			pstmt = con.prepareStatement(
					"select cart_sq, mber_sq, a.itm_sq, nm, fl_pth, pc, itm_cnt from inf_cart_tb a inner join inf_itm_tb b on a.itm_sq=b.itm_sq where mber_sq=? and del_fl=0");
			pstmt.setInt(1, imber_sq);
			rs = pstmt.executeQuery();
			while (rs.next()) { // 다음줄이 null(false) 될떄까지 반복실행
				CartListVo clv = new CartListVo();
				clv.setMber_sq(rs.getInt("mber_sq"));
				clv.setItm_sq(rs.getInt("itm_sq"));
				clv.setCart_sq(rs.getInt("cart_sq"));
				clv.setFl_pth(rs.getString("fl_pth"));
				clv.setItm_pc(rs.getInt("pc"));
				clv.setItm_nm(rs.getString("nm"));
				clv.setItm_cnt(rs.getInt("itm_cnt"));
				list.add(clv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}
	//장바구니 삭제
	public int delete(int cart_sq) {
		PreparedStatement pstmt = null; // 쿼리문 작성할 메소드
		int count = 0;
		try {
			pstmt = con.prepareStatement("delete from inf_cart_tb where cart_sq=?");
			pstmt.setInt(1, cart_sq);
			count = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	//장바구니 전체삭제
	public int deleteAll(int mber_sq) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("delete from inf_cart_tb where mber_sq=?");
			pstmt.setInt(1, mber_sq);
			count = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
}
