package shop.mammastore.admin.amanager.action;

import static shop.mammastore.common.RegExp.REGEXP_EMAIL;
import static shop.mammastore.common.RegExp.REGEXP_NAME;
import static shop.mammastore.common.RegExp.REGEXP_PHONE;

import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.amanager.service.AmanagerService;
import shop.mammastore.admin.vo.AmanagerVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.BCrypt;
import shop.mammastore.common.RegExp;

public class ModifyProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 확인
		HttpSession session = request.getSession();
		String mngr_sq = String.valueOf(session.getAttribute("mngr_sq"));
		if (mngr_sq.equals("null")) {
			mngr_sq = null;
		}

		if (mngr_sq == null || mngr_sq.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}

		String dmngr_sq = request.getParameter("mngr_sq");
		String pwd = request.getParameter("pwd");
		String pwdc = request.getParameter("pwdc");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String nm = request.getParameter("nm");

		if (!pwd.equals("")) {
			if (!pwd.equals(pwdc) || !Pattern.matches("^[a-zA-Z0-9!@#$%^&*]{4,20}$", pwd)
					|| !RegExp.isValidExp(email, REGEXP_EMAIL) || !RegExp.isValidExp(phone, REGEXP_PHONE)
					|| RegExp.isEmpty(dmngr_sq) || !RegExp.isValidExp(nm, REGEXP_NAME)) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
				out.close();
				return null;
			}
		}

		AmanagerVo amanagerVo = new AmanagerVo();
		if (pwd == null || pwd.equals("")) {
			amanagerVo.setPwd(null);
		} else {
			amanagerVo.setPwd(BCrypt.hashpw(pwd, BCrypt.gensalt(12)));
		}
		amanagerVo.setMngr_sq(Integer.parseInt(dmngr_sq));
		amanagerVo.setEmail(email);
		amanagerVo.setPhone(phone);
		amanagerVo.setNm(nm);

		AmanagerService svc = new AmanagerService();
		if (!svc.modify(amanagerVo)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원정보 수정에 실패하였습니다..'); history.back(); </script>");
			out.close();
			return null;
		}

		ActionForward forward = new ActionForward();
		forward.setPath("/amanager/list");
		forward.setRedirect(true);
		return forward;
	}

}