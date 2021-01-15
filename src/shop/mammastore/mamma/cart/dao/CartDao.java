package shop.mammastore.mamma.cart.dao;

import static shop.mammastore.common.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import shop.mammastore.mamma.vo.CartListVo;
import shop.mammastore.mamma.vo.CartVo;

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

	// 장바구니에 담겨있는 물품 불러오기
	public CartListVo getTempDetail(int cart_sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CartListVo cartListVo = null;
		try {
			pstmt = con.prepareStatement("select * from inf_cart_tb inner join inf_itm_tb on inf_cart_tb.itm_sq = inf_itm_tb.itm_sq where inf_cart_tb.cart_sq = ?");
			pstmt.setInt(1, cart_sq);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cartListVo = new CartListVo();
				cartListVo.setCart_sq(rs.getInt("cart_sq"));
				cartListVo.setMber_sq(rs.getInt("mber_sq"));
				cartListVo.setItm_sq(rs.getInt("itm_sq"));
				cartListVo.setItm_cnt(rs.getInt("itm_cnt"));
				cartListVo.setFl_pth(rs.getString("inf_itm_tb.fl_pth"));
				cartListVo.setItm_nm(rs.getString("inf_itm_tb.nm"));
				cartListVo.setItm_pc(rs.getInt("inf_itm_tb.pc"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return cartListVo;
	}

	// 장바구니에 담긴 물품 갯수 수정
	public int modify(CartVo cartVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("update inf_cart_tb a inner join inf_itm_tb b on a.itm_sq = b.itm_sq set itm_cnt = ? where cart_sq = ?");
			pstmt.setInt(1, cartVo.getItm_cnt());
			pstmt.setInt(2, cartVo.getCart_sq());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
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
