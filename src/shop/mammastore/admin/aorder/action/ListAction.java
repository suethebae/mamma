package shop.mammastore.admin.aorder.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.aorder.service.AorderService;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.Pagenation;
import shop.mammastore.mamma.vo.OrderListVo;

public class ListAction implements Action {
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
		// 페이지 넘버 받아오기
		String pn = request.getParameter("pn");
		if (pn == null || pn == "") {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/aorder/list?pn=1';</script>");
			out.close();
			return null;
		}
		int page = Integer.parseInt(pn);

		// filter, keyword, startDate, endDate, sfilter
		String filter = request.getParameter("filter");
		String keyword = request.getParameter("keyword");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String sfilter = request.getParameter("sfilter");
		String query = "";

		// filter와 keyword 판별
		if (filter == null || filter.equals("")) {
			filter = "";
		}
		if (filter.equals("id")) {
			filter = "b.id";
		}
		if (filter.equals("order_cd")) {
			filter = "a.order_cd";
		}
		if (filter.equals("") || keyword == null || keyword.equals("")) {
			keyword = "";
		}
		if (!filter.equals("") && !keyword.equals("")) {
			query += " and " + filter + " ='" + keyword + "'";
		}

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
		// inf_order_tb에서 주문 내역 데이터 가지고 오기(order_sq, order_cd, sttus, all_pc)
		AorderService svc = new AorderService();

		// 페이지네이션
		Pagenation pagenation = new Pagenation(page, svc.getOrderCount(query));
		// 끝 이상으로 넘어가면 마지막 페이지 표시
		if (page > pagenation.getTotalPageCount()) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/aorder/list?pn=" + pagenation.getTotalPageCount() + "';</script>");
			out.close();
			return null;
		}

		ArrayList<OrderListVo> list = svc.getOrderList(pagenation, query);

		if (list == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('주문목록 로드에 실패했습니다.'); history.back(); </script>");
			out.close();
			return null;
		}

		// 클라우드에 올림
		request.setAttribute("list", list);
		request.setAttribute("pagenation", pagenation);
		request.setAttribute("filter", filter);
		request.setAttribute("keyword", keyword);
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		request.setAttribute("sfilter", sfilter);
		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/aorder/list.jsp?pn="+page);
		return forward;
	}
}
