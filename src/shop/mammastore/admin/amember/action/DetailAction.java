package shop.mammastore.admin.amember.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.amember.service.AmemberService;
import shop.mammastore.admin.vo.AmemberVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;

public class DetailAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String mngr_sq = lm.getMemberId(session);
		String mber_sq = request.getParameter("mber_sq");

		if (mngr_sq == null || mngr_sq.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); loaction.href='/'; </script>");
			out.close();
			return null;
		}

		if (mber_sq == null || mber_sq.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); loaction.href='/'; </script>");
			out.close();
			return null;
		}


		AmemberService svc = new AmemberService();
		AmemberVo amemberVo = svc.getDetailMber(Integer.parseInt(mber_sq));

		if (amemberVo == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원 정보 로드에 실패 했습니다.'); loaction.href='/'; </script>");
			out.close();
			return null;
		}

		request.setAttribute("amemberVo", amemberVo);

		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/amember/detail.jsp");
		return forward;
	}
}
