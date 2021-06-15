package com.ict.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.BVO;
import com.ict.db.DAO;

public class OneListCommand implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		String b_idx = request.getParameter("b_idx");
		BVO bvo = DAO.getOneList(b_idx);

		// 조회수 업데이터
		System.out.println(bvo.getFile_name());
		// 내용 가져오기
		// 수정과 삭제를 위해서 session 저장
		request.getSession().setAttribute("bvo", bvo);

		// b_idx를 가지고 관련 댓글 가져오기

		return "view/onelist.jsp";

	}

}
