package shop.mammastore.admin.aitem.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.actgry.service.ActgryService;
import shop.mammastore.admin.vo.ActgryVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;

public class RegisterAction implements Action{
@Override
public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	HttpSession session = request.getSession();
	LoginManager lm = LoginManager.getInstance();
	String mngr_sq = lm.getMemberId(session);
	
	if(mngr_sq==null||mngr_sq.equals("")) {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('잘못된 접근입니다.'); loaction.href='/'; </script>"); 
		out.close();
		return null;
	}
	
	ArrayList<ActgryVo> list = null;
	ActgryService svc = new ActgryService();
	list = svc.getCtgryList();
	if (list == null) {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('카테고리를 불러오는데 실패했습니다.'); history.back(); </script>");
		out.close();
		return null;
	}
		
	request.setAttribute("list", list);
	
	//경로설정
	ActionForward forward = new ActionForward();
	forward.setPath("/views/admin/aitem/registerForm.jsp");
	return forward;
}
}
