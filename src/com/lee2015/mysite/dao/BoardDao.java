package com.lee2015.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Parent;

import com.lee2015mysite.vo.BoardVo;



public class BoardDao {
	private static BoardDao instance = null;

	BoardDao() {
	}

	public static BoardDao getInstance() {
		if (instance == null)
			instance = new BoardDao();

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
	
	public void insert(BoardVo vo) throws SQLException{
		
		Connection con = getConnection();
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "insert into board values(board_no_seq.nextval,?,?,?,?, 0, sysdate)";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContent());
		pstmt.setLong(3, vo.getMemberNo());
		pstmt.setString(4, vo.getMemberName());
		
		pstmt.executeUpdate();
		
		pstmt.close();
		con.close();
	}
	
	public List<BoardVo> getList() throws SQLException{
		
		List<BoardVo> list = new ArrayList<BoardVo>();
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Connection con = getConnection();
		stmt = con.createStatement();
		String sql = "select no, title, content, member_no, member_name, view_cnt, to_char(reg_date, 'YYYY-MM-DD HH:MM:SS') from board order by no desc";
		
		rs = stmt.executeQuery(sql);
		while(rs.next()){
			
			long no = rs.getLong(1);
			String title = rs.getString(2);
			String content = rs.getString(3);
			long memberNo = rs.getLong(4);
			String memberName = rs.getString(5);
			long viewCnt = rs.getLong(6);
			String regDate = rs.getString(7);
			
			BoardVo vo = new BoardVo();
			vo.setNo(no);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setMemberNo(memberNo);
			vo.setMemberName(memberName);
			vo.setViewCnt(viewCnt);
			vo.setRegDate(regDate);
			
			list.add(vo);
			
		}
		rs.close();
		stmt.close();
		con.close();
		
		return list;
	}
	
	public List<BoardVo> getList(int currentPageNo) throws SQLException{
		
		List<BoardVo> list = new ArrayList<BoardVo>();
		
		Connection con = getConnection();
		String sql = "select * from ( select A.*, rownum as rnum, floor((rownum-1)/5+1) as page,count(*) over() as totcnt from (select no, view_cnt, member_no, member_name, title, to_char(reg_date,'YYYY-MM-DD HH:MI:SS')from board order by reg_date desc)A ) where page=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, currentPageNo);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()){
			long no = rs.getLong(1);
			long viewCnt = rs.getLong(2);
			long memberNo = rs.getLong(3);
			String memberName=rs.getString(4);
			String title = rs.getString(5);
			String regDate = rs.getString(6);
			
			
			BoardVo vo = new BoardVo();
			vo.setNo(no);
			vo.setViewCnt(viewCnt);
			vo.setMemberNo(memberNo);
			vo.setMemberName(memberName);
			vo.setTitle(title);
			vo.setRegDate(regDate);
			
			
			list.add(vo);
		}
		
		rs.close();
		pstmt.close();
		con.close();
		return list;
		
	}
	
	public void delete(long no) throws SQLException{
		
		Connection con = getConnection();
		
		String sql="delete from board where no=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setLong(1, no);
		
		pstmt.executeUpdate();
		pstmt.close();
		con.close();
	}
	
	public BoardVo get(long no) throws SQLException{
		
		Connection con = getConnection();
		
		String sql="select no, title, content, member_no, member_name, view_cnt, to_char(reg_date, 'YYYY-MM-DD HH:MM:SS') from board where no=? ";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setLong(1, no);
		ResultSet rs = pstmt.executeQuery();
		
		BoardVo vo =null;
		
		if(rs.next()){
		
			vo = new BoardVo();
			
			vo.setNo(rs.getLong(1));
			vo.setTitle(rs.getString(2));
			vo.setContent(rs.getString(3));
			vo.setMemberNo(rs.getLong(4));
			vo.setMemberName(rs.getString(5));
			vo.setViewCnt(rs.getLong(6));
			vo.setRegDate(rs.getString(7));
			
		}
		
		return vo;
	}
		


	public void update(long no, long viewCnt) throws SQLException{
		
		Connection con = getConnection();
		
		String sql = "update BOARD set view_cnt = ?where no=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setLong(1, viewCnt+1);
		pstmt.setLong(2, no);
		
		pstmt.executeUpdate();
		pstmt.close();
		
		con.close();
		
	}
	
	public void update(long no, String title, String content) throws SQLException{
		
		Connection con = getConnection();
		
		String sql = "update BOARD set title = ?, content=? where no=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setLong(3, no);
		
		pstmt.executeUpdate();
		pstmt.close();
		
		con.close();
		
	}
	
	public List<BoardVo> search(String kwd) throws SQLException{
		
		Connection con = getConnection();
		String sql ="select no, title, content, member_no, member_name, view_cnt, to_char(reg_date, 'YYYY-MM-DD HH:MM:SS') from board where content like ? or title like ? or member_name like ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, "%"+kwd+"%");
		pstmt.setString(2, "%"+kwd+"%");
		pstmt.setString(3, "%"+kwd+"%");
		ResultSet rs = pstmt.executeQuery();
		List<BoardVo> list = new ArrayList<BoardVo>();
		
		while(rs.next()){
			
			long no = rs.getLong(1);
			String title = rs.getString(2);
			String content = rs.getString(3);
			long memberNo = rs.getLong(4);
			String memberName = rs.getString(5);
			long viewCnt = rs.getLong(6);
			String regDate = rs.getString(7);
			
			BoardVo vo = new BoardVo();
			vo.setNo(no);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setMemberNo(memberNo);
			vo.setMemberName(memberName);
			vo.setViewCnt(viewCnt);
			vo.setRegDate(regDate);
			
			list.add(vo);
		}
		return list;
	}
	
	public int totCnt() throws SQLException{
		
		Connection con = getConnection();
		
		String sql = "select count(*) from board";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		int cnt = 0;
		if(rs.next()){
			cnt = rs.getInt(1);
		}
		
		rs.close();
		stmt.close();
		con.close();
		
		return cnt;
	}
	
	
}
