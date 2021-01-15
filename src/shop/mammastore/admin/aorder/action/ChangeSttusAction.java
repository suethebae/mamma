package shop.mammastore.admin.aorder.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.aorder.service.AorderService;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;
import shop.mammastore.common.RegExp;

public class ChangeSttusAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String mngr_sq = lm.getMemberId(session);
		// order_sq 로드 및 유효성 검사
		String order_sq[] = request.getParameterValues("order_sq");
		String sttus = request.getParameter("sttus");
		if (mngr_sq == null || order_sq == null || RegExp.isEmpty(sttus)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); loaction.href='/'; </script>");
			out.close();
			return null;
		}

		// db에서 order_sq를 기준으로 sttus 변경
		AorderService svc = new AorderService();
		if (!svc.changeSttus(order_sq, Integer.parseInt(sttus))) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('주문상태 변경에 실패 했습니다.'); history.back(); </script>");
			out.close();
			return null;
		}

		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/aorder/list");
		return forward;
	}
}
