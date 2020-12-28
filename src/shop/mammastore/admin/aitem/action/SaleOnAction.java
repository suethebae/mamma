package shop.mammastore.admin.aitem.action;

import static shop.mammastore.common.RegExp.REGEXP_NUMBER;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.aitem.service.AitemService;

import shop.mammastore.admin.vo.AitemVo;

import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;
import shop.mammastore.common.RegExp;

public class SaleOnAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String mngr_sq = lm.getMemberId(session);

		if (mngr_sq == null || mngr_sq.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); loaction.href='/'; </script>");
			out.close();
			return null;
		}

		String ditem_sq = request.getParameter("itm_sq");

		String itm_sq = request.getParameter("sq");
		if (!RegExp.isValidExp(itm_sq, REGEXP_NUMBER)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); loaction.href='/'; </script>");
			out.close();
			return null;
		}

		AitemVo aitemVo = new AitemVo();
		aitemVo.setItem_sq(Integer.parseInt(itm_sq));

		/* AitemVo aitemVo = new AitemVo(); */
		AitemService svc = new AitemService();
		if (!svc.saleOn(aitemVo)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('상품 개시 실패하였습니다..); history.back(); </script>");
			out.close();
			return null;
		}

		/*
		 * AitemVo aitemVo = svc.getAitemDetail(Integer.parseInt(itm_sq)); if
		 * (aitemVo==null) { response.setContentType("text/html; charset=UTF-8");
		 * PrintWriter out = response.getWriter(); out.
		 * println("<script>alert('상품 정보 로드에 실패 했습니다.'); loaction.href='/'; </script>");
		 * out.close(); return null; }
		 */

		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/aitem/list");
		return forward;
	}
}
