package shop.mammastore.admin.vo;

public class AnoticeVo {
		
	private int notice_sq;
	private int mngr_sq;
	private boolean del_fl;
	private String sj;
	private String cntnt;
	private String dttm;
	
	public int getNotice_sq() {
		return notice_sq;
	}
	public void setNotice_sq(int notice_sq) {
		this.notice_sq = notice_sq;
	}
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
	public String getSj() {
		return sj;
	}
	public void setSj(String sj) {
		this.sj = sj;
	}
	public String getCntnt() {
		return cntnt;
	}
	public void setCntnt(String cntnt) {
		this.cntnt = cntnt;
	}
	public String getDttm() {
		return dttm;
	}
	public void setDttm(String dttm) {
		this.dttm = dttm;
	}
	
	
}