package com.lee2015.mysite.user.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lee2015.mysite.web.action.Action;
import com.lee2015.mysite.web.util.WebUtil;

public class LogoutAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		
		if(session==null){
			WebUtil.redirect(request, response, "/user?a=loginform");
			return;
		}
		
		session.removeAttribute("authUser");
		session.invalidate();
		
		WebUtil.redirect(request, response, "/");

	}

}
