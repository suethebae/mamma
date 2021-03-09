package shop.mammastore.mamma.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;

public class ReviewRegisterAction implements Action{
@Override
public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
	String subject = request.getParameter("subject");
	String content = request.getParameter("content");
	
		
	
	//경로설정
	ActionForward forward = new ActionForward();
	forward.setPath("/views/board/review/reviewList.jsp");
	return forward;
}
}