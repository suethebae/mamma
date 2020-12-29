package shop.mammastore.ajax.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

<<<<<<< Updated upstream
import shop.mammastore.common.BCrypt;
=======
import shop.mammastore.admin.vo.ActgryVo;
import shop.mammastore.admin.vo.AitemVo;
import shop.mammastore.admin.vo.AmanagerVo;
>>>>>>> Stashed changes
import shop.mammastore.mamma.vo.MemberVo;

import static shop.mammastore.common.JdbcUtil.close;

public class AjaxDao {

	private Connection con;

	private AjaxDao() {

	}

	private static class LazyHolder {
		private static final AjaxDao INSTANCE = new AjaxDao();
	}

	public static AjaxDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	public int checkId(MemberVo memberVo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("select count(*) from inf_mber_tb where id = ? and del_fl=0");
			pstmt.setString(1, memberVo.getId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public int checkEmail(MemberVo memberVo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("select count(*) from inf_mber_tb where email = ? and del_fl=0");
			pstmt.setString(1, memberVo.getEmail());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public int checkPhone(MemberVo memberVo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("select count(*) from inf_mber_tb where phone = ? and del_fl=0");
			pstmt.setString(1, memberVo.getPhone());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
<<<<<<< Updated upstream
=======

	public int checkAId(AmanagerVo amanagerVo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("select count(*) from inf_mngr_tb where id = ? and del_fl=0");
			pstmt.setString(1, amanagerVo.getId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public int checkAEmail(AmanagerVo amanagerVo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("select count(*) from inf_mngr_tb where email = ? and del_fl=0");
			pstmt.setString(1, amanagerVo.getEmail());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public int checkAPhone(AmanagerVo amanagerVo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("select count(*) from inf_mngr_tb where phone = ? and del_fl=0");
			pstmt.setString(1, amanagerVo.getPhone());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public AmanagerVo detailMngr(int mngr_sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AmanagerVo amanagerVo = new AmanagerVo();
		try {
			pstmt = con.prepareStatement("select * from inf_mngr_tb where mngr_sq=? and del_fl=0");
			pstmt.setInt(1, mngr_sq);
			;
			rs = pstmt.executeQuery();
			while (rs.next()) {
				amanagerVo.setMngr_sq(rs.getInt("mngr_sq"));
				amanagerVo.setAuthor(rs.getBoolean("author"));
				amanagerVo.setId(rs.getString("id"));
				amanagerVo.setNm(rs.getString("nm"));
				amanagerVo.setDttm(rs.getString("dttm"));
				amanagerVo.setPhone(rs.getString("phone"));
				amanagerVo.setEmail(rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return amanagerVo;
	}

	public AitemVo showItemList(int ctgry_sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AitemVo aitemVo = new AitemVo();
		try {
			pstmt = con.prepareStatement("select itm_sq, ctgry_sq, pc, nm, thumb_pth from inf_itm_tb where ctgry_sq=? and del_fl=0");
			pstmt.setInt(1, ctgry_sq);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				aitemVo.setItm_sq(rs.getInt("itm_sq"));
				aitemVo.setCtgry_sq(rs.getInt("ctgry_sq"));
				aitemVo.setPc(rs.getInt("pc"));
				aitemVo.setNm(rs.getString("nm"));
				aitemVo.setThumb_pth(rs.getString("thumb_pth"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return aitemVo;
	}

>>>>>>> Stashed changes
}
