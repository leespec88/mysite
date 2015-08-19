package com.lee2015.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lee2015mysite.vo.BoardVo;
import com.lee2015mysite.vo.CommentVo;

public class CommentDao {
	
	private static CommentDao instance = null;

	CommentDao() {
	}

	public static CommentDao getInstance() {
		if (instance == null)
			instance = new CommentDao();

		return instance;
	}

	private Connection getConnection() throws SQLException {

		Connection con = null;
		
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bitdb", "bitdb");

		} catch (ClassNotFoundException ex) {

			System.out.println("드라이버 오류:" + ex);
		}
		return con;
	}
	
	public void insert(String content, long contentNo, long userNo, String userName) throws SQLException{
		
		Connection con = getConnection();
		
		String sql="insert into CONTENT_COMMENT values(comm_seq.nextval, ?, sysdate, ?,  ?, ?)";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, content);
		pstmt.setLong(2, contentNo);
		pstmt.setLong(3, userNo);
		pstmt.setString(4, userName);
		
		pstmt.executeUpdate();
		pstmt.close();
		con.close();
	}
	
	public List<CommentVo> getList() throws SQLException{
		
		Connection con = getConnection();
		List<CommentVo> list = new ArrayList<CommentVo>();
		Statement stmt = con.createStatement();
		String sql = "select * from content_comment order by reg_date asc";
		
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			
			CommentVo vo =new CommentVo();
			
			long no = rs.getLong(1);
			String content = rs.getString(2);
			String regDate = rs.getString(3);
			long contentNo = rs.getLong(4);
			long authUserNo = rs.getLong(5);
			String authUserName = rs.getString(6);
			
			vo.setNo(no);
			vo.setContent(content);
			vo.setRegDate(regDate);
			vo.setContentNo(contentNo);
			vo.setAuthUserNo(authUserNo);
			vo.setAuthUserName(authUserName);
			
			list.add(vo);
		}
		rs.close();
		stmt.close();
		con.close();
		
		return list;
		
	}
	
	public void delete(long no) throws SQLException{
		Connection con = getConnection();
		
		String sql = "delete from content_comment where no=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setLong(1, no);
		pstmt.executeUpdate();
		
		pstmt.close();
		con.close();
	}
	
	
}
