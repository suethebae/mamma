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

	public int registerHistory(MemberVo memberVo) {
		PreparedStatement pstmt = null; // 쿼리문 작성할 메소드
		int count = 0;
		try {
			pstmt = con.prepareStatement("insert into hist_lgn_tb (mber_sq) values(?)");
			pstmt.setInt(1, memberVo.getMber_sq());
			count = pstmt.executeUpdate(); // 데이터가 정확히 입력되었으면 카운트가 올라감.

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public MemberVo findId(String query) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVo memberVo = null;
		try {
			pstmt = con.prepareStatement("select id from inf_mber_tb where del_fl=0" + query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				memberVo = new MemberVo();
				memberVo.setId(rs.getString("id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return memberVo;
	}

	public MemberVo findPwd(MemberVo memberVo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVo vo = null;
		try {
			pstmt = con.prepareStatement("select * from inf_mber_tb where del_fl=0 and id=? and nm=? and email=?");
			pstmt.setString(1, memberVo.getId());
			pstmt.setString(2, memberVo.getNm());
			pstmt.setString(3, memberVo.getEmail());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new MemberVo();
				vo.setId(rs.getString("id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return vo;
	}

	public int setPwd(MemberVo memberVo) {
		PreparedStatement pstmt = null; // 쿼리문 작성할 메소드
		int count = 0;
		try {
			pstmt = con
					.prepareStatement("update inf_mber_tb set pwd = ? where del_fl=0 and id=? and nm=? and email=? ");
			pstmt.setString(1, memberVo.getPwd());
			pstmt.setString(2, memberVo.getId());
			pstmt.setString(3, memberVo.getNm());
			pstmt.setString(4, memberVo.getEmail());

			count = pstmt.executeUpdate(); // 데이터가 정확히 입력되었으면 카운트가 올라감.

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public int modify(MemberVo memberVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			if (memberVo.getPwd() == null || memberVo.getPwd().equals("")) {
				pstmt = con.prepareStatement("update inf_mber_tb set email=?, phone=? where mber_sq=? and del_fl=0");
				pstmt.setString(1, memberVo.getEmail());
				pstmt.setString(2, memberVo.getPhone());
				pstmt.setInt(3, memberVo.getMber_sq());
			} else {
				pstmt = con.prepareStatement(
						"update inf_mber_tb set pwd=?, email=?, phone=? where mber_sq=? and del_fl=0");
				pstmt.setString(1, memberVo.getPwd());
				pstmt.setString(2, memberVo.getEmail());
				pstmt.setString(3, memberVo.getPhone());
				pstmt.setInt(4, memberVo.getMber_sq());
			}
			count = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public MemberVo getUserData(int mber_sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVo memberVo = null;
		try {
			pstmt = con.prepareStatement("select * from inf_mber_tb where mber_sq=? and del_fl=0");
			pstmt.setInt(1, mber_sq);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				memberVo = new MemberVo();
				memberVo.setMber_sq(rs.getInt("mber_sq"));
				memberVo.setDttm(rs.getString("dttm"));
				memberVo.setId(rs.getString("id"));
				memberVo.setPwd(rs.getString("pwd"));
				memberVo.setNm(rs.getString("nm"));
				memberVo.setEmail(rs.getString("email"));
				memberVo.setPhone(rs.getString("phone"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return memberVo;
	}

	public int leaveMember(MemberVo memberVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("update inf_mber_tb set del_fl=? where mber_sq=?");
			pstmt.setBoolean(1, memberVo.isDel_fl());
			pstmt.setInt(2, memberVo.getMber_sq());

			count = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public MemberVo getMemberLoginInfo(String mber_sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVo vo = null;
		try {
			pstmt = con.prepareStatement("select id, pwd from inf_mber_tb where id=? and del_fl=0");
			pstmt.setString(1, mber_sq);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new MemberVo();
				vo.setId(rs.getString("id"));
				vo.setPwd(rs.getString("pwd"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return vo;

	}

}
