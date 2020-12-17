package shop.mammastore.mamma.vo;

public class AdminVo {
	private int mngr_sq;
	private boolean del_fl;
	private boolean author;
	private String dttm;
	private String id;
	private String pwd;
	private String nm;
	private String email;
	private String phone;
	public int getMngr_sq() {
		return mngr_sq;
	}
	public void setMngr_sq(int mngr_sq) {
		this.mngr_sq = mngr_sq;
	}
	public boolean isDel_fl() {
		return del_fl;
	}
	public void setDel_fl(boolean del_fl) {
		this.del_fl = del_fl;
	}
	public boolean isAuthor() {
		return author;
	}
	public void setAuthor(boolean author) {
		this.author = author;
	}
	public String getDttm() {
		return dttm;
	}
	public void setDttm(String dttm) {
		this.dttm = dttm;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNm() {
		return nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
