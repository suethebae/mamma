package shop.mammastore.ajax.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.mammastore.admin.vo.AmanagerVo;
import shop.mammastore.ajax.service.AjaxService;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;

public class DetailMngrAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String mngr_sq = request.getParameter("sq");

		AjaxService svc = new AjaxService();
		AmanagerVo amanagerVo = svc.detailMngr(Integer.parseInt(mngr_sq));
		request.setAttribute("detailMngr", amanagerVo);

		ActionForward forward = new ActionForward();
		forward.setPath("/views/ajax/detailMngr.jsp");
		return forward;
	}

}
