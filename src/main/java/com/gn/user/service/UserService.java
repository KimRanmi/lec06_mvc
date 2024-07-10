package com.gn.user.service;

import static com.gn.common.sql.JDBCTemplate.getConnection; 
import static com.gn.common.sql.JDBCTemplate.close;

import java.sql.Connection;

import com.gn.user.dao.UserDao;
import com.gn.user.vo.User;

public class UserService {
	
	public int createUser(User u) {
		// static한 메소드 import 받아주기
		Connection conn = getConnection();
		int result = new UserDao().createUser(u,conn);
		close(conn);
		return result;
	}
	public User loginUser(String id, String pw) {
		// 만들기
		Connection conn = getConnection();
		User u = new UserDao().loginUser(id,pw,conn);
		return u;
	}
}
