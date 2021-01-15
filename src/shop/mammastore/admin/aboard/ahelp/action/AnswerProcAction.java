package shop.mammastore.admin.aboard.ahelp.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.aboard.ahelp.service.HelpService;
import shop.mammastore.admin.vo.AhelpVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;

public class AnswerProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 확인
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
		//help_sq, answer 데이터 로드
		String help_sq = request.getParameter("help_sq");
		String answer = request.getParameter("answer");
		
		//help_sq, answer 유효성 검사
		if (help_sq == null || help_sq.equals("")|| answer == null || answer.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); loaction.href='/'; </script>");
			out.close();
			return null;
		}
		//AhelpVo 에 위 데이터 셋
		AhelpVo ahelpVo =new AhelpVo();
		ahelpVo.setHelp_sq(Integer.parseInt(help_sq));
		ahelpVo.setAnswer(answer);
		
		//inf_help_tb 에서 help_sq를 기준으로 answer 업데이트
		HelpService svc = new HelpService();
		if (!svc.register(ahelpVo)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('답변 등록에 실패했습니다.'); history.back(); </script>");
			out.close();
			return null;
		}
		
		
		// 경로설정
		ActionForward forward = new ActionForward();
//		forward.setRedirect(true);
		forward.setPath("/ahelp/detail?help_sq="+help_sq);
		return forward;
	}
}
