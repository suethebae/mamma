package shop.mammastore.mamma.vo;

public class CartListVo extends CartVo {
	private String itm_nm;
	private String fl_pth;
	private int itm_pc;

	public String getItm_nm() {
		return itm_nm;
	}

	public void setItm_nm(String itm_nm) {
		this.itm_nm = itm_nm;
	}

	public String getFl_pth() {
		return fl_pth;
	}

	public void setFl_pth(String fl_pth) {
		this.fl_pth = fl_pth;
	}

	public int getItm_pc() {
		return itm_pc;
	}

	public void setItm_pc(int itm_pc) {
		this.itm_pc = itm_pc;
	}

}
