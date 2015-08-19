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

public class DoCheckAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		try{
			String sno = request.getParameter("no");
			long no = Long.parseLong(sno);
			String password = request.getParameter("password");
			
			UserDao dao = UserDao.getInstance();
			UserVo vo = dao.get(no, password);
			
			if(vo == null){
				WebUtil.redirect(request, response, "/user?a=pwcheck");
				return;
			}
			
			WebUtil.forward(request, response, "/user?a=modifyform");
		
		
		}catch(SQLException ex){
			System.out.println("db error:"+ex);
		}

	}

}
