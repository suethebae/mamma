package shop.mammastore.mamma.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.BCrypt;
import shop.mammastore.common.LoginManager;
import shop.mammastore.common.RegExp;
import shop.mammastore.mamma.member.service.MemberService;
import shop.mammastore.mamma.vo.MemberVo;

public class FindPwdProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String mber_sq = lm.getMemberId(session);

		if (mber_sq != null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}

		String id = request.getParameter("id");
		String nm = request.getParameter("nm");
		String email = request.getParameter("email");

		if (RegExp.isEmpty(id) || RegExp.isEmpty(nm) || RegExp.isEmpty(email)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}

		MemberVo memberVo = new MemberVo();
		memberVo.setId(id);
		memberVo.setNm(nm);
		memberVo.setEmail(email);

		MemberService svc = new MemberService();
		if (svc.findPwd(memberVo) == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('비밀번호 찾기에 실패하였습니다.'); history.back(); </script>");
			out.close();
			return null;
		}
		String[] list = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i",
				"j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
		int selectPwd = 0;
		String tempPwd = "";
		for (int i = 0; i < 8; i++) {
			selectPwd = (int) Math.floor(Math.random() * list.length);
			tempPwd += list[selectPwd];
		}
		memberVo.setPwd(BCrypt.hashpw(tempPwd, BCrypt.gensalt(12)));
		if (!svc.setPwd(memberVo)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('비밀번호 재설정에 실패하였습니다.'); history.back(); </script>");
			out.close();
			return null;
		}

		request.setAttribute("pwd", tempPwd);
		ActionForward forward = new ActionForward();
		forward.setPath("/views/member/findPwdResult.jsp");
		return forward;
	}
}
