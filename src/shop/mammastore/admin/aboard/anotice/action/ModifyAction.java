package shop.mammastore.admin.aboard.anotice.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.mammastore.admin.aboard.anotice.service.AnoticeService;
import shop.mammastore.admin.vo.AnoticeVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;

public class ModifyAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 정상적인 경로로 접속했는지 확인
		String notice_sq = request.getParameter("sq");
		if (notice_sq == null || notice_sq.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); loaction.href='/'; </script>");
			out.close();
			return null;
		}
		// 공지사항 불러오기
		AnoticeService svc = new AnoticeService();
		AnoticeVo anoticeVo = svc.getNoticeDetail(Integer.parseInt(notice_sq));
	
		if (anoticeVo==null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('상품정보 로드에 실패 했습니다.'); loaction.href='/'; </script>"); 
			out.close();
			return null;
		}
		
		request.setAttribute("anoticeVo", anoticeVo);
		
		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/board/notice/modifyForm.jsp");
		return forward;
	}
}
