package shop.mammastore.admin.amember.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.amember.service.AmemberService;
import shop.mammastore.admin.vo.AmemberVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;

public class ModifyAction implements Action {
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

		String mber_sq = request.getParameter("sq");
		// 회원 시퀀스 확인
		if (mber_sq == null || mber_sq.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}

		AmemberService svc = new AmemberService();
		AmemberVo amemberVo = svc.getAmemberDetail(Integer.parseInt(mber_sq));
		if (amemberVo == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원정보를 불러오는데 실패했습니다.'); location.href='/'; </script>");
			out.close();
		}

		request.setAttribute("amemberVo", amemberVo);

		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/amember/modifyForm.jsp");
		return forward;
	}
}