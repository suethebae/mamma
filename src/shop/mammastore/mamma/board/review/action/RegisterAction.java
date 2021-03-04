package shop.mammastore.mamma.board.review.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.aitem.service.AitemService;
import shop.mammastore.admin.vo.AitemVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;

public class RegisterAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String mber_sq = lm.getMemberId(session);

		if (mber_sq == null || mber_sq.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('리뷰는 회원만 작성 가능합니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}
		String itm_sq = request.getParameter("itm_sq");
		String order_cd = request.getParameter("order_cd");
		String fl_pth = request.getParameter("fl_pth");
		
		AitemService svc = new AitemService();
		AitemVo aitemVo = svc.getItemDetail(Integer.parseInt(itm_sq));
		if (aitemVo == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('상품을 불러오는데 실패 했습니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}
		
		// 받아온 데이터 업로드
		request.setAttribute("order_cd", order_cd);
		request.setAttribute("aitemVo", aitemVo);
		request.setAttribute("fl_pth", fl_pth);
		
		// sq를 한글로 가져오고싶어!
		ArrayList<AitemVo> list = null;
		AitemService svc1 = new AitemService();
		list = svc1.getItemList();
		if (list == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('카테고리를 불러오는데 실패했습니다.'); history.back(); </script>");
			out.close();
			return null;
		}

		request.setAttribute("list", list);
		
		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/board/review/registerForm.jsp");
		return forward;
	}
}
