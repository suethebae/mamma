package shop.mammastore.admin.aitem.action;

import static shop.mammastore.common.RegExp.REGEXP_NUMBER;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.aitem.service.AitemService;
import shop.mammastore.admin.amanager.service.AmanagerService;
import shop.mammastore.admin.vo.AitemVo;
import shop.mammastore.admin.vo.AmanagerVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;
import shop.mammastore.common.RegExp;
import shop.mammastore.mamma.member.service.MemberService;

public class aModifyProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 확인
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String mngr_sq = lm.getMemberId(session);

		if (mngr_sq == null || mngr_sq.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); loaction.href='/'; </script>");
			out.close();
			return null;
		}
		
		String itm_sq = request.getParameter("sq");
		if(!RegExp.isValidExp(itm_sq, REGEXP_NUMBER)) {
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
		
		AitemVo itemVo = new AitemVo();
		itemVo.setItem_sq(Integer.parseInt(itm_sq));
		itemVo.setNm(nm);
		itemVo.setPc(Integer.parseInt(pc));
		itemVo.setStock(Integer.parseInt(stock));
		itemVo.setCntnt(cntnt);
		
		AitemService svc = new AitemService();
		if (!svc.modify(itemVo)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('상품정보 수정에 실패하였습니다..'); history.back(); </script>");
			out.close();
			return null;
		}
		

		// 경로설정
	ActionForward forward = new ActionForward();
	forward.setPath("/views/admin/aitem/detail.jsp");
	return forward;
	}
}
