package shop.mammastore.ajax.dao;

import static shop.mammastore.common.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import shop.mammastore.admin.vo.AitemVo;
import shop.mammastore.admin.vo.AmanagerVo;
import shop.mammastore.mamma.vo.MemberOrderVo;
import shop.mammastore.mamma.vo.MemberVo;

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
			close(rs);
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
			close(rs);
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
			close(rs);
		}
		return count;
	}

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
			close(rs);
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
			close(rs);
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
			close(rs);
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
			close(rs);
		}
		return amanagerVo;
	}

	public AitemVo showItemList(int ctgry_sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AitemVo aitemVo = new AitemVo();
		try {
			pstmt = con.prepareStatement(
					"select itm_sq, ctgry_sq, pc, nm, thumb_pth from inf_itm_tb where ctgry_sq=? and del_fl=0");
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
			close(rs);
		}
		return aitemVo;
	}

	public ArrayList<AitemVo> getItemList(int iCtgry_sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<AitemVo> list = new ArrayList<AitemVo>();
		try {
			if (iCtgry_sq == 0) {
				pstmt = con.prepareStatement(
						"select itm_sq, ctgry_sq, pc, nm, fl_pth from inf_itm_tb where del_fl=0 and sttus_fl=1");
			} else {
				pstmt = con.prepareStatement(
						"select itm_sq, ctgry_sq, pc, nm, fl_pth from inf_itm_tb where del_fl=0 and sttus_fl=1 and ctgry_sq=?");
				pstmt.setInt(1, iCtgry_sq);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AitemVo aitemVO = new AitemVo();
				aitemVO.setItm_sq(rs.getInt("itm_sq"));
				aitemVO.setCtgry_sq(rs.getInt("ctgry_sq"));
				aitemVO.setPc(rs.getInt("pc"));
				aitemVO.setNm(rs.getString("nm"));
				aitemVO.setFl_pth(rs.getString("fl_pth"));
				list.add(aitemVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}

	public MemberOrderVo inputMberData(int mber_sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberOrderVo MOVo = new MemberOrderVo();
		try {
			pstmt = con.prepareStatement(
					"select * from inf_mber_tb a left join inf_adres_tb b on a.mber_sq=b.mber_sq and b.adres_base=true where a.mber_sq=?");
			pstmt.setInt(1, mber_sq);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MOVo.setNm(rs.getString("a.nm"));
				MOVo.setPhone(rs.getString("a.phone"));
				MOVo.setEmail(rs.getString("a.email"));
				MOVo.setAdres(rs.getString("b.adres"));
				MOVo.setAdres_detail(rs.getString("b.adres_detail"));
				MOVo.setZip_cd(rs.getString("b.zip_cd"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return MOVo;
	}
}
