package tasks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.CompanyDAO;
import dao.CouponDAO;
import dao.CustomerDAO;
import exception.CouponException;
import javabeans.Coupon;

public class DailyCouponExpirationTask implements Runnable {

	private CompanyDAO companyDao;
	private CustomerDAO customerDao;
	private CouponDAO couponDao;
	private boolean quit = false;

	Thread currentThread = Thread.currentThread();

	public Thread getCurrentThread() {
		return currentThread;
	}

	public void setCurrentThread(Thread currentThread) {
		this.currentThread = currentThread;
	}

	public DailyCouponExpirationTask() {
		super();
	}

	public DailyCouponExpirationTask(CompanyDAO companyDao, CustomerDAO customerDao, CouponDAO couponDao,
			boolean quit) {
		super();
		this.companyDao = companyDao;
		this.customerDao = customerDao;
		this.couponDao = couponDao;
		this.quit = quit;

	}

	@Override
	public void run() {
		while (quit == false & !Thread.currentThread().isInterrupted()) {
			try {
				removeOldCoupons();
			} catch (CouponException e) {
				System.out.println(e.getMessage());
			}
			try {
				Thread.sleep(1000 * 60 * 60 * 24);
			} catch (InterruptedException e) {
				String msg = "it will restart in 24 hours";
				System.out.println(msg);
			}
		}
	}

	public void endTask() throws CouponException {
		quit = true;
		Thread.currentThread().interrupt();
	}

	public void removeOldCoupons() throws CouponException {
		Date today = new Date();
		List<Coupon> allCoupons = couponDao.getCoupons();
		List<Coupon> expiredCoupons = new ArrayList<>();
		for (Coupon coupon : allCoupons) {
			if (coupon.getEndDate().before(today)) {
				expiredCoupons.add(coupon);
			}
		}

		for (Coupon coupon : expiredCoupons) {
			long customerId = customerDao.getCustomerByCoupon(coupon.getCouponId());
			companyDao.removeCouponFromCompany(coupon.getCouponId());
			customerDao.removeCouponFromCustomer(customerId, coupon.getCouponId());
			couponDao.removeCoupon(coupon);
		}
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

	public boolean isQuit() {
		return quit;
	}

}
