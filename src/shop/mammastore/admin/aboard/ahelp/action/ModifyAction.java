package shop.mammastore.admin.aboard.ahelp.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.aboard.ahelp.service.HelpService;
import shop.mammastore.admin.vo.AhelpVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.RegExp;

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
		// 받아온 help_sq 유효성검사
		String help_sq = request.getParameter("help_sq");
		if (RegExp.isEmpty(help_sq)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}

		// AhelpService 불러와 db에 등록
		HelpService svc = new HelpService();
		AhelpVo ahelpVo = svc.getHelpDetail(Integer.parseInt(help_sq));
		if (ahelpVo == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('1:1문의를 불러들이는데 실패했습니다.'); history.back(); </script>");
			out.close();
			return null;
		}
		// 받아온 데이터 업로드
		request.setAttribute("ahelpVo", ahelpVo);

		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/board/help/modifyForm.jsp");
		return forward;
	}
}
