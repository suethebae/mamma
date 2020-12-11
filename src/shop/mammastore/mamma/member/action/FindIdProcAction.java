package shop.mammastore.mamma.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.RegExp;
import shop.mammastore.mamma.member.service.MemberService;
import shop.mammastore.mamma.vo.MemberVo;

public class FindIdProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String radio = request.getParameter("radio");

		String query = "";

		if (RegExp.isEmpty(name) || RegExp.isEmpty(radio)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}

		if (radio.equals("email")) {
			if (RegExp.isEmpty(email)) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
				out.close();
				return null;
			} else {
				query = " and name =\'" + name + "\'and email =\'" + email + "\'";
				// where뒤에 붙을 쿼리문.
			}
		} else if (radio.equals("phone")) {
			if (RegExp.isEmpty(phone)) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
				out.close();
				return null;
			} else {
				query = " and name =\'" + name + "\'and phone=\'" + phone + "\'";
			}
		}

		MemberService svc = new MemberService();
		MemberVo memberVo = svc.findId(query);
		if (memberVo == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('아이디 찾기에 실패하였습니다.'); history.back(); </script>");
			out.close();
			return null; // 쿼리로 보내고 멤버Vo로 받음.
		}

		request.setAttribute("id", memberVo.getId());
		ActionForward forward = new ActionForward();
		forward.setPath("/views/member/findIdResult.jsp");
		return forward;
	}
}
