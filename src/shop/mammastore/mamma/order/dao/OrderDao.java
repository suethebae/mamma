package shop.mammastore.mamma.order.dao;

import static shop.mammastore.common.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import shop.mammastore.mamma.vo.AdresVo;
import shop.mammastore.mamma.vo.CartListVo;
import shop.mammastore.mamma.vo.CartVo;
import shop.mammastore.mamma.vo.OrderItemListVo;
import shop.mammastore.mamma.vo.OrderListVo;
import shop.mammastore.mamma.vo.OrderVo;

public class OrderDao {

	private Connection con;

	private OrderDao() {

	}

	private static class LazyHolder {
		private static final OrderDao INSTANCE = new OrderDao();
	}

	public static OrderDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	// 오더 폼에서 아이템 데이터 가지고 오기
	public ArrayList<CartListVo> getItemList(String cart_sq[]) {
		PreparedStatement pstmt = null; // 쿼리문 작성할 메소드
		ArrayList<CartListVo> list = new ArrayList<CartListVo>();
		ResultSet rs = null;
		try {
			for (int i = 0; i < cart_sq.length; i++) {
				pstmt = con.prepareStatement(
						"select * from inf_cart_tb a inner join inf_itm_tb b on a.itm_sq=b.itm_sq where a.cart_sq=?");
				pstmt.setInt(1, Integer.parseInt(cart_sq[i]));
				rs = pstmt.executeQuery(); // 데이터가 정확히 입력되었으면 카운트가 올라감.
				while (rs.next()) {
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
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return list;
	}

	// itm_sq, itm_cnt 리스트 형태로 가지고 오기
	public ArrayList<CartVo> getItem_sq(String cart_sq[]) {
		PreparedStatement pstmt = null; // 쿼리문 작성할 메소드
		ArrayList<CartVo> list = new ArrayList<CartVo>();
		ResultSet rs = null;
		try {
			for (int i = 0; i < cart_sq.length; i++) {
				pstmt = con.prepareStatement("select itm_sq, itm_cnt from inf_cart_tb where cart_sq=?");
				pstmt.setInt(1, Integer.parseInt(cart_sq[i]));
				rs = pstmt.executeQuery(); // 데이터가 정확히 입력되었으면 카운트가 올라감.
				while (rs.next()) {
					CartVo cartVo = new CartVo();
					cartVo.setItm_sq(rs.getInt("itm_sq"));
					cartVo.setItm_cnt(rs.getInt("itm_cnt"));
					list.add(cartVo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return list;
	}

	// inf_order_tb와 inf_orderdetail_tb에 값 삽입
	public int register(OrderVo orderVo, ArrayList<CartVo> item) {
		PreparedStatement pstmt = null; // 쿼리문 작성할 메소드
		int count = 0;
		try {
			pstmt = con.prepareStatement(
					"insert into inf_order_tb (order_code, mber_sq, sttus, all_pc, nm, phone, zip_cd, adres, adres_detail, mssage, agree) values (?,?,?,?,?,?,?,?,?,?,1)");
			pstmt.setString(1, orderVo.getOrder_code());
			pstmt.setInt(2, orderVo.getMber_sq());
			pstmt.setInt(3, orderVo.getSttus());
			pstmt.setInt(4, orderVo.getAll_pc());
			pstmt.setString(5, orderVo.getNm());
			pstmt.setString(6, orderVo.getPhone());
			pstmt.setString(7, orderVo.getZip_cd());
			pstmt.setString(8, orderVo.getAdres());
			pstmt.setString(9, orderVo.getAdres_detail());
			pstmt.setString(10, orderVo.getMssage());
			count = pstmt.executeUpdate(); // 데이터가 정확히 입력되었으면 카운트가 올라감.
			if (count > 0) {
				for (int i = 0; i < item.size(); i++) {
					pstmt = con.prepareStatement(
							"insert into inf_orderdetail_tb (order_code, orderDetail_cd, mber_sq, itm_sq, itm_cnt) values (?,?,?,?,?)");
					pstmt.setString(1, orderVo.getOrder_code());
					pstmt.setString(2, orderVo.getOrder_code() + (i + 1));
					pstmt.setInt(3, orderVo.getMber_sq());
					pstmt.setInt(4, item.get(i).getItm_sq());
					pstmt.setInt(5, item.get(i).getItm_cnt());
					count = pstmt.executeUpdate();
				}
			} else {
				count = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	// 기본 주소 지정 시 현재 기본 주소 설정 삭제
	public int deleteAdresBase(int mber_sq) {
		PreparedStatement pstmt = null; // 쿼리문 작성할 메소드
		int count = 0;
		try {
			pstmt = con.prepareStatement("update inf_adres_tb set adres_base=0 where mber_sq=?");
			pstmt.setInt(1, mber_sq);
			pstmt.executeUpdate();// 데이터가 정확히 입력되었으면 카운트가 올라감.
			count = 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	// 주소 저장
	public int registerAdres(AdresVo adresVo) {
		PreparedStatement pstmt = null; // 쿼리문 작성할 메소드
		int count = 0;
		try {
			pstmt = con.prepareStatement(
					"insert into inf_adres_tb (mber_sq,zip_cd,adres,adres_detail,adres_base) values (?,?,?,?,?)");
			pstmt.setInt(1, adresVo.getMber_sq());
			pstmt.setString(2, adresVo.getZip_cd());
			pstmt.setString(3, adresVo.getAdres());
			pstmt.setString(4, adresVo.getAdres_detail());
			pstmt.setBoolean(5, adresVo.isAdres_base());
			count = pstmt.executeUpdate(); // 데이터가 정확히 입력되었으면 카운트가 올라감.
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	// 사용된 카트 삭제
	public int deleteCart(String cart_sq[]) {
		PreparedStatement pstmt = null; // 쿼리문 작성할 메소드
		int count = 0;
		try {
			for (int i = 0; i < cart_sq.length; i++) {
				pstmt = con.prepareStatement("delete from inf_cart_tb where cart_sq=?");
				pstmt.setInt(1, Integer.parseInt(cart_sq[i]));
				count = pstmt.executeUpdate(); // 데이터가 정확히 입력되었으면 카운트가 올라감.
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	// 주문 목록 가지고 오기
	public ArrayList<OrderListVo> getOrderList(int mber_sq) {
		PreparedStatement pstmt = null; // 쿼리문 작성할 메소드
		ArrayList<OrderListVo> list = new ArrayList<OrderListVo>();
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(
					"select * from inf_order_tb a inner join inf_mber_tb b on a.mber_sq=b.mber_sq where a.mber_sq=?  order by a.dttm desc");
			pstmt.setInt(1, mber_sq);
			rs = pstmt.executeQuery(); // 데이터가 정확히 입력되었으면 카운트가 올라감.
			while (rs.next()) {
				OrderListVo OLVo = new OrderListVo();
				OLVo.setOrder_sq(rs.getInt("a.order_sq"));
				OLVo.setOrder_code(rs.getString("a.order_code"));
				OLVo.setMber_sq(rs.getInt("a.mber_sq"));
				OLVo.setMber_nm(rs.getString("b.nm"));
				OLVo.setSttus(rs.getInt("a.sttus"));
				OLVo.setAll_pc(rs.getInt("a.all_pc"));
				OLVo.setNm(rs.getString("a.nm"));
				OLVo.setPhone(rs.getString("a.phone"));
				OLVo.setZip_cd(rs.getString("a.zip_cd"));
				OLVo.setAdres(rs.getString("a.adres"));
				OLVo.setAdres_detail(rs.getString("a.adres_detail"));
				OLVo.setMssage(rs.getString("a.mssage"));
				OLVo.setDttm(rs.getString("a.dttm"));
				list.add(OLVo);
			}
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					ArrayList<OrderItemListVo> itemList = new ArrayList<OrderItemListVo>();
					pstmt = con.prepareStatement(
							"select * from inf_orderdetail_tb a inner join inf_itm_tb b on a.itm_sq = b.itm_sq where a.order_code=?");
					pstmt.setString(1, list.get(i).getOrder_code());
					rs = pstmt.executeQuery();
					while (rs.next()) {
						OrderItemListVo OILVo = new OrderItemListVo();
						OILVo.setItm_sq(rs.getInt("a.itm_sq"));
						OILVo.setNm(rs.getString("b.nm"));
						OILVo.setFl_pth(rs.getString("b.fl_pth"));
						OILVo.setItm_cnt(rs.getInt("a.itm_cnt"));
						OILVo.setPc(rs.getInt("b.pc"));
						OILVo.setOrderDetail_cd(rs.getString("a.orderDetail_cd"));
						itemList.add(OILVo);
					}
					list.get(i).setItemList(itemList);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return list;
	}

	// 주문 취소, 구매 확정
	public int changeSttus(OrderVo orderVo) {
		PreparedStatement pstmt = null; // 쿼리문 작성할 메소드
		int count = 0;
		try {
			pstmt = con.prepareStatement("update inf_order_tb set sttus=? where order_sq=?");
			pstmt.setInt(1, orderVo.getSttus());
			pstmt.setInt(2, orderVo.getOrder_sq());
			pstmt.executeUpdate();// 데이터가 정확히 입력되었으면 카운트가 올라감.
			count = 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	// 단일 주문 order register
	public int registerOne(OrderVo orderVo) {
		PreparedStatement pstmt = null; // 쿼리문 작성할 메소드
		int count = 0;
		try {
			pstmt = con.prepareStatement(
					"insert into inf_order_tb (order_code, mber_sq, sttus, all_pc, nm, phone, zip_cd, adres, adres_detail, mssage, agree) values (?,?,?,?,?,?,?,?,?,?,1)");
			pstmt.setString(1, orderVo.getOrder_code());
			pstmt.setInt(2, orderVo.getMber_sq());
			pstmt.setInt(3, orderVo.getSttus());
			pstmt.setInt(4, orderVo.getAll_pc());
			pstmt.setString(5, orderVo.getNm());
			pstmt.setString(6, orderVo.getPhone());
			pstmt.setString(7, orderVo.getZip_cd());
			pstmt.setString(8, orderVo.getAdres());
			pstmt.setString(9, orderVo.getAdres_detail());
			pstmt.setString(10, orderVo.getMssage());
			count = pstmt.executeUpdate(); // 데이터가 정확히 입력되었으면 카운트가 올라감.
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	//단일 주문 orderdetail register
	public int registerDetailOne(OrderItemListVo orderDetailVo) {
		PreparedStatement pstmt = null; // 쿼리문 작성할 메소드
		int count = 0;
		try {
			pstmt = con.prepareStatement(
					"insert into inf_orderdetail_tb (order_code, orderDetail_cd, mber_sq, itm_sq, itm_cnt) values (?,?,?,?,?)");
			pstmt.setString(1, orderDetailVo.getOrder_code());
			pstmt.setString(2, orderDetailVo.getOrderDetail_cd());
			pstmt.setInt(3, orderDetailVo.getMber_sq());
			pstmt.setInt(4, orderDetailVo.getItm_sq());
			pstmt.setInt(5, orderDetailVo.getItm_cnt());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
}
