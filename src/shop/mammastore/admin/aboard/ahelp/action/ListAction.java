package shop.mammastore.admin.aboard.ahelp.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.aboard.ahelp.service.HelpService;
import shop.mammastore.admin.vo.AhelpVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.Pagenation;

public class ListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 관리자 로그인 체크
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
			out.println("<script>location.href='/ahelp/list?pn=1';</script>");
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
			filter = "i.id";
		}
		if (filter.equals("order_cd")) {
			filter = "i1.order_cd";
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
			query += " and date_format(i1.dttm, '%Y-%m-%d')>='" + startDate + "'";
		}
		if (endDate == null || endDate.equals("")) {
			endDate = "";
		} else {
			query += " and date_format(i1.dttm, '%Y-%m-%d')<='" + endDate + "'";
		}

		// sfilter
		if (sfilter == null || sfilter.equals("")) {
			sfilter = "";
		} else {
			query += " and i1.answer_fl=" + Boolean.parseBoolean(sfilter);
		}

		// db에 등록
		HelpService svc = new HelpService();

		// 페이지네이션
		Pagenation pagenation = new Pagenation(page, svc.getHelpCount(query));
		// 끝 이상으로 넘어가면 마지막 페이지 표시
		if (page > pagenation.getTotalPageCount()) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/ahelp/list?pn=" + pagenation.getTotalPageCount() + "';</script>");
			out.close();
			return null;
		}

		ArrayList<AhelpVo> list = svc.getHelpList(pagenation, query);
		if (list == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('1:1 문의 리스트를 불러오는데 실패했습니다.'); history.back(); </script>");
			out.close();
			return null;
		}
		// 받아온 데이터 업로드
		request.setAttribute("list", list);
		request.setAttribute("pagenation", pagenation);
		request.setAttribute("filter", filter);
		request.setAttribute("keyword", keyword);
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		request.setAttribute("sfilter", sfilter);

		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/board/help/list.jsp?pn="+page);
		return forward;
	}
}
