package shop.mammastore.admin.aitem.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.mammastore.admin.aitem.action.listAction;
import shop.mammastore.admin.aitem.action.registerItemAction;
import shop.mammastore.admin.amanager.action.AloginProcAction;
import shop.mammastore.admin.amanager.action.AlogoutAction;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.mamma.member.action.LogoutAction;

@WebServlet("/aitem/*")
//웹서블릿 어노테이션으로 모든 .do 파일이 이쪽으로 온다
public class AitemController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // requset에있는 문자를 모두 UTF-8로 바꾼다
		// 도메인뒤에 붙어있는 경로 가져온다. 다 있던 메소드 사용
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length()).replaceAll("/aitem", "");// 여기에 우리가 들고올 마지막
																										// 경로를 가져온다
		ActionForward forward = null;

		// 상품등록 페이지 가기
		if (command.equals("/registerItem")) {
			Action action = new registerItemAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/list")) {
			Action action = new listAction();
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
