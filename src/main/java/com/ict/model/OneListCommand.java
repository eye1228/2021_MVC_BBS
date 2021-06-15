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

		// ��ȸ�� ��������
		System.out.println(bvo.getFile_name());
		// ���� ��������
		// ������ ������ ���ؼ� session ����
		request.getSession().setAttribute("bvo", bvo);

		// b_idx�� ������ ���� ��� ��������

		return "view/onelist.jsp";

	}

}
