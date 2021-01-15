package shop.mammastore.admin.aboard.anotice.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.aboard.anotice.service.AnoticeService;
import shop.mammastore.admin.vo.AnoticeVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;
import shop.mammastore.common.Parser;
import shop.mammastore.common.RegExp;

public class RegisterProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		// 관리자 확인
		String mngr_sq = lm.getMemberId(session);
		if (mngr_sq == null || mngr_sq.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인을 해야 접근할 수 있습니다.'); loaction.href='/'; </script>");
			out.close();
			return null;
		}
		// anoticeVo에 데이터 담기
		String sj = request.getParameter("sj");
		String content = request.getParameter("content");

		AnoticeVo anoticeVo = new AnoticeVo();
		anoticeVo.setSj(sj);
		anoticeVo.setCntnt(Parser.chgToStr(content));
		anoticeVo.setMngr_sq(Integer.parseInt(mngr_sq));

		if (RegExp.isEmpty(anoticeVo.getSj()) || RegExp.isEmpty(anoticeVo.getCntnt())) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}

		AnoticeService svc = new AnoticeService();
		if (!svc.register(anoticeVo)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('글 작성에 실패하였습니다.'); history.back(); </script>");
			out.close();
			return null;
		}

		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/anotice/list");
		return forward;
	}
}