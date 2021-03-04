package shop.mammastore.admin.aboard.anotice.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.aboard.anotice.service.AnoticeService;
import shop.mammastore.admin.vo.AnoticeVo;
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
			out.println("<script>location.href='/anotice/list?pn=1';</script>");
			out.close();
			return null;
		}
		int page = Integer.parseInt(pn);

		// 공지사항 목록 받아오기
		ArrayList<AnoticeVo> list = null;
		AnoticeService svc = new AnoticeService();
		
		// 페이지네이션
		Pagenation pagenation = new Pagenation(page, svc.getNoticeCount());

		// 끝 이상으로 넘어가면 마지막 페이지 표시
		if (page > pagenation.getTotalPageCount()) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/anotice/list?pn=" + pagenation.getTotalPageCount() + "';</script>");
			out.close();
			return null;
		}

		list = svc.getNoticeList(pagenation);
		if (list == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('공지사항 목록을 불러오는데 실패했습니다.'); history.back(); </script>");
			out.close();
			return null;
		}

		request.setAttribute("list", list);
		request.setAttribute("pagenation", pagenation);

		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/board/notice/noticeList.jsp?pn="+page);
		return forward;
	}
}
