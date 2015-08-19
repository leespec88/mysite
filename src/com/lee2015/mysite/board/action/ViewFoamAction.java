package com.lee2015.mysite.board.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lee2015.mysite.dao.BoardDao;
import com.lee2015.mysite.dao.CommentDao;
import com.lee2015.mysite.web.action.Action;
import com.lee2015.mysite.web.util.WebUtil;
import com.lee2015mysite.vo.BoardVo;
import com.lee2015mysite.vo.CommentVo;

public class ViewFoamAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try{
			String sno = request.getParameter("no");
			long no = Long.parseLong(sno);
			BoardDao dao = BoardDao.getInstance();
			
			BoardVo vo = dao.get(no);
			dao.update(vo.getNo(), vo.getViewCnt());
			
			
			CommentDao cdao = CommentDao.getInstance();
			List<CommentVo> list = cdao.getList();
			
			request.setAttribute("bdvo", vo);
			request.setAttribute("list",list);
			
			WebUtil.forward(request, response, "/views/board/view.jsp");
		
		}catch(SQLException ex){
			System.out.println("db error :" + ex);
		}
		
		
		
		

	}

}
