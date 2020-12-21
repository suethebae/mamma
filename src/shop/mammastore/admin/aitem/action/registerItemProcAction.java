package shop.mammastore.admin.aitem.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import shop.mammastore.admin.aitem.service.AitemService;
import shop.mammastore.admin.vo.AitemVo;
import shop.mammastore.common.Action;
import shop.mammastore.common.ActionForward;
import shop.mammastore.common.LoginManager;
import shop.mammastore.common.RegExp;

public class registerItemProcAction implements Action {
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

		String path="/uploadFile";
		ServletContext context= request.getSession().getServletContext();
		String uploadPath = context.getRealPath(path);
		int maxSize = 1024 * 1024 * 10;
		String nm = "";
		String pc = "";
		String stock = "";
		String cntnt = "";

		String fileName1 = "";
		String originalName1 = "";
		long fileSize = 0;
		String fileType = "";

		MultipartRequest multi = null;
		try {
			multi = new MultipartRequest(request, uploadPath, maxSize,"utf-8",new DefaultFileRenamePolicy());
			nm = multi.getParameter("nm");
			pc = multi.getParameter("pc");
			stock = multi.getParameter("stock");
			cntnt = multi.getParameter("content");
			
			Enumeration files = multi.getFileNames();
			
			while(files.hasMoreElements()) {
				String file1 = (String)files.nextElement();
				originalName1 = multi.getOriginalFileName(file1);
				fileName1 = multi.getFilesystemName(file1);
				fileType = multi.getContentType(file1);
				File file = multi.getFile(file1);
				fileSize = file.length();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String fl_pth = uploadPath+"\\"+fileName1;
//	String nm = request.getParameter("nm");
//	String pc = request.getParameter("pc");
//	String stock = request.getParameter("stock");
//	String cntnt = request.getParameter("content");
//	
//	if(RegExp.isEmpty(nm)||RegExp.isEmpty(pc)||RegExp.isEmpty(stock)||RegExp.isEmpty(cntnt)) {
//		response.setContentType("text/html;charset=UTF-8");
//		PrintWriter out = response.getWriter();
//		out.println("<script>alert('잘못된 접근입니다.'); location.href='/'; </script>");
//		out.close();
//		return null;
//	}

		cntnt = cntnt.replaceAll("&", "&amp");
		cntnt = cntnt.replaceAll("<", "&lt");
		cntnt = cntnt.replaceAll(">", "&gt");
		cntnt = cntnt.replaceAll("/", "&quot");

		AitemVo aitemVo = new AitemVo();
		aitemVo.setFl_pth(fl_pth);
		aitemVo.setNm(nm);
		aitemVo.setPc(Integer.parseInt(pc));
		aitemVo.setStock(Integer.parseInt(stock));
		aitemVo.setCntnt(cntnt);

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
		forward.setPath("/aitem/list");
		return forward;
	}
}
