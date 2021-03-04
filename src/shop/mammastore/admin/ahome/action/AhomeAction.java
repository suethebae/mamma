package shop.mammastore.admin.ahome.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.amanager.service.AmanagerService;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;

public class AhomeAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 확인
		HttpSession session = request.getSession();
		String mngr_sq = String.valueOf(session.getAttribute("mngr_sq"));
		if (mngr_sq.equals("null")) {
			mngr_sq = null;
		}

		AmanagerService svc = new AmanagerService();
		// 슈퍼 매니저 계정이 있을때
		if (svc.isSManager()) {
			// 로그인이 안되어있을때
			if (mngr_sq == null || mngr_sq.equals("")) {
				ActionForward forward = new ActionForward();
				forward.setPath("/views/admin/beforeIndex.jsp");
				return forward;
			}
			// 로그인이 되어있을때
			else {
				ActionForward forward = new ActionForward();
				forward.setPath("/views/admin/afterIndex.jsp");
				return forward;
			}
		}
		// 슈퍼 매니저 계정이 없을떄
		else {
			ActionForward forward = new ActionForward();
			forward.setPath("/views/admin/amanager/sRegisterForm.jsp");
			return forward;
		}

	}
}
