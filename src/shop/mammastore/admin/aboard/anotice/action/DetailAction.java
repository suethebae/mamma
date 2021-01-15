package shop.mammastore.admin.aboard.anotice.action;

import static shop.mammastore.common.RegExp.REGEXP_NUMBER;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.aboard.anotice.service.AnoticeService;
import shop.mammastore.admin.vo.AnoticeVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;
import shop.mammastore.common.Parser;
import shop.mammastore.common.RegExp;

public class DetailAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		// 매니저 로그인 확인
		String mngr_sq = lm.getMemberId(session);
		if (mngr_sq == null || mngr_sq.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); loaction.href='/'; </script>");
			out.close();
			return null;
		}
		// 공지사항 글 확인
		String notice_sq = request.getParameter("sq");
		if (!RegExp.isValidExp(notice_sq, REGEXP_NUMBER)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); loaction.href='/'; </script>");
			out.close();
			return null;
		}

		// anoticeVo에 데이터 담기
		AnoticeService svc = new AnoticeService();
		AnoticeVo anoticeVo = svc.getNoticeDetail(Integer.parseInt(notice_sq));
		if (anoticeVo == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('공지사항을 불러오는데 실패 했습니다.'); loaction.href='/'; </script>");
			out.close();
			return null;
		}
		anoticeVo.setCntnt(Parser.chgToHTML(anoticeVo.getCntnt()));

		// 데이터 전송
		request.setAttribute("anoticeVo", anoticeVo);
		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/board/notice/detail.jsp");
		return forward;
	}
}
