package com.lee2015.mysite.guestbook.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lee2015.mysite.dao.GuestBookDao;
import com.lee2015.mysite.web.action.Action;
import com.lee2015.mysite.web.util.WebUtil;
import com.lee2015mysite.vo.GuestBookVo;

public class IndexAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		try{
			GuestBookDao dao = GuestBookDao.getInstance();
			List<GuestBookVo> list = dao.getList();
			
			request.setAttribute("list", list);
			
			WebUtil.forward(request, response,"/views/guestbook/list.jsp");
		
		}catch(SQLException ex){
			System.out.println("db error : "+ex);
		}
		

	}

}
