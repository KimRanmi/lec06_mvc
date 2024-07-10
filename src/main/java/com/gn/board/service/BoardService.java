package com.gn.board.service;

import static com.gn.common.sql.JDBCTemplate.close;
import static com.gn.common.sql.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.gn.board.dao.BoardDao;
import com.gn.board.vo.Board;

public class BoardService {
	
	public int createBoard(Board b) {
		Connection conn = getConnection();
		int result = new BoardDao().createBoard(b,conn);
		close(conn);
		return result;
	}
	
	public List<Board> selectBoardList(Board option){
		Connection conn = getConnection();
		List<Board> list = new BoardDao().selectBoardList(option,conn);
		close(conn);
		return list;
	}
}
