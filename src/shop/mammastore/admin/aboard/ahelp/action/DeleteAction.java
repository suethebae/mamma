package shop.mammastore.admin.aboard.ahelp.action;

import static shop.mammastore.common.RegExp.REGEXP_NUMBER;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.aboard.ahelp.service.HelpService;
import shop.mammastore.admin.vo.AhelpVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.RegExp;

public class DeleteAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 확인
		HttpSession session = request.getSession();
		String mngr_sq = String.valueOf(session.getAttribute("mngr_sq"));
		if(mngr_sq.equals("null")) {
			mngr_sq=null;
		}

		if (mngr_sq == null || mngr_sq.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}
		//help_sq 유효성 체크
		String help_sq = request.getParameter("help_sq");
		if (!RegExp.isValidExp(help_sq, REGEXP_NUMBER)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}
		//AhelpVo에 데이터 저장
		AhelpVo ahelpVo = new AhelpVo();
		ahelpVo.setHelp_sq(Integer.parseInt(help_sq));
		
		//데이터 베이스에 등록
		HelpService svc = new HelpService();
		if (!svc.deleteHelp(ahelpVo)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('1:1문의 삭제에 실패하였습니다..); history.back(); </script>");
			out.close();
			return null;
		}
		// 받아온 데이터 업로드
		request.setAttribute("ahelpVo", ahelpVo);
		
		ahelpVo.setDel_fl(true);

		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/ahelp/list");
		return forward;
	}
}
