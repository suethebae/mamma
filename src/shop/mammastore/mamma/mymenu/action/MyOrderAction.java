package shop.mammastore.mamma.mymenu.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;
import shop.mammastore.common.Pagenation;
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
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}

		// 페이지 넘버 받아오기
		String pn = request.getParameter("pn");
		if (pn == null || pn == "") {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/mymenu/myOrder?pn=1';</script>");
			out.close();
			return null;
		}
		int page = Integer.parseInt(pn);

		// startDate, endDate, sfilter
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String sfilter = request.getParameter("sfilter");
		String query = "";

		// startDate, endDate
		if (startDate == null || startDate.equals("")) {
			startDate = "";
		} else {
			query += " and date_format(a.dttm, '%Y-%m-%d')>='" + startDate + "'";
		}
		if (endDate == null || endDate.equals("")) {
			endDate = "";
		} else {
			query += " and date_format(a.dttm, '%Y-%m-%d')<='" + endDate + "'";
		}

		// sfilter
		if (sfilter == null || sfilter.equals("")) {
			sfilter = "";
		} else {
			query += " and a.sttus=" + sfilter;
		}

		// mber_sq기준으로 inf_order_tb에서 주문 내역 데이터 가지고 오기(order_sq, order_cd, sttus,
		// all_pc)
		OrderService svc = new OrderService();

		// 페이지네이션
		Pagenation pagenation = new Pagenation(page, svc.getOrderCount(Integer.parseInt(mber_sq), query));

		// 끝 이상으로 넘어가면 마지막 페이지 표시
		if (page > pagenation.getTotalPageCount()) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/mymenu/myOrder?pn=" + pagenation.getTotalPageCount() + "';</script>");
			out.close();
			return null;
		}

		ArrayList<OrderListVo> list = svc.getOrderList(Integer.parseInt(mber_sq), pagenation, query);

		if (list == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('주문목록 로드에 실패했습니다.'); history.back(); </script>");
			out.close();
			return null;
		}
		// order_cd를 기준으로 inf_order_detail_tb와 inf_itm_tb 주문 상품 데이터 가지고 오기(order_cd,
		// orderDetail_cd, itm_sq, itm_cnt(inf_orderdetail_tb),pc,nm,fl_pth)

		// 클라우드에 올림
		request.setAttribute("list", list);
		request.setAttribute("pagenation", pagenation);
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		request.setAttribute("sfilter", sfilter);
		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/mymenu/myOrder.jsp?pn="+page);
		return forward;
	}
}
