package shop.mammastore.mamma.cart.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.vo.ActgryVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;
import shop.mammastore.common.RegExp;
import shop.mammastore.mamma.cart.service.CartService;
import shop.mammastore.mamma.vo.CartVo;

public class DeleteAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
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

		String cart_sq = request.getParameter("cart_sq");
		if (cart_sq == null || cart_sq.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); loaction.href='/'; </script>");
			out.close();
			return null;
		}

		CartService svc = new CartService();
		if (!svc.delete(Integer.parseInt(cart_sq))) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('장바구니 상품 삭제에 실패하였습니다..); history.back(); </script>");
			out.close();
			return null;
		}

		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/cart/list");
		return forward;
	}
}