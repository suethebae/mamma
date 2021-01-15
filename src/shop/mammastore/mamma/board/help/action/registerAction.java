package shop.mammastore.mamma.board.help.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;
import shop.mammastore.mamma.board.help.service.HelpService;
import shop.mammastore.mamma.vo.HelpVo;

public class registerAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String mber_sq = lm.getMemberId(session);

		if (mber_sq == null||mber_sq.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}
		
		HelpService svc = new HelpService();
		ArrayList<HelpVo> olist = svc.getOrderCode(Integer.parseInt(mber_sq));
		
		// 받아온 데이터 업로드
		request.setAttribute("olist", olist);
		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/board/help/registerForm.jsp");
		return forward;
	}
}
