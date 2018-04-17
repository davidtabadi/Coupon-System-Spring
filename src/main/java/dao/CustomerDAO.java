package dao;

import java.util.List;

import exception.CouponException;
import javabeans.Coupon;
import javabeans.Customer;

public interface CustomerDAO {

	void createCustomer(Customer customerToCreate) throws CouponException;

	void removeCustomer(Customer customerToRemove) throws CouponException;

	void updateCustomer(Customer customerToUpdate) throws CouponException;

	Customer getCustomer(long customerId) throws CouponException;

	List<Customer> getAllCustomers() throws CouponException;

	List<Coupon> getCoupons(long customerId) throws CouponException;

	boolean login(String custName, String password) throws CouponException;

	long customerLogin(String custName, String password) throws CouponException;

	void addCouponToCustomer(long customerId, long couponId) throws CouponException;

	void removeCouponFromCustomer(long customerId, long couponId) throws CouponException;

	long getCustomerByCoupon(long couponId) throws CouponException;
}
