package shop.mammastore.admin.vo;

public class AitemVo {
	private int itm_sq;
	private int ctgry_sq;
	private boolean del_fl;
	private boolean sttus_fl;
	private int pc;
	private int stock;
	private String nm;
	private String cntnt;
	private String fl_pth;
	private String thumb_pth;
	private String dttm;

	public String getDttm() {
		return dttm;
	}

	public void setDttm(String dttm) {
		this.dttm = dttm;
	}

	public int getItm_sq() {
		return itm_sq;
	}

	public void setItm_sq(int itm_sq) {
		this.itm_sq = itm_sq;
	}

	public int getCtgry_sq() {
		return ctgry_sq;
	}

	public void setCtgry_sq(int ctgry_sq) {
		this.ctgry_sq = ctgry_sq;
	}

	public boolean isDel_fl() {
		return del_fl;
	}

	public void setDel_fl(boolean del_fl) {
		this.del_fl = del_fl;
	}

	public boolean isSttus_fl() {
		return sttus_fl;
	}

	public void setSttus_fl(boolean sttus_fl) {
		this.sttus_fl = sttus_fl;
	}

	public int getPc() {
		return pc;
	}

	public void setPc(int pc) {
		this.pc = pc;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getNm() {
		return nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}

	public String getCntnt() {
		return cntnt;
	}

	public void setCntnt(String cntnt) {
		this.cntnt = cntnt;
	}

	public String getFl_pth() {
		return fl_pth;
	}

	public void setFl_pth(String fl_pth) {
		this.fl_pth = fl_pth;
	}

	public String getThumb_pth() {
		return thumb_pth;
	}

	public void setThumb_pth(String thumb_pth) {
		this.thumb_pth = thumb_pth;
	}
}
