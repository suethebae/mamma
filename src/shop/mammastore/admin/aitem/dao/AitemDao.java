package shop.mammastore.admin.aitem.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import shop.mammastore.admin.vo.AitemVo;
import static shop.mammastore.common.JdbcUtil.close;

public class AitemDao {

	private Connection con;

	

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
			pstmt = con.prepareStatement("insert into inf_itm_tb (nm,pc,stock,cntnt) values(?,?,?,?)");
			pstmt.setString(1, aitemVo.getNm());
			pstmt.setInt(2, aitemVo.getPc());
			pstmt.setInt(3, aitemVo.getStock());
			pstmt.setString(4, aitemVo.getCntnt());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public ArrayList<AitemVo> getAitemList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null; // DB의 결과문(쿼리값)을 받아와야함. 우선 빈값으로 설정하자.
		ArrayList<AitemVo> list = new ArrayList<AitemVo>();
		try {
			pstmt = con.prepareStatement("select itm_sq, sttus_fl, nm, pc, dttm, fl_pth from inf_itm_tb where del_fl=0");
			rs = pstmt.executeQuery();
			while (rs.next()) { // 다음줄이 null(false) 될떄까지 반복실행
				AitemVo aitemVo = new AitemVo();
				aitemVo.setItem_sq(rs.getInt("itm_sq"));
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
}
