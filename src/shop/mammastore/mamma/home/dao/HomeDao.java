package shop.mammastore.mamma.home.dao;

import static shop.mammastore.common.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import shop.mammastore.admin.vo.AitemVo;

public class HomeDao {

	private Connection con;

	private HomeDao() {

	}

	private static class LazyHolder {
		private static final HomeDao INSTANCE = new HomeDao();
	}

	public static HomeDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	public ArrayList<AitemVo> getNewItem() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<AitemVo> list = new ArrayList<AitemVo>();
		;
		try {
			pstmt = con.prepareStatement("select itm_sq, nm, fl_pth, pc from inf_itm_tb "
					+ "where del_fl=0 and sttus_fl=1 " + "ORDER BY dttm desc limit 0,4;");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AitemVo aitemVo = new AitemVo();
				aitemVo.setItm_sq(rs.getInt("itm_sq"));
				aitemVo.setNm(rs.getString("nm"));
				aitemVo.setFl_pth(rs.getString("fl_pth"));
				aitemVo.setPc(rs.getInt("pc"));
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

	// 잘팔리는 아이템 4개 sq
	public ArrayList<Integer> getHitItem_sq() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Integer> list = new ArrayList<Integer>();
		try {
			pstmt = con.prepareStatement(
					"select i.itm_sq, count(i1.itm_sq) as countitm, sum(i1.itm_cnt) as sumitm from inf_itm_tb i inner join inf_orderdetail_tb i1 on i.itm_sq=i1.itm_sq and i.sttus_fl=true and i.del_fl=FALSE group by i.itm_sq order by sumitm desc limit 0,4");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getInt("itm_sq"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}

	// 잘팔리는 아이템 4개
	public ArrayList<AitemVo> getHitItem(ArrayList<Integer> SqList) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<AitemVo> list = new ArrayList<AitemVo>();
		try {
			for (int i = 0; i < SqList.size(); i++) {
				pstmt = con.prepareStatement("select itm_sq, nm, fl_pth, pc from inf_itm_tb "
						+ "where del_fl=0 and sttus_fl=1 and itm_sq=? ");
				pstmt.setInt(1, SqList.get(i));
				rs = pstmt.executeQuery();
				while (rs.next()) {
					AitemVo aitemVo = new AitemVo();
					aitemVo.setItm_sq(rs.getInt("itm_sq"));
					aitemVo.setNm(rs.getString("nm"));
					aitemVo.setFl_pth(rs.getString("fl_pth"));
					aitemVo.setPc(rs.getInt("pc"));
					list.add(aitemVo);
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
}
