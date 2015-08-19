package com.lee2015.mysite.user.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.collections.map.HashedMap;

import com.lee2015.mysite.dao.UserDao;
import com.lee2015.mysite.web.action.Action;
import com.lee2015mysite.vo.UserVo;

public class CheckEmailAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		try{
			String email=request.getParameter("email");
			
			UserDao dao = UserDao.getInstance();
			UserVo vo = dao.get(email);
			
			// { "result":"exist" }
			// { "result":"not exist" }
			
			response.setContentType("application/json; charset=utf-8");
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", (vo==null)?"not exist":"exist");
			
			JSONObject jsonObject = JSONObject.fromObject(map);
	
			PrintWriter out = response.getWriter();
			out.print(jsonObject.toString());
		
		}catch(SQLException ex){
			System.out.println("db error:"+ex);
		}
	
	}

}
