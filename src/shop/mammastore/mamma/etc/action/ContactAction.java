package shop.mammastore.mamma.etc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;

public class ContactAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

	
		ActionForward forward = new ActionForward();
		forward.setPath("/views/etc/contact.jsp");
		return forward;
	}
}
