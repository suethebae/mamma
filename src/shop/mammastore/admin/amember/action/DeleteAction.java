package shop.mammastore.admin.amember.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.amember.service.AmemberService;
import shop.mammastore.admin.vo.AmemberVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;

public class DeleteAction implements Action{
@Override
public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	HttpSession session = request.getSession();
	LoginManager lm = LoginManager.getInstance();
	String mngr_sq = lm.getMemberId(session);
	String mber_sq = request.getParameter("sq");
	
	if(mngr_sq==null||mngr_sq.equals("")) {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('잘못된 접근입니다.'); loaction.href='/'; </script>"); 
		out.close();
		return null;
	}
	
	if (mber_sq == null || mber_sq.equals("")) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
		out.close();
		return null;
	}
	
	AmemberVo amemberVo = new AmemberVo();
	amemberVo.setMber_sq(Integer.parseInt(mber_sq));
	
	AmemberService svc = new AmemberService();
	if (!svc.deleteMember(amemberVo)) {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('회원정보를 지우는데 실패했습니다.'); loaction.href='/'; </script>"); 
		out.close();
	}
	
	//경로설정
	ActionForward forward = new ActionForward();
	forward.setPath("/amember/list");
	return forward;
}
}
