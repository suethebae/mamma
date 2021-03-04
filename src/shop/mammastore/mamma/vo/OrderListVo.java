package shop.mammastore.mamma.vo;

import java.util.ArrayList;

public class OrderListVo extends OrderVo {
	private String id;
	private String mber_nm;
	private ArrayList<OrderItemListVo> itemList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMber_nm() {
		return mber_nm;
	}

	public void setMber_nm(String mber_nm) {
		this.mber_nm = mber_nm;
	}

	public ArrayList<OrderItemListVo> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<OrderItemListVo> itemList) {
		this.itemList = itemList;
	}

}
