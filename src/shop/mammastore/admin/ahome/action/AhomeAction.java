package shop.mammastore.admin.ahome.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;

public class AhomeAction implements Action{
@Override
public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
	//경로설정
	ActionForward forward = new ActionForward();
	forward.setPath("/views/admin/aindex.jsp");
	return forward;
}
}
