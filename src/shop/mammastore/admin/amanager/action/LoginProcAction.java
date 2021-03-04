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
import shop.mammastore.common.RegExp;

public class LoginProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 안되어있는지 확인
		HttpSession session = request.getSession();
		String mngr_sq = String.valueOf(session.getAttribute("mngr_sq"));
		if (mngr_sq.equals("null")) {
			mngr_sq = null;
		}

		if (mngr_sq != null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}

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
		
		// 로그인
		mngr_sq = Integer.toString(amanagerVo.getMngr_sq());
		session.setAttribute("mngr_sq", mngr_sq);

		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/afterIndex.jsp");
		forward.setRedirect(true);
		return forward;
	}
}
