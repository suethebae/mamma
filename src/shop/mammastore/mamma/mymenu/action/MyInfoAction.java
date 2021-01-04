package shop.mammastore.mamma.mymenu.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;
import shop.mammastore.mamma.member.service.MemberService;
import shop.mammastore.mamma.vo.MemberVo;

public class MyInfoAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String mber_sq = lm.getMemberId(session);

		if (mber_sq == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}
		
		MemberService svc = new MemberService();
		MemberVo memberVo = svc.getUserData(Integer.parseInt(mber_sq));
		if (memberVo == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원정보를 불러오는데 실패하였습니다.'); history.back(); </script>");
			out.close();
			return null;
		}
		request.setAttribute("memberVo", memberVo);
		
		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/mymenu/myInfo.jsp");
		return forward;
	}
}
