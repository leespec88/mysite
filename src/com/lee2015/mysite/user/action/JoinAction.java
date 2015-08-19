package com.lee2015.mysite.user.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lee2015.mysite.dao.UserDao;
import com.lee2015.mysite.web.action.Action;
import com.lee2015.mysite.web.util.WebUtil;
import com.lee2015mysite.vo.UserVo;

public class JoinAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try{
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");
			
			UserVo vo = new UserVo();
			
			vo.setName(name);
			vo.setEmail(email);
			vo.setPassword(password);
			vo.setGender(gender);
			
			UserDao dao = UserDao.getInstance();
			dao.insert(vo);
			WebUtil.redirect(request, response, "/user?a=joinsuccess");
			
		}catch(SQLException ex){
			System.out.println("db error :"+ex);
		}

	}

}
