package shop.mammastore.ajax.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.mammastore.admin.vo.AitemVo;
import shop.mammastore.ajax.service.AjaxService;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;

public class ShowItemListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ctgry_sq = request.getParameter("sq");
		if (ctgry_sq == null || ctgry_sq.equals("")) {
			ctgry_sq = "0";
		}

		int iCtgry_sq = Integer.parseInt(ctgry_sq);
		ArrayList<AitemVo> list = null;
		AjaxService svc = new AjaxService();
		list = svc.getItemList(iCtgry_sq);
		if (list == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('상품정보를 불러오는데 실패했습니다.'); history.back(); </script>");
			out.close();
			return null;
		}
		request.setAttribute("itemList", list);

		ActionForward forward = new ActionForward();
		forward.setPath("/views/ajax/itemList.jsp");
		return forward;
	}

}
