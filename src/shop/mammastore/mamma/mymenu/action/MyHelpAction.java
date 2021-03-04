package shop.mammastore.mamma.mymenu.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;
import shop.mammastore.common.Pagenation;
import shop.mammastore.mamma.board.help.service.HelpService;
import shop.mammastore.mamma.vo.HelpVo;

public class MyHelpAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String mber_sq = lm.getMemberId(session);

		if (mber_sq == null || mber_sq.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}

		// 페이지 넘버 받아오기
		String pn = request.getParameter("pn");
		if (pn == null || pn == "") {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/mymenu/myHelp?pn=1';</script>");
			out.close();
			return null;
		}
		int page = Integer.parseInt(pn);

		HelpService svc = new HelpService();
		// 페이지네이션
		Pagenation pagenation = new Pagenation(page, svc.getHelpCount(Integer.parseInt(mber_sq)));
		// 끝 이상으로 넘어가면 마지막 페이지 표시
		if (page > pagenation.getTotalPageCount()) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/mymenu/myHelp?pn=" + pagenation.getTotalPageCount() + "';</script>");
			out.close();
			return null;
		}

		ArrayList<HelpVo> list = svc.getHelpList(Integer.parseInt(mber_sq), pagenation);
		if (list == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('1:1 문의 리스트를 불러오는데 실패했습니다.'); history.back(); </script>");
			out.close();
			return null;
		}

		request.setAttribute("list", list);
		request.setAttribute("pagenation", pagenation);

		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/mymenu/myHelp.jsp?pn="+page);
		return forward;
	}
}
