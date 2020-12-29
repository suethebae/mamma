package shop.mammastore.mamma.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
<<<<<<< Updated upstream:src/shop/mammastore/mamma/board/controller/BoardController.java
import shop.mammastore.mamma.board.action.ReviewListAction;
import shop.mammastore.mamma.board.action.ReviewDeleteAction;
import shop.mammastore.mamma.board.action.ReviewDetailAction;
import shop.mammastore.mamma.board.action.ReviewModifyAction;
import shop.mammastore.mamma.board.action.ReviewModifyProcAction;
import shop.mammastore.mamma.board.action.ReviewRegisterAction;
import shop.mammastore.mamma.board.action.ReviewWriteAction;
=======
import shop.mammastore.mamma.reivew.action.DeleteAction;
import shop.mammastore.mamma.reivew.action.DetailAction;
import shop.mammastore.mamma.reivew.action.ListAction;
import shop.mammastore.mamma.reivew.action.ModifyAction;
import shop.mammastore.mamma.reivew.action.ModifyProcAction;
import shop.mammastore.mamma.reivew.action.RegisterAction;
import shop.mammastore.mamma.reivew.action.WriteAction;
>>>>>>> Stashed changes:src/shop/mammastore/mamma/reivew/controller/ReviewController.java

@WebServlet("/board/*")
//웹서블릿 어노테이션으로 모든 .do 파일이 이쪽으로 온다
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // requset에있는 문자를 모두 UTF-8로 바꾼다
		// 도메인뒤에 붙어있는 경로 가져온다. 다 있던 메소드 사용
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length()).replaceAll("/board", "");// 여기에 우리가 들고올 마지막 경로를 가져온다
		ActionForward forward = null;

		// reviewBbs
		if (command.equals("/reviewList")) {
			Action action = new ReviewListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/reviewDetail")) {
			Action action = new ReviewDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/reviewWrite")) {
			Action action = new ReviewWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/reviewRegister")) {
			Action action = new ReviewRegisterAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/reviewModify")) {
			Action action = new ReviewModifyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/reviewModifyProc")) {
			Action action = new ReviewModifyProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/reviewDelete")) {
			Action action = new ReviewDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//

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
