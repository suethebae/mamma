package shop.mammastore.mamma.vo;

public class MemberOrderVo extends MemberVo {
	private String zip_cd;
	private String adres;
	private String adres_detail;

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

}
