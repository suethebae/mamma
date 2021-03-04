package shop.mammastore.mamma.board.notice.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.mammastore.admin.vo.AnoticeVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.Pagenation;
import shop.mammastore.mamma.board.notice.service.NoticeService;

public class ListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 페이지 넘버 받아오기
		String pn = request.getParameter("pn");
		if (pn == null || pn == "") {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/notice/list?pn=1';</script>");
			out.close();
			return null;
		}
		int page = Integer.parseInt(pn);

		// 공지사항 목록 받아오기
		ArrayList<AnoticeVo> list = null;
		NoticeService svc = new NoticeService();

		// 페이지네이션
		Pagenation pagenation = new Pagenation(page, svc.getNoticeCount());

		// 끝 이상으로 넘어가면 마지막 페이지 표시
		if (page > pagenation.getTotalPageCount()) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/notice/list?pn=" + pagenation.getTotalPageCount() + "';</script>");
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
		forward.setPath("/views/board/notice/noticeList.jsp?pn=" + page);
		return forward;
	}
}
