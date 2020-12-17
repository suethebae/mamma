package shop.mammastore.admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.mammastore.admin.action.AboardAction;
import shop.mammastore.admin.action.AitemAction;
import shop.mammastore.admin.action.AloginAction;
import shop.mammastore.admin.action.AloginProcAction;
import shop.mammastore.admin.action.AlogoutAction;
import shop.mammastore.admin.action.AmainAction;
import shop.mammastore.admin.action.AmemberAction;
import shop.mammastore.admin.action.AorderAction;
import shop.mammastore.admin.action.AregisterAction;
import shop.mammastore.admin.action.AregisterProcAction;
import shop.mammastore.admin.action.AstoreAction;
import shop.mammastore.admin.action.registerItemAction;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;

@WebServlet("/admin/*")
//웹서블릿 어노테이션으로 모든 .do 파일이 이쪽으로 온다
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // requset에있는 문자를 모두 UTF-8로 바꾼다
		// 도메인뒤에 붙어있는 경로 가져온다. 다 있던 메소드 사용
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length()).replaceAll("/admin", "");// 여기에 우리가 들고올 마지막 경로를 가져온다
		ActionForward forward = null;

		// admin 로그인 페이지 가기
		if (command.equals("")) {
			Action action = new AloginAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// admin 메인 페이지 이동
		else if (command.equals("/amain")) {
			Action action = new AmainAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// admin member 관리페이지 가기
		else if (command.equals("/amember")) {
			Action action = new AmemberAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// admin 관리자 회원가입 롬이동
		else if (command.equals("/aregister")) {
			Action action = new AregisterAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// admin 관리자 회원가입 절차
		else if (command.equals("/aregisterProc")) {
			Action action = new AregisterProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// admin 로그d인 절차
		else if (command.equals("/aloginProc")) {
			Action action = new AloginProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// admin 로그아웃 절차
		else if (command.equals("/alogout")) {
			Action action = new AlogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// admin store 관리페이지 가기
		else if (command.equals("/astore")) {
			Action action = new AstoreAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// admin registerItem 페이지 가기
		else if (command.equals("/registerItem")) {
			Action action = new registerItemAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// admin order 관리페이지 가기
		else if (command.equals("/aorder")) {
			Action action = new AorderAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// admin item 관리페이지 가기
		else if (command.equals("/aitem")) {
			Action action = new AitemAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// admin board 관리페이지 가기
		else if (command.equals("/aboard")) {
			Action action = new AboardAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// redirect or dispatch
		if (forward != null) {
			if (forward.isRedirect()) { // 리다이렉트 -요청값 바뀜 리퀘스트 정보 안남음
				response.sendRedirect(forward.getPath());

			} else { // 디스패치 -데이터 유지시키려면 디스패치로 이동해야함
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
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
