package shop.mammastore.admin.aboard.ahelp.action;

import static shop.mammastore.common.RegExp.REGEXP_NUMBER;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.aboard.ahelp.service.HelpService;
import shop.mammastore.admin.vo.AhelpVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.Parser;
import shop.mammastore.common.RegExp;

public class DetailAction implements Action {
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
		
		// help_sq 데이터 로드, 유효성 검사
		String help_sq = request.getParameter("help_sq");
		if (!RegExp.isValidExp(help_sq, REGEXP_NUMBER)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}

		// db에 등록
		HelpService svc = new HelpService();
		AhelpVo ahelpVo = svc.getHelpDetail(Integer.parseInt(help_sq));

		if (ahelpVo == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('1:1문의 로드에 실패 했습니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}

		ahelpVo.setCntnt(Parser.chgToHTML(ahelpVo.getCntnt()));

		// 받아온 데이터 업로드
		request.setAttribute("ahelpVo", ahelpVo);

		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/board/help/detail.jsp");
		return forward;

	}
}
