package shop.mammastore.mamma.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;

public class ReviewWriteAction implements Action{
@Override
public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
	String content = request.getParameter("content");
	
	
	
	//경로설정
	ActionForward forward = new ActionForward();
	forward.setPath("/views/board/review/writeForm.jsp");
	return forward;
}
}