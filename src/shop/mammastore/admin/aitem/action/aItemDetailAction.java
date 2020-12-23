package shop.mammastore.admin.aitem.action;

import java.io.PrintWriter;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static shop.mammastore.common.RegExp.REGEXP_NUMBER;
import shop.mammastore.admin.aitem.service.AitemService;
import shop.mammastore.admin.amanager.service.AmanagerService;
import shop.mammastore.admin.vo.AitemVo;
import shop.mammastore.admin.vo.AmanagerVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;
import shop.mammastore.common.RegExp;

public class aItemDetailAction implements Action{
@Override
public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
	String itm_sq = request.getParameter("sq");
	if(!RegExp.isValidExp(itm_sq, REGEXP_NUMBER)) {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('잘못된 접근입니다.'); loaction.href='/'; </script>"); 
		out.close();
		return null;
	}
	
	/* String ditm_sq = request.getParameter("itm_sq"); */
	
	AitemService svc = new AitemService();
	AitemVo aitemVo = svc.getAitemDetail(Integer.parseInt(itm_sq));
	
	if (aitemVo==null) {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('상품 정보 로드에 실패 했습니다.'); loaction.href='/'; </script>"); 
		out.close();
		return null;
	}
	
	
	
	
	request.setAttribute("aitemVo", aitemVo);
	
	//경로설정
	ActionForward forward = new ActionForward();
	forward.setPath("/views/admin/aitem/detail.jsp");
	return forward;
}
}
