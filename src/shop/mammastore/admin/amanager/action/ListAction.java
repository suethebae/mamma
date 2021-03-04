package shop.mammastore.admin.amanager.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.amanager.service.AmanagerService;
import shop.mammastore.admin.vo.AmanagerVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;

public class ListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 확인
		HttpSession session = request.getSession();
		String mngr_sq = String.valueOf(session.getAttribute("mngr_sq"));
		if (mngr_sq.equals("null")) {
			mngr_sq = null;
		}

		if (mngr_sq == null || mngr_sq.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}

		ArrayList<AmanagerVo> list = null;
		AmanagerService svc = new AmanagerService();
		list = svc.getMngrList();
		if (list == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('관리자 리스트를 불러오는데 실패했습니다.'); history.back(); </script>");
			out.close();
			return null;
		}

		request.setAttribute("list", list);

		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/amanager/list.jsp");
		return forward;
	}
}
