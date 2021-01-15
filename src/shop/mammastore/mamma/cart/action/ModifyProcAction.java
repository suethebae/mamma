package shop.mammastore.mamma.cart.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;
import shop.mammastore.mamma.cart.service.CartService;
import shop.mammastore.mamma.vo.CartVo;

public class ModifyProcAction implements Action {
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

		// cart_sq를 로드
		String cart_sq = request.getParameter("cart_sq");
		String itm_cnt = request.getParameter("itm_cnt");

		if (cart_sq == null || cart_sq.equals("") || itm_cnt == null || itm_cnt.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); loaction.href='/'; </script>");
			out.close();
			return null;
		}

		CartVo cartVo = new CartVo();
		cartVo.setCart_sq(Integer.parseInt(cart_sq));
		cartVo.setItm_cnt(Integer.parseInt(itm_cnt));

		CartService svc = new CartService();

		// 데이터 베이스에 물품수량 수정한거 올리기
		if (!svc.modify(cartVo)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('수정에 실패 했습니다.'); history.back(); </script>");
			out.close();
			return null;
		}

		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/cart/list");
		return forward;
	}
}