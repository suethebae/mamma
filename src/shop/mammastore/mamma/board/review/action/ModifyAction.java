package shop.mammastore.mamma.board.review.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;
import shop.mammastore.common.RegExp;
import shop.mammastore.mamma.board.review.service.ReviewService;
import shop.mammastore.mamma.vo.ReviewVo;

public class ModifyAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		// 로그인 확인
		String mber_sq = lm.getMemberId(session);
		if (mber_sq == null || mber_sq.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('리뷰는 회원만 작성 가능합니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}

		// review_sq 데이터 검사
		String review_sq = request.getParameter("review_sq");
		if (RegExp.isEmpty(review_sq)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}	

		// db에 등록
		ReviewService svc = new ReviewService();
		ReviewVo reviewVo = svc.getReviewDetail(Integer.parseInt(review_sq));
		if (reviewVo == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('작성한 리뷰를 불러들이는데 실패했습니다.'); history.back(); </script>");
			out.close();
			return null;
		}
		
		request.setAttribute("reviewVo", reviewVo);
		
		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/board/review/modifyForm.jsp");
		return forward;
	}
}
