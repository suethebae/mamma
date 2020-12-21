package shop.mammastore.admin.ahome.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.mammastore.admin.amanager.service.AmanagerService;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;

public class AhomeAction implements Action{
@Override
public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	AmanagerService svc = new AmanagerService();
	//슈퍼 관리자 계정가 있을 시
	if(svc.isSManager()) {
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/aindex.jsp");
		return forward;
	}
	//슈퍼 관리자 계정이 없을 시
	else {
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/amanager/asregisterForm.jsp");
		return forward;
	}
	
}
}
