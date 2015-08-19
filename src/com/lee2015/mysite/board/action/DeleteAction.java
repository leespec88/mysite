package com.lee2015.mysite.board.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lee2015.mysite.dao.BoardDao;
import com.lee2015.mysite.web.action.Action;
import com.lee2015.mysite.web.util.WebUtil;

public class DeleteAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try{
		
		String sno = request.getParameter("no");
		long no = Long.parseLong(sno);
		System.out.println(no);
		
		BoardDao dao = BoardDao.getInstance();
		dao.delete(no);
		
		WebUtil.redirect(request, response, "/board");
		}catch(SQLException ex){
			System.out.println("DB ERROR :"+ex);
		}

	}

}
