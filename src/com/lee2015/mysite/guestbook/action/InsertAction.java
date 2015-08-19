package com.lee2015.mysite.guestbook.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lee2015.mysite.dao.GuestBookDao;
import com.lee2015.mysite.web.action.Action;
import com.lee2015.mysite.web.util.WebUtil;
import com.lee2015mysite.vo.GuestBookVo;

public class InsertAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		try{
			
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String message = request.getParameter("message");
		
		GuestBookVo vo = new GuestBookVo();
		
		vo.setName(name);
		vo.setPassword(password);
		vo.setMessage(message);
		
		GuestBookDao dao = GuestBookDao.getInstance();
		
		dao.insert(vo);
		
		WebUtil.redirect(request, response, "/guestbook");
		
		}catch(SQLException ex)
		{
			System.out.println("db error :"+ex);
		}
	}

}
