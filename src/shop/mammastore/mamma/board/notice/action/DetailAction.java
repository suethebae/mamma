package shop.mammastore.mamma.board.notice.action;

import static shop.mammastore.common.RegExp.REGEXP_NUMBER;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.mammastore.admin.vo.AnoticeVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.Parser;
import shop.mammastore.common.RegExp;
import shop.mammastore.mamma.board.notice.service.NoticeService;

public class DetailAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 공지사항 글 확인
		String notice_sq = request.getParameter("sq");
		if (!RegExp.isValidExp(notice_sq, REGEXP_NUMBER)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}
		// anoticeVo에 데이터 담기
		NoticeService svc = new NoticeService();
		AnoticeVo anoticeVo = svc.getNoticeDetail(Integer.parseInt(notice_sq));
		if (anoticeVo == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('공지사항을 불러오는데 실패 했습니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}
		anoticeVo.setCntnt(Parser.chgToHTML(anoticeVo.getCntnt()));
		// 데이터 전송
		request.setAttribute("anoticeVo", anoticeVo);

		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/board/notice/detail.jsp");
		return forward;
	}
}
