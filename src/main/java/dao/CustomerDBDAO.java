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
import javabeans.Customer;
import javabeans.CustomerCoupon;

public class CustomerDBDAO implements CustomerDAO {

	ConnectionPool connectionPool;

	public CustomerDBDAO() throws CouponException {
		try {
			this.connectionPool = ConnectionPool.getInstance();
		} catch (CouponException e) {
			throw new CouponException("wait until connection will be available for you", e);
		}
	}

	@Override
	public void createCustomer(Customer customerToCreate) throws CouponException {
		String create_Customer = "INSERT INTO CUSTOMER VALUES (?,?,?)";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(create_Customer);
			pstmt.setLong(1, customerToCreate.getId());
			pstmt.setString(2, customerToCreate.getCustName());
			pstmt.setString(3, customerToCreate.getPassword());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			String msg = "can't get connection to customer table";
			CouponException couponException = new CouponException(msg, e);
			throw couponException;
		} finally {
			connectionPool.returnConnection(con);
		}
	}

	@Override
	public void removeCustomer(Customer customerToRemove) throws CouponException {
		String removeCustomerAndCoupons = "DELETE FROM CUSTOMER WHERE CUST_ID = ?";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(removeCustomerAndCoupons);
			pstmt.setLong(1, customerToRemove.getId());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			String msg = "can't get connection to customer table";
			CouponException couponException = new CouponException(msg, e);
			throw couponException;
		} finally {
			connectionPool.returnConnection(con);
		}
	}

	@Override
	public void updateCustomer(Customer customerToUpdate) throws CouponException {
		String update_Customer = "UPDATE CUSTOMER SET PASSWARD = ? WHERE CUST_ID = ?";
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(update_Customer);
			pstmt.setString(1, customerToUpdate.getPassword());
			pstmt.setLong(2, customerToUpdate.getId());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			String msg = "can't get connection to customer table";
			CouponException couponException = new CouponException(msg, e);
			throw couponException;
		} finally {
			connectionPool.returnConnection(con);
		}
	}

	@Override
	public Customer getCustomer(long customerId) throws CouponException {
		String get_Customer = "SELECT * FROM CUSTOMER WHERE CUST_ID = ?";
		Connection con = connectionPool.getConnection();
		Customer customerSelected = new Customer();
		try {
			PreparedStatement pstmt = con.prepareStatement(get_Customer);
			pstmt.setLong(1, customerId);
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				customerSelected.setId(resultSet.getLong("CUST_ID"));
				customerSelected.setCustName(resultSet.getString("CUST_NAME"));
				customerSelected.setPassword(resultSet.getString("PASSWARD"));
			}
			pstmt.close();
		} catch (SQLException e) {
			String msg = "can't get connection to customer table";
			CouponException couponException = new CouponException(msg, e);
			throw couponException;
		} finally {
			connectionPool.returnConnection(con);
		}
		return customerSelected;
	}

	@Override
	public List<Customer> getAllCustomers() throws CouponException {
		String get_All_Customers = "SELECT * FROM CUSTOMER";
		List<Customer> allCustomers = new ArrayList<>();
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(get_All_Customers);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Customer customerSelected = new Customer();
				customerSelected.setId(rs.getLong("CUST_ID"));
				customerSelected.setCustName(rs.getString("CUST_NAME"));
				customerSelected.setPassword(rs.getString("PASSWARD"));
				allCustomers.add(customerSelected);
			}
			pstmt.close();
		} catch (SQLException e) {
			String msg = "can't get connection to customer table";
			CouponException couponException = new CouponException(msg, e);
			throw couponException;
		} finally {
			connectionPool.returnConnection(con);
		}
		return allCustomers;
	}

	// Get all customer coupons of the customer by customer id
	@Override
	public List<Coupon> getCoupons(long customerId) throws CouponException {
		String joinCustomerCoupon = "SELECT CUSTOMER_COUPON.CUSTOMER_ID, COUPON.COUPON_ID, COUPON.TITLE, COUPON.START_DATE, COUPON.END_DATE, COUPON.AMOUNT, COUPON.TYPE, COUPON.MESSAGE, COUPON.PRICE, COUPON.IMAGE FROM CUSTOMER_COUPON JOIN COUPON ON CUSTOMER_COUPON.COUPON_ID=COUPON.COUPON_ID WHERE CUSTOMER_ID = ?";
		List<Coupon> allCustomerCoupons = new ArrayList<>();
		Connection con = connectionPool.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(joinCustomerCoupon);
			pstmt.setLong(1, customerId);
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
				allCustomerCoupons.add(couponSelected);
			}
			pstmt.close();
		} catch (SQLException e) {
			String msg = "can't get connection to customer or coupon table";
			CouponException couponException = new CouponException(msg, e);
			throw couponException;
		} finally {
			connectionPool.returnConnection(con);
		}
		return allCustomerCoupons;
	}

	@Override
	public boolean login(String custName, String password) throws CouponException {
		String make_Login = "SELECT * FROM CUSTOMER WHERE (CUST_NAME = ?) AND (PASSWARD=?)";
		Connection con = connectionPool.getConnection();
		Customer customerSelected = new Customer();
		boolean successLogin;
		try {
			PreparedStatement pstmt = con.prepareStatement(make_Login);
			pstmt.setString(1, customerSelected.getCustName());
			pstmt.setString(2, customerSelected.getPassword());
			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				successLogin = true;
			} else {
				successLogin = false;
			}
			pstmt.close();
		} catch (SQLException e) {
			String msg = "can't get connection to customer table";
			CouponException couponException = new CouponException(msg, e);
			throw couponException;
		} finally {
			connectionPool.returnConnection(con);
		}
		return successLogin;
	}

	@Override
	public void addCouponToCustomer(long customerId, long couponId) throws CouponException {
		Connection con = connectionPool.getConnection();
		String add_Customer_Coupon = "INSERT INTO CUSTOMER_COUPON VALUES (?, ?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(add_Customer_Coupon);
			pstmt.setLong(1, customerId);
			pstmt.setLong(2, couponId);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			String msg = "can't get connection to customer or coupon table";
			CouponException couponException = new CouponException(msg, e);
			throw couponException;
		} finally {
			connectionPool.returnConnection(con);
		}
	}

	@Override
	public void removeCouponFromCustomer(long customerId, long couponId) throws CouponException {
		Connection con = connectionPool.getConnection();
		String remove_Customer_Coupon = "DELETE FROM CUSTOMER_COUPON  WHERE CUSTOMER_ID = ? AND COUPON_ID = ? ";
		try {
			PreparedStatement pstmt = con.prepareStatement(remove_Customer_Coupon);
			pstmt.setLong(1, customerId);
			pstmt.setLong(2, couponId);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			String msg = "can't get connection to customer or coupon table";
			CouponException couponException = new CouponException(msg, e);
			throw couponException;
		} finally {
			connectionPool.returnConnection(con);
		}
	}

	@Override
	public long customerLogin(String custName, String password) throws CouponException {
		String make_Login = "SELECT * FROM CUSTOMER WHERE (CUST_NAME = ?) AND (PASSWARD=?)";
		Connection con = connectionPool.getConnection();
		Customer customerSelected = new Customer();
		try {
			PreparedStatement pstmt = con.prepareStatement(make_Login);
			pstmt.setString(1, custName);
			pstmt.setString(2, password);
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				customerSelected.setId(resultSet.getLong("CUST_ID"));
				customerSelected.setCustName(resultSet.getString("CUST_NAME"));
				customerSelected.setPassword(resultSet.getString("PASSWARD"));
			}
			pstmt.close();
		} catch (SQLException e) {
			String msg = "can't get connection to customer table";
			CouponException couponException = new CouponException(msg, e);
			throw couponException;
		} finally {
			connectionPool.returnConnection(con);
		}
		return customerSelected.getId();
	}

	@Override
	public long getCustomerByCoupon(long couponId) throws CouponException {
		Connection con = connectionPool.getConnection();
		String select_Customer_Coupon = "SELECT * FROM CUSTOMER_COUPON  WHERE COUPON_ID = ? ";
		CustomerCoupon customerCoupon = new CustomerCoupon();
		try {
			PreparedStatement pstmt = con.prepareStatement(select_Customer_Coupon);
			pstmt.setLong(1, couponId);
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				customerCoupon.setCustomerId(resultSet.getLong("CUSTOMER_ID"));
				customerCoupon.setCouponId(resultSet.getLong("COUPON_ID"));
			}
			pstmt.close();
		} catch (SQLException e) {
			String msg = "can't get connection to customer or coupon table";
			CouponException couponException = new CouponException(msg, e);
			throw couponException;
		} finally {
			connectionPool.returnConnection(con);
		}
		return customerCoupon.getCustomerId();
	}
}
