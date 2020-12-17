package shop.mammastore.mamma.mymenu.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.mamma.mymenu.action.MyInfoAction;
import shop.mammastore.mamma.mymenu.action.MyOrderAction;
import shop.mammastore.mamma.mymenu.action.MyPageAction;
import shop.mammastore.mamma.mymenu.action.MyQuestionAction;
import shop.mammastore.mamma.mymenu.action.MyReviewAction;

@WebServlet("/mymenu/*")
//웹서블릿 어노테이션으로 모든 .do 파일이 이쪽으로 온다
public class MyMenuController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // requset에있는 문자를 모두 UTF-8로 바꾼다
		// 도메인뒤에 붙어있는 경로 가져온다. 다 있던 메소드 사용
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length()).replaceAll("/mymenu", "");// 여기에 우리가 들고올 마지막 경로를
																								// 가져온다

		ActionForward forward = null;
		//
		if (command.equals("/myPage")) {
			Action action = new MyPageAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 마이주문내역
		else if (command.equals("/myOrder")) {
			Action action = new MyOrderAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 마이 리뷰리스트
		else if (command.equals("/myReview")) {
			Action action = new MyReviewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 일대일 문의
		else if (command.equals("/myQuestion")) {
			Action action = new MyQuestionAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 내정보
		else if (command.equals("/myInfo")) {
			Action action = new MyInfoAction();
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

			} else { // 디스패치 -데이터 유지시키려면 디스패치로 이동해야함.
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
