package shop.mammastore.mamma.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import shop.mammastore.mamma.vo.MemberVo;

import static shop.mammastore.common.JdbcUtil.close;

public class MemberDao {

	private Connection con;

	private MemberDao() {

	}

	private static class LazyHolder {
		private static final MemberDao INSTANCE = new MemberDao();
	}

	public static MemberDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	public int register(MemberVo vo) {
		PreparedStatement pstmt = null; // 쿼리문 작성할 메소드
		int count = 0;
		try {
			pstmt = con.prepareStatement(
					"insert into inf_mber_tb(id, pwd, nm, email, phone, magre_fl, pagre_fl) values(?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getNm());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getPhone());
			pstmt.setBoolean(6, vo.isMagre_fl());
			pstmt.setBoolean(7, vo.isPagre_fl());

			count = pstmt.executeUpdate(); // 데이터가 정확히 입력되었으면 카운트가 올라감.

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public MemberVo getLoginInfo(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null; // DB의 결과문(쿼리값)을 받아와야함. 우선 빈값으로 설정하자.
		MemberVo memberVo = null;
		try {
			pstmt = con.prepareStatement("select mber_sq, id, pwd from inf_mber_tb where id=? and del_fl=0");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) { // 다음줄이 null(false) 될떄까지 반복실행
				memberVo = new MemberVo();
				memberVo.setMber_sq(rs.getInt("mber_sq"));
				memberVo.setId(rs.getString("id"));
				memberVo.setPwd(rs.getString("pwd"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return memberVo;
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
			pstmt = con.prepareStatement(
					"update inf_mber_tb set pwd = ? where del_fl=0 and id=? and nm=? and email=? ");
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
			if(memberVo.getPwd()==null||memberVo.getPwd().equals("")) {
				pstmt = con.prepareStatement("update inf_mber_tb set email=?, phone=? where mber_sq=? and del_fl=0"); 
				pstmt.setString(1, memberVo.getEmail());
				pstmt.setString(2, memberVo.getPhone());
				pstmt.setInt(3, memberVo.getMber_sq());
			} else {
				pstmt = con.prepareStatement("update inf_mber_tb set pwd=?, email=?, phone=? where mber_sq=? and del_fl=0"); 
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
			close(rs);
		}
		return vo;

	}

}
