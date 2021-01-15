package shop.mammastore.admin.aboard.anotice.action;

import static shop.mammastore.common.RegExp.REGEXP_NUMBER;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.aboard.anotice.service.AnoticeService;
import shop.mammastore.admin.vo.AnoticeVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;
import shop.mammastore.common.RegExp;

public class DeleteAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		// 로그인 확인
		String mngr_sq = lm.getMemberId(session);
		if (mngr_sq == null || mngr_sq.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); loaction.href='/'; </script>");
			out.close();
			return null;
		}
		// 게시글정보 
		String notice_sq = request.getParameter("sq");
		if (!RegExp.isValidExp(notice_sq, REGEXP_NUMBER)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); loaction.href='/'; </script>");
			out.close();
			return null;
		}
		// 게시글삭제
		AnoticeVo anoticeVo = new AnoticeVo();
		anoticeVo.setNotice_sq(Integer.parseInt(notice_sq));

		AnoticeService svc = new AnoticeService();
		if (!svc.deleteNotice(anoticeVo)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('게시글 삭제에 실패하였습니다..); history.back(); </script>");
			out.close();
			return null;
		}
		//삭제완료처리
		request.setAttribute("anoticeVo", anoticeVo);

		anoticeVo.setDel_fl(true);
		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/anotice/list");
		return forward;
	}
}
