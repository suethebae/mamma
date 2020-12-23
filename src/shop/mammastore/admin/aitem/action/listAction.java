package shop.mammastore.admin.aitem.action;

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

public class listAction implements Action{
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
	
	ArrayList<AitemVo> list = null;
	AitemService svc = new AitemService();
	list = svc.getAitemList();
	if(list==null) {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('상품목록 리스트를 불러오는데 실패했습니다.'); history.back(); </script>");
		out.close();
		return null;
	}
	
	request.setAttribute("list", list);
	
	//경로설정
	ActionForward forward = new ActionForward();
	forward.setPath("/views/admin/aitem/list.jsp");
	return forward;
}
}
