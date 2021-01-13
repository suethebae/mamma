package shop.mammastore.admin.aboard.ahelp.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;

public class WriteProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String mber_sq = lm.getMemberId(session);
		
		if(mber_sq==null||mber_sq.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인을 해야 접근할 수 있습니다.'); loaction.href='/'; </script>"); 
			out.close();
			return null;
		} 
		
		
		
		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/board/help/writeForm.jsp");
		return forward;
	}
}
