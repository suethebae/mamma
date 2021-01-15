package shop.mammastore.mamma.cart.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;
import shop.mammastore.mamma.cart.service.CartService;
import shop.mammastore.mamma.vo.CartListVo;

public class ListAction implements Action {
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

		int imber_sq = Integer.parseInt(mber_sq);

		// cartService 불러오기
		CartService svc = new CartService();

		// 데이터 베이스에 등록
		ArrayList<CartListVo> list = svc.getCartList(imber_sq);
		if (list == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('장바구니 등록에 실패 했습니다.'); history.back(); </script>");
			out.close();
			return null;
		}

		request.setAttribute("list", list);

		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/cart/list.jsp");
		return forward;
	}
}
