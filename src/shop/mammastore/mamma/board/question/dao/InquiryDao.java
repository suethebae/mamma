package shop.mammastore.mamma.board.question.dao;

import java.sql.Connection;

public class InquiryDao {

	private Connection con;

	private InquiryDao() {

	}

	private static class LazyHolder {
		private static final InquiryDao INSTANCE = new InquiryDao();
	}

	public static InquiryDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	

}
