package dao;

import java.util.List;

import exception.CouponException;
import javabeans.Coupon;
import javabeans.CouponType;

public interface CouponDAO {

	void createCoupon(Coupon couponToCreate) throws CouponException;

	void removeCoupon(Coupon couponToRemove) throws CouponException;

	void updateCoupon(Coupon couponToUpdate) throws CouponException;

	Coupon getCoupon(long couponId) throws CouponException;

	List<Coupon> getCoupons() throws CouponException;

	List<Coupon> getCouponByType(CouponType type) throws CouponException;

}
