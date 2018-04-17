package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import connectionpool.ConnectionPool;
import exception.CouponException;
import javabeans.Coupon;
import javabeans.CouponType;

public class CouponDBDAO implements CouponDAO {

	private ConnectionPool connectionPool;

	public CouponDBDAO() throws CouponException {
		try {
			this.connectionPool = ConnectionPool.getInstance();
		} catch (CouponException e) {
			throw new CouponException("wait until connection will be available for you", e);
		}
	}

	@Override
	public void createCoupon(Coupon couponToCreate) throws CouponException {
		String create_Coupon = "INSERT INTO COUPON VALUES (?,?,?,?,?,?,?,?,?)";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(create_Coupon);
			pstmt.setLong(1, couponToCreate.getCouponId());
			pstmt.setString(2, couponToCreate.getTitle());

			Date startDate = couponToCreate.getStartDate();
			long time = startDate.getTime();
			pstmt.setDate(3, new java.sql.Date(time));

			pstmt.setDate(4, new java.sql.Date(couponToCreate.getEndDate().getTime()));

			pstmt.setInt(5, couponToCreate.getAmount());
			pstmt.setString(6, couponToCreate.getType().toString());
			pstmt.setString(7, couponToCreate.getMessage());
			pstmt.setDouble(8, couponToCreate.getPrice());
			pstmt.setString(9, couponToCreate.getImage());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			String msg = "can't get connection to coupon table";
			CouponException couponException = new CouponException(msg, e);
			throw couponException;
		} finally {
			connectionPool.returnConnection(con);
		}
	}

	@Override
	public void removeCoupon(Coupon couponToRemove) throws CouponException {
		String remove_Coupon = "DELETE FROM COUPON WHERE COUPON_ID = ?";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(remove_Coupon);
			pstmt.setLong(1, couponToRemove.getCouponId());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			String msg = "can't get connection to coupon table";
			CouponException couponException = new CouponException(msg, e);
			throw couponException;
		} finally {
			connectionPool.returnConnection(con);
		}
	}

	@Override
	public void updateCoupon(Coupon couponToUpdate) throws CouponException {
		String update_Coupon = "UPDATE COUPON SET END_DATE=?, PRICE=?, AMOUNT = ? WHERE COUPON_ID = ?";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(update_Coupon);
			pstmt.setDate(1, new java.sql.Date(couponToUpdate.getEndDate().getTime()));
			pstmt.setDouble(2, couponToUpdate.getPrice());
			pstmt.setInt(3, couponToUpdate.getAmount());
			pstmt.setLong(4, couponToUpdate.getCouponId());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			String msg = "can't get connection to coupon table";
			CouponException couponException = new CouponException(msg, e);
			throw couponException;
		} finally {
			connectionPool.returnConnection(con);
		}
	}

	@Override
	public Coupon getCoupon(long couponId) throws CouponException {
		String get_Coupon = "SELECT * FROM COUPON WHERE COUPON_ID = ?";
		Connection con = connectionPool.getConnection();
		Coupon couponSelected = new Coupon();
		try {
			PreparedStatement pstmt = con.prepareStatement(get_Coupon);
			pstmt.setLong(1, couponId);
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				couponSelected.setCouponId(resultSet.getLong("COUPON_ID"));
				couponSelected.setTitle(resultSet.getString("TITLE"));

				java.sql.Date dateSQLStart = resultSet.getDate("START_DATE");
				long millisStart = dateSQLStart.getTime();
				couponSelected.setStartDate(new Date(millisStart));

				java.sql.Date dateSQLEnd = resultSet.getDate("END_DATE");
				long millisEnd = dateSQLEnd.getTime();
				couponSelected.setEndDate(new Date(millisEnd));

				couponSelected.setAmount(resultSet.getInt("AMOUNT"));
				couponSelected.setType(CouponType.valueOf(resultSet.getString("TYPE")));
				couponSelected.setMessage(resultSet.getString("MESSAGE"));
				couponSelected.setPrice(resultSet.getDouble("PRICE"));
				couponSelected.setImage(resultSet.getString("IMAGE"));
			}
			pstmt.close();
		} catch (SQLException e) {
			String msg = "can't get connection to coupon table";
			CouponException couponException = new CouponException(msg, e);
			throw couponException;
		} finally {
			connectionPool.returnConnection(con);
		}
		return couponSelected;
	}

	@Override
	public List<Coupon> getCoupons() throws CouponException {
		String get_Coupons = "SELECT * FROM COUPON";
		List<Coupon> allCoupons = new ArrayList<>();
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(get_Coupons);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Coupon couponSelected = new Coupon();
				couponSelected.setCouponId(rs.getLong("COUPON_ID"));
				couponSelected.setTitle(rs.getString("TITLE"));

				java.sql.Date dateSQLStart = rs.getDate("START_DATE");
				long millisStart = dateSQLStart.getTime();
				couponSelected.setStartDate(new Date(millisStart));

				java.sql.Date dateSQLEnd = rs.getDate("END_DATE");
				long millisEnd = dateSQLEnd.getTime();
				couponSelected.setEndDate(new Date(millisEnd));

				couponSelected.setAmount(rs.getInt("AMOUNT"));
				CouponType type = CouponType.valueOf(rs.getString("TYPE"));
				couponSelected.setType(type);
				couponSelected.setMessage(rs.getString("MESSAGE"));
				couponSelected.setPrice(rs.getDouble("PRICE"));
				couponSelected.setImage(rs.getString("IMAGE"));
				allCoupons.add(couponSelected);
			}
			pstmt.close();
		} catch (SQLException e) {
			String msg = "can't get connection to coupon table";
			CouponException couponException = new CouponException(msg, e);
			throw couponException;
		} finally {
			connectionPool.returnConnection(con);
		}
		return allCoupons;
	}

	@Override
	public List<Coupon> getCouponByType(CouponType type) throws CouponException {
		String get_Coupon_Type = "SELECT * FROM COUPON WHERE TYPE = ?";
		List<Coupon> allCouponsByType = new ArrayList<>();
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(get_Coupon_Type);
			pstmt.setString(1, type.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Coupon couponSelected = new Coupon();
				couponSelected.setCouponId(rs.getLong("COUPON_ID"));
				couponSelected.setTitle(rs.getString("TITLE"));

				java.sql.Date dateSQLStart = rs.getDate("START_DATE");
				long millisStart = dateSQLStart.getTime();
				couponSelected.setStartDate(new Date(millisStart));

				java.sql.Date dateSQLEnd = rs.getDate("END_DATE");
				long millisEnd = dateSQLEnd.getTime();
				couponSelected.setEndDate(new Date(millisEnd));

				couponSelected.setAmount(rs.getInt("AMOUNT"));
				couponSelected.setType(CouponType.valueOf(rs.getString("TYPE")));
				couponSelected.setMessage(rs.getString("MESSAGE"));
				couponSelected.setPrice(rs.getDouble("PRICE"));
				couponSelected.setImage(rs.getString("IMAGE"));
				allCouponsByType.add(couponSelected);
			}
			pstmt.close();
		} catch (SQLException e) {
			String msg = "can't get connection to coupon table";
			CouponException couponException = new CouponException(msg, e);
			throw couponException;
		} finally {
			connectionPool.returnConnection(con);
		}
		return allCouponsByType;
	}
}
