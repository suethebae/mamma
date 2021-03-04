package shop.mammastore.mamma.board.help.action;

import static shop.mammastore.common.RegExp.REGEXP_NUMBER;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;
import shop.mammastore.common.RegExp;
import shop.mammastore.mamma.board.help.service.HelpService;
import shop.mammastore.mamma.vo.HelpVo;

public class DeleteAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 확인
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
		// help_sq 유효성검사
		String help_sq = request.getParameter("help_sq");
		if (!RegExp.isValidExp(help_sq, REGEXP_NUMBER)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}
		// HelpVo 데이터 저장
		HelpVo helpVo = new HelpVo();
		helpVo.setHelp_sq(Integer.parseInt(help_sq));

		// 데이터 베이스에 등록
		HelpService svc = new HelpService();
		if (!svc.deleteHelp(helpVo)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('1:1문의 삭제에 실패하였습니다..); history.back(); </script>");
			out.close();
			return null;
		}

		request.setAttribute("helpVo", helpVo);

		helpVo.setDel_fl(true);

		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/mymenu/myHelp");
		return forward;
	}
}
