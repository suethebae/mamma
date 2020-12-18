package shop.mammastore.admin.amanager.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;

public class AlogoutAction implements Action{
@Override
public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
	//경로설정
	ActionForward forward = new ActionForward();
	forward.setPath("/views/admin");
	return forward;
}
}
