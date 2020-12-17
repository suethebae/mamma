package shop.mammastore.admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.mammastore.admin.service.AdminService;
import shop.mammastore.admin.vo.AdminVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.BCrypt;
import shop.mammastore.common.LoginManager;
import shop.mammastore.common.RegExp;

public class AloginProcAction implements Action {
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

		AdminService svc = new AdminService();
		AdminVo adminVo = svc.getLoginInfo(id);
		if (adminVo == null || !pwd.equals(adminVo.getPwd())) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 실패'); history.back(); </script>");
			out.close();
			return null;
		}

		String admin_sq = Integer.toString(adminVo.getAdmin_sq());
		LoginManager lm = LoginManager.getInstance();
		lm.setSession(request.getSession(), admin_sq);

		ActionForward forward = new ActionForward();
		forward.setPath("/admin/amain");
		return forward;
	}
}
