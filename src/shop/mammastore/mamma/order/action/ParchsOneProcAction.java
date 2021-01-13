package shop.mammastore.mamma.order.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;
import shop.mammastore.common.RegExp;
import shop.mammastore.mamma.order.service.OrderService;
import shop.mammastore.mamma.vo.AdresVo;
import shop.mammastore.mamma.vo.OrderItemListVo;
import shop.mammastore.mamma.vo.OrderVo;

public class ParchsOneProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 확인
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String mber_sq = lm.getMemberId(session);

		if (mber_sq == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); loaction.href='/'; </script>");
			out.close();
			return null;
		}
		
		//데이터 로드(아이템 번호, 아이템 갯수, 이름, 총 가격, 휴대폰 번호, 이메일, 우편번호, 주소, 상세주소, 약관동의여부, 기본주소지 지정 여부)
		String itm_sq = request.getParameter("itm_sq");
		String itm_cnt = request.getParameter("itm_cnt");
		String nm = request.getParameter("nm");
		String pc = request.getParameter("pc");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String zip_cd = request.getParameter("zip_cd");
		String adres = request.getParameter("adres");
		String adres_detail = request.getParameter("adres_detail");
		String message = request.getParameter("message");
		String agree = request.getParameter("agree");
		String saveBase = request.getParameter("saveBase");
		
		//데이터 유효성 검사
		if(RegExp.isEmpty(itm_sq)||RegExp.isEmpty(itm_cnt)||RegExp.isEmpty(nm)||RegExp.isEmpty(pc)||RegExp.isEmpty(phone)||RegExp.isEmpty(email)||RegExp.isEmpty(zip_cd)||RegExp.isEmpty(adres)||agree==null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); loaction.href='/'; </script>");
			out.close();
			return null;
		}
		
		//상세주소가 비었을 시 빈값 처리
		if(RegExp.isEmpty(adres_detail)) {
			adres_detail="";
		}
		
		//배송메세지가 비었을 시 빈값 처리
		if(RegExp.isEmpty(message)) {
			message="";
		}
		
		//주문 번호 생성, 상품 번호 생성
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date now = new Date();
		int rNum = (int)Math.floor(Math.random()*90000)+10000;
		String order_code=format.format(now)+rNum;
		String orderDetail_cd = order_code+"1";
		
		//결재 상태 지정
		int sttus = 2;
		
		//orderVo에 입력
		OrderVo orderVo = new OrderVo();
		orderVo.setOrder_code(order_code);
		orderVo.setMber_sq(Integer.parseInt(mber_sq));
		orderVo.setSttus(sttus);
		orderVo.setAll_pc(Integer.parseInt(pc));
		orderVo.setNm(nm);
		orderVo.setPhone(phone);
		orderVo.setZip_cd(zip_cd);
		orderVo.setAdres(adres);
		orderVo.setAdres_detail(adres_detail);
		orderVo.setMssage(message);
		//orderItemListVo에 입력
		OrderItemListVo orderDetailVo = new OrderItemListVo();
		orderDetailVo.setOrder_code(order_code);
		orderDetailVo.setOrderDetail_cd(orderDetail_cd);
		orderDetailVo.setMber_sq(Integer.parseInt(mber_sq));
		orderDetailVo.setItm_sq(Integer.parseInt(itm_sq));
		orderDetailVo.setItm_cnt(Integer.parseInt(itm_cnt));
		OrderService svc = new OrderService();
		
		//inf_order_tb와 inf_orderdetail_tb에 삽입
		if(!svc.registerOne(orderVo)||!svc.registerDetailOne(orderDetailVo)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('주문 등록에 실패했습니다.'); history.back(); </script>");
			out.close();
			return null;
		}
		//기본 주소 지정 시 기본 주소 등록
		boolean adres_base = false;
		if(!(saveBase==null)) {
			adres_base=true;
		}
		AdresVo adresVo = new AdresVo();
		adresVo.setMber_sq(Integer.parseInt(mber_sq));
		adresVo.setZip_cd(zip_cd);
		adresVo.setAdres(adres);
		adresVo.setAdres_detail(adres_detail);
		adresVo.setAdres_base(adres_base);
		
		if(adres_base==true) {
			if(!svc.deleteAdresBase(Integer.parseInt(mber_sq))) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('주소 등록에 실패했습니다.'); history.back(); </script>");
				out.close();
				return null;
			}
		}
		if(!svc.registerAdres(adresVo)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('주소 등록에 실패했습니다.'); history.back(); </script>");
			out.close();
			return null;
		}
		
		// 경로설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/order/result.jsp");
		return forward;
	}
}
