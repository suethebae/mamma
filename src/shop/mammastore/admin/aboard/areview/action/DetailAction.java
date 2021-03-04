package shop.mammastore.admin.aboard.areview.action;

import static shop.mammastore.common.RegExp.REGEXP_NUMBER;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.aboard.areview.service.AreviewService;
import shop.mammastore.admin.vo.AreviewVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.Parser;
import shop.mammastore.common.RegExp;

public class DetailAction implements Action {
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
		// 리뷰 글 확인
		String review_sq = request.getParameter("sq");
		if (!RegExp.isValidExp(review_sq, REGEXP_NUMBER)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}

		// areviewVo에 데이터 담기
		AreviewService svc = new AreviewService();
		AreviewVo areviewVo = svc.getReviewDetail(Integer.parseInt(review_sq));
		if (areviewVo == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('리뷰을 불러오는데 실패 했습니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}
		areviewVo.setCntnt(Parser.chgToHTML(areviewVo.getCntnt()));

		// 데이터 전송
		request.setAttribute("areviewVo", areviewVo);
		
		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/board/review/detail.jsp");
		return forward;
	}
}
