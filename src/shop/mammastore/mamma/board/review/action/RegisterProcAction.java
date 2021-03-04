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

public class RegisterProcAction implements Action {
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
		// order_cd, sj, content, evl를 로드
		String order_cd = request.getParameter("order_cd");
		String sj = request.getParameter("sj");
		String cntnt = request.getParameter("content");
		String evl = request.getParameter("star-input");
		String itm_sq = request.getParameter("itm_sq");

		// sj, cntnt 유효성 검사
		if (RegExp.isEmpty(sj) || RegExp.isEmpty(cntnt)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}

		// sj 데이터 vo 넣기
		ReviewVo reviewVo = new ReviewVo();
		reviewVo.setSj(sj);
		reviewVo.setOrder_cd(order_cd);
		reviewVo.setCntnt(Parser.chgToStr(cntnt));
		reviewVo.setMber_sq(Integer.parseInt(mber_sq));
		reviewVo.setEvl(Integer.parseInt(evl));
		reviewVo.setItm_sq(Integer.parseInt(itm_sq));

		// db에 Vo전달하여 카테고리 입력
		ReviewService svc = new ReviewService();
		if (!svc.register(reviewVo)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('리뷰 작성에 실패하였습니다.'); history.back(); </script>");
			out.close();
			return null;
		}

		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/mymenu/myReview");
		return forward;
	}
}
