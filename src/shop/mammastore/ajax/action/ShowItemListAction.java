package shop.mammastore.ajax.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.mammastore.admin.aitem.service.AitemService;
import shop.mammastore.admin.vo.AitemVo;
import shop.mammastore.admin.vo.AmanagerVo;
import shop.mammastore.ajax.service.AjaxService;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;

public class ShowItemListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ctgry_sq = request.getParameter("sq");
		
		
		ArrayList<AitemVo> aitemVo = null;
		AitemService svc = new AitemService();
		aitemVo = svc.getItemList();
		if(aitemVo==null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('관리자 리스트를 불러오는데 실패했습니다.'); history.back(); </script>");
			out.close();
			return null;
		}
		request.setAttribute("showItemList", aitemVo);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/ajax/itemList.jsp");
		return forward;
	}
	
}
