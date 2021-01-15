package shop.mammastore.admin.aboard.ahelp.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.aboard.ahelp.service.HelpService;
import shop.mammastore.admin.vo.AhelpVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;

public class ListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//관리자 로그인 체크
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
		
		// db에 등록
		HelpService svc = new HelpService();
		ArrayList<AhelpVo> list =svc.getHelpList();
		if (list == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('1:1 문의 리스트를 불러오는데 실패했습니다.'); history.back(); </script>");
			out.close();
			return null;
		}
		// 받아온 데이터 업로드
		request.setAttribute("list", list);

		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/ahelp/list.jsp");
		return forward;
	}
}
