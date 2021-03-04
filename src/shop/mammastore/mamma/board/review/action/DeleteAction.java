package shop.mammastore.mamma.board.review.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;
import shop.mammastore.mamma.board.review.service.ReviewService;
import shop.mammastore.mamma.vo.ReviewVo;

public class DeleteAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		
		String mber_sq = lm.getMemberId(session);
		if (mber_sq == null || mber_sq.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}

		// review_sq 유효성검사
		String review_sq = request.getParameter("review_sq");
		if (review_sq == null || review_sq.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}
		
		// HelpVo 데이터 저장
		ReviewVo reviewVo = new ReviewVo();
		reviewVo.setReview_sq(Integer.parseInt(review_sq));

		// 데이터 베이스에 등록
		ReviewService svc = new ReviewService();
		if (!svc.deleteReview(reviewVo)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('1:1문의 삭제에 실패하였습니다..); history.back(); </script>");
			out.close();
			return null;
		}

		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/mymenu/myReview");
		return forward;
	}
}
