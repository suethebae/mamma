package shop.mammastore.admin.amember.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.amember.service.AmemberService;
import shop.mammastore.admin.vo.AmemberVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.Pagenation;

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
			out.println("<script>location.href='/amember/list?pn=1';</script>");
			out.close();
			return null;
		}
		int page = Integer.parseInt(pn);

		// filter, keyword, startDate, endDate, sfilter
		String filter = request.getParameter("filter");
		String keyword = request.getParameter("keyword");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String query = "";

		// filter와 keyword 판별
		if (filter == null || filter.equals("")) {
			filter = "";
		}
		if (filter.equals("id")) {
			filter = "id";
		}
		if (filter.equals("nm")) {
			filter = "nm";
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
			query += " and date_format(dttm, '%Y-%m-%d')>='" + startDate + "'";
		}
		if (endDate == null || endDate.equals("")) {
			endDate = "";
		} else {
			query += " and date_format(dttm, '%Y-%m-%d')<='" + endDate + "'";
		}

		// 회원 정보 담을 곳 생성
		ArrayList<AmemberVo> list = null;
		// 회원 정보 담음
		AmemberService svc = new AmemberService();
		// 페이지네이션
		Pagenation pagenation = new Pagenation(page, svc.getMemberCount(query));
		// 끝 이상으로 넘어가면 마지막 페이지 표시
		if (page > pagenation.getTotalPageCount()) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/amember/list?pn=" + pagenation.getTotalPageCount() + "';</script>");
			out.close();
			return null;
		}

		list = svc.getMberList(pagenation, query);
		if (list == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원 정보를 불러오는데 실패했습니다.'); history.back(); </script>");
			out.close();
			return null;
		}

		// 회원 정보 클라우드에 업로드
		request.setAttribute("list", list);
		request.setAttribute("pagenation", pagenation);
		request.setAttribute("filter", filter);
		request.setAttribute("keyword", keyword);
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/amember/list.jsp?pn="+page);
		return forward;
	}
}