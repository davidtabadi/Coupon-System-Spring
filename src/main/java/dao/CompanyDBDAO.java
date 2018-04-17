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
import javabeans.Company;
import javabeans.Coupon;
import javabeans.CouponType;

public class CompanyDBDAO implements CompanyDAO {

	private ConnectionPool connectionPool;

	public CompanyDBDAO() throws CouponException {
		try {
			this.connectionPool = ConnectionPool.getInstance();
		} catch (CouponException e) {
			throw new CouponException("wait until connection will be available for you", e);
		}
	}

	@Override
	public void createCompany(Company companyToCreate) throws CouponException {
		String create_Company = "INSERT INTO COMPANY VALUES (?,?,?,?)";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(create_Company);
			pstmt.setLong(1, companyToCreate.getId());
			pstmt.setString(2, companyToCreate.getCompName());
			pstmt.setString(3, companyToCreate.getPassward());
			pstmt.setString(4, companyToCreate.getEmail());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			String msg = "can't get connection to company table";
			CouponException couponException = new CouponException(msg, e);
			throw couponException;
		} finally {
			connectionPool.returnConnection(con);
		}
	}

	@Override
	public void removeCompany(Company companyToRemove) throws CouponException {
		Connection con = connectionPool.getConnection();
		String remove_Company = "DELETE FROM COMPANY WHERE COMP_ID = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(remove_Company);
			pstmt.setLong(1, companyToRemove.getId());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			String msg = "can't get connection to company table";
			CouponException couponException = new CouponException(msg, e);
			throw couponException;
		} finally {
			connectionPool.returnConnection(con);
		}
	}

	@Override
	public void updateCompany(Company companyToUpdate) throws CouponException {
		String update_Company = "UPDATE COMPANY SET PASSWARD=?, EMAIL=?  WHERE COMP_ID = ?";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(update_Company);
			pstmt.setString(1, companyToUpdate.getPassward());
			pstmt.setString(2, companyToUpdate.getEmail());
			pstmt.setLong(3, companyToUpdate.getId());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			String msg = "can't get connection to company table";
			CouponException couponException = new CouponException(msg, e);
			throw couponException;
		} finally {
			connectionPool.returnConnection(con);
		}
	}

	@Override
	public Company getCompany(long companyId) throws CouponException {
		String get_Company = "SELECT * FROM COMPANY WHERE COMP_ID = ?";
		Connection con = connectionPool.getConnection();
		Company companySelected = new Company();
		try {
			PreparedStatement pstmt = con.prepareStatement(get_Company);
			pstmt.setLong(1, companyId);
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				companySelected.setId(resultSet.getLong("COMP_ID"));
				companySelected.setCompName(resultSet.getString("COMP_NAME"));
				companySelected.setPassward(resultSet.getString("PASSWARD"));
				companySelected.setEmail(resultSet.getString("EMAIL"));
			}
			pstmt.close();
		} catch (SQLException e) {
			String msg = "can't get connection to company table";
			CouponException couponException = new CouponException(msg, e);
			throw couponException;
		} finally {
			connectionPool.returnConnection(con);
		}
		return companySelected;
	}

	@Override
	public List<Company> getAllCompanies() throws CouponException {
		String get_All_Companies = "SELECT * FROM COMPANY";
		List<Company> allCompanies = new ArrayList<>();
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(get_All_Companies);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Company companySelected = new Company();
				companySelected.setId(rs.getLong("COMP_ID"));
				companySelected.setCompName(rs.getString("COMP_NAME"));
				companySelected.setPassward(rs.getString("PASSWARD"));
				companySelected.setEmail(rs.getString("EMAIL"));
				allCompanies.add(companySelected);
			}
			pstmt.close();
		} catch (SQLException e) {
			String msg = "can't get connection to company table ";
			CouponException couponException = new CouponException(msg, e);
			throw couponException;
		} finally {
			connectionPool.returnConnection(con);
		}
		return allCompanies;
	}

	// Get all company coupons of the company by company id
	@Override
	public List<Coupon> getCoupons(long companyId) throws CouponException {
		String joinCompanyCoupon = "SELECT COMPANY_COUPON.COMPANY_ID, COUPON.COUPON_ID, COUPON.TITLE, COUPON.START_DATE, COUPON.END_DATE, COUPON.AMOUNT, COUPON.TYPE, COUPON.MESSAGE, COUPON.PRICE, COUPON.IMAGE FROM COMPANY_COUPON JOIN COUPON ON COMPANY_COUPON.COUPON_ID=COUPON.COUPON_ID WHERE COMPANY_ID = ?";
		List<Coupon> allCompanyCoupons = new ArrayList<>();
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(joinCompanyCoupon);
			pstmt.setLong(1, companyId);
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				Coupon couponSelected = new Coupon();
				couponSelected.setCouponId(resultSet.getLong("COUPON_ID"));
				couponSelected.setTitle(resultSet.getString("TITLE"));

				java.sql.Date dateSQLStart = resultSet.getDate("START_DATE");
				long millisStart = dateSQLStart.getTime();
				couponSelected.setStartDate(new Date(millisStart));

				java.sql.Date dateSQLEnd = resultSet.getDate("END_DATE");
				long millisEnd = dateSQLEnd.getTime();
				couponSelected.setEndDate(new Date(millisEnd));

				couponSelected.setAmount(resultSet.getInt("AMOUNT"));

				CouponType type = CouponType.valueOf(resultSet.getString("TYPE"));
				couponSelected.setType(type);

				couponSelected.setMessage(resultSet.getString("MESSAGE"));
				couponSelected.setPrice(resultSet.getDouble("PRICE"));
				couponSelected.setImage(resultSet.getString("IMAGE"));
				allCompanyCoupons.add(couponSelected);
			}
			pstmt.close();
		} catch (SQLException e) {
			String msg = "can't get connection to company or coupon table";
			CouponException couponException = new CouponException(msg, e);
			throw couponException;
		} finally {
			connectionPool.returnConnection(con);
		}
		return allCompanyCoupons;
	}

	@Override
	public boolean login(String compName, String password) throws CouponException {
		String make_Login = "SELECT * FROM COMPANY WHERE (COMP_NAME = ?) AND (PASSWARD=?)";
		Connection con = connectionPool.getConnection();
		Company companySelected = new Company();
		boolean successLogin;
		try {
			PreparedStatement pstmt = con.prepareStatement(make_Login);
			pstmt.setString(1, companySelected.getCompName());
			pstmt.setString(2, companySelected.getPassward());
			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				successLogin = true;
			} else {
				successLogin = false;
			}
			pstmt.close();
		} catch (SQLException e) {
			String msg = "can't get connection to company table";
			CouponException couponException = new CouponException(msg, e);
			throw couponException;
		} finally {
			connectionPool.returnConnection(con);
		}
		return successLogin;
	}

	@Override
	public void addCouponToCompany(long companyId, long couponId) throws CouponException {
		Connection con = connectionPool.getConnection();
		String add_Company_Coupon = "INSERT INTO COMPANY_COUPON VALUES (?, ?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(add_Company_Coupon);
			pstmt.setLong(1, companyId);
			pstmt.setLong(2, couponId);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			String msg = "can't get connection to company or coupon table";
			CouponException couponException = new CouponException(msg, e);
			throw couponException;
		} finally {
			connectionPool.returnConnection(con);
		}
	}

	@Override
	public void removeCouponFromCompany(long couponId) throws CouponException {
		Connection con = connectionPool.getConnection();
		String remove_Company_Coupon = "DELETE FROM COMPANY_COUPON WHERE COUPON_ID = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(remove_Company_Coupon);
			pstmt.setLong(1, couponId);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			String msg = "can't get connection to company or coupon table";
			CouponException couponException = new CouponException(msg, e);
			throw couponException;
		} finally {
			connectionPool.returnConnection(con);
		}
	}

	@Override
	public long companyLogin(String compName, String password) throws CouponException {
		String make_Login = "SELECT * FROM COMPANY WHERE (COMP_NAME = ?) AND (PASSWARD=?)";
		Connection con = connectionPool.getConnection();
		Company companySelected = new Company();
		try {
			PreparedStatement pstmt = con.prepareStatement(make_Login);
			pstmt.setString(1, compName);
			pstmt.setString(2, password);
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				companySelected.setId(resultSet.getLong("COMP_ID"));
				companySelected.setCompName(resultSet.getString("COMP_NAME"));
				companySelected.setPassward(resultSet.getString("PASSWARD"));
				companySelected.setEmail(resultSet.getString("EMAIL"));
			}
			pstmt.close();
		} catch (SQLException e) {
			String msg = "can't get connection to company table";
			CouponException couponException = new CouponException(msg, e);
			throw couponException;
		} finally {
			connectionPool.returnConnection(con);
		}
		return companySelected.getId();
	}

}
