package shop.mammastore.admin.aorder.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.aorder.service.AorderService;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;
import shop.mammastore.mamma.vo.OrderListVo;

public class ListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 확인
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String mngr_sq = lm.getMemberId(session);

		if (mngr_sq == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); loaction.href='/'; </script>");
			out.close();
			return null;
		}
		
		//inf_order_tb에서 주문 내역 데이터 가지고 오기(order_sq, order_code, sttus, all_pc)
		AorderService svc = new AorderService();
		ArrayList<OrderListVo> list = svc.getOrderList();
		
		if(list==null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('주문목록 로드에 실패했습니다.'); history.back(); </script>");
			out.close();
			return null;
		}
			
		//클라우드에 올림
		request.setAttribute("list", list);
		
		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/aorder/list.jsp");
		return forward;
	}
}
