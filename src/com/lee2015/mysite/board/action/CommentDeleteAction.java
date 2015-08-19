package com.lee2015.mysite.board.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lee2015.mysite.dao.CommentDao;
import com.lee2015.mysite.web.action.Action;
import com.lee2015.mysite.web.util.WebUtil;

public class CommentDeleteAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		try{
			long no = Long.parseLong(request.getParameter("no"));
			String contentNo = request.getParameter("contentNo");
			CommentDao dao = CommentDao.getInstance();
			dao.delete(no);
			
			WebUtil.redirect(request, response, "/board?a=viewform&no="+contentNo);
		
		}catch(SQLException ex){
			System.out.println("db Error : "+ ex);
		}
	}

}
