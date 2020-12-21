package shop.mammastore.admin.aitem.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.aitem.service.AitemService;
import shop.mammastore.admin.vo.AitemVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;
import shop.mammastore.common.RegExp;

public class registerItemProcAction implements Action{
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
	
	String nm = request.getParameter("nm");
	String pc = request.getParameter("pc");
	String stock = request.getParameter("stock");
	String cntnt = request.getParameter("content");
	
	if(RegExp.isEmpty(nm)||RegExp.isEmpty(pc)||RegExp.isEmpty(stock)||RegExp.isEmpty(cntnt)) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
		out.close();
		return null;
	}
	
	cntnt=cntnt.replaceAll("&","&amp");
	cntnt=cntnt.replaceAll("<", "&lt");
	cntnt=cntnt.replaceAll(">", "&gt");
	cntnt=cntnt.replaceAll(" ", "&nbsp");
	cntnt=cntnt.replaceAll("'", "&#039");
	cntnt=cntnt.replaceAll("/", "&quot");
	

	AitemVo aitemVo = new AitemVo();
	aitemVo.setNm(nm);
	aitemVo.setPc(Integer.parseInt(pc));
	aitemVo.setStock(Integer.parseInt(stock));
	aitemVo.setCntnt(cntnt);
	
	AitemService svc = new AitemService();
	if(!svc.register(aitemVo)) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('상품등록에 실패하였습니다.'); history.back(); </script>");
		out.close();
		return null;
	}
	
	//경로설정
	ActionForward forward = new ActionForward();
	forward.setPath("/aitem/list");
	return forward;
}
}
