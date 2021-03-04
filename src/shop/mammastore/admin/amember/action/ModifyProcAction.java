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

		String mber_sq = request.getParameter("mber_sq");
		// 회원 시퀀스 확인
		if (mber_sq == null || mber_sq.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}

		String nm = request.getParameter("nm");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");

		if (!RegExp.isValidExp(nm, REGEXP_NAME) || !RegExp.isValidExp(email, REGEXP_EMAIL)
				|| !RegExp.isValidExp(phone, REGEXP_PHONE)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter(); // html에서 alert창을 직접 사용하겠다. + 자바
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>"); // 데이터를 받아서 접근하는게 아니라 강제로 들어왔을때
																						// 접근을 막기위해서 씀
			out.close();
			return null;
		}

		AmemberVo amemberVo = new AmemberVo();
		amemberVo.setNm(nm);
		amemberVo.setEmail(email);
		amemberVo.setPhone(phone);
		amemberVo.setMber_sq(Integer.parseInt(mber_sq));

		// =========정보수정완료했을때 그 멤버의 상세정보페이지로가야함. 아직수정모택동
		AmemberService svc = new AmemberService();
		if (!svc.modify(amemberVo)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원정보 수정에 실패하였습니다..'); history.back(); </script>");
			out.close();
			return null;
		}

		ActionForward forward = new ActionForward();
		forward.setPath("/amember/detail?sq=" + amemberVo.getMber_sq());
		forward.setRedirect(true);
		return forward;
	}

}