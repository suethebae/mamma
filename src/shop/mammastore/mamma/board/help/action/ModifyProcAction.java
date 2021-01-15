package shop.mammastore.mamma.board.help.action;

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

public class ModifyProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 확인
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String mber_sq = lm.getMemberId(session);

		if (mber_sq == null || mber_sq.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); loaction.href='/'; </script>");
			out.close();
			return null;
		}
		// 받아온 help_sq 널값 확인
		String help_sq = request.getParameter("help_sq");
		String order_cd = request.getParameter("order_cd");
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

		// sj, help_sq 데이터 vo에 넣기
		HelpVo helpVo = new HelpVo();
		helpVo.setHelp_sq(Integer.parseInt(help_sq));
		helpVo.setSj(sj);
		helpVo.setCntnt(Parser.chgToStr(cntnt));
		helpVo.setOrder_cd(order_cd);
		// db에 Vo전달하여 등록
		HelpService svc = new HelpService();
		if (!svc.modify(helpVo)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('문의 수정에 실패했습니다.'); history.back(); </script>");
			out.close();
			return null;
		}

		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/help/detail?help_sq=" + helpVo.getHelp_sq());
		return forward;
	}
}
