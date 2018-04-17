package dao;

import java.util.List;

import exception.CouponException;
import javabeans.Company;
import javabeans.Coupon;

public interface CompanyDAO {

	void createCompany(Company companyToCreate) throws CouponException;

	void removeCompany(Company companyToRemove) throws CouponException;

	void updateCompany(Company companyToUpdate) throws CouponException;

	Company getCompany(long companyId) throws CouponException;

	List<Company> getAllCompanies() throws CouponException;

	List<Coupon> getCoupons(long companyId) throws CouponException;

	boolean login(String compName, String password) throws CouponException;

	long companyLogin(String compName, String password) throws CouponException;

	void addCouponToCompany(long companyId, long couponId) throws CouponException;

	void removeCouponFromCompany(long couponId) throws CouponException;
}
