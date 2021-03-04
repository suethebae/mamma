package shop.mammastore.mamma.board.help.action;

import static shop.mammastore.common.RegExp.REGEXP_NUMBER;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;
import shop.mammastore.common.Parser;
import shop.mammastore.common.RegExp;
import shop.mammastore.mamma.board.help.service.HelpService;
import shop.mammastore.mamma.vo.HelpVo;

public class DetailAction implements Action {
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
		// help_sq 유효성 검사
		String help_sq = request.getParameter("help_sq");
		if (!RegExp.isValidExp(help_sq, REGEXP_NUMBER)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}

		// help_sq 기준으로 db에 등록
		HelpService svc = new HelpService();
		HelpVo helpVo = svc.getHelpDetail(Integer.parseInt(help_sq));

		if (helpVo == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('1:1문의 로드에 실패 했습니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}

		// 컨텐츠 내용 변경
		helpVo.setCntnt(Parser.chgToHTML(helpVo.getCntnt()));
		if (helpVo.getAnswer() == null || helpVo.getAnswer().equals("")) {
			helpVo.setAnswer("");
		}

		// 받아온 데이터 업로드
		request.setAttribute("helpVo", helpVo);

		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/board/help/detail.jsp");
		return forward;

	}
}
