package shop.mammastore.mamma.cart.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;
import shop.mammastore.mamma.cart.service.CartService;
import shop.mammastore.mamma.vo.CartListVo;

public class ModifyAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();

		String mber_sq = lm.getMemberId(session);
		// 로그인 여부 확인
		if (mber_sq == null || mber_sq.equals("")) {
			ActionForward forward = new ActionForward();
			forward.setPath("/member/login");
			return forward;
		}

		// cart_sq 인자값 보내주기
		String cart_sq = request.getParameter("cart_sq");
		
		// cartService 불러오기
		CartService svc = new CartService();
		CartListVo cartListVo = svc.getTempDetail(Integer.parseInt(cart_sq));

		// cart_sq를 로드
		if (cart_sq == null || cart_sq.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); loaction.href='/'; </script>");
			out.close();
			return null;
		}

		
		if (cartListVo == null || cartListVo.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('장바구니 로딩에 실패했습니다.'); loaction.href='/'; </script>");
			out.close();
			return null;
		}

		request.setAttribute("cartListVo", cartListVo);
		

		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/cart/modifyForm.jsp");
		return forward;
	}
}