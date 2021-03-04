package shop.mammastore.mamma.board.review.action;

import static shop.mammastore.common.RegExp.REGEXP_NUMBER;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;
import shop.mammastore.common.Parser;
import shop.mammastore.common.RegExp;
import shop.mammastore.mamma.board.review.service.ReviewService;
import shop.mammastore.mamma.vo.ReviewVo;

public class DetailAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		// 로그인 확인
		String mber_sq = lm.getMemberId(session);
		if (mber_sq == null || mber_sq.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}
		// review_sq 유효성 검사
		String review_sq = request.getParameter("review_sq");
		if (!RegExp.isValidExp(review_sq, REGEXP_NUMBER)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}
		// revew_sq 기준으로 db에 등록
		ReviewService svc = new ReviewService();
		ReviewVo reviewVo = svc.getReviewDetail(Integer.parseInt(review_sq));

		if (reviewVo == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('리뷰를 불러오는데 실패 했습니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}
		
		reviewVo.setCntnt(Parser.chgToHTML(reviewVo.getCntnt()));
		
		// 받아온 데이터 업로드
		request.setAttribute("reviewVo", reviewVo);
		
		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/board/review/detail.jsp");
		return forward;
	}
}
