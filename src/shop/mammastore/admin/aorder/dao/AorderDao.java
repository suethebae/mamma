package shop.mammastore.admin.aorder.dao;

import static shop.mammastore.common.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import shop.mammastore.common.Pagenation;
import shop.mammastore.mamma.vo.OrderItemListVo;
import shop.mammastore.mamma.vo.OrderListVo;

public class AorderDao {

	private Connection con;

	private AorderDao() {

	}

	private static class LazyHolder {
		private static final AorderDao INSTANCE = new AorderDao();
	}

	public static AorderDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	// 주문 목록 가지고 오기
	public ArrayList<OrderListVo> getOrderList(Pagenation pagenation, String query) {
		PreparedStatement pstmt = null; // 쿼리문 작성할 메소드
		ArrayList<OrderListVo> list = new ArrayList<OrderListVo>();
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(
					"select * from inf_order_tb a inner join inf_mber_tb b on a.mber_sq=b.mber_sq"+query+" order by a.dttm desc limit ?,?");
			pstmt.setInt(1, pagenation.getStartArticleNumber());
			pstmt.setInt(2, pagenation.getARTICLE_COUNT_PER_PAGE());
			rs = pstmt.executeQuery(); // 데이터가 정확히 입력되었으면 카운트가 올라감.
			while (rs.next()) {
				OrderListVo OLVo = new OrderListVo();
				OLVo.setOrder_sq(rs.getInt("a.order_sq"));
				OLVo.setOrder_cd(rs.getString("a.order_cd"));
				OLVo.setMber_sq(rs.getInt("a.mber_sq"));
				OLVo.setId(rs.getString("b.id"));
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
							"select * from inf_orderdetail_tb a inner join inf_itm_tb b on a.itm_sq = b.itm_sq where a.order_cd=?");
					pstmt.setString(1, list.get(i).getOrder_cd());
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
			close(rs);
		}
		return list;
	}

	// 주문 취소, 구매 확정
	public int changeSttus(String order_sq[], int sttus) {
		PreparedStatement pstmt = null; // 쿼리문 작성할 메소드
		int count = 0;
		try {
			for (int i = 0; i < order_sq.length; i++) {
				pstmt = con.prepareStatement("update inf_order_tb set sttus=? where order_sq=?");
				pstmt.setInt(1, sttus);
				pstmt.setInt(2, Integer.parseInt(order_sq[i]));
				count = pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	// 페이지네이션
	public int getOrderCount(String query) {
		PreparedStatement pstmt = null; // 쿼리문 작성할 메소드
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("select count(order_sq) from inf_order_tb a inner join inf_mber_tb b on a.mber_sq=b.mber_sq" + query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return count;
	}
}
