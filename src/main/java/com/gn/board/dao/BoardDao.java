package com.gn.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import static com.gn.common.sql.JDBCTemplate.close;

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
			pstmt.setInt(3, b.getBoard_wirter());
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
}
