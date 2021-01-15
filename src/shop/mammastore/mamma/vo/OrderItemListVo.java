package shop.mammastore.mamma.vo;

import shop.mammastore.admin.vo.AitemVo;

public class OrderItemListVo extends AitemVo{
	private int orderDetail_sq;
	private String order_code;
	private String orderDetail_cd;
	private int mber_sq;
	private int itm_sq;
	private int itm_cnt;
	
	public int getOrderDetail_sq() {
		return orderDetail_sq;
	}
	public void setOrderDetail_sq(int orderDetail_sq) {
		this.orderDetail_sq = orderDetail_sq;
	}
	public String getOrder_code() {
		return order_code;
	}
	public void setOrder_code(String order_code) {
		this.order_code = order_code;
	}
	public String getOrderDetail_cd() {
		return orderDetail_cd;
	}
	public void setOrderDetail_cd(String orderDetail_cd) {
		this.orderDetail_cd = orderDetail_cd;
	}
	public int getMber_sq() {
		return mber_sq;
	}
	public void setMber_sq(int mber_sq) {
		this.mber_sq = mber_sq;
	}
	public int getItm_sq() {
		return itm_sq;
	}
	public void setItm_sq(int itm_sq) {
		this.itm_sq = itm_sq;
	}
	public int getItm_cnt() {
		return itm_cnt;
	}
	public void setItm_cnt(int itm_cnt) {
		this.itm_cnt = itm_cnt;
	}
	
	
}
