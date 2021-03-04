package shop.mammastore.admin.aorder.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.aorder.service.AorderService;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.RegExp;

public class ChangeSttusAction implements Action {
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
		
		// order_sq 로드 및 유효성 검사
		String order_sq[] = request.getParameterValues("order_sq");
		String sttus = request.getParameter("sttus");
		if (order_sq == null || RegExp.isEmpty(sttus)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
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
