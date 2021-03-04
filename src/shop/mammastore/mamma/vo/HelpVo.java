package shop.mammastore.mamma.vo;

public class HelpVo extends MemberVo {
	private int help_sq;
	private int mber_sq;
	private String order_cd;
	private boolean answer_fl;
	private boolean del_fl;
	private String dttm;
	private String sj;
	private String cntnt;
	private String answer;

	public int getHelp_sq() {
		return help_sq;
	}

	public void setHelp_sq(int help_sq) {
		this.help_sq = help_sq;
	}

	public int getMber_sq() {
		return mber_sq;
	}

	public void setMber_sq(int mber_sq) {
		this.mber_sq = mber_sq;
	}

	public String getOrder_cd() {
		return order_cd;
	}

	public void setOrder_cd(String order_cd) {
		this.order_cd = order_cd;
	}

	public boolean isAnswer_fl() {
		return answer_fl;
	}

	public void setAnswer_fl(boolean answer_fl) {
		this.answer_fl = answer_fl;
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

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}