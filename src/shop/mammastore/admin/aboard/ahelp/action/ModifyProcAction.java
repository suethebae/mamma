package shop.mammastore.admin.aboard.ahelp.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.aboard.ahelp.service.HelpService;
import shop.mammastore.admin.vo.AhelpVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;
import shop.mammastore.common.Parser;
import shop.mammastore.common.RegExp;

public class ModifyProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 확인
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String mngr_sq = lm.getMemberId(session);

		if (mngr_sq == null || mngr_sq.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); loaction.href='/'; </script>");
			out.close();
			return null;
		}
		// 받아온 hel_sq 유효성 검사
		String help_sq = request.getParameter("help_sq");
		if (help_sq == null || help_sq.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); loaction.href='/'; </script>");
			out.close();
			return null;
		}

		// sj, cntnt유효성 검사
		String cntnt = request.getParameter("content");
		String sj = request.getParameter("sj");

		if (RegExp.isEmpty(sj) || RegExp.isEmpty(cntnt)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); loaction.href='/'; </script>");
			out.close();
			return null;
		}

		// sj 데이터 vo에 넣기
		AhelpVo ahelpVo = new AhelpVo();
		ahelpVo.setHelp_sq(Integer.parseInt(help_sq));
		ahelpVo.setSj(sj);
		ahelpVo.setCntnt(Parser.chgToStr(cntnt));

		// db에 Vo전달하여 카테고리 입력
		HelpService svc = new HelpService();
		if (!svc.modify(ahelpVo)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('문의 등록에 실패했습니다.'); history.back(); </script>");
			out.close();
			return null;
		}
		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/ahelp/detail?sq=" + ahelpVo.getHelp_sq());
		return forward;
	}
}
