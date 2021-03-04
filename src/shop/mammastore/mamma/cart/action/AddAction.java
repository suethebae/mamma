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

public class AddAction implements Action {
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

		// item_sq를 로드
		String temp = request.getParameter("itm_sq");
		if (temp == null || temp.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}
		int itm_sq = Integer.parseInt(temp);

		// itm_cnt 로드
		String temp1 = request.getParameter("itm_cnt");
		if (temp1 == null || temp1.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}
		int itm_cnt = Integer.parseInt(temp1);
		
		// 계속 쇼핑 하는 지 확인
		String maintainBuy = request.getParameter("maintainBuy");
		
		// cartVO 생성
		CartVo cartVo = new CartVo();

		// cartVO에 mber_sq, itm_sq, itm_cnt 집어넣기
		cartVo.setMber_sq(Integer.parseInt(mber_sq));
		cartVo.setItm_sq(itm_sq);
		cartVo.setItm_cnt(itm_cnt);

		// cartService 불러오기
		CartService svc = new CartService();

		// 데이터 베이스에 등록
		if (!svc.add(cartVo)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('장바구니 등록에 실패 했습니다.'); history.back(); </script>");
			out.close();
			return null;
		}

		// 경로설정
		if(Boolean.parseBoolean(maintainBuy)) {
			ActionForward forward = new ActionForward();
			forward.setPath("/item/detail?itm_sq=" + itm_sq);
			forward.setRedirect(true);
			return forward;	
		}
		else {
			ActionForward forward = new ActionForward();
			forward.setPath("/cart/list");
			forward.setRedirect(true);
			return forward;	
		}
		
	}
}
