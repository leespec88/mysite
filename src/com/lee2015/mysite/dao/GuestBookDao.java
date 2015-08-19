package com.lee2015.mysite.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.lee2015mysite.vo.GuestBookVo;


public class GuestBookDao {
	
	// GuestBookDao 싱글톤 만들기.
	
	private static GuestBookDao instance = null;
	
	 GuestBookDao(){
		
	}
	
	public static GuestBookDao getInstance(){
		if(instance == null)
			instance = new GuestBookDao();
		
		return instance;
	}
	
	private Connection getConnection() throws SQLException{
		
		Connection con = null;
		try{
		// 1. 드라이버 로드
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		// 2. connection 얻기
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bitdb","bitdb");
		}catch (ClassNotFoundException ex)
		{
			System.out.println("드라이버 오류:"+ex);
		}
		return con;
	}
	
	public void insert(GuestBookVo vo) throws SQLException{
		
		Connection con = getConnection();
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "insert into guestbook values(guestbook_seq.nextval,?,?,?,sysdate)";
		pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, vo.getName());
		pstmt.setString(2, vo.getPassword());
		pstmt.setString(3, vo.getMessage());
		
		pstmt.executeUpdate();
		
		pstmt.close();
		con.close();
		
	}
	
	public List<GuestBookVo> getList() throws SQLException{
		
		List<GuestBookVo> list = new ArrayList<GuestBookVo>();
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Connection con = getConnection();
		stmt = con.createStatement();
		String sql = "select no, name, password, message, to_char(reg_date, 'YYYY-MM-DD HH:MM:SS') from guestbook order by no";
		
		rs = stmt.executeQuery(sql);
		while(rs.next()){
			GuestBookVo vo = new GuestBookVo();
			long no = rs.getLong(1);
			String name = rs.getString(2);
			String message = rs.getString(4);
			String reg_date = rs.getString(5);
			
			vo.setNo(no);
			vo.setName(name);
			vo.setMessage(message);
			vo.setReg_date(reg_date);
			
			list.add(vo);
		}
		rs.close();
		stmt.close();
		con.close();
		
		return list;
	}
	
	public void delete( long no, String pw ) throws SQLException{
		
		Connection con = getConnection();
		
		String sql="delete from guestbook where no=? and password=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setLong(1, no);
		pstmt.setString(2, pw);
		
		pstmt.executeUpdate();
		pstmt.close();
		con.close();
		
	}
}
