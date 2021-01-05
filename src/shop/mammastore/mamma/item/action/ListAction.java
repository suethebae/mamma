package shop.mammastore.mamma.item.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.mammastore.admin.actgry.service.ActgryService;
import shop.mammastore.admin.vo.ActgryVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;

public class ListAction implements Action{
@Override
public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	//카테고리 리스트 넣을 공간 생성
	ArrayList<ActgryVo> list = null;
	//카테고리 리스트 받아오기
	ActgryService svc = new ActgryService();
	list = svc.getCtgryList();
	if(list==null) {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('카테고리 목록을 불러오는데 실패했습니다.'); history.back(); </script>");
		out.close();
		return null;
	}
	//카테고리 리스트 올리기
	request.setAttribute("ctgryList", list);
	//경로설정
	ActionForward forward = new ActionForward();
	forward.setPath("/views/item/list.jsp");
	return forward;
}
}
