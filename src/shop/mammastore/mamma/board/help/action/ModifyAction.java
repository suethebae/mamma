package shop.mammastore.mamma.board.help.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;
import shop.mammastore.common.RegExp;
import shop.mammastore.mamma.board.help.service.HelpService;
import shop.mammastore.mamma.vo.HelpVo;

public class ModifyAction implements Action {
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
		// help_sq 데이터 로드
		String help_sq = request.getParameter("help_sq");
		if (RegExp.isEmpty(help_sq)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); loaction.href='/'; </script>");
			out.close();
			return null;
		}

		// db에 등록
		HelpService svc = new HelpService();
		HelpVo helpVo = svc.getHelpDetail(Integer.parseInt(help_sq));
		if (helpVo == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('1:1문의 정보를 불러들이는데 실패했습니다.'); history.back(); </script>");
			out.close();
			return null;
		}
		// mber_sq 기준으로 주문코드 db에 등록
		ArrayList<HelpVo> olist = svc.getOrderCode(Integer.parseInt(mber_sq));

		// 받아온 데이터 업로드
		request.setAttribute("helpVo", helpVo);
		request.setAttribute("olist", olist);
		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/board/help/modifyForm.jsp");
		return forward;
	}
}
