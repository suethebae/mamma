package shop.mammastore.admin.aitem.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.mammastore.admin.aitem.service.AitemService;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;

public class ModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 데이터 베이스에서 글정보 불러와야합니다
		AitemService svc = new AitemService();
		
		// Vo에 정보가 저장되어 있음
		// Vo에서 치환
		/*
		 * vo.setContent(vo.getContent().replaceAll("&amp", "&"));
		 * vo.setContent(vo.getContent().replaceAll("&lt", "<"));
		 * vo.setContent(vo.getContent().replaceAll("&gt", ">"));
		 * vo.setContent(vo.getContent().replaceAll("&quot", "/"));
		 */

		ActionForward forward = new ActionForward();
		forward.setPath("view/admin/aitem/updateForm.jsp");
		return forward;
	}

}
