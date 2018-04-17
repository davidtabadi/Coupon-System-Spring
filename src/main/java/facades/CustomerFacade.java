package facades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.CompanyDAO;
import dao.CouponDAO;
import dao.CustomerDAO;
import exception.CouponException;
import javabeans.Coupon;
import javabeans.CouponType;
import javabeans.Customer;
import utils.ValidationUtils;

public class CustomerFacade implements CouponClientFacade {

	private CompanyDAO companyDao;
	private CustomerDAO customerDao;
	private CouponDAO couponDao;

	private Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setCompanyDao(CompanyDAO companyDao) {
		this.companyDao = companyDao;
	}

	public void setCustomerDao(CustomerDAO customerDao) {
		this.customerDao = customerDao;
	}

	public void setCouponDao(CouponDAO couponDao) {
		this.couponDao = couponDao;
	}

	public CustomerFacade(CompanyDAO companyDao, CustomerDAO customerDao, CouponDAO couponDao, Customer customer) {
		super();
		this.companyDao = companyDao;
		this.customerDao = customerDao;
		this.couponDao = couponDao;
		this.customer = customer;
	}

	/**
	 * Login method at this stage is looking for the customer ID from customer table
	 * and returns customer facade if the customer ID !=0, else - bad Login
	 */
	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) throws CouponException {
		long customerId = customerDao.customerLogin(name, password);
		customer = customerDao.getCustomer(customerId);
		if (customerId != 0) {
			return this;
		} else {
			String msg = "Customer Login wasn't successful";
			throw new CouponException(msg);
		}
	}

	/**
	 * Purchase coupon method - 3 conditions for purchasing coupon: the coupon not
	 * purchased by customer yet, the coupon amount is sufficient for purchasing
	 * (>0), the coupon is not expired yet (end date of the coupon). after
	 * purchasing it added to customer_coupon table and amount decreasing
	 * 
	 * @param couponToPurchase
	 * @throws CouponException
	 */
	public void purchaseCoupon(Coupon couponToPurchase) throws CouponException {
		Date today = new Date();
		List<Coupon> allPurchasedCouponsByCustomer = getAllPurchasedCoupons();
		for (Coupon coupon : allPurchasedCouponsByCustomer) {
			if (coupon.getCouponId() == couponToPurchase.getCouponId()) {
				String msg = "coupon already purchased";
				CouponException couponException = new CouponException(msg);
				throw couponException;
			}
			if (couponToPurchase.getAmount() == 0) {
				String msg = "there is no coupon amount in stock";
				CouponException couponException = new CouponException(msg);
				throw couponException;
			}
			if (couponToPurchase.getEndDate().before(today)) {
				String msg = "coupon expired";
				CouponException couponException = new CouponException(msg);
				throw couponException;
			}
		}
		customerDao.addCouponToCustomer(this.customer.getId(), couponToPurchase.getCouponId());
		couponToPurchase.setAmount(couponToPurchase.getAmount() - 1);
		couponDao.updateCoupon(couponToPurchase);
	}

	/**
	 * Get all my purchased coupons method
	 * 
	 * @return my purchased coupons collection
	 * @throws CouponException
	 */
	public List<Coupon> getAllPurchasedCoupons() throws CouponException {
		List<Coupon> allPurchasedCouponsByCustomer = customerDao.getCoupons(this.customer.getId());
		return allPurchasedCouponsByCustomer;
	}

	/**
	 * Get all my purchased coupons method by type
	 * 
	 * @param type
	 * @return my purchased coupons collection
	 * @throws CouponException
	 */
	public List<Coupon> getAllPurchasedCouponsByType(CouponType type) throws CouponException {
		if (ValidationUtils.isEmpty(type.toString())) {
			String msg = "Type can't be null";
			throw new CouponException(msg);
		}
		List<Coupon> allCouponsPurchasedByCustomer = getAllPurchasedCoupons();
		List<Coupon> allCouponsPurchasedByCustomerByType = new ArrayList<>();
		for (Coupon coupon : allCouponsPurchasedByCustomer) {
			if (coupon.getType().equals(type)) {
				allCouponsPurchasedByCustomerByType.add(coupon);
			}
		}
		return allCouponsPurchasedByCustomerByType;
	}

	/**
	 * Get all my purchased coupons method under price
	 * 
	 * @param price
	 * @return my purchased coupons collection
	 * @throws CouponException
	 */
	public List<Coupon> getAllPurchasedCouponsByPrice(double price) throws CouponException {
		if (price <= 0) {
			String msg = "Price must be greater than 0";
			throw new CouponException(msg);
		}
		List<Coupon> allCouponsPurchasedByCustomer = getAllPurchasedCoupons();
		List<Coupon> underPriceCouponsPurchasedByCustomer = new ArrayList<>();
		for (Coupon coupon : allCouponsPurchasedByCustomer) {
			if (coupon.getPrice() < price) {
				underPriceCouponsPurchasedByCustomer.add(coupon);
			}
		}
		return underPriceCouponsPurchasedByCustomer;
	}

	public List<Coupon> getAllCoupons() throws CouponException {
		List<Coupon> allCoupons = couponDao.getCoupons();
		return allCoupons;
	}

	public List<Coupon> getAllCouponsByType(CouponType type) throws CouponException {
		List<Coupon> allCoupons = getAllCoupons();
		List<Coupon> allCouponsByType = new ArrayList<>();
		for (Coupon coupon : allCoupons) {
			if (coupon.getType().equals(type)) {
				allCouponsByType.add(coupon);
			}
		}
		return allCouponsByType;
	}

	public List<Coupon> getAllCouponsByPrice(double price) throws CouponException {
		if (price <= 0) {
			String msg = "Price must be greater than 0";
			throw new CouponException(msg);
		}
		List<Coupon> allCoupons = getAllCoupons();
		List<Coupon> allCouponsByPrice = new ArrayList<>();
		for (Coupon coupon : allCoupons) {
			if (coupon.getPrice() < price) {
				allCouponsByPrice.add(coupon);
			}
		}
		return allCouponsByPrice;
	}

	public List<Coupon> getAllCouponsForSale() throws CouponException {
		List<Coupon> allCouponsForSale = new ArrayList<>();
		List<Coupon> allCoupons = couponDao.getCoupons();
		List<Coupon> allPurchasedCoupons = this.getAllPurchasedCoupons();
		int check;
		if (allPurchasedCoupons == null) {
			return allCoupons;
		} else {
			for (int j = 0; j < allCoupons.size(); j++) {
				check = 0;
				for (int i = 0; i < allPurchasedCoupons.size(); i++) {
					if (allPurchasedCoupons.get(i).getCouponId() == allCoupons.get(j).getCouponId()) {
						check = 1;
					}
				}
				if (check == 0) {
					allCouponsForSale.add(allCoupons.get(j));
				}
			}
		}
		return allCouponsForSale;
	}

	public List<Coupon> getAllCouponsForSaleByType(CouponType type) throws CouponException {
		List<Coupon> allCouponsForSale = this.getAllCouponsForSale();
		List<Coupon> allCouponsForSaleByType = new ArrayList<>();
		for (Coupon coupon : allCouponsForSale) {
			if (coupon.getType().equals(type)) {
				allCouponsForSaleByType.add(coupon);
			}
		}
		return allCouponsForSaleByType;
	}

	public List<Coupon> getAllCouponsForSaleByPrice(double price) throws CouponException {
		if (price <= 0) {
			String msg = "Price must be greater than 0";
			throw new CouponException(msg);
		}
		List<Coupon> allCouponsForSale = this.getAllCouponsForSale();
		List<Coupon> allCouponsForSaleByPrice = new ArrayList<>();
		for (Coupon coupon : allCouponsForSale) {
			if (coupon.getPrice() < price) {
				allCouponsForSaleByPrice.add(coupon);
			}
		}
		return allCouponsForSaleByPrice;
	}
}
