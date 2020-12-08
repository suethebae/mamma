package shop.mammastore.mamma.vo;

public class MemberVo {
	//데이터베이스와 동일하게 이름 정하기
	//여기는 변수랑 게터/세터만
	//절대 생성자 만들지 말것!
	
	private int mber_sq;
	private String id;
	private String pwd;
	private String email;
	private String phone;
	private boolean del_fl;
	private String dttm;
	public int getMber_sq() {
		return mber_sq;
	}
	public void setMber_sq(int mber_sq) {
		this.mber_sq = mber_sq;
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
	public boolean isDel_fl() {
		return del_fl;
	}
	public void setDel_fl(boolean del_fl) {
		this.del_fl = del_fl;
	}
	public String getDttm() {
		return dttm;
	}
	public void setDttm(String dttm) {
		this.dttm = dttm;
	}
	
}
