package com.ict.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.BVO;

public class Comm_DelCommand implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		BVO bvo = (BVO)request.getSession().getAttribute("bvo");
		return "MyController?cmd=onlist&b_idx=" + bvo.getB_idx();
	}

}
