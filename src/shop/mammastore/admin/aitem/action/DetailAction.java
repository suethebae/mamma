package shop.mammastore.admin.aitem.action;

import static shop.mammastore.common.RegExp.REGEXP_NUMBER;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.mammastore.admin.aitem.service.AitemService;
import shop.mammastore.admin.vo.AitemVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.Parser;
import shop.mammastore.common.RegExp;

public class DetailAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String itm_sq = request.getParameter("sq");
		if (!RegExp.isValidExp(itm_sq, REGEXP_NUMBER)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); loaction.href='/'; </script>");
			out.close();
			return null;
		}

		AitemService svc = new AitemService();
		AitemVo aitemVo = svc.getItemDetail(Integer.parseInt(itm_sq));

		if (aitemVo == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('상품 정보 로드에 실패 했습니다.'); loaction.href='/'; </script>");
			out.close();
			return null;
		}

		aitemVo.setCntnt(Parser.chgToHTML(aitemVo.getCntnt()));

		request.setAttribute("aitemVo", aitemVo);

		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/aitem/detail.jsp");
		return forward;
	}
}
