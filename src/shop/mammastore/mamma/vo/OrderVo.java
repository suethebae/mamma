package shop.mammastore.mamma.vo;

public class OrderVo {
	private int order_sq;
	private String order_cd;
	private int mber_sq;
	private int sttus;
	private int all_pc;
	private String nm;
	private String phone;
	private String zip_cd;
	private String adres;
	private String adres_detail;
	private String mssage;
	private boolean agree;
	private String dttm;

	public int getOrder_sq() {
		return order_sq;
	}

	public void setOrder_sq(int order_sq) {
		this.order_sq = order_sq;
	}

	public String getOrder_cd() {
		return order_cd;
	}

	public void setOrder_cd(String order_cd) {
		this.order_cd = order_cd;
	}

	public int getMber_sq() {
		return mber_sq;
	}

	public void setMber_sq(int mber_sq) {
		this.mber_sq = mber_sq;
	}

	public int getSttus() {
		return sttus;
	}

	public void setSttus(int sttus) {
		this.sttus = sttus;
	}

	public int getAll_pc() {
		return all_pc;
	}

	public void setAll_pc(int all_pc) {
		this.all_pc = all_pc;
	}

	public String getNm() {
		return nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getZip_cd() {
		return zip_cd;
	}

	public void setZip_cd(String zip_cd) {
		this.zip_cd = zip_cd;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public String getAdres_detail() {
		return adres_detail;
	}

	public void setAdres_detail(String adres_detail) {
		this.adres_detail = adres_detail;
	}

	public String getMssage() {
		return mssage;
	}

	public void setMssage(String message) {
		this.mssage = message;
	}

	public boolean isAgree() {
		return agree;
	}

	public void setAgree(boolean agree) {
		this.agree = agree;
	}

	public String getDttm() {
		return dttm;
	}

	public void setDttm(String dttm) {
		this.dttm = dttm;
	}

}
