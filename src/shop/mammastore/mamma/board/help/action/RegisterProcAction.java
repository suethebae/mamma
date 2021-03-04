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

public class RegisterProcAction implements Action {
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
		// order_cd, sj, content를 로드
		String order_cd = request.getParameter("order_cd");
		String sj = request.getParameter("sj");
		String cntnt = request.getParameter("content");

		// 주문코드 비었을 시 빈값 처리
		if (RegExp.isEmpty(order_cd)) {
			order_cd = "";
		}

		// sj, cntnt 유효성 검사
		if (RegExp.isEmpty(sj) || RegExp.isEmpty(cntnt)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}

		// sj 데이터 vo 넣기
		HelpVo helpVo = new HelpVo();
		helpVo.setSj(sj);
		helpVo.setOrder_cd(order_cd);
		helpVo.setCntnt(Parser.chgToStr(cntnt));
		helpVo.setMber_sq(Integer.parseInt(mber_sq));

		// db에 Vo전달하여 카테고리 입력
		HelpService svc = new HelpService();
		if (!svc.register(helpVo)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('문의 등록에 실패하였습니다.'); history.back(); </script>");
			out.close();
			return null;
		}

		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/mymenu/myHelp");
		return forward;
	}
}
