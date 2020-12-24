package shop.mammastore.common;

import java.io.File;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import shop.mammastore.admin.vo.AitemVo;

public class FileUpload {
	public AitemVo fileUpload(HttpServletRequest request) {
		String domain = request.getServerName();
		String path = "/uploadFile";
		ServletContext context = request.getSession().getServletContext();
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
			multi = new MultipartRequest(request, uploadPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
			nm = multi.getParameter("nm");
			pc = multi.getParameter("pc");
			stock = multi.getParameter("stock");
			cntnt = multi.getParameter("content");

			Enumeration files = multi.getFileNames();

			while (files.hasMoreElements()) {
				String file1 = (String) files.nextElement();
				originalName1 = multi.getOriginalFileName(file1);
				fileName1 = multi.getFilesystemName(file1);
				fileType = multi.getContentType(file1);
				File file = multi.getFile(file1);
				fileSize = file.length();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String fl_pth = "http://" + domain + path + "/" + fileName1;
		AitemVo aitemVo = new AitemVo();
		Parser parser = new Parser();
		aitemVo.setFl_pth(parser.chgToStr(fl_pth));
		aitemVo.setNm(nm);
		aitemVo.setPc(Integer.parseInt(pc));
		aitemVo.setStock(Integer.parseInt(stock));
		aitemVo.setCntnt(parser.chgToStr(cntnt));
		return aitemVo;
	}
}
