package db_creating;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import entrypoint.DatabaseHelperUtilities;
import exception.CouponException;

public class DatabaseCreator {

	String url = "jdbc:derby://localhost:1527/DBCoupon;create=true";
	String companyTBL = "CREATE TABLE Company(COMP_ID BIGINT Primary Key, COMP_NAME VARCHAR(25), PASSWARD VARCHAR(25), EMAIL VARCHAR(25))";
	String customerTBL = "CREATE TABLE Customer(CUST_ID BIGINT Primary Key, CUST_NAME VARCHAR(25), PASSWARD VARCHAR(25))";
	String couponTBL = "CREATE TABLE Coupon(COUPON_ID BIGINT Primary Key, TITLE VARCHAR(25), START_DATE  DATE, END_DATE DATE, AMOUNT INTEGER, TYPE VARCHAR(25), MESSAGE VARCHAR(255), PRICE FLOAT, IMAGE VARCHAR(255))";
	String company_couponTBL = "CREATE TABLE Company_Coupon (Company_ID BIGINT, Coupon_ID BIGINT, Primary Key (Company_ID, Coupon_ID))";
	String customer_couponTBL = "CREATE TABLE Customer_Coupon (Customer_ID BIGINT, Coupon_ID BIGINT, Primary Key (Customer_ID, Coupon_ID))";

	public DatabaseCreator() {

	}

	public void createDbAndTables() throws CouponException {
		createCompanyTBL("Company");
		createCustomerTBL("Customer");
		createCouponTBL("Coupon");
		createCompanyCouponTBL("Company_Coupon");
		createCustomerCouponTBL("Customer_Coupon");
	}

	private void createCompanyTBL(String tableName) throws CouponException {
		try {
			Connection con = DriverManager.getConnection(url);
			Statement stmt = con.createStatement();
			stmt.execute(companyTBL);
			DatabaseHelperUtilities.printColumnNames(DriverManager.getConnection(url), tableName);
		} catch (SQLException e) {
			String msg = "Can't get connnection for creating company table";
			CouponException couponException = new CouponException(msg, e);
			throw couponException;
		}
	}

	private void createCustomerTBL(String tableName) throws CouponException {
		Connection con;
		try {
			con = DriverManager.getConnection(url);
			System.out.println("Conneceted to: " + con);
			Statement stmt = con.createStatement();
			stmt.execute(customerTBL);
			DatabaseHelperUtilities.printColumnNames(DriverManager.getConnection(url), tableName);
		} catch (SQLException e) {
			String msg = "Can't get connnection for creating customer table";
			CouponException couponException = new CouponException(msg, e);
			throw couponException;
		}

	}

	private void createCouponTBL(String tableName) throws CouponException {
		Connection con;
		try {
			con = DriverManager.getConnection(url);
			System.out.println("Conneceted to: " + con);
			Statement stmt = con.createStatement();
			stmt.execute(couponTBL);
			DatabaseHelperUtilities.printColumnNames(DriverManager.getConnection(url), tableName);
		} catch (SQLException e) {
			String msg = "Can't get connnection for creating coupon table";
			CouponException couponException = new CouponException(msg, e);
			throw couponException;
		}
	}

	private void createCompanyCouponTBL(String tableName) throws CouponException {
		Connection con;
		try {
			con = DriverManager.getConnection(url);
			System.out.println("Conneceted to: " + con);
			Statement stmt = con.createStatement();
			stmt.execute(company_couponTBL);
			DatabaseHelperUtilities.printColumnNames(DriverManager.getConnection(url), tableName);
		} catch (SQLException e) {
			String msg = "Can't get connnection for creating company_coupon table";
			CouponException couponException = new CouponException(msg, e);
			throw couponException;
		}
	}

	private void createCustomerCouponTBL(String tableName) throws CouponException {
		Connection con;
		try {
			con = DriverManager.getConnection(url);
			System.out.println("Conneceted to: " + con);
			Statement stmt = con.createStatement();
			stmt.execute(customer_couponTBL);
			DatabaseHelperUtilities.printColumnNames(DriverManager.getConnection(url), tableName);
		} catch (SQLException e) {
			String msg = "Can't get connnection for creating customer_coupon table";
			CouponException couponException = new CouponException(msg, e);
			throw couponException;
		}
	}
}
