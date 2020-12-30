package shop.mammastore.admin.aitem.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import shop.mammastore.admin.vo.AitemVo;
import shop.mammastore.admin.vo.AmanagerVo;

import static shop.mammastore.common.JdbcUtil.close;

public class AitemDao {

	private Connection con;

	private AitemDao() {

	}

	private static class LazyHolder {
		private static final AitemDao INSTANCE = new AitemDao();
	}

	public static AitemDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	public int register(AitemVo aitemVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("insert into inf_itm_tb (nm,pc,stock,cntnt,fl_pth,ctgry_sq) values(?,?,?,?,?,?)");
			pstmt.setString(1, aitemVo.getNm());
			pstmt.setInt(2, aitemVo.getPc());
			pstmt.setInt(3, aitemVo.getStock());
			pstmt.setString(4, aitemVo.getCntnt());
			pstmt.setString(5, aitemVo.getFl_pth());
			pstmt.setInt(6, aitemVo.getCtgry_sq());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public ArrayList<AitemVo> getItemList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null; // DB�� �����(������)�� �޾ƿ;���. �켱 ������ ��������.
		ArrayList<AitemVo> list = new ArrayList<AitemVo>();
		try {
			pstmt = con.prepareStatement("select itm_sq, sttus_fl, nm, pc, dttm, fl_pth from inf_itm_tb where del_fl=0");
			rs = pstmt.executeQuery();
			while (rs.next()) { // �������� null(false) �ɋ����� �ݺ�����
				AitemVo aitemVo = new AitemVo();
				aitemVo.setItm_sq(rs.getInt("itm_sq"));
				aitemVo.setSttus_fl(rs.getBoolean("sttus_fl"));
				aitemVo.setPc(rs.getInt("pc"));
				aitemVo.setDttm(rs.getString("dttm"));
				aitemVo.setNm(rs.getString("nm"));
				aitemVo.setFl_pth(rs.getString("fl_pth"));
				list.add(aitemVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}
	public AitemVo getAitemDetail(int itm_sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null; // DB�� �����(������)�� �޾ƿ;���. �켱 ������ ��������.
		AitemVo aitemVo = null;
		try {
			pstmt = con.prepareStatement("select * from inf_itm_tb where itm_sq=? and del_fl=0");
			pstmt.setInt(1, itm_sq);
			rs = pstmt.executeQuery();
			while (rs.next()) { // �������� null(false) �ɋ����� �ݺ�����
				aitemVo = new AitemVo();
				aitemVo.setItm_sq(rs.getInt("itm_sq"));
				aitemVo.setCtgry_sq(rs.getInt("ctgry_sq"));
				aitemVo.setSttus_fl(rs.getBoolean("sttus_fl"));
				aitemVo.setNm(rs.getString("nm"));
				aitemVo.setPc(rs.getInt("pc"));
				aitemVo.setDttm(rs.getString("dttm"));
				aitemVo.setFl_pth(rs.getString("fl_pth"));
				aitemVo.setCntnt(rs.getString("cntnt"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return aitemVo;
	}
	
	public int modify(AitemVo aitemVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			if(aitemVo.getFl_pth()==null) {
				pstmt = con.prepareStatement("update inf_itm_tb set nm=?, pc=?, stock=?, cntnt=? where itm_sq=? and del_fl=0"); 
				pstmt.setString(1, aitemVo.getNm());
				pstmt.setInt(2, aitemVo.getPc());
				pstmt.setInt(3, aitemVo.getStock());
				pstmt.setString(4, aitemVo.getCntnt());
				pstmt.setInt(5, aitemVo.getItm_sq());
				count = pstmt.executeUpdate();
			}else {
				pstmt = con.prepareStatement("update inf_itm_tb set nm=?, pc=?, stock=?, cntnt=?, fl_pth=? where itm_sq=? and del_fl=0"); 
				pstmt.setString(1, aitemVo.getNm());
				pstmt.setInt(2, aitemVo.getPc());
				pstmt.setInt(3, aitemVo.getStock());
				pstmt.setString(4, aitemVo.getCntnt());
				pstmt.setString(5, aitemVo.getFl_pth());
				pstmt.setInt(6, aitemVo.getItm_sq());
			
				count = pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	public int deleteItem(AitemVo aitemVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {									
				pstmt = con.prepareStatement("delete from inf_itm_tb where itm_sq=? and del_fl=0"); 
				pstmt.setInt(1, aitemVo.getItm_sq());
				count = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	public int saleOn(AitemVo aitemVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {									
				pstmt = con.prepareStatement("update inf_itm_tb set sttus_fl=1 where itm_sq=? and del_fl=0"); 
				pstmt.setInt(1, aitemVo.getItm_sq());
				count = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public int saleOff(AitemVo aitemVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {									
				pstmt = con.prepareStatement("update inf_itm_tb set sttus_fl=0 where itm_sq=? and del_fl=0"); 
				pstmt.setInt(1, aitemVo.getItm_sq());
				count = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
}
