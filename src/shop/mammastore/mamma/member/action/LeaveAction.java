package shop.mammastore.mamma.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;

public class LeaveAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/member/leaveForm.jsp");
		return forward;
	}
}
