package shop.mammastore.mamma.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.mamma.member.action.FindIdAction;
import shop.mammastore.mamma.member.action.FindIdProcAction;
import shop.mammastore.mamma.member.action.FindPwdAction;
import shop.mammastore.mamma.member.action.FindPwdProcAction;
import shop.mammastore.mamma.member.action.LeaveAction;
import shop.mammastore.mamma.member.action.LeaveProcAction;
import shop.mammastore.mamma.member.action.LoginAction;
import shop.mammastore.mamma.member.action.LoginProcAction;
import shop.mammastore.mamma.member.action.LogoutAction;
import shop.mammastore.mamma.member.action.ModifyAction;
import shop.mammastore.mamma.member.action.ModifyProcAction;
import shop.mammastore.mamma.member.action.RegisterAction;
import shop.mammastore.mamma.member.action.RegisterProcAction;
import shop.mammastore.mamma.member.action.RegisterResultAction;

@WebServlet("/member/*")
//웹서블릿 어노테이션으로 모든 .do 파일이 이쪽으로 온다
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // requset에있는 문자를 모두 UTF-8로 바꾼다
		// 도메인뒤에 붙어있는 경로 가져온다. 다 있던 메소드 사용
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length()).replaceAll("/member", "");// 여기에 우리가 들고올 마지막 경로를
																								// 가져온다

		ActionForward forward = null;
		// 로그인
		if (command.equals("/login")) {
			Action action = new LoginAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/loginProc")) {
			Action action = new LoginProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 로그아웃
		else if (command.equals("/logout")) {
			Action action = new LogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 회원가입
		else if (command.equals("/register")) {
			Action action = new RegisterAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/registerProc")) {
			Action action = new RegisterProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 회원가입 결과
		else if (command.equals("/registerResult")) {
			Action action = new RegisterResultAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		// 회원정보수정
		else if (command.equals("/modify")) {
			Action action = new ModifyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/modifyProc")) {
			Action action = new ModifyProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 회원탈퇴
		else if (command.equals("/leave")) {
			Action action = new LeaveAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/leaveProc")) {
			Action action = new LeaveProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 아이디찾기
		else if (command.equals("/findId")) {
			Action action = new FindIdAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/findIdProc")) {
			Action action = new FindIdProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 비밀번호찾기
		else if (command.equals("/findPwd")) {
			Action action = new FindPwdAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/findPwdProc")) {
			Action action = new FindPwdProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// redirect or dispatch
		if (forward != null) {
			if (forward.isRedirect()) { // 리다이렉트 -요청값 바뀜 리퀘스트 정보 안남음
				response.sendRedirect(forward.getPath()); // 정보 처리

			} else { // 디스패치 -데이터 유지시키려면 디스패치로 이동해야함
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response); // 정보 보내기
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
