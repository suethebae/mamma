package shop.mammastore.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JdbcUtil {
	public static Connection getConnection() {
		Connection con = null;
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env"); // �뤃�뜑寃쎈줈 �꽕�젙
			DataSource ds = (DataSource) envCtx.lookup("jdbc/oracle"); // name�씠 �씠嫄곗씤 �젙蹂� 媛��졇�샂
			con = ds.getConnection();// �떎�젣濡� �뿰寃곕맂 媛앹껜瑜� 媛��졇�샂
			con.setAutoCommit(false);// �옄�룞 Commit �걫

		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	//諛붽�吏� 3媛� �떕�븘以섏빞�븿 �룎�젮以섏빞�븿
	public static void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//commit �빐二쇰뒗 硫붿냼�뱶
	public static void commit(Connection con) {
		if (con != null) {
			try {
				con.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//rollback �빐二쇰뒗 硫붿냼�뱶
	public static void rollback(Connection con) {
		if (con != null) {
			try {
				con.rollback();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
