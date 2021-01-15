package shop.mammastore.admin.aboard.ahelp.dao;

import java.sql.Connection;

public class HelpDao {

	private Connection con;

	private HelpDao() {

	}

	private static class LazyHolder {
		private static final HelpDao INSTANCE = new HelpDao();
	}

	public static HelpDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	

}
