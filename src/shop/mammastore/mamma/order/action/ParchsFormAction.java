package shop.mammastore.mamma.order.action;

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

public class ParchsFormAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String mber_sq = lm.getMemberId(session);

		if (mber_sq == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); loaction.href='/'; </script>");
			out.close();
			return null;
		}

		// cart_sq 가지고 오기
		String cart_sq[] = request.getParameterValues("cart_sq");

		if (cart_sq == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('선택한 상품이 없습니다.'); history.back(); </script>");
			out.close();
			return null;
		}

		// arraylist 형태 cart_sq에 대한 상세 내용 가지고 오기
		OrderService svc = new OrderService();
		ArrayList<CartListVo> list = svc.getItemList(cart_sq);

		// arraylist 클라우드에 업로드
		request.setAttribute("list", list);

		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/order/parchsForm.jsp");
		return forward;
	}
}
