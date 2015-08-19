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

public class ModifyAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		try{
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");
			String sno = request.getParameter("no");
			long no = Long.parseLong(sno);
			
			UserDao dao = UserDao.getInstance();
			dao.update(name, password, gender, no);
			
			HttpSession session = request.getSession();
			UserVo vo = (UserVo)session.getAttribute("authUser");
			
			vo.setName(name);
			vo.setPassword(password);
			vo.setGender(gender);
			
			WebUtil.redirect(request, response, "/");
		
		}catch(SQLException ex){
			System.out.println("db error :"+ex);
		}
		

	}

}
