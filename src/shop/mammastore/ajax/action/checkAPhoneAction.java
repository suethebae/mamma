package shop.mammastore.ajax.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.mammastore.admin.vo.AmanagerVo;
import shop.mammastore.ajax.service.AjaxService;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;

public class checkAPhoneAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String phone = request.getParameter("phone");
		
		AmanagerVo amanagerVo = new AmanagerVo();
		amanagerVo.setPhone(phone);
		
		AjaxService svc = new AjaxService();
		request.setAttribute("isDuplicate", svc.checkAPhone(amanagerVo));
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/ajax/registerCheck.jsp");
		return forward;
	}
	
}
