package shop.mammastore.mamma.member.action;

import static shop.mammastore.common.RegExp.REGEXP_EMAIL;
import static shop.mammastore.common.RegExp.REGEXP_PWD;
import static shop.mammastore.common.RegExp.REGEXP_PHONE;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.regex.Pattern;

import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.BCrypt;
import shop.mammastore.common.LoginManager;
import shop.mammastore.common.RegExp;
import shop.mammastore.mamma.member.service.MemberService;
import shop.mammastore.mamma.vo.MemberVo;

public class ModifyProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String mber_sq = lm.getMemberId(session);

		if (mber_sq == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}

		String pwd = request.getParameter("pwd");
		String pwdc = request.getParameter("pwdc");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");

		if (!pwd.equals("")) {
			if (!pwd.equals(pwdc) || !Pattern.matches("^[a-zA-Z0-9!@#$%^&*]{4,20}$", pwd)) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
				out.close();
				return null;
			}
		}
		if (!RegExp.isValidExp(email, REGEXP_EMAIL) || !RegExp.isValidExp(phone, REGEXP_PHONE)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}

		MemberVo memberVo = new MemberVo();
		if (pwd == null || pwd.equals("")) {
			memberVo.setPwd(null);
		} else {
			memberVo.setPwd(BCrypt.hashpw(pwd, BCrypt.gensalt(12)));
		}
		memberVo.setEmail(email);
		memberVo.setPhone(phone);
		memberVo.setMber_sq(Integer.parseInt(mber_sq));

		MemberService svc = new MemberService();
		if (!svc.modify(memberVo)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원정보 수정에 실패하였습니다..'); history.back(); </script>");
			out.close();
			return null;
		}

		ActionForward forward = new ActionForward();
		forward.setPath("/mymenu/myInfo");
		forward.setRedirect(true);
		return forward;
	}

}