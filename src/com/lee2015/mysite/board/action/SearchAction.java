package com.lee2015.mysite.board.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lee2015.mysite.dao.BoardDao;
import com.lee2015.mysite.web.action.Action;
import com.lee2015.mysite.web.util.WebUtil;
import com.lee2015mysite.vo.BoardVo;

public class SearchAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try{
			String kwd = request.getParameter("kwd");
			
			BoardDao dao = BoardDao.getInstance();
			List<BoardVo> list = dao.search(kwd);
			
			request.setAttribute("pageList", list);
			WebUtil.forward(request, response, "/views/board/list.jsp");

		}catch(SQLException ex){
			System.out.println("DB Error : "+ex);
		}
		
	}

}
