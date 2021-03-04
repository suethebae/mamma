package shop.mammastore.mamma.board.review.action;

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

public class ModifyProcAction implements Action {
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
		
		// reveiw_sq값이 null인지 확인
		String review_sq = request.getParameter("review_sq");
		String order_cd = request.getParameter("order_cd");
		if (review_sq == null || review_sq.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}
		
		// sj, cntnt유효성 검사
		String cntnt = request.getParameter("content");
		String sj = request.getParameter("sj");
		if (RegExp.isEmpty(sj) || RegExp.isEmpty(cntnt)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}
		
		String evl = request.getParameter("star-input");
		
		// sj, review_sq 데이터 vo에 넣기
		ReviewVo reviewVo = new ReviewVo();
		reviewVo.setReview_sq(Integer.parseInt(review_sq));
		reviewVo.setSj(sj);
		reviewVo.setCntnt(Parser.chgToStr(cntnt));
		reviewVo.setOrder_cd(order_cd);
		reviewVo.setEvl(Integer.parseInt(evl));
		
		// db에 Vo전달하여 등록
		ReviewService svc = new ReviewService();
		if (!svc.modify(reviewVo)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('리뷰 수정에 실패했습니다.'); history.back(); </script>");
			out.close();
			return null;
		}
		
		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/review/detail?review_sq" + reviewVo.getReview_sq());
		return forward;
	}
}
