package shop.mammastore.common;

public class ActionForward {
	//�꽌釉붾┸�뿉�꽌 �슂泥� 泥섎━ �썑 �룷�썙�뵫 �맆 酉� �럹�씠吏� url
	private String path;
	//�룷�썙�뵫 諛⑹떇(true:redirect-二쇱냼媛� 諛붾��, request瑜� �깉濡쒗븿 / false:dispatch-二쇱냼 �븞諛붾��, setAttribute)
	private boolean redirect;
	
	public ActionForward() {
	}
	
	public ActionForward(String path, boolean redirect) {
		this.path = path;
		this.redirect = redirect;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isRedirect() {
		return redirect;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}

	
	
}
