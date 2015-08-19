package com.lee2015.mysite.board.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lee2015.mysite.dao.BoardDao;
import com.lee2015.mysite.web.action.Action;
import com.lee2015.mysite.web.util.WebUtil;

public class ModifyAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		try{
		String sno = request.getParameter("no");
		long no = Long.parseLong(sno);
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardDao dao = BoardDao.getInstance();
		dao.update(no, title, content);
		
		WebUtil.redirect(request, response, "/board");
	
		}catch(SQLException ex){
			System.out.println("DB Error :"+ex);
		}
	}

}
