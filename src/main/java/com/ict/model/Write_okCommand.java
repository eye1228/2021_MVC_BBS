package com.ict.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.BVO;
import com.ict.db.DAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class Write_okCommand implements Command{
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {

		try {
			String path = request.getServletContext().getRealPath("/upload");
			MultipartRequest mr = new MultipartRequest(request, path, 100*1024*1024, "utf-8", new DefaultFileRenamePolicy());
			BVO bvo = new BVO();
			bvo.setSubject(mr.getParameter("subject"));
			bvo.setWriter(mr.getParameter("writer"));
			bvo.setContent(mr.getParameter("content"));
			bvo.setPwd(mr.getParameter("pwd"));
			
			//파일 존재 여부 확인
			if(mr.getFile("file_name")!=null) {
				bvo.setFile_name(mr.getFilesystemName("file_name"));
			}else {
				bvo.setFile_name("");
			}
			
			int result = DAO.getInsert(bvo);
			if(result>0) {
				return "MyController?cmd=list";
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		
		return null;
	}
}
