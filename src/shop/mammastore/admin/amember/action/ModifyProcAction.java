package shop.mammastore.admin.amember.action;

import static shop.mammastore.common.RegExp.REGEXP_EMAIL;
import static shop.mammastore.common.RegExp.REGEXP_NAME;
import static shop.mammastore.common.RegExp.REGEXP_PHONE;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.amember.service.AmemberService;
import shop.mammastore.admin.vo.AmemberVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;
import shop.mammastore.common.RegExp;

public class ModifyProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String mngr_sq = lm.getMemberId(session);
		String mber_sq = request.getParameter("mber_sq");
		
		if (mngr_sq == null || mngr_sq.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); loaction.href='/'; </script>");
			out.close();
			return null;
		}

		
		if (mber_sq == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}
		String dmber_sq = request.getParameter("mber_sq");
		String nm = request.getParameter("nm");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");

		if (!RegExp.isValidExp(email, REGEXP_EMAIL) || !RegExp.isValidExp(phone, REGEXP_PHONE)|| !RegExp.isValidExp(nm, REGEXP_NAME)|| RegExp.isEmpty(dmber_sq)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}

		AmemberVo amemberVo = new AmemberVo();
		amemberVo.setNm(nm);
		amemberVo.setEmail(email);
		amemberVo.setPhone(phone);
		amemberVo.setMber_sq(Integer.parseInt(mber_sq));

		AmemberService svc = new AmemberService();
		if (!svc.modify(amemberVo)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원정보 수정에 실패하였습니다..'); history.back(); </script>");
			out.close();
			return null;
		}

		ActionForward forward = new ActionForward();
		forward.setPath("/amember/detail?mber_sq="+amemberVo.getMber_sq());
		forward.setRedirect(true);
		return forward;
	}

}