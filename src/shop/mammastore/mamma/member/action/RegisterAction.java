package shop.mammastore.mamma.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;

public class RegisterAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/member/registerForm.jsp");
		return forward;
		
		
	}
}
