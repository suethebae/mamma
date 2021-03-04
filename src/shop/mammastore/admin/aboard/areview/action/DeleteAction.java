package shop.mammastore.admin.aboard.areview.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.aboard.areview.service.AreviewService;
import shop.mammastore.admin.vo.AreviewVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.RegExp;

public class DeleteAction implements Action {
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
		// 리뷰 상세
		String review_sq = request.getParameter("review_sq");
		if (RegExp.isEmpty(review_sq)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}
		// 리뷰 삭제
		AreviewVo areviewVo = new AreviewVo();
		areviewVo.setReview_sq(Integer.parseInt(review_sq));

		AreviewService svc = new AreviewService();
		if (!svc.deleteReview(areviewVo)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('게시글 삭제에 실패하였습니다..); history.back(); </script>");
			out.close();
			return null;
		}
		
		request.setAttribute("areviewVo", areviewVo);
		
		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/areview/list");
		return forward;
	}
}
