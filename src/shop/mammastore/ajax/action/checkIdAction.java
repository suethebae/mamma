package shop.mammastore.ajax.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.mammastore.ajax.service.AjaxService;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.mamma.vo.MemberVo;

public class checkIdAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");

		MemberVo memberVo = new MemberVo();
		memberVo.setId(id);

		AjaxService svc = new AjaxService();
		request.setAttribute("isDuplicate", svc.checkId(memberVo));

		ActionForward forward = new ActionForward();
		forward.setPath("/views/ajax/registerCheck.jsp");
		return forward;
	}

}
