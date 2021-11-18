package com.ssafy.happyhouse.model.mapper;
//package com.ssafy.model.mapper;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.ssafy.model.NoticeDto;
//import com.ssafy.util.DBUtil;
//
//public class NoticeDaoImpl implements NoticeDao {
//	private DBUtil db = DBUtil.getInstance();
//	private static NoticeDao instance = new NoticeDaoImpl();
//
//	public static NoticeDao getInstance() {
//		return instance;
//	}
//	@Override
//	public void insert(NoticeDto notice) throws SQLException {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		try {
//			conn = db.getConnection();
//			StringBuilder sql = new StringBuilder();
//			sql.append("insert into notice (no, title, content, ndate, userName) \n");
//			sql.append(" values (?,?,?,now(),?) ");
//			pstmt = conn.prepareStatement(sql.toString());
//			int idx = 1;
//			pstmt.setString(idx++, notice.getNo());
//			pstmt.setString(idx++, notice.getTitle());
//			pstmt.setString(idx++, notice.getContent());
//			pstmt.setString(idx++, notice.getUserName());
//			pstmt.executeUpdate();
//		} finally {
//			db.close(conn);
//			db.close(pstmt);
//		}
//	}
//
//	@Override
//	public void update(NoticeDto notice) throws SQLException {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		try {
//			conn = db.getConnection();
//			StringBuilder sql = new StringBuilder();
//			sql.append("update notice set title=?, content=? \n");
//			sql.append(" where no = ? ");
//			pstmt = conn.prepareStatement(sql.toString());
//			int idx = 1;
//			pstmt.setString(idx++, notice.getTitle());
//			pstmt.setString(idx++, notice.getContent());
//			pstmt.setString(idx++, notice.getNo());
//			pstmt.executeUpdate();
//		} finally {
//			db.close(conn);
//			db.close(pstmt);
//		}
//	}
//
//	@Override
//	public void delete(String no) throws SQLException {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		try {
//			conn = db.getConnection();
//			StringBuilder sql = new StringBuilder(" delete from notice where no = ?");
//			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setString(1, no);
//			pstmt.executeUpdate();
//		} finally {
//			db.close(pstmt);
//			db.close(conn);
//		}
//	}
//
//	@Override
//	public NoticeDto select(String no) throws SQLException {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			conn = db.getConnection();
//			StringBuilder sql = new StringBuilder();
//			sql.append(" select * from notice where no = ? ");
//			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setString(1, no);
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				return new NoticeDto(rs.getString("no"), rs.getString("title"), rs.getString("content"),
//						rs.getString("ndate"),rs.getString("userName"));
//			}
//		} finally {
//			db.close(conn);
//			db.close(pstmt);
//			db.close(rs);
//		}
//		return null;
//	}
//
//	@Override
//	public List<NoticeDto> select(String key, String word) throws Exception {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		List<NoticeDto> notices = new ArrayList<>();
//		try {
//			conn = db.getConnection();
//			StringBuilder sql = new StringBuilder();
//			sql.append(" select * from notice \n");
//			if (!word.isEmpty()) {
//				if (key.equals("no")) {
//					sql.append(" where no = ? ");
//				} else if (key.equals("title")) {
//					sql.append(" where title like ? ");
//				} else if (key.equals("userName")) {
//					sql.append(" where userName like ? ");
//				}
//			}
//			pstmt = conn.prepareStatement(sql.toString());
//			System.out.println(sql);
//			if (!word.isEmpty()) {
//				if (key.equals("no")) {
//					pstmt.setString(1, word);
//				} else {
//					pstmt.setString(1, "%" + word + "%");
//				}
//			}
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				notices.add(new NoticeDto(rs.getString("no"), rs.getString("title"), rs.getString("content"),
//						rs.getString("ndate"),rs.getString("userName")));
//			}
//		} finally {
//			db.close(conn);
//			db.close(pstmt);
//			db.close(rs);
//		}
//		return notices;
//	}
//
//}
