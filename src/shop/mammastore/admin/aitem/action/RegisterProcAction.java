package shop.mammastore.admin.aitem.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.admin.aitem.service.AitemService;
import shop.mammastore.admin.vo.AitemVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.FileUpload;
import shop.mammastore.common.LoginManager;
import shop.mammastore.common.RegExp;

public class RegisterProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
		FileUpload fileUpload = new FileUpload();
		AitemVo aitemVo = fileUpload.fileUpload(request);

		if (RegExp.isEmpty(aitemVo.getNm()) || RegExp.isEmpty(Integer.toString(aitemVo.getPc()))
				|| RegExp.isEmpty(Integer.toString(aitemVo.getStock())) || RegExp.isEmpty(aitemVo.getCntnt())) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
			out.close();
			return null;
		}

		AitemService svc = new AitemService();
		if (!svc.register(aitemVo)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('상품등록에 실패하였습니다.'); history.back(); </script>");
			out.close();
			return null;
		}

		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/aitem/list");
		return forward;
	}
}
