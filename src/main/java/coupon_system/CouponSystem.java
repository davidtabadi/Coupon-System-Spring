package coupon_system;

import connectionpool.ConnectionPool;
import dao.CompanyDAO;
import dao.CompanyDBDAO;
import dao.CouponDAO;
import dao.CouponDBDAO;
import dao.CustomerDAO;
import dao.CustomerDBDAO;
import exception.CouponException;
import facades.AdminFacade;
import facades.ClientType;
import facades.CompanyFacade;
import facades.CouponClientFacade;
import facades.CustomerFacade;
import javabeans.Company;
import javabeans.Customer;
import tasks.DailyCouponExpirationTask;

public class CouponSystem {

	private AdminFacade adminFacade;
	private CustomerFacade customerFacade;
	private CompanyFacade companyFacade;

	private CompanyDAO companyDao;
	private CustomerDAO customerDao;
	private CouponDAO couponDao;

	public void setCompanyDao(CompanyDAO companyDao) {
		this.companyDao = companyDao;
	}

	public void setCustomerDao(CustomerDAO customerDao) {
		this.customerDao = customerDao;
	}

	public void setCouponDao(CouponDAO couponDao) {
		this.couponDao = couponDao;
	}

	private Company company;
	private Customer customer;

	private DailyCouponExpirationTask couponExpirationTask;

	private Thread dailyThread;

	private static CouponSystem instance;

	public static CouponSystem getInstance() throws CouponException {
		if (instance == null) {
			instance = new CouponSystem();
		}
		return instance;
	}

	/**
	 * 
	 * Login method: administrator user and password is admin,1234 but as for
	 * company or customer if the login succeed we can match the ID from the user
	 * name and password they entered so we can identify them immediately after
	 * successful login so they not needed to verify again any more
	 * 
	 * @param userName
	 * @param password
	 * @param clientType
	 * @return
	 * @throws CouponException
	 */
	public CouponClientFacade login(String userName, String password, ClientType clientType) throws CouponException {
		if (clientType == ClientType.ADMIN) {
			adminFacade = (AdminFacade) new AdminFacade(companyDao, customerDao, couponDao).login(userName, password,
					clientType);
			return adminFacade;
		} else if (clientType == ClientType.COMPANY) {
			companyFacade = (CompanyFacade) new CompanyFacade(companyDao, customerDao, couponDao, company)
					.login(userName, password, clientType);
			return companyFacade;
		} else if (clientType == ClientType.CUSTOMER) {
			customerFacade = (CustomerFacade) new CustomerFacade(companyDao, customerDao, couponDao, customer)
					.login(userName, password, clientType);
			return customerFacade;
		}
		return null;
	}

	private CouponSystem() throws CouponException {

		companyDao = new CompanyDBDAO();
		customerDao = new CustomerDBDAO();
		couponDao = new CouponDBDAO();

		adminFacade = new AdminFacade(companyDao, customerDao, couponDao);
		customerFacade = new CustomerFacade(companyDao, customerDao, couponDao, customer);
		companyFacade = new CompanyFacade(companyDao, customerDao, couponDao, company);

		couponExpirationTask = new DailyCouponExpirationTask(companyDao, customerDao, couponDao, false);
		dailyThread = new Thread(couponExpirationTask);
		// dailyThread.start();
	}

	/**
	 * Shutdown method closes all connections in the pool and stops the daily thread
	 * 
	 * @throws CouponException
	 */
	public void shutDown() throws CouponException {
		ConnectionPool.getInstance().closeAllConnections();
		couponExpirationTask.endTask();
	}

}
