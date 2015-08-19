package com.lee2015.mysite.user.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lee2015.mysite.dao.UserDao;
import com.lee2015.mysite.web.action.Action;
import com.lee2015.mysite.web.util.WebUtil;
import com.lee2015mysite.vo.UserVo;

public class LoginAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		try{
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			UserDao dao = UserDao.getInstance();
			UserVo vo = dao.get(email,password);
			
			if(vo==null){
				WebUtil.redirect(request, response, "/user?a=loginform&error=true");
				return;
			}
			
			HttpSession session = request.getSession(true);
			session.setAttribute("authUser", vo);
			
			WebUtil.redirect(request, response, "/");
		
		}catch(SQLException ex){
			System.out.println("db error :"+ex);
		}
		
	}
}
