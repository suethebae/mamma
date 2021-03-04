package shop.mammastore.admin.amanager.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.amanager.service.AmanagerService;
import shop.mammastore.admin.vo.AmanagerVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.RegExp;

public class LeaveAction implements Action {
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

		String dmngr_sq = request.getParameter("mngr_sq");

		if (RegExp.isEmpty(dmngr_sq)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}

		AmanagerVo amanagerVo = new AmanagerVo();
		amanagerVo.setMngr_sq(Integer.parseInt(dmngr_sq));

		AmanagerService svc = new AmanagerService();
		if (!svc.leave(amanagerVo)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원 탈퇴에 실패하였습니다..'); history.back(); </script>");
			out.close();
			return null;
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("/amanager/list");
		forward.setRedirect(true);
		return forward;
	}

}