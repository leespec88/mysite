package com.lee2015.mysite.board.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lee2015.mysite.dao.CommentDao;
import com.lee2015.mysite.web.action.Action;
import com.lee2015.mysite.web.util.WebUtil;
import com.lee2015mysite.vo.UserVo;

public class CommentAddAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		try{
			long commentNo = Long.parseLong(request.getParameter("commentNo"));
			String content = request.getParameter("content");
			
			HttpSession session = request.getSession();
			
			if(session==null){
				WebUtil.redirect(request, response, "/user?a=loginform");
				return;
			}
			UserVo vo = (UserVo)session.getAttribute("authUser");
			
			
			CommentDao dao = CommentDao.getInstance();
			dao.insert(content,commentNo, vo.getNo(), vo.getName());
			
			WebUtil.redirect(request, response, "board?a=viewform&no="+commentNo);
		
		}catch(SQLException ex){
			System.out.println("db Error :"+ex);
		}
		
		
		

	}

}
