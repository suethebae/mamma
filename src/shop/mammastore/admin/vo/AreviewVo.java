package shop.mammastore.admin.vo;

import shop.mammastore.mamma.vo.ReviewVo;

public class AreviewVo extends ReviewVo {
	private int review_sq;
	private int mngr_sq;
	private String order_cd;
	private int itm_sq;
	private boolean del_fl;
	private int evl;
	private String sj;
	private String cntnt;
	private String fl_pth;
	private String dttm;
	
	public int getReview_sq() {
		return review_sq;
	}

	public void setReview_sq(int review_sq) {
		this.review_sq = review_sq;
	}

	public int getMngr_sq() {
		return mngr_sq;
	}

	public void setMngr_sq(int mngr_sq) {
		this.mngr_sq = mngr_sq;
	}

	public String getOrder_cd() {
		return order_cd;
	}

	public void setOrder_cd(String order_cd) {
		this.order_cd = order_cd;
	}

	public int getItm_sq() {
		return itm_sq;
	}

	public void setItm_sq(int itm_sq) {
		this.itm_sq = itm_sq;
	}

	public boolean isDel_fl() {
		return del_fl;
	}

	public void setDel_fl(boolean del_fl) {
		this.del_fl = del_fl;
	}

	public int getEvl() {
		return evl;
	}

	public void setEvl(int evl) {
		this.evl = evl;
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

	public String getFl_pth() {
		return fl_pth;
	}

	public void setFl_pth(String fl_pth) {
		this.fl_pth = fl_pth;
	}

	public String getDttm() {
		return dttm;
	}

	public void setDttm(String dttm) {
		this.dttm = dttm;
	}

}
