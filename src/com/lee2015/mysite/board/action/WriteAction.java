package com.lee2015.mysite.board.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lee2015.mysite.dao.BoardDao;
import com.lee2015.mysite.dao.UserDao;
import com.lee2015.mysite.web.action.Action;
import com.lee2015.mysite.web.util.WebUtil;
import com.lee2015mysite.vo.BoardVo;
import com.lee2015mysite.vo.UserVo;

public class WriteAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		try{
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			HttpSession session = request.getSession();
			UserVo uvo = (UserVo)session.getAttribute("authUser");
			
			BoardVo bvo = new BoardVo();
			bvo.setMemberNo(uvo.getNo());
			bvo.setMemberName(uvo.getName());
			bvo.setTitle(title);
			bvo.setContent(content);
			
			BoardDao dao = BoardDao.getInstance();
			dao.insert(bvo);
			
			WebUtil.redirect(request, response, "/board");
		}catch(SQLException ex){
			System.out.println("db Error : "+ex);
		}

	}

}
