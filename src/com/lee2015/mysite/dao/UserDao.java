package com.lee2015.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lee2015mysite.vo.UserVo;

public class UserDao {

	private static UserDao instance = null;

	UserDao() {
	}

	public static UserDao getInstance() {
		if (instance == null)
			instance = new UserDao();

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

	public void insert(UserVo vo) throws SQLException {
		Connection con = getConnection();

		String sql = "insert into member values(member_no_seq.nextval, ?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, vo.getName());
		pstmt.setString(2, vo.getEmail());
		pstmt.setString(3, vo.getPassword());
		pstmt.setString(4, vo.getGender());

		pstmt.executeUpdate();
		pstmt.close();
		con.close();
	}
	//
	public UserVo get(String email) throws SQLException{
		
		Connection con = getConnection();
		String sql="select no, name, email, password, gender from member where email=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, email);
		
		ResultSet rs = pstmt.executeQuery();
		
		UserVo vo = null;
		
		if(rs.next()){
			
			vo=new UserVo();
			
			vo.setNo(rs.getLong(1));
			vo.setName(rs.getString(2));
			vo.setEmail(rs.getString(3));
			vo.setPassword(rs.getString(4));
			vo.setGender(rs.getString(5));
		}
		
		return vo;
	}
	
	//로그인
	public UserVo get(String email, String password) throws SQLException{
		
		Connection con = getConnection();
		String sql="select no, name, email, password, gender from member where email=? and password=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, email);
		pstmt.setString(2, password);
		
		ResultSet rs = pstmt.executeQuery();
		
		UserVo vo = null;
		
		if(rs.next()){
			
			vo=new UserVo();
			
			vo.setNo(rs.getLong(1));
			vo.setName(rs.getString(2));
			vo.setEmail(rs.getString(3));
			vo.setPassword(rs.getString(4));
			vo.setGender(rs.getString(5));
		}
		
		return vo;
	}

	public UserVo get(long no, String password) throws SQLException{
		
		Connection con = getConnection();
		String sql="select no, name, email, password, gender from member where no=? and password=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setLong(1, no);
		pstmt.setString(2, password);
		
		ResultSet rs = pstmt.executeQuery();
		
		UserVo vo = null;
		
		if(rs.next()){
			
			vo=new UserVo();
			
			vo.setNo(rs.getLong(1));
			vo.setName(rs.getString(2));
			vo.setEmail(rs.getString(3));
			vo.setPassword(rs.getString(4));
			vo.setGender(rs.getString(5));
		}
		
		return vo;
	}
	
	public void update(String name, String password, String gender, long no) throws SQLException{
		
		Connection con = getConnection();
		
		String sql = "update member set name=?, password=?, gender=? where no=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, name);
		pstmt.setString(2, password);
		pstmt.setString(3, gender);
		pstmt.setLong(4, no);
		
		pstmt.executeUpdate();
		pstmt.close();
		con.close();
	}
}
