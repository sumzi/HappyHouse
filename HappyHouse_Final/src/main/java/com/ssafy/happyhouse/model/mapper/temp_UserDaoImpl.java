package com.ssafy.happyhouse.model.mapper;
//package com.ssafy.model.mapper;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import com.ssafy.model.UserDto;
//import com.ssafy.util.DBUtil;
//
//public class UserDaoImpl implements UserDao {
//	private DBUtil db = DBUtil.getInstance();
//	private static UserDao instance = new UserDaoImpl();
//
//	public static UserDao getInstance() {
//		return instance;
//	}
//	@Override
//	public void insert(UserDto user) throws SQLException {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		try {
//			conn = db.getConnection();
//			StringBuilder sql = new StringBuilder();
//			sql.append(" insert into userinfo (userId,userPw,userEmail,userPhone,userName) \n");
//			sql.append(" values (?,?,?,?,?) ");
//			pstmt = conn.prepareStatement(sql.toString());
//
//			pstmt.setString( 1, user.getUserId());
//			pstmt.setString( 2, user.getUserPw());
//			pstmt.setString( 3, user.getUserEmail());
//			pstmt.setString( 4, user.getUserPhone());
//			pstmt.setString( 5, user.getUserName());
//			pstmt.executeUpdate();
//		} finally {
//			db.close(pstmt);
//			db.close(conn);
//		}
//	}
//	@Override
//	public void update(UserDto user) throws SQLException {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		try {
//			conn = db.getConnection();
//			StringBuilder sql = new StringBuilder();
//			sql.append(" update userinfo set userPw=?, userEmail=?, userPhone =?, userName=? \n");
//			sql.append(" where userId=?");
//			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setString( 1, user.getUserPw());
//			pstmt.setString( 2, user.getUserEmail());
//			pstmt.setString( 3, user.getUserPhone());
//			pstmt.setString( 4, user.getUserName());
//			pstmt.setString( 5, user.getUserId());
//			pstmt.executeUpdate();
//		} finally {
//			db.close(pstmt);
//			db.close(conn);
//		}
//	}
//
//	@Override
//	public void delete(String id) throws SQLException {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		try {
//			conn = db.getConnection();
//			StringBuilder sql = new StringBuilder("delete from userinfo where userId=? ");
//			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setString(1, id);
//			pstmt.executeUpdate();
//		} finally {
//			db.close(pstmt);
//			db.close(conn);
//		}
//	}
//
//	@Override
//	public String findPass(String id, String email, String name) throws SQLException {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			conn = db.getConnection();
//			StringBuilder sql = new StringBuilder();
//			sql.append(" select * from userinfo \n");
//			sql.append(" where userId=? and userEmail=? and userName=? ");
//			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setString(1, id);
//			pstmt.setString(2, email);
//			pstmt.setString(3, name);
//			rs = pstmt.executeQuery();
//			if(rs.next()) {
//				return rs.getString("userPw");
//			}
//		} finally {
//			db.close(rs);
//			db.close(pstmt);
//			db.close(conn);
//		}
//		return null;
//	}
//
//	@Override
//	public UserDto login(String id, String pass) throws SQLException {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			conn = db.getConnection();
//			StringBuilder sql = new StringBuilder();
//			sql.append(" select * from userinfo where userId=? and userPw=?" );
//			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setString(1, id);
//			pstmt.setString(2, pass);
//			rs = pstmt.executeQuery();
//			if(rs.next()) {
//				return new UserDto( rs.getString("userId"), 
//						 		 rs.getString("userPw"), 
//						 		 rs.getString("userEmail"), 
//						 		 rs.getString("userPhone"),
//						 		 rs.getString("userName"));
//			}
//		} finally {
//			db.close(rs);
//			db.close(pstmt);
//			db.close(conn);
//		}
//		return null;
//	}
//
//}
