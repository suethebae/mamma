package shop.mammastore.mamma.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.BCrypt;
import shop.mammastore.common.LoginManager;
import shop.mammastore.common.RegExp;
import shop.mammastore.mamma.member.service.MemberService;
import shop.mammastore.mamma.vo.MemberVo;

public class LoginProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String mber_sq = lm.getMemberId(session);
		
		if(mber_sq != null) {
			ActionForward forward = new ActionForward();
			forward.setPath("/");
			forward.setRedirect(true);
			return forward;
		}
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");

		if (RegExp.isEmpty(id) || RegExp.isEmpty(pwd)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>"); 
			out.close();
			return null;
		}
		
		MemberService svc = new MemberService();
		MemberVo memberVo = svc.getLoginInfo(id);
		if (memberVo == null || !BCrypt.checkpw(pwd, memberVo.getPwd())) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 실패'); history.back(); </script>");
			out.close();
			return null;
		}
		
		if(!svc.registerHistory(memberVo)){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 히스토리 실패'); history.back(); </script>");
			out.close();
			return null;
		}
		mber_sq = Integer.toString(memberVo.getMber_sq());
		lm.setSession(request.getSession(), mber_sq);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/");
		return forward;
	}

}
