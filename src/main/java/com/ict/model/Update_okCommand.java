package com.ict.model;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.BVO;
import com.ict.db.DAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class Update_okCommand implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {

		try {
			String path = request.getServletContext().getRealPath("/upload");
			MultipartRequest mr = new MultipartRequest(request, path, 100*1024*1024, "utf-8", new DefaultFileRenamePolicy());
			
			BVO bvo2 = (BVO)request.getSession().getAttribute("bvo");
			BVO bvo = new BVO();
			
			bvo.setB_idx(bvo2.getB_idx());
			bvo.setSubject(mr.getParameter("subject"));
			bvo.setWriter(mr.getParameter("writer"));
			bvo.setContent(mr.getParameter("content"));
			bvo.setPwd(mr.getParameter("pwd"));
			
			String old_file_name = mr.getParameter("old_file_name");
			
			System.out.println(old_file_name);
			
			// 첨부파일이 없으면 이전파일로 대체한다.
			if (mr.getFile("file_name") == null) {
				bvo.setFile_name(old_file_name);
				
			} else {// 있으면 이걸로 한다.
				bvo.setFile_name(mr.getFilesystemName("file_name"));
			}
			
			int result = DAO.getUpdate(bvo);
			//DB에서 삭제가 되었을 때
			if(result>0) {
				//삭제하는거 넣으면됨
				try {
					File file = new File(path + "/" + new String(old_file_name.getBytes("utf-8")));
					if (file.exists() && !bvo.getFile_name().equals(old_file_name)) {
						file.delete();
					}
				} catch (Exception e) {
					System.out.println("삭제쪽 문제");
					System.out.println(e);
				}
				return "MyController?cmd=onelist&b_idx=" + bvo.getB_idx();
			}
		} catch (Exception e) {
			System.out.println("어디문제냐");
			System.out.println(e);
		}
		System.out.println("여기까지 내려오냐?");
		return null;
	}

}
