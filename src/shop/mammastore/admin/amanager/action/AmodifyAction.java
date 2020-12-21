package shop.mammastore.admin.amanager.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.amanager.service.AmanagerService;
import shop.mammastore.admin.vo.AmanagerVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;

public class AmodifyAction implements Action {
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

		String dmngr_sq = request.getParameter("mngr_sq");

		AmanagerService svc = new AmanagerService();
		AmanagerVo amanagerVo = svc.getDetailMngr(Integer.parseInt(dmngr_sq));
		
		if (amanagerVo==null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원 정보 로드에 실패 했습니다.'); loaction.href='/'; </script>"); 
			out.close();
			return null;
		}
		
		request.setAttribute("amanagerVo", amanagerVo);

		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/amanager/amodifyForm.jsp");
		return forward;
	}
}
