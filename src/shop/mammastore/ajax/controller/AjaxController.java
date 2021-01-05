package shop.mammastore.ajax.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.mammastore.ajax.action.DetailMngrAction;
import shop.mammastore.ajax.action.ShowItemListAction;
import shop.mammastore.ajax.action.checkAEmailAction;
import shop.mammastore.ajax.action.checkAIdAction;
import shop.mammastore.ajax.action.checkAPhoneAction;
import shop.mammastore.ajax.action.checkEmailAction;
import shop.mammastore.ajax.action.checkIdAction;
import shop.mammastore.ajax.action.checkPhoneAction;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;

@WebServlet("/ajax/*")
public class AjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length()).replaceAll("/ajax", "");// 여기에 우리가 들고올 마지막 경로를
																								// 가져온다

		ActionForward forward = null;
		//아이디 체크
		if (command.equals("/checkId")) {
			Action action = new checkIdAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/checkEmail")) {
			Action action = new checkEmailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/checkPhone")) {
			Action action = new checkPhoneAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/checkAId")) {
			Action action = new checkAIdAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/checkAEmail")) {
			Action action = new checkAEmailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/checkAPhone")) {
			Action action = new checkAPhoneAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/detailMngr")) {
			Action action = new DetailMngrAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/itemList")) {
			Action action = new ShowItemListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		if (forward != null) {
			if (forward.isRedirect()) {
				// 리다이렉트
				response.sendRedirect(forward.getPath());
			} else {
				// 디스패치
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
