package com.gn.user.dao;


import static com.gn.common.sql.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import com.gn.user.vo.User;

public class UserDao {
	public int createUser(User u, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			String sql = "select count(*) as cnt from `user` where user_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u.getUser_id());
			
			rs = pstmt.executeQuery(); //select
			if(rs.next()) {
				result = rs.getInt("cnt");
			}
			
			if(result == 0){
				String sql2= "insert into `user` (user_id,user_pw,user_name) values (?,?,?)";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, u.getUser_id());
				pstmt.setString(2, u.getUser_pw());
				pstmt.setString(3, u.getUser_name());
				
				result = pstmt.executeUpdate(); // select 외 모든 것
			}else {
				result = 0;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
		
	}
	public User loginUser(String id,String pw, Connection conn) {
		User u = null;
		return u;
	}
}
