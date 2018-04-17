package facades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.CompanyDAO;
import dao.CouponDAO;
import dao.CustomerDAO;
import exception.CouponException;
import javabeans.Company;
import javabeans.Coupon;
import javabeans.CouponType;
import utils.ValidationUtils;

public class CompanyFacade implements CouponClientFacade {

	private CompanyDAO companyDao;
	private CustomerDAO customerDao;
	private CouponDAO couponDao;

	private Company company;

	public CompanyFacade(Company company) {
		super();
		this.company = company;
	}

	public Company getCompany() {
		return company;
	}

	public CompanyFacade(CompanyDAO companyDao, CustomerDAO customerDao, CouponDAO couponDao, Company company) {
		super();
		this.companyDao = companyDao;
		this.customerDao = customerDao;
		this.couponDao = couponDao;
		this.company = company;
	}

	/**
	 * Login method at this stage is looking for the company ID from company table
	 * and returns company facade if the company ID !=0, else - bad Login
	 * 
	 * @param name,
	 *            password
	 * @throws CouponException
	 */
	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) throws CouponException {
		long companyId = companyDao.companyLogin(name, password);
		company = companyDao.getCompany(companyId);
		if (companyId != 0) {
			return this;
		} else {
			String msg = "Company Login wasn't successful";
			throw new CouponException(msg);
		}
	}

	/**
	 * Create coupon method - After all input validations checks if coupon title
	 * does not exist coupon created only by company so it is added to coupon table
	 * and also added to company_coupon table company creates coupon and it is added
	 * to both tables
	 * 
	 * @param newCouponToCreate
	 * 
	 * @throws CouponException
	 */
	public void createCuopon(Coupon newCouponToCreate) throws CouponException {
		Date today = new Date();
		long couponId = newCouponToCreate.getCouponId();
		if (couponId <= 0) {
			String msg = "Illegal Coupon ID";
			throw new CouponException(msg);
		}
		CouponType couponType = newCouponToCreate.getType();
		if (ValidationUtils.isEmpty(couponType.toString())) {
			String msg = "Coupon type can't be null";
			throw new CouponException(msg);
		}
		int amount = newCouponToCreate.getAmount();
		if (amount <= 0) {
			String msg = "Coupon amount must be greater than 0";
			throw new CouponException(msg);
		}
		Date endDate = newCouponToCreate.getEndDate();
		Date startDate = newCouponToCreate.getStartDate();
		if (endDate.before(startDate)) {
			String msg = "Coupon end date cannot be before start date";
			throw new CouponException(msg);
		}
		if (newCouponToCreate.getEndDate() == null) {
			String msg = "Coupon end date can't be null";
			throw new CouponException(msg);
		}
		if (newCouponToCreate.getStartDate() == null) {
			String msg = "Coupon start date can't be null";
			throw new CouponException(msg);
		}
		if (newCouponToCreate.getEndDate().before(today)) {
			String msg = "Coupon end date can't be before today";
			throw new CouponException(msg);
		}
		String couponTitle = newCouponToCreate.getTitle();
		if (ValidationUtils.isEmpty(couponTitle)) {
			String msg = "Coupon title can't be null";
			throw new CouponException(msg);
		}
		double price = newCouponToCreate.getPrice();
		if (price <= 0) {
			String msg = "Coupon price must be greater than 0";
			throw new CouponException(msg);
		}
		List<Coupon> allCoupons = couponDao.getCoupons();
		for (Coupon coupon : allCoupons) {
			if (coupon.getTitle().equals(couponTitle)) {
				throw new CouponException("coupon with title " + couponTitle + " already exists");
			}
		}
		couponDao.createCoupon(newCouponToCreate);
		companyDao.addCouponToCompany(this.company.getId(), newCouponToCreate.getCouponId());
	}

	/**
	 * Remove coupon method removes all coupons from DB, removes it from coupon
	 * table and from company_coupon table and customer_coupon all together
	 * 
	 * @param couponToRemove
	 * @throws CouponException
	 */
	public void removeCoupon(Coupon couponToRemove) throws CouponException {
		long couponId = couponToRemove.getCouponId();
		List<Coupon> coupons = couponDao.getCoupons();
		for (Coupon coupon : coupons) {
			if (coupon.getCouponId() == couponId) {
				long customerId = customerDao.getCustomerByCoupon(couponId);
				customerDao.removeCouponFromCustomer(customerId, coupon.getCouponId());
				companyDao.removeCouponFromCompany(coupon.getCouponId());
				couponDao.removeCoupon(coupon);
			}
		}
	}

	/**
	 * Update coupon method - after validations, updates the coupon in coupon table
	 * 
	 * @param couponToUpdate
	 * @throws CouponException
	 */
	public void updateCoupon(Coupon couponToUpdate) throws CouponException {
		Date today = new Date();
		long couponId = couponToUpdate.getCouponId();
		if (couponId <= 0) {
			String msg = "Illegal Coupon ID";
			throw new CouponException(msg);
		}
		CouponType couponType = couponToUpdate.getType();
		if (ValidationUtils.isEmpty(couponType.toString())) {
			String msg = "Coupon type can't be null";
			throw new CouponException(msg);
		}
		int amount = couponToUpdate.getAmount();
		if (amount <= 0) {
			String msg = "Coupon amount must be greater than 0";
			throw new CouponException(msg);
		}
		Date endDate = couponToUpdate.getEndDate();
		Date startDate = couponToUpdate.getStartDate();
		if (endDate.before(startDate)) {
			String msg = "Coupon end date cannot be before start date";
			throw new CouponException(msg);
		}
		if (couponToUpdate.getEndDate() == null) {
			String msg = "Coupon end date can't be null";
			throw new CouponException(msg);
		}
		if (couponToUpdate.getStartDate() == null) {
			String msg = "Coupon start date can't be null";
			throw new CouponException(msg);
		}
		if (couponToUpdate.getEndDate().before(today)) {
			String msg = "Coupon end date can't be before today";
			throw new CouponException(msg);
		}
		String couponTitle = couponToUpdate.getTitle();
		if (ValidationUtils.isEmpty(couponTitle)) {
			String msg = "Coupon title can't be null";
			throw new CouponException(msg);
		}
		double price = couponToUpdate.getPrice();
		if (price <= 0) {
			String msg = "Coupon price must be greater than 0";
			throw new CouponException(msg);
		}

		Coupon originCoupon = getCoupon(couponToUpdate.getCouponId());
		if (!originCoupon.getStartDate().equals(couponToUpdate.getStartDate())) {
			String msg = "Coupon Start Date can't be updated";
			throw new CouponException(msg);
		}
		if (originCoupon.getAmount() != couponToUpdate.getAmount()) {
			String msg = "Coupon amount can't be updated";
			throw new CouponException(msg);
		}
		if (!originCoupon.getType().toString().equals(couponToUpdate.getType().toString())) {
			String msg = "Coupon type can't be updated";
			throw new CouponException(msg);
		}
		if (!originCoupon.getMessage().equals(couponToUpdate.getMessage())) {
			String msg = "Coupon message can't be updated";
			throw new CouponException(msg);
		}
		if (!originCoupon.getImage().equals(couponToUpdate.getImage())) {
			String msg = "Coupon image can't be updated";
			throw new CouponException(msg);
		}
		if (!originCoupon.getTitle().equals(couponToUpdate.getTitle())) {
			String msg = "Coupon title can't be updated";
			throw new CouponException(msg);
		}

		List<Coupon> companyCoupons = companyDao.getCoupons(this.company.getId());
		for (Coupon coupon : companyCoupons) {
			if (coupon.getCouponId() == couponId) {
				coupon = couponToUpdate;
				couponDao.updateCoupon(coupon);
			}
		}
	}

	/**
	 * Get coupon by coupon ID
	 * 
	 * @param couponId
	 * @return Coupon
	 * @throws CouponException
	 */
	public Coupon getCoupon(long couponId) throws CouponException {
		if (couponId <= 0) {
			String msg = "Illegal Coupon ID";
			throw new CouponException(msg);
		}
		List<Coupon> companyCoupons = companyDao.getCoupons(this.company.getId());
		for (Coupon coupon : companyCoupons) {
			if (coupon.getCouponId() == couponId) {
				return couponDao.getCoupon(couponId);
			}
		}
		// String msg = "No coupon found with this ID";
		// throw new CouponException(msg);
		return null;
	}

	/**
	 * Get all company's coupons returns my company's coupons
	 * 
	 * @return company's coupons collection
	 * @throws CouponException
	 */
	public List<Coupon> getAllCompanyCoupons() throws CouponException {
		return companyDao.getCoupons(this.company.getId());

	}

	/**
	 * Get all company's coupons by Coupon type
	 * 
	 * @param type
	 * @return company's coupons collection
	 * @throws CouponException
	 */
	public List<Coupon> getCouponsByType(CouponType type) throws CouponException {
		if (ValidationUtils.isEmpty(type.toString())) {
			String msg = "Type can't be null";
			throw new CouponException(msg);
		}
		List<Coupon> allCompanyCoupons = getAllCompanyCoupons();
		List<Coupon> allCompanyCouponsByType = new ArrayList<>();
		for (Coupon coupon : allCompanyCoupons) {
			if (coupon.getType().equals(type)) {
				allCompanyCouponsByType.add(coupon);
			}
		}
		return allCompanyCouponsByType;
	}

	/**
	 * Get all company's coupons by the coupon price
	 * 
	 * @param price
	 * @return company's coupons collection
	 * @throws CouponException
	 */
	public List<Coupon> getCouponsUntilPrice(double price) throws CouponException {
		if (price <= 0) {
			String msg = "Price must be greater than 0";
			throw new CouponException(msg);
		}
		List<Coupon> allCoupons = getAllCompanyCoupons();
		List<Coupon> underPriceCoupons = new ArrayList<>();
		for (Coupon coupon : allCoupons) {
			if (coupon.getPrice() < price) {
				underPriceCoupons.add(coupon);
			}
		}
		return underPriceCoupons;
	}

	/**
	 * Get all company's coupons by Coupon End Date
	 * 
	 * @param date
	 * @return company's coupons collection
	 * @throws CouponException
	 */
	public List<Coupon> getCouponsBeforeEndDate(Date date) throws CouponException {
		if (date == null) {
			String msg = "Date can't be null";
			throw new CouponException(msg);
		}
		List<Coupon> allCoupons = getAllCompanyCoupons();
		List<Coupon> beforeDateCoupons = new ArrayList<>();
		for (Coupon coupon : allCoupons) {
			if (coupon.getEndDate().before(date)) {
				beforeDateCoupons.add(coupon);
			}
		}
		return beforeDateCoupons;
	}

	public void setCompanyDao(CompanyDAO companyDao) {
		this.companyDao = companyDao;
	}

	public void setCouponDao(CouponDAO couponDao) {
		this.couponDao = couponDao;
	}

	public void setCustomerDao(CustomerDAO customerDao) {
		this.customerDao = customerDao;
	}
}
