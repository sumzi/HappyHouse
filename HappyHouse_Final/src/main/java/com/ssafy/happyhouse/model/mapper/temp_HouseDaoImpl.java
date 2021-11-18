package com.ssafy.happyhouse.model.mapper;
///*
// * package com.ssafy.model.mapper;
// * 
// * import java.sql.Connection; import java.sql.PreparedStatement; import
// * java.sql.ResultSet; import java.sql.SQLException; import java.util.ArrayList;
// * import java.util.List;
// * 
// * import com.ssafy.model.HouseDto; import com.ssafy.util.DBUtil;
// * 
// * public class HouseDaoImpl implements HouseDao { // private static HouseDao
// * dealDao;
// * 
// * 
// * private DBUtil dbutil = DBUtil.getInstance();
// * 
// * // public static HouseDao getDealDao() { // if(dealDao == null) // dealDao =
// * new HouseDaoImpl(); // return dealDao; // }
// * 
// * @Override public void register(HouseDto dealDto) throws SQLException {
// * Connection conn = null; PreparedStatement pstmt = null; try { conn =
// * dbutil.getConnection(); String sql =
// * "insert into housedeal(dong, AptName, code, dealAmount, " +
// * "buildYear, dealYear, dealMonth, dealDay, " +
// * "area, floor, jibun, type, rentMoney)\r\n" +
// * "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; pstmt =
// * conn.prepareStatement(sql); pstmt.setString(1, dealDto.getDong());
// * pstmt.setString(2, dealDto.getAptName()); pstmt.setString(3,
// * dealDto.getCode()); pstmt.setString(4, dealDto.getDealAmount());
// * pstmt.setString(5, dealDto.getBuildYear()); pstmt.setString(6,
// * dealDto.getDealYear()); pstmt.setString(7, dealDto.getDealMonth());
// * pstmt.setString(8, dealDto.getDealDay()); pstmt.setString(9,
// * dealDto.getArea()); pstmt.setString(10, dealDto.getFloor());
// * pstmt.setString(11, dealDto.getJibun()); pstmt.setString(12,
// * dealDto.getType()); pstmt.setString(13, dealDto.getRentMoney());
// * 
// * pstmt.execute(); }finally { dbutil.close(pstmt, conn); }
// * 
// * }
// * 
// * @Override public List<HouseDto> searchAll() throws SQLException {
// * List<HouseDto> list = new ArrayList<>();
// * 
// * try (Connection conn = dbutil.getConnection();) {
// * 
// * String sql = "select d.*, i.lat, i.lng \n" + "from housedeal d \n" +
// * "left outer join houseinfo i \n" +
// * "on(d.dong = i.dong and d.AptName = i.AptName and d.code = i.code)\n" +
// * "limit 100";
// * 
// * 
// * try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs =
// * pstmt.executeQuery();) { while (rs.next()) { HouseDto dealDto = new
// * HouseDto(); dealDto.setNo(rs.getInt("no"));
// * dealDto.setDong(rs.getString("dong"));
// * dealDto.setAptName(rs.getString("AptName"));
// * dealDto.setCode(rs.getString("code"));
// * dealDto.setDealAmount(rs.getString("dealAmount"));
// * dealDto.setBuildYear(rs.getString("buildYear"));
// * dealDto.setDealYear(rs.getString("dealYear"));
// * dealDto.setDealMonth(rs.getString("dealMonth"));
// * dealDto.setDealDay(rs.getString("dealDay"));
// * dealDto.setArea(rs.getString("area"));
// * dealDto.setFloor(rs.getString("floor"));
// * dealDto.setJibun(rs.getString("jibun"));
// * dealDto.setType(rs.getString("type"));
// * dealDto.setRentMoney(rs.getString("rentMoney"));
// * dealDto.setLat(rs.getString("lat")); dealDto.setLng(rs.getString("lng"));
// * 
// * list.add(dealDto); } } } return list; }
// * 
// * @Override public List<HouseDto> searchByAptName(String aptName) throws
// * SQLException { List<HouseDto> list = new ArrayList<>(); PreparedStatement
// * pstmt = null;
// * 
// * try (Connection conn = dbutil.getConnection();) { String sql =
// * "select d.*, i.lat, i.lng \n" + "from housedeal d \n" +
// * "left outer join houseinfo i \n" +
// * "on(d.dong = i.dong and d.AptName = i.AptName and d.code = i.code)\n" +
// * "where d.AptName like ? \n" + "order by d.AptName \n" + "limit 100 \n";
// * 
// * pstmt = conn.prepareStatement(sql); pstmt.setString(1, "%"+aptName+"%");
// * 
// * try (ResultSet rs = pstmt.executeQuery();) { while (rs.next()) { HouseDto
// * dealDto = new HouseDto(); dealDto.setNo(rs.getInt("no"));
// * dealDto.setDong(rs.getString("dong"));
// * dealDto.setAptName(rs.getString("AptName"));
// * dealDto.setCode(rs.getString("code"));
// * dealDto.setDealAmount(rs.getString("dealAmount"));
// * dealDto.setBuildYear(rs.getString("buildYear"));
// * dealDto.setDealYear(rs.getString("dealYear"));
// * dealDto.setDealMonth(rs.getString("dealMonth"));
// * dealDto.setDealDay(rs.getString("dealDay"));
// * dealDto.setArea(rs.getString("area"));
// * dealDto.setFloor(rs.getString("floor"));
// * dealDto.setJibun(rs.getString("jibun"));
// * dealDto.setType(rs.getString("type"));
// * dealDto.setRentMoney(rs.getString("rentMoney"));
// * dealDto.setLng(rs.getString("lng")); dealDto.setLat(rs.getString("lat"));
// * 
// * list.add(dealDto); } } return list; }
// * 
// * }
// * 
// * @Override public List<HouseDto> searchByDong(String dong) throws SQLException
// * { List<HouseDto> list = new ArrayList<>(); PreparedStatement pstmt = null;
// * 
// * try (Connection conn = dbutil.getConnection();) { String sql =
// * "select d.*, i.lat, i.lng \n" + "from housedeal d \n" +
// * "left outer join houseinfo i \n" +
// * "on(d.dong = i.dong and d.AptName = i.AptName and d.code = i.code)\n" +
// * "where d.dong like ? \n" + "order by d.AptName desc \n" + "limit 100 \n";
// * 
// * pstmt = conn.prepareStatement(sql); pstmt.setString(1, "%"+dong+"%");
// * 
// * System.out.println(); System.out.println(pstmt.toString());
// * 
// * try (ResultSet rs = pstmt.executeQuery();) { while (rs.next()) { HouseDto
// * dealDto = new HouseDto(); dealDto.setNo(rs.getInt("no"));
// * dealDto.setDong(rs.getString("dong"));
// * dealDto.setAptName(rs.getString("AptName"));
// * dealDto.setCode(rs.getString("code"));
// * dealDto.setDealAmount(rs.getString("dealAmount"));
// * dealDto.setBuildYear(rs.getString("buildYear"));
// * dealDto.setDealYear(rs.getString("dealYear"));
// * dealDto.setDealMonth(rs.getString("dealMonth"));
// * dealDto.setDealDay(rs.getString("dealDay"));
// * dealDto.setArea(rs.getString("area"));
// * dealDto.setFloor(rs.getString("floor"));
// * dealDto.setJibun(rs.getString("jibun"));
// * dealDto.setType(rs.getString("type"));
// * dealDto.setRentMoney(rs.getString("rentMoney"));
// * dealDto.setLng(rs.getString("lng")); dealDto.setLat(rs.getString("lat"));
// * 
// * list.add(dealDto); } } return list; }
// * 
// * }
// * 
// * @Override public void updateAptName(int no, String aptName) throws
// * SQLException { PreparedStatement pstmt = null; Connection conn = null;
// * 
// * try { conn = dbutil.getConnection(); String sql = "update housedeal \n" +
// * "set AptName = ? \n" + "where no = ?";
// * 
// * pstmt = conn.prepareStatement(sql); pstmt.setString(1, aptName);
// * pstmt.setInt(2, no);
// * 
// * pstmt.executeUpdate(); } finally { dbutil.close(pstmt, conn); }
// * 
// * 
// * }
// * 
// * @Override public void delete(int no) throws SQLException { Connection conn =
// * null; PreparedStatement pstmt = null; try { conn = dbutil.getConnection();
// * String sql = "delete from housedeal where no = ?"; pstmt =
// * conn.prepareStatement(sql); pstmt.setInt(1, no);
// * 
// * pstmt.executeUpdate(); } finally { try { if (pstmt != null) pstmt.close(); if
// * (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace();
// * } }
// * 
// * }
// * 
// * @Override public HouseDto searchByNo(String no) throws SQLException {
// * HouseDto house = new HouseDto(); PreparedStatement pstmt = null;
// * 
// * try (Connection conn = dbutil.getConnection();) { String sql =
// * "select d.*, i.lat, i.lng \n" + "from housedeal d \n" +
// * "left outer join houseinfo i \n" +
// * "on(d.dong = i.dong and d.AptName = i.AptName and d.code = i.code)\n" +
// * "where d.no = ?" + "order by d.AptName";
// * 
// * pstmt = conn.prepareStatement(sql); pstmt.setString(1, no);
// * 
// * try (ResultSet rs = pstmt.executeQuery();) { while (rs.next()) {
// * house.setNo(rs.getInt("no")); house.setDong(rs.getString("dong"));
// * house.setAptName(rs.getString("AptName"));
// * house.setCode(rs.getString("code"));
// * house.setDealAmount(rs.getString("dealAmount"));
// * house.setBuildYear(rs.getString("buildYear"));
// * house.setDealYear(rs.getString("dealYear"));
// * house.setDealMonth(rs.getString("dealMonth"));
// * house.setDealDay(rs.getString("dealDay"));
// * house.setArea(rs.getString("area")); house.setFloor(rs.getString("floor"));
// * house.setJibun(rs.getString("jibun")); house.setType(rs.getString("type"));
// * house.setRentMoney(rs.getString("rentMoney"));
// * house.setLng(rs.getString("lng")); house.setLat(rs.getString("lat")); return
// * house; } } return null; } }
// * 
// * @Override public void updateApt(int no, HouseDto house) throws SQLException {
// * PreparedStatement pstmt = null; Connection conn = null;
// * 
// * try { conn = dbutil.getConnection(); String sql = "update housedeal \n" +
// * "set \n" + "dong = ? \n" + "AptName = ? \n" + "code = ? \n" +
// * "dealAmount = ? \n" + "buildYear = ? \n" + "dealYear = ? \n" +
// * "dealMonth = ? \n" + "dealDay = ? \n" + "area = ? \n" + "floor = ? \n" +
// * "jibun = ? \n" + "type = ? \n" + "rentMoney = ? \n" + "where no = ?";
// * 
// * pstmt = conn.prepareStatement(sql); pstmt.setString(1, house.getDong());
// * pstmt.setString(2, house.getAptName()); pstmt.setString(3, house.getCode());
// * pstmt.setString(4, house.getDealAmount()); pstmt.setString(5,
// * house.getBuildYear()); pstmt.setString(6, house.getDealYear());
// * pstmt.setString(7, house.getDealMonth()); pstmt.setString(8,
// * house.getDealDay()); pstmt.setString(9, house.getArea()); pstmt.setString(10,
// * house.getFloor()); pstmt.setString(11, house.getJibun()); pstmt.setString(12,
// * house.getType()); pstmt.setString(13, house.getRentMoney()); pstmt.setInt(14,
// * house.getNo());
// * 
// * pstmt.executeUpdate(); } finally { dbutil.close(pstmt, conn); }
// * 
// * }
// * 
// * }
// */