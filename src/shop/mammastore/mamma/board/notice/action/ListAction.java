package shop.mammastore.mamma.board.notice.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.mammastore.admin.vo.AnoticeVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.mamma.board.notice.service.NoticeService;

public class ListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 공지사항 목록 받아오기
		ArrayList<AnoticeVo> list = null;
		NoticeService svc = new NoticeService();
		list = svc.getNoticeList();
		if (list == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('공지사항 목록을 불러오는데 실패했습니다.'); history.back(); </script>");
			out.close();
			return null;
		}

		request.setAttribute("list", list);

		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/board/notice/noticeList.jsp");
		return forward;
	}
}
