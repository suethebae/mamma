package shop.mammastore.admin.aitem.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.mammastore.admin.actgry.service.ActgryService;
import shop.mammastore.admin.aitem.service.AitemService;
import shop.mammastore.admin.vo.ActgryVo;
import shop.mammastore.admin.vo.AitemVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;

public class ModifyAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 확인
		String itm_sq = request.getParameter("sq");
		if (itm_sq == null || itm_sq.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); loaction.href='/'; </script>");
			out.close();
			return null;
		}

		AitemService svc = new AitemService();
		AitemVo aitemVo = svc.getItemDetail(Integer.parseInt(itm_sq));
	
		if (aitemVo==null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('상품정보 로드에 실패 했습니다.'); loaction.href='/'; </script>"); 
			out.close();
			return null;
		}
		
		request.setAttribute("aitemVo", aitemVo);

		ArrayList<ActgryVo> list = null;
		ActgryService svc1 = new ActgryService();
		list = svc1.getCtgryList();
		if(list==null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('카테고리를 불러오는데 실패했습니다.'); history.back(); </script>");
			out.close();
			return null;
		}
		
		request.setAttribute("list", list);
		
		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/aitem/modifyForm.jsp");
		return forward;
	}
}
