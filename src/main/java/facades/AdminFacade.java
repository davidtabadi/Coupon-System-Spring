package facades;

import java.util.List;

import dao.CompanyDAO;
import dao.CouponDAO;
import dao.CustomerDAO;
import exception.CouponException;
import javabeans.Company;
import javabeans.Coupon;
import javabeans.Customer;
import utils.ValidationUtils;

public class AdminFacade implements CouponClientFacade {

	private CompanyDAO companyDao;
	private CustomerDAO customerDao;
	private CouponDAO couponDao;

	public AdminFacade(CompanyDAO companyDao, CustomerDAO customerDao, CouponDAO couponDao) {
		super();
		this.companyDao = companyDao;
		this.customerDao = customerDao;
		this.couponDao = couponDao;
	}

	public AdminFacade() {
		super();
	}

	// Login method
	/**
	 * Login method for Admin facade is by default "admin","1234" for Login, throws
	 * Coupon Exception for bad Login
	 */
	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) throws CouponException {
		if (name.equals("admin") && password.equals("1234")) {
			return this;
		} else {
			String msg = "Admin Login wasn't successful";
			throw new CouponException(msg);
		}
	}

	/**
	 * Create company method - after all validations, checks if name does not exist
	 * and add company to company table in DB
	 * 
	 * @param newCompanyToCreate
	 * @throws CouponException
	 */
	public void createCompany(Company newCompanyToCreate) throws CouponException {
		long companyId = newCompanyToCreate.getId();
		if (companyId <= 0) {
			String msg = "Illegal Company ID ";
			throw new CouponException(msg);
		}
		String passward = newCompanyToCreate.getPassward();
		if (ValidationUtils.isEmpty(passward)) {
			String msg = "Company password can't be null";
			throw new CouponException(msg);
		}
		String email = newCompanyToCreate.getEmail();
		if (ValidationUtils.isEmpty(email)) {
			String msg = "Company email can't be null";
			throw new CouponException(msg);
		}
		String compName = newCompanyToCreate.getCompName();
		if (ValidationUtils.isEmpty(compName)) {
			String msg = "Company name can't be null";
			throw new CouponException(msg);
		}
		List<Company> allCompanies = companyDao.getAllCompanies();
		for (Company company : allCompanies) {
			if (company.getCompName().equals(compName)) {
				throw new CouponException("company " + compName + " already exists");
			}
		}
		companyDao.createCompany(newCompanyToCreate);
	}

	/**
	 * Remove company method removes the company company table , removes companies
	 * coupons from company_coupon table , removes customer coupons holding the
	 * company coupons from customer_coupon
	 */
	public void removeCompany(Company companyToRemove) throws CouponException {
		long companyId = companyToRemove.getId();
		List<Company> companies = companyDao.getAllCompanies();
		for (Company company : companies) {
			if (company.getId() == companyId) {
				List<Coupon> allCompanyCoupons = companyDao.getCoupons(company.getId());
				for (Coupon coupon : allCompanyCoupons) {
					long customerId = customerDao.getCustomerByCoupon(coupon.getCouponId());
					companyDao.removeCouponFromCompany(coupon.getCouponId());
					customerDao.removeCouponFromCustomer(customerId, coupon.getCouponId());
					couponDao.removeCoupon(coupon);
				}
				companyDao.removeCompany(company);
			}
		}
	}

	/**
	 * Update company method - after validations, updates company in company table
	 * 
	 * @param companyToUpdate
	 * @throws CouponException
	 */
	public void updateCompany(Company companyToUpdate) throws CouponException {
		long companyId = companyToUpdate.getId();
		if (companyId <= 0) {
			String msg = "Illegal Company ID ";
			throw new CouponException(msg);
		}
		String password = companyToUpdate.getPassward();
		if (ValidationUtils.isEmpty(password)) {
			String msg = "Company password can't be null";
			throw new CouponException(msg);
		}
		String email = companyToUpdate.getEmail();
		if (ValidationUtils.isEmpty(email)) {
			String msg = "Company email can't be null";
			throw new CouponException(msg);
		}
		String compname = companyToUpdate.getCompName();
		if (ValidationUtils.isEmpty(compname)) {
			String msg = "Company name can't be null";
			throw new CouponException(msg);
		}

		Company originCompany = getCompany(companyToUpdate.getId());
		if (!originCompany.getCompName().equals(companyToUpdate.getCompName())) {
			String msg = "Company name can't be updated";
			throw new CouponException(msg);
		}

		List<Company> companies = companyDao.getAllCompanies();
		for (Company company : companies) {
			if (company.getId() == companyId) {
				company = companyToUpdate;
				companyDao.updateCompany(company);
			}
		}
	}

	/**
	 * Get company by company ID
	 * 
	 * @param companyId
	 * @return
	 * @throws CouponException
	 */
	public Company getCompany(long companyId) throws CouponException {
		if (companyId <= 0) {
			String msg = "Illegal Company ID ";
			throw new CouponException(msg);
		}
		List<Company> companies = companyDao.getAllCompanies();
		for (Company company : companies) {
			if (company.getId() == companyId) {
				return companyDao.getCompany(companyId);
			}
		}
		// String msg = "No company found with this ID";
		// throw new CouponException(msg);
		return null;
	}

	/**
	 * Get all companies method
	 * 
	 * @return collection of companies
	 * @throws CouponException
	 */
	public List<Company> getAllCompanies() throws CouponException {
		return companyDao.getAllCompanies();
	}

	/**
	 * Create customer method - after all validations, checks if name does not exist
	 * and add customer to customer table in DB
	 * 
	 * @param newCustomerToCreate
	 * @throws CouponException
	 */
	public void createCustomer(Customer newCustomerToCreate) throws CouponException {
		long customerId = newCustomerToCreate.getId();
		if (customerId <= 0) {
			String msg = "Illegal Customer ID ";
			throw new CouponException(msg);
		}
		String password = newCustomerToCreate.getPassword();
		if (ValidationUtils.isEmpty(password)) {
			String msg = "Customer password can't be null ";
			throw new CouponException(msg);
		}
		String custName = newCustomerToCreate.getCustName();
		if (ValidationUtils.isEmpty(custName)) {
			String msg = "Customer name can't be null ";
			throw new CouponException(msg);
		}
		List<Customer> allCustomers = customerDao.getAllCustomers();
		for (Customer customer : allCustomers) {
			if (customer.getCustName().equals(custName)) {
				throw new CouponException("customer " + custName + " already exists");
			}
		}
		customerDao.createCustomer(newCustomerToCreate);
	}

	/**
	 * Remove customer method removes the customer from customer table, removes
	 * customer coupons from customer_coupon table, it should not remove companies
	 * coupons - only customer_coupons
	 * 
	 * @param customerToRemove
	 * @throws CouponException
	 */
	public void removeCustomer(Customer customerToRemove) throws CouponException {
		long customerId = customerToRemove.getId();
		List<Customer> customers = customerDao.getAllCustomers();
		for (Customer customer : customers) {
			if (customer.getId() == customerId) {
				List<Coupon> allCustomerCoupons = customerDao.getCoupons(customer.getId());
				for (Coupon coupon : allCustomerCoupons) {
					customerDao.removeCouponFromCustomer(customer.getId(), coupon.getCouponId());
				}
				customerDao.removeCustomer(customer);
			}
		}
	}

	/**
	 * Update customer method - after all validations updates customer in customer
	 * table in the DB
	 * 
	 * @param customerToUpdate
	 * @throws CouponException
	 */
	public void updateCustomer(Customer customerToUpdate) throws CouponException {
		long customerId = customerToUpdate.getId();
		if (customerId <= 0) {
			String msg = "Illegal Customer ID ";
			throw new CouponException(msg);
		}
		String password = customerToUpdate.getPassword();
		if (ValidationUtils.isEmpty(password)) {
			String msg = "Customer password can't be null ";
			throw new CouponException(msg);
		}
		String custName = customerToUpdate.getCustName();
		if (ValidationUtils.isEmpty(custName)) {
			String msg = "Customer name can't be null ";
			throw new CouponException(msg);
		}

		Customer originCustomer = getCustomer(customerToUpdate.getId());
		if (!originCustomer.getCustName().equals(customerToUpdate.getCustName())) {
			String msg = "Customer name can't be updated";
			throw new CouponException(msg);
		}

		List<Customer> customers = customerDao.getAllCustomers();
		for (Customer customer : customers) {
			if (customer.getId() == customerId) {
				customer = customerToUpdate;
				customerDao.updateCustomer(customer);
			}
		}
	}

	/**
	 * Get customer by ID method
	 * 
	 * @param customerId
	 * @return
	 * @throws CouponException
	 */
	public Customer getCustomer(long customerId) throws CouponException {
		if (customerId <= 0) {
			String msg = "Illegal Customer ID ";
			throw new CouponException(msg);
		}
		List<Customer> customers = customerDao.getAllCustomers();
		for (Customer customer : customers) {
			if (customer.getId() == customerId) {
				return customerDao.getCustomer(customerId);
			}
		}
		// String msg = "No customer found with this ID";
		// throw new CouponException(msg);
		return null;
	}

	/**
	 * Get all customers
	 * 
	 * @return collection of customers
	 * @throws CouponException
	 */
	public List<Customer> getAllCustomers() throws CouponException {
		return customerDao.getAllCustomers();
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

}