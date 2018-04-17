package main;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import coupon_system.CouponSystem;
import exception.CouponException;
import facades.AdminFacade;
import facades.ClientType;
import facades.CompanyFacade;
import facades.CustomerFacade;
import javabeans.Company;
import javabeans.Coupon;
import javabeans.CouponType;
import javabeans.Customer;

public class TestMainCouponSystem {

	public static void main(String[] args) {

		CouponSystem couponSystem = null;

		String password = null;
		String userName = null;

		try {
			// coupon system loaded
			couponSystem = CouponSystem.getInstance();

			// all the 3 facades can login
			AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
			CompanyFacade companyFacade = (CompanyFacade) couponSystem.login("Toyota", "zzz", ClientType.COMPANY);
			CustomerFacade customerFacade = (CustomerFacade) couponSystem.login("Hana", "yosefyosef1",
					ClientType.CUSTOMER);

			// Date today = new Date();
			// System.out.println(today);
			// companyFacade.getCouponsBeforeEndDate(today);
			// System.out.println(companyFacade.getCouponsBeforeEndDate(today));

			// all the 3 facades are ready to work in the system
			CouponType type = CouponType.SPORTS;
			double price = 100;

			System.out.println(customerFacade.getAllCouponsForSale().size());
			System.out.println(customerFacade.getAllPurchasedCoupons().size());
			System.out.println(customerFacade.getAllCouponsForSaleByType(type).size());
			System.out.println(customerFacade.getAllPurchasedCouponsByType(type).size());
			System.out.println(customerFacade.getAllCouponsForSaleByPrice(price).size());
			System.out.println(customerFacade.getAllPurchasedCouponsByPrice(price).size());

			/**
			 * The Test for 3 facade starts here
			 */
			// Start the test here: ======================================================
			// =====================================================================
			List<Company> dummyCompaniesData = DummyDataHelper.getDummyCompaniesData();
			for (Company company : dummyCompaniesData) {
				// adminFacade.createCompany(company);
				// System.out.println(company.toString());
				// adminFacade.removeCompany(company);
			}
			// System.out.println(dummyCompaniesData.toString());
			// Company company1= adminFacade.getCompany(111111111);
			// System.out.println(adminFacade.getCompany(111111111).toString());
			// Company company2 = new Company(111111111, "IBM", "ibm@imb", "ibm@ibm");
			// System.out.println("before " + company2.toString());
			// adminFacade.updateCompany(company2);
			// System.out.println("after " + company2.toString());
			// adminFacade.getAllCompanies();
			// System.out.println(adminFacade.getAllCompanies());
			// System.out.println(adminFacade.getAllCompanies().size());

			List<Customer> dummyCustomerData = DummyDataHelper.getDummyCustomerData();
			for (Customer customer : dummyCustomerData) {
				// adminFacade.createCustomer(customer);
				// System.out.println(customer.toString());
				// adminFacade.removeCustomer(customer);
			}
			// Customer customer1 = adminFacade.getCustomer(998877665);
			// System.out.println(adminFacade.getCustomer(998877665));
			// Customer customer2 = new Customer(998977665, "ELIAHU", "debiliahoo");
			// System.out.println("before " + customer2.toString());
			// adminFacade.updateCustomer(customer2);
			// System.out.println("after " + customer2.toString());
			// adminFacade.getAllCustomers();
			// System.out.println(adminFacade.getAllCustomers());
			// System.out.println(adminFacade.getAllCustomers().size());

			Coupon coupon1 = (new Coupon(999999999, "xyzyzx",
					(new GregorianCalendar(2018, Calendar.DECEMBER, 1).getTime()),
					(new GregorianCalendar(2020, Calendar.DECEMBER, 15).getTime()), 85, CouponType.FOOD,
					"verygoodcoupon", 15.99, "verygoodcoupon"));

			// customerFacade.purchaseCoupon(coupon1);

			Coupon coupon2 = (new Coupon(10000010, "ajaxsport",
					(new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime()),
					(new GregorianCalendar(2017, Calendar.DECEMBER, 22).getTime()), 85, CouponType.SPORTS,
					"verygoodcoupon", 15.99, "verygoodcoupon"));
			Coupon coupon3 = (new Coupon(100500175, "wrq2",
					(new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime()),
					(new GregorianCalendar(2018, Calendar.DECEMBER, 15).getTime()), 85, CouponType.FOOD,
					"verygoodcoupon", 15.99, "verygoodcoupon"));
			Coupon coupon4 = (new Coupon(10000008, "sports",
					(new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime()),
					(new GregorianCalendar(2018, Calendar.DECEMBER, 15).getTime()), 85, CouponType.SPORTS,
					"verygoodcoupon", 15.99, "verygoodcoupon"));
			Coupon coupon5 = (new Coupon(109999978, "p15pop",
					(new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime()),
					(new GregorianCalendar(2018, Calendar.DECEMBER, 15).getTime()), 85, CouponType.FOOD,
					"verygoodcoupon", 15.99, "verygoodcoupon"));
			Coupon coupon6 = (new Coupon(10000010, "ajaxsport9",
					(new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime()),
					(new GregorianCalendar(2017, Calendar.DECEMBER, 22).getTime()), 85, CouponType.SPORTS,
					"verygoodcoupon", 15.99, "verygoodcoupon"));
			Coupon coupon7 = (new Coupon(100000175, "q2a",
					(new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime()),
					(new GregorianCalendar(2018, Calendar.DECEMBER, 15).getTime()), 85, CouponType.FOOD,
					"verygoodcoupon", 15.99, "verygoodcoupon"));
			Coupon coupon8 = (new Coupon(100888888, "tomiboy",
					(new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime()),
					(new GregorianCalendar(2018, Calendar.DECEMBER, 15).getTime()), 85, CouponType.SPORTS,
					"verygoodcoupon", 15.99, "verygoodcoupon"));

			Coupon coupon9 = (new Coupon(10000008, "abcabcabc",
					(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime()),
					(new GregorianCalendar(2200, Calendar.JUNE, 15).getTime()), 100, CouponType.FOOD, "badbadbad",
					88.33, "badbadbad"));

			// companyFacade.createCuopon(coupon2);
			// companyFacade.createCuopon(coupon8);
			// companyFacade.createCuopon(coupon7);
			// companyFacade.createCuopon(coupon5);
			// companyFacade.createCuopon(coupon1);
			// companyFacade.createCuopon(coupon3);
			// companyFacade.createCuopon(coupon4);
			// Coupon coupon = companyFacade.getCoupon(100500175);
			// System.out.println(coupon.toString());
			// List<Coupon> couponsList = companyFacade.getAllCompanyCoupons();
			// System.out.println(couponsList.size());
			// companyFacade.updateCoupon(coupon9);
			// companyFacade.getCouponsByType(CouponType.FOOD);
			// System.out.println(companyFacade.getCouponsByType(CouponType.FOOD).size());

			// customerFacade.purchaseCoupon(coupon9);
			// customerFacade.purchaseCoupon(coupon1);
			// customerFacade.purchaseCoupon(coupon2);
			// customerFacade.purchaseCoupon(coupon3);
			// customerFacade.purchaseCoupon(coupon7);
			// customerFacade.purchaseCoupon(coupon5);
			// customerFacade.purchaseCoupon(coupon4);
			// customerFacade.purchaseCoupon(coupon8);
			// customerFacade.getAllPurchasedCoupons();
			// customerFacade.getAllPurchasedCouponsByPrice(30);
			// System.out.println(customerFacade.getAllPurchasedCoupons().size());
			// System.out.println(customerFacade.getAllPurchasedCouponsByPrice(30).size());

			// customerFacade.getAllPurchasedCoupons();
			// System.out.println(customerFacade.getAllPurchasedCoupons());
			// System.out.println(customerFacade.getAllPurchasedCoupons().size());

			// companyFacade.removeCoupon(coupon1);
			// companyFacade.createCuopon(coupon1);

			// Coupon c1 = companyFacade.getCoupon(100888888);
			// System.out.println(c1.toString());

			Company companyToRemove = new Company(123456789, "Mazda", "admintoyota1", "toyota@service");
			Customer customerToRemove = new Customer(998877660, "Hana", "yosefyosef1");

			// adminFacade.createCompany(companyToRemove);
			// adminFacade.createCustomer(customerToRemove);

			// adminFacade.removeCompany(companyToRemove);
			// adminFacade.removeCustomer(customerToRemove);

			// The companies data in the Dummy Data Helper
			// List<Company> dummyCompaniesData = DummyDataHelper.getDummyCompaniesData();
			// for (Company company : dummyCompaniesData) {
			// Company
			// we will check all the admin facade methods
			// ===============================================
			// create company method - dummy data helper - working well
			// adminFacade.createCompany(company);
			// System.out.println(company.toString() + "created");
			// ===============================================
			// remove company method - dummy data helper - working well
			// adminFacade.removeCompany(company);
			// System.out.println(company.toString() + "removed");
			// ===============================================
			// update company method - dummy data helper - working well
			// adminFacade.updateCompany(company);
			// System.out.println(company.toString() + "updated");
			// ===============================================
			// get company - method - dummy data helper - working well
			// adminFacade.getCompany(555555559);
			// System.out.println(company.toString() + "selected");
			// ===============================================
			// }
			// ===============================================
			// get all companies method - dummy data helper - working well
			// adminFacade.getAllCompanies();
			// List<Company> companies = new ArrayList<>();
			// companies = adminFacade.getAllCompanies();
			// System.out.println(companies.size());
			// ===============================================

			// new Company(111111111, "Toyota", "admintoyota1", "toyota@service");
			// Company company2 = new Company(666666666, "Ford", "admintoyota1",
			// "toyota@service");

			// adminFacade.removeCompany(company2);

			// adminFacade.createCompany(company2);
			// adminFacade.removeCompany(company1);
			// adminFacade.createCompany(company1);

			// List<Coupon> coupons = companyFacade.getAllCompanyCoupons();
			// // System.out.println(companyFacade.getAllCompanyCoupons());
			// for (Coupon coupon : coupons) {
			//// Object couponDao = new CouponDBDAO();
			//// System.out.println(coupon.toString());
			//// ((CouponDBDAO) couponDao).removeCoupon(coupon);
			// }
			// System.out.println(companyFacade.getAllCompanyCoupons().size());

			// Customer customer1 = new Customer(1211111789, "marko", "yosefyosef1");
			// adminFacade.createCompany(company1);
			// adminFacade.createCustomer(customer1);
			// adminFacade.createCustomer(customer1);
			// System.out.println(customer1.toString() + " created");
			// adminFacade.removeCustomer(customer1);
			// System.out.println(customer1.toString() + " removed");

			// System.out.println(company1.getCompName());
			// String name = "LINDA";
			// boolean bool = false;
			// if (company1.getCompName().equals(name)) {
			// bool = true;
			// }
			// System.out.println(bool);

			// adminFacade.createCompany(company1);
			// adminFacade.removeCompany(company1);
			// System.out.println(company1.toString() + " removed");

			// The customers data in the Dummy Data Helper
			// List<Customer> dummyCustomerData = DummyDataHelper.getDummyCustomerData();
			// for (Customer customer : dummyCustomerData) {
			// Customer
			// we will check all the admin facade methods
			// ===============================================
			// create customer method - dummy data helper - working well
			// adminFacade.createCustomer(customer);
			// System.out.println(customer.toString() + "created");
			// ===============================================
			// remove customer method - dummy data helper - working well
			// adminFacade.removeCustomer(customer);
			// System.out.println(customer.toString() + "removed");
			// ===============================================
			// update customer method - dummy data helper - working well
			// adminFacade.updateCustomer(customer);
			// System.out.println(customer.toString() + "updated");
			// ===============================================
			// get customer method - dummy data helper - working well
			// ===============================================
			// }

			// adminFacade.getCustomer(998807660);
			// System.out.println(adminFacade.getCustomer(998807660) + "selected");
			// ===============================================
			// get all customers method - dummy data helper - working well
			// adminFacade.getAllCustomers();
			// List<Customer> customers = new ArrayList<>();
			// customers = adminFacade.getAllCustomers();
			// System.out.println(customers.size());
			// ===============================================

			// all coupons start date is 01/12/2017
			// The coupons data in the Dummy Data Helper
			// List<Coupon> dummyCouponData = DummyDataHelper.getDummyCouponData();
			// for (Coupon coupon : dummyCouponData) {
			// List<Coupon> coupons = new ArrayList<>();
			// Coupon coupon1 = (new Coupon(999999999, "xyzyzx",
			// (new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime()),
			// (new GregorianCalendar(2018, Calendar.DECEMBER, 15).getTime()), 85,
			// CouponType.FOOD,
			// "verygoodcoupon", 15.99, "verygoodcoupon"));
			// Coupon coupon2 = (new Coupon(10000010, "ajaxsport",
			// (new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime()),
			// (new GregorianCalendar(2017, Calendar.DECEMBER, 22).getTime()), 85,
			// CouponType.SPORTS,
			// "verygoodcoupon", 15.99, "verygoodcoupon"));
			// Coupon coupon3 = (new Coupon(100000175, "q2", (new GregorianCalendar(2017,
			// Calendar.DECEMBER, 1).getTime()),
			// (new GregorianCalendar(2018, Calendar.DECEMBER, 15).getTime()), 85,
			// CouponType.FOOD,
			// "verygoodcoupon", 15.99, "verygoodcoupon"));
			// Coupon coupon4 = (new Coupon(10000008, "sports",
			// (new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime()),
			// (new GregorianCalendar(2018, Calendar.DECEMBER, 15).getTime()), 85,
			// CouponType.SPORTS,
			// "verygoodcoupon", 15.99, "verygoodcoupon"));
			// Coupon coupon5 = (new Coupon(109999978, "p15pop",
			// (new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime()),
			// (new GregorianCalendar(2018, Calendar.DECEMBER, 15).getTime()), 85,
			// CouponType.FOOD,
			// "verygoodcoupon", 15.99, "verygoodcoupon"));
			// Coupon coupon6 = (new Coupon(10000010, "ajaxsport9",
			// (new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime()),
			// (new GregorianCalendar(2017, Calendar.DECEMBER, 22).getTime()), 85,
			// CouponType.SPORTS,
			// "verygoodcoupon", 15.99, "verygoodcoupon"));
			// Coupon coupon7 = (new Coupon(100000175, "q2a",
			// (new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime()),
			// (new GregorianCalendar(2018, Calendar.DECEMBER, 15).getTime()), 85,
			// CouponType.FOOD,
			// "verygoodcoupon", 15.99, "verygoodcoupon"));
			// Coupon coupon8 = (new Coupon(100888888, "tomiboy",
			// (new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime()),
			// (new GregorianCalendar(2018, Calendar.DECEMBER, 15).getTime()), 85,
			// CouponType.SPORTS,
			// "verygoodcoupon", 15.99, "verygoodcoupon"));

			// companyFacade.createCuopon(coupon1);
			// System.out.println(coupon1.toString() + " created");
			// adminFacade.removeCompany(company1);

			// companyFacade.createCuopon(coupon8);
			// companyFacade.removeCoupon(coupon3);

			// companyFacade.createCuopon(coupon3);
			// customerFacade.purchaseCoupon(coupon8);
			// customerFacade.purchaseCoupon(coupon3);

			// companyFacade.removeCoupon(coupon1);
			// companyFacade.removeCoupon(coupon3);

			// adminFacade.createCompany(company1);

			// companyFacade.removeCoupon(coupon7);

			// companyFacade.getCoupon(77777);
			// System.out.println(companyFacade.getCoupon(7777));
			// companyFacade.createCuopon(coupon2);
			// System.out.println(coupon2.toString() + " created");
			// companyFacade.createCuopon(coupon3);
			// System.out.println(coupon3.toString() + " created");
			// companyFacade.createCuopon(coupon4);
			// System.out.println(coupon4.toString() + " created");

			// companyFacade.createCuopon(coupon8);
			// companyFacade.createCuopon(coupon7);
			// companyFacade.createCuopon(coupon6);
			// companyFacade.createCuopon(coupon5);
			// companyFacade.createCuopon(coupon4);

			// companyFacade.createCuopon(coupon6);
			// System.out.println(coupon6.toString() + " created");
			// customerFacade.purchaseCoupon(coupon6);
			// System.out.println(coupon6.toString() + " purchased");

			// companyFacade.removeCoupon(coupon1);
			// System.out.println(coupon1.toString() + " removed");
			// companyFacade.removeCoupon(coupon4);
			// System.out.println(coupon4.toString() + " removed");

			// customerFacade.purchaseCoupon(coupon6);
			// System.out.println(coupon6.toString() + " purchased");
			// customerFacade.purchaseCoupon(coupon7);
			// System.out.println(coupon7.toString() + " purchased");
			// customerFacade.purchaseCoupon(coupon8);
			// System.out.println(coupon8.toString() + " purchased");

			// customerFacade.purchaseCoupon(coupon8);
			// System.out.println(coupon8.toString() + " purchased");
			// customerFacade.purchaseCoupon(coupon7);
			// System.out.println(coupon7.toString() + " purchased");
			// customerFacade.purchaseCoupon(coupon6);
			// System.out.println(coupon6.toString() + " purchased");
			// customerFacade.purchaseCoupon(coupon5);
			// System.out.println(coupon5.toString() + " purchased");
			// customerFacade.purchaseCoupon(coupon4);
			// System.out.println(coupon4.toString() + " purchased");
			// customerFacade.purchaseCoupon(coupon3);
			// System.out.println(coupon3.toString() + " purchased");
			// customerFacade.purchaseCoupon(coupon2);
			// System.out.println(coupon2.toString() + " purchased");
			// customerFacade.purchaseCoupon(coupon1);
			// System.out.println(coupon1.toString() + " purchased");
			// customerFacade.purchaseCoupon(coupon1);
			// System.out.println(coupon1.toString() + " purchased");
			Company company5 = new Company(1111199992, "Ynet", "zzzzzzzz", "zzzzz@zzzzz");
			// adminFacade.updateCompany(company5);

			Customer cus7 = new Customer(998877661, "Eran", "yosefyosef1");
			// adminFacade.removeCustomer(cus7);
			// adminFacade.createCustomer(cus7);

			// adminFacade.updateCustomer(cus7);

			// adminFacade.getAllCompanies();
			// System.out.println(adminFacade.getAllCompanies());

			// adminFacade.getAllCustomers();
			// System.out.println(adminFacade.getAllCustomers());

			// customerFacade.getAllPurchasedCouponsByPrice(10);
			// System.out.println(customerFacade.getAllPurchasedCouponsByPrice(10));

			// our company facade is company Toyota
			// ===============================================
			// we will check all the company facade methods
			// ===============================================
			// create coupon method - dummy data helper - working well + adding to company
			// ===============================================
			// companyFacade.createCuopon(coupon1);
			// System.out.println(coupon1.toString() + "created");
			// companyFacade.removeCoupon(coupon1);
			// System.out.println(coupon1.toString() + "removed");

			Coupon cp2 = (new Coupon(900900900, "milanalev",
					(new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime()),
					(new GregorianCalendar(2020, Calendar.DECEMBER, 01).getTime()), 80, CouponType.SPORTS,
					"verygoodcoupon", 135.99, "verygoodcoupon"));
			// companyFacade.createCuopon(cp2);
			// companyFacade.updateCoupon(cp2);

			// companyFacade.createCuopon(cp1);
			// customerFacade.purchaseCoupon(cp1);
			// customerFacade.getAllPurchasedCoupons();
			// System.out.println(customerFacade.getAllPurchasedCoupons());

			// CompanyDAO companyDao = new CompanyDBDAO();
			// companyDao.addCouponToCompany(company.getId(), coupon2.getCouponId());
			// ===============================================
			// remove coupon method - dummy data helper - working well removes coupon from
			// coupon + from company_coupn + from customer_coupon
			// ===============================================
			// companyFacade.removeCoupon(coupon1);
			// System.out.println(coupon1.toString() + "removed");
			// ===============================================
			// get coupon method - dummy data helper - working well
			// companyFacade.getCoupon(10000006);
			// System.out.println(coupon1.toString() + "selected");
			// ===============================================
			// get all company coupons - dummy data helper - working well
			// companyFacade.getAllCompanyCoupons();
			// List<Coupon> coupons = new ArrayList<>();
			// coupons = companyFacade.getAllCompanyCoupons();
			// System.out.println(coupons.size());
			// ===============================================
			// get coupon method- dummy data helper - working well
			// companyFacade.getCoupon(coupon1.getCouponId());
			// System.out.println(coupon1.toString() + "selected");
			// ===============================================
			// get coupon method- dummy data helper - working well
			// System.out.println("before" + coupon1.toString());
			// coupon1.setAmount(95);
			// companyFacade.updateCoupon(coupon1);
			// System.out.println("after" + coupon1.toString());
			// ===============================================
			// get coupons by type - dummy data helper - working well
			// CouponType type = coupon1.getType();
			// companyFacade.getCouponsByType(type.FOOD);
			// System.out.println(coupon1.toString());
			// ===============================================
			// get coupons until price - dummy data helper - working well
			// Double price = 99.99;
			// companyFacade.getCouponsUntilPrice(price);
			// System.out.println(coupon1.toString());
			// ===============================================
			// get coupons before date - dummy data helper - working well
			// Date today = new Date();
			// companyFacade.getCouponsBeforeEndDate(today);
			// System.out.println(coupon1.getEndDate());

			// our customer facade is yossef customer id = 998877665
			// ===============================================
			// purchase coupon method- dummy data helper - working well + decreasing amount
			// in 1 and adding to customer_coupon table too
			// customerFacade.purchaseCoupon(coupon1);
			// System.out.println(coupon1.toString() + "purchased");
			// ===============================================
			// get all purchased coupons by customer - working well
			// customerFacade.getAllPurchasedCoupons();
			// List<Coupon> coupons = customerFacade.getAllPurchasedCoupons();
			// System.out.println(coupons.size());
			// ===============================================
			// get all purchased coupons customer by type - working well
			// CouponType type = coupon3.getType();
			// customerFacade.getAllPurchasedCouponsByType(type);
			// List<Coupon> coupons = customerFacade.getAllPurchasedCouponsByType(type);
			// System.out.println(coupons.size());
			// ===============================================
			// get all purchased coupons customer under price - working well
			// customerFacade.getAllPurchasedCouponsByPrice(99);
			// List<Coupon> coupons = customerFacade.getAllPurchasedCouponsByPrice(99);
			// System.out.println(coupons.size());
			// ===============================================
			// }

			// Date today = new Date();
			// ===============================================
			// at the end we closing the coupon system and returning all connections
			// couponSystem.shutDown();
			// System.out.println("goodby");
			// the system is down after shutDown method
			// ===============================================
			// }
			// }
		} catch (

		CouponException e) {
			System.out.println(e.getMessage());
		} finally {
			if (couponSystem != null) {
				try {
					couponSystem.shutDown();
				} catch (CouponException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

}
