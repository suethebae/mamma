package shop.mammastore.ajax.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.ajax.service.AjaxService;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;
import shop.mammastore.mamma.vo.MemberOrderVo;

public class InputMberDataAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 멤머 sq 가져오기
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String mber_sq = lm.getMemberId(session);
		// ajax 서비스 불러오기
		AjaxService svc = new AjaxService();

		// 데이터 베이스에서 memberOrderVo 형태로 데이터 가지고 와서 담기
		MemberOrderVo MOVo = svc.inputMberData(Integer.parseInt(mber_sq));

		// 가지고 온 데이터 클라우드에 업로드
		request.setAttribute("MOVo", MOVo);

		ActionForward forward = new ActionForward();
		forward.setPath("/views/ajax/inputMberData.jsp");
		return forward;
	}

}
