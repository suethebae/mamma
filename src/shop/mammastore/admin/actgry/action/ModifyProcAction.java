package shop.mammastore.admin.actgry.action;

import static shop.mammastore.common.RegExp.REGEXP_CTGRY_NM;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.actgry.service.ActgryService;
import shop.mammastore.admin.vo.ActgryVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.RegExp;

public class ModifyProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 확인
		HttpSession session = request.getSession();
		String mngr_sq = String.valueOf(session.getAttribute("mngr_sq"));
		if (mngr_sq.equals("null")) {
			mngr_sq = null;
		}

		if (mngr_sq == null || mngr_sq.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}
		// 넘어온 sq확인
		String ctgry_sq = request.getParameter("ctgry_sq");
		if (RegExp.isEmpty(ctgry_sq)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}
		// nm 유효성 검사
		String nm = request.getParameter("nm");
		if (!RegExp.isValidExp(nm, REGEXP_CTGRY_NM)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}

		// nm 데이터 vo에 넣기
		ActgryVo actgryVo = new ActgryVo();
		actgryVo.setCtgry_sq(Integer.parseInt(ctgry_sq));
		actgryVo.setNm(nm);

		// db에 Vo전달하여 카테고리 입력
		ActgryService svc = new ActgryService();
		if (!svc.modify(actgryVo)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('카테고리 등록에 실패했습니다.'); history.back(); </script>");
			out.close();
			return null;
		}
		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/actgry/list");
		return forward;
	}
}