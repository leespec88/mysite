package com.lee2015.mysite.board.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lee2015.mysite.web.action.Action;
import com.lee2015.mysite.web.util.WebUtil;

public class WriteFormAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		WebUtil.forward(request, response, "/views/board/write.jsp");

	}

}
