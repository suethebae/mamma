package shop.mammastore.admin.aboard.ahelp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.mammastore.admin.aboard.ahelp.action.DeleteAction;
import shop.mammastore.admin.aboard.ahelp.action.DetailAction;
import shop.mammastore.admin.aboard.ahelp.action.ModifyAction;
import shop.mammastore.admin.aboard.ahelp.action.ModifyProcAction;
import shop.mammastore.admin.aboard.ahelp.action.WriteAction;
import shop.mammastore.admin.aboard.ahelp.action.WriteProcAction;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;

@WebServlet("/ahelp/*")
//웹서블릿 어노테이션으로 모든 .do 파일이 이쪽으로 온다
public class HelpController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // requset에있는 문자를 모두 UTF-8로 바꾼다
		// 도메인뒤에 붙어있는 경로 가져온다. 다 있던 메소드 사용
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length()).replaceAll("/ahelp", "");// 여기에 우리가 들고올 마지막 경로를
																								// 가져온다
		ActionForward forward = null;

		// 문의 게시판 상세 페이지
		if (command.equals("/detail")) {
			Action action = new DetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 문의 게시판 글쓰기 폼으로 가기
		else if (command.equals("/write")) {
			Action action = new WriteProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 문의 게시판 글 등록
		else if (command.equals("/register")) {
			Action action = new WriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 문의 게시판 글 수정 폼으로 가기
		else if (command.equals("/modify")) {
			Action action = new ModifyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 문의 게시판 글 수정 완료하기
		else if (command.equals("/modifyProc")) {
			Action action = new ModifyProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 문의 게시판 글 삭제하기
		else if (command.equals("/delete")) {
			Action action = new DeleteAction();
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
