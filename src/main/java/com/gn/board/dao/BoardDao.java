package com.gn.board.dao;

import static com.gn.common.sql.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gn.board.vo.Board;

public class BoardDao {
	
	public int createBoard(Board b,Connection conn) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = "insert into `board` (board_title,board_content,board_writer,ori_thumbnail,new_thumbnail) values (?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getBoard_title());
			pstmt.setString(2, b.getBoard_content());
			pstmt.setInt(3, b.getBoard_writer());
			pstmt.setString(4, b.getOri_thumbnail());
			pstmt.setString(5, b.getNew_thumbnail());
			
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public List<Board> selectBoardList(Board option , Connection conn){
		List<Board> list = new ArrayList<Board>(); // 비어있는 리스트 먼저 만들어주기
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 검색 조건
			// X : select * from board
			// O : select * from board where board_title like concat('%',board_title,'%')
			
			String sql = "select * from board";
			if(option.getBoard_title() != null) {
				sql += " where board_title like concat('%','"+option.getBoard_title()+"','%')";
			}
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Board resultVo = new Board(rs.getInt("board_no"),
						rs.getString("board_title"),
						rs.getString("board_content"),
						rs.getInt("board_writer"),
						rs.getTimestamp("reg_date").toLocalDateTime(),
						rs.getTimestamp("mod_date").toLocalDateTime(),
						rs.getString("ori_thumbnail"),
						rs.getString("new_thumbnail"));
				
				list.add(resultVo);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
}
