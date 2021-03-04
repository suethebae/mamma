package shop.mammastore.mamma.order.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.aitem.service.AitemService;
import shop.mammastore.admin.vo.AitemVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;
import shop.mammastore.common.RegExp;
import shop.mammastore.mamma.vo.CartListVo;

public class ParchsOneFormAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String mber_sq = lm.getMemberId(session);

		// 로그인 안되어있을 시 로그인 이동
		if (mber_sq == null) {
			ActionForward forward = new ActionForward();
			forward.setPath("/member/login");
			return forward;
		}

		// itm_sq가지고 오기,itm_cnt 가지고 오기
		String itm_sq = request.getParameter("itm_sq");
		String itm_cnt = request.getParameter("itm_cnt");
		if (mber_sq == null || RegExp.isEmpty(itm_sq) || RegExp.isEmpty(itm_cnt)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}

		// itm_cnt 갯수 검사
		if (Integer.parseInt(itm_cnt) < 1) {
			itm_cnt = "1";
		}
		
		// inf_itm_tb 에서 아이템 데이터 가지고 오기
		AitemService isvc = new AitemService();
		AitemVo AitemVo = isvc.getItemDetail(Integer.parseInt(itm_sq));

		// CartListVo에 각 값 삽입
		CartListVo CLVo = new CartListVo();
		CLVo.setFl_pth(AitemVo.getFl_pth());
		CLVo.setItm_cnt(Integer.parseInt(itm_cnt));
		CLVo.setItm_nm(AitemVo.getNm());
		CLVo.setItm_pc(AitemVo.getPc());
		CLVo.setItm_sq(Integer.parseInt(itm_sq));

		// 클라우드에 올리기
		request.setAttribute("CLVo", CLVo);

		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/order/parchsOneForm.jsp");
		return forward;
	}
}
