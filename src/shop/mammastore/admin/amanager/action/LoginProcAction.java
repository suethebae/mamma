package shop.mammastore.admin.amanager.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.amanager.service.AmanagerService;
import shop.mammastore.admin.vo.AmanagerVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.BCrypt;
import shop.mammastore.common.LoginManager;
import shop.mammastore.common.RegExp;

public class LoginProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");

		if (RegExp.isEmpty(id) || RegExp.isEmpty(pwd)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}

		AmanagerService svc = new AmanagerService();
		AmanagerVo amanagerVo = svc.getLoginInfo(id);
		if (amanagerVo == null || !BCrypt.checkpw(pwd, amanagerVo.getPwd())) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 실패'); history.back(); </script>");
			out.close();
			return null;
		}
		//일반 로그인과 관리자 로그인 세션 분리 어떻게 할지 물어봐야함
		String mngr_sq = Integer.toString(amanagerVo.getMngr_sq());
		LoginManager lm = LoginManager.getInstance();
		lm.setSession(request.getSession(), mngr_sq);

		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/ahome.jsp");
		return forward;
	}
}
