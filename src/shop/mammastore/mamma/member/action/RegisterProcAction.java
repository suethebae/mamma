package shop.mammastore.mamma.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.BCrypt;
import shop.mammastore.common.RegExp;
import shop.mammastore.mamma.member.service.MemberService;
import shop.mammastore.mamma.vo.MemberVo;

import static shop.mammastore.common.RegExp.REGEXP_ID;
import static shop.mammastore.common.RegExp.REGEXP_PWD;
import static shop.mammastore.common.RegExp.REGEXP_NAME;
import static shop.mammastore.common.RegExp.REGEXP_EMAIL;

public class RegisterProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String id = request.getParameter("id"); // id가 id인 value값을 가져오겠다. "id" : key값 . 후에 key값을 호출할 예정임.
		String pwd = request.getParameter("pwd");
		String pwdc = request.getParameter("pwdc");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String magre = request.getParameter("magre");
		String pagre = request.getParameter("pagre");
		if (!RegExp.isValidExp(id, REGEXP_ID) || !RegExp.isValidExp(pwd, REGEXP_PWD)
				|| !RegExp.isValidExp(name, REGEXP_NAME) || !pwd.equals(pwdc) || !RegExp.isValidExp(email, REGEXP_EMAIL)
				|| RegExp.isEmpty(phone) || !magre.equals("1") || !pagre.equals("1")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter(); // html에서 alert창을 직접 사용하겠다. + 자바
			out.println("<script>alert('잘못된 접근입니다.') location.href='/'; </script>"); // 데이터를 받아서 접근하는게 아니라 강제로 들어왔을때 접근을 막기위해서 씀
			out.close();
			return null;
		}

		boolean bMagre = false;
		boolean bPagre = false;
		if (Integer.parseInt(magre) == 1) {
			bMagre = true;
		}
		if (Integer.parseInt(pagre) == 1) {
			bPagre = true;
		}

		MemberVo memberVo = new MemberVo();
		memberVo.setId(id);
		memberVo.setPwd(BCrypt.hashpw(pwd, BCrypt.gensalt(12)));
		memberVo.setName(name);
		memberVo.setEmail(email);
		memberVo.setPhone(phone);
		memberVo.setMagre_fl(bMagre);
		memberVo.setPagre_fl(bPagre);

		MemberService svc = new MemberService(); // 사용자가 어딘가 잘못된 데이터를 넣어서 회원가입을 못하는 경우
		if (!svc.register(memberVo)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원가입에 실패하였습니다.'); history.back(); </script>");
			out.close();
			return null;
		}

		ActionForward forward = new ActionForward();
		forward.setPath("/views/member/registerResult.jsp");
		return forward;
	}
}
