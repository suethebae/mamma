package shop.mammastore.admin.vo;

public class AmemberVo {
	//데이터베이스와 동일하게 이름 정하기
	//여기는 변수랑 게터/세터만
	//절대 생성자 만들지 말것!
	private int mngr_sq;
	private int mber_sq;
	private boolean del_fl;
	private boolean magre_fl;
	private boolean pagre_fl;
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
	public int getMber_sq() {
		return mber_sq;
	}
	public void setMber_sq(int mber_sq) {
		this.mber_sq = mber_sq;
	}
	public boolean isDel_fl() {
		return del_fl;
	}
	public void setDel_fl(boolean del_fl) {
		this.del_fl = del_fl;
	}
	public boolean isMagre_fl() {
		return magre_fl;
	}
	public void setMagre_fl(boolean magre_fl) {
		this.magre_fl = magre_fl;
	}
	public boolean isPagre_fl() {
		return pagre_fl;
	}
	public void setPagre_fl(boolean pagre_fl) {
		this.pagre_fl = pagre_fl;
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