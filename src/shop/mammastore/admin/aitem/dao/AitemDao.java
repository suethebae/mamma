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
			pstmt = con.prepareStatement("insert into inf_itm_tb (nm,pc,stock,cntnt,fl_pth) values(?,?,?,?,?)");
			pstmt.setString(1, aitemVo.getNm());
			pstmt.setInt(2, aitemVo.getPc());
			pstmt.setInt(3, aitemVo.getStock());
			pstmt.setString(4, aitemVo.getCntnt());
			pstmt.setString(5, aitemVo.getFl_pth());
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
		ResultSet rs = null;
		ArrayList<AitemVo> list = new ArrayList<>();
		try {
			pstmt = con.prepareStatement("select * from inf_itm_tb where del_fl=0");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AitemVo temp = new AitemVo();
				temp.setFl_pth(rs.getString("fl_pth"));
				list.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return list;
	}
}
