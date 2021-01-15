package shop.mammastore.admin.aorder.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;
import shop.mammastore.mamma.order.service.OrderService;
import shop.mammastore.mamma.vo.CartListVo;
import shop.mammastore.mamma.vo.OrderListVo;
import shop.mammastore.mamma.vo.OrderVo;

public class CancelAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String mber_sq = lm.getMemberId(session);
		//order_sq 로드 및 유효성 검사
		String order_sq = request.getParameter("order_sq");
		if (mber_sq == null||order_sq==null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); loaction.href='/'; </script>");
			out.close();
			return null;
		}
		
		//db에서 order_sq를 기준으로 sttus 6으로 업데이트
		OrderVo orderVo = new OrderVo();
		orderVo.setOrder_sq(Integer.parseInt(order_sq));
		orderVo.setSttus(6);
		OrderService svc = new OrderService();
		if(!svc.changeSttus(orderVo)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('주문 취소에 실패 했습니다.'); history.back(); </script>");
			out.close();
			return null;
		}
		
		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/mymenu/myOrder");
		return forward;
	}
}
