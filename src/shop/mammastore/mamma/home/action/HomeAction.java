package shop.mammastore.mamma.home.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.mammastore.admin.vo.AitemVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.mamma.home.service.HomeService;

public class HomeAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 상품 최신순으로 4개 불러오기
		HomeService svc = new HomeService();
		ArrayList<AitemVo> newList = svc.getNewItem();
		if (newList == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('상품정보를 불러오는데 실패했습니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}
		
		// 잘팔리는 상품 4개 불러오기
		ArrayList<Integer> SqList = svc.getHitItem_sq();
		if (SqList == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('상품정보를 불러오는데 실패했습니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}
		
		ArrayList<AitemVo> hitList = svc.getHitItem(SqList);
		if (hitList == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('상품정보를 불러오는데 실패했습니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}
		
		
		// 리스트 클라우드에 업로드
		request.setAttribute("newList", newList);
		request.setAttribute("hitList", hitList);
		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/index.jsp");
		return forward;
	}
}
