package shop.mammastore.admin.amanager.action;

import static shop.mammastore.common.RegExp.REGEXP_EMAIL;
import static shop.mammastore.common.RegExp.REGEXP_ID;
import static shop.mammastore.common.RegExp.REGEXP_NAME;
import static shop.mammastore.common.RegExp.REGEXP_PWD;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.mammastore.admin.amanager.service.AmanagerService;
import shop.mammastore.admin.vo.AmanagerVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.BCrypt;
import shop.mammastore.common.RegExp;


public class AsregisterProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String pwdc = request.getParameter("pwdc");
		String nm = request.getParameter("nm");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		if (!RegExp.isValidExp(id, REGEXP_ID) || !RegExp.isValidExp(pwd, REGEXP_PWD)
				|| !RegExp.isValidExp(nm, REGEXP_NAME) || !pwd.equals(pwdc) || !RegExp.isValidExp(email, REGEXP_EMAIL)
				|| RegExp.isEmpty(phone)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter(); 
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");	
			out.close();
			return null;
		}
		
		AmanagerVo amanagerVo = new AmanagerVo();
		amanagerVo.setId(id);
		amanagerVo.setPwd(BCrypt.hashpw(pwd, BCrypt.gensalt(12)));
		amanagerVo.setNm(nm);
		amanagerVo.setEmail(email);
		amanagerVo.setPhone(phone);
							
		AmanagerService svc = new AmanagerService();
		if (!svc.asregister(amanagerVo)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원가입에 실패하였습니다.'); history.back(); </script>");
			out.close();
			return null;
		}

		ActionForward forward = new ActionForward();
		forward.setPath("/admin");
		return forward;
	}
}
