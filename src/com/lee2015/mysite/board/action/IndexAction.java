package com.lee2015.mysite.board.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

import com.lee2015.mysite.dao.BoardDao;
import com.lee2015.mysite.web.action.Action;
import com.lee2015.mysite.web.util.WebUtil;
import com.lee2015mysite.vo.BoardVo;

public class IndexAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		try{
			
			String pageNo = request.getParameter("currentPageNo");
			//현재 페이지넘버
			int currentPageNo;
			if(pageNo==null){
				currentPageNo=1;
			}else{
				currentPageNo = Integer.parseInt(pageNo);
			}
			
			BoardDao dao = BoardDao.getInstance();
			List<BoardVo> pageList = dao.getList(currentPageNo);
			
			
			
			int totCnt = dao.totCnt(); // 총게시물 수
			int pageSize = 5; // 페이지 당 게시물 수
			int totPageNo = (totCnt/pageSize)+(totCnt%pageSize==0?0:1);// 페이지 총수 
			int groupSize = 3; // 페이지를 구룹핑할 갯수 [1][2][3] 다음 [4][5][6] 세개다 이게
			int currentGroupNo = (currentPageNo/groupSize)+(currentPageNo%groupSize==0?0:1); //현재 내가 누른 페이지의 구릅넘버
			int currentGroupStartPage = (currentGroupNo-1)*groupSize+1;
			int currentGroupEndPage = (currentGroupNo*groupSize);
			int startRow = (currentPageNo-1)*pageSize+1;
			int endRow = currentPageNo*pageSize;
			int endGroupNo = (totCnt/pageSize)+(totCnt%groupSize==0?0:1);
			int pageJumpRight = (currentGroupStartPage+groupSize);
			int pageJumpLeft = (currentGroupEndPage-groupSize);
			int jumperPagingEndNumb = (totPageNo%groupSize);
			
			
			request.setAttribute("totCnt", totCnt);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("groupSize", groupSize);
			request.setAttribute("currentGroupNo", currentGroupNo);
			request.setAttribute("startRow", startRow);
			request.setAttribute("endRow", endRow);
			request.setAttribute("currentGroupStartPage", currentGroupStartPage);
			request.setAttribute("currentGroupEndPage", currentGroupEndPage);
			request.setAttribute("pageJumpRight", pageJumpRight);
			request.setAttribute("pageJumpLeft", pageJumpLeft);
			request.setAttribute("currentPageNo", currentPageNo);
			request.setAttribute("endGroupNo", endGroupNo);
			request.setAttribute("jumperPagingEndNumb", jumperPagingEndNumb);
			request.setAttribute("pageList", pageList);
			
			WebUtil.forward(request, response, "/views/board/list.jsp");
			
		}catch(SQLException ex){
			System.out.println("db error :"+ex);
		}
		

	}

}
