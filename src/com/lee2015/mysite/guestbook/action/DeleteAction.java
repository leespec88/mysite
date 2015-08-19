package com.lee2015.mysite.guestbook.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lee2015.mysite.dao.GuestBookDao;
import com.lee2015.mysite.web.action.Action;
import com.lee2015.mysite.web.util.WebUtil;

public class DeleteAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try{
			String sno = request.getParameter("no");
			long no = Long.parseLong(sno);
			String password = request.getParameter("password");
			
			GuestBookDao dao = GuestBookDao.getInstance();
			
			dao.delete(no, password);
			
			WebUtil.redirect(request, response, "/guestbook");
		}catch(SQLException ex){
			System.out.println("db error :"+ex);
		}
		
	}

}
