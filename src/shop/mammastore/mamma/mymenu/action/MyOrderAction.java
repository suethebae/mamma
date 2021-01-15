package shop.mammastore.mamma.mymenu.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;
import shop.mammastore.mamma.order.service.OrderService;
import shop.mammastore.mamma.vo.OrderListVo;

public class MyOrderAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 확인
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
		// mber_sq기준으로 inf_order_tb에서 주문 내역 데이터 가지고 오기(order_sq, order_code, sttus, all_pc)
		OrderService svc = new OrderService();
		ArrayList<OrderListVo> list = svc.getOrderList(Integer.parseInt(mber_sq));

		if (list == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('주문목록 로드에 실패했습니다.'); history.back(); </script>");
			out.close();
			return null;
		}
		// order_code를 기준으로 inf_order_detail_tb와 inf_itm_tb 주문 상품 데이터 가지고 오기(order_code, orderDetail_cd, itm_sq, itm_cnt(inf_orderdetail_tb),pc,nm,fl_pth)

		// 클라우드에 올림
		request.setAttribute("list", list);

		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/mymenu/myOrder.jsp");
		return forward;
	}
}
