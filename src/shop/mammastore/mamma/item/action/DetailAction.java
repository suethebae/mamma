package shop.mammastore.mamma.item.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.mammastore.admin.aitem.service.AitemService;
import shop.mammastore.admin.vo.AitemVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.Parser;

public class DetailAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 아이템 SQ 로드
		String itm_sq = request.getParameter("itm_sq");

		if (itm_sq == null || itm_sq.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); loaction.href='/'; </script>");
			out.close();
			return null;
		}

		// 아이템 SQ 를 기준으로 데이터 받아오기
		AitemVo aitemVo = null;
		AitemService svc = new AitemService();
		aitemVo = svc.getItemDetail(Integer.parseInt(itm_sq));
		if (aitemVo == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); loaction.href='/'; </script>");
			out.close();
			return null;
		}
		// 컨텐츠 내용 변경
		aitemVo.setCntnt(Parser.chgToHTML(aitemVo.getCntnt()));
		// 받아온 데이터 업로드
		request.setAttribute("aitemVo", aitemVo);

		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/item/detail.jsp");
		return forward;
	}
}
