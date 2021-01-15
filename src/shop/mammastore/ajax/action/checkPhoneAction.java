package shop.mammastore.ajax.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.mammastore.ajax.service.AjaxService;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.mamma.vo.MemberVo;

public class checkPhoneAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String phone = request.getParameter("phone");

		MemberVo memberVo = new MemberVo();
		memberVo.setPhone(phone);

		AjaxService svc = new AjaxService();
		request.setAttribute("isDuplicate", svc.checkPhone(memberVo));

		ActionForward forward = new ActionForward();
		forward.setPath("/views/ajax/registerCheck.jsp");
		return forward;
	}

}
