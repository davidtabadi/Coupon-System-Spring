package main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javabeans.Company;
import javabeans.Coupon;
import javabeans.CouponType;
import javabeans.Customer;

// In this class all the tables data: companies, customers, coupons
public class DummyDataHelper {
	public static List<Company> getDummyCompaniesData() {
		List<Company> companies = new ArrayList<>();
		companies.add(new Company(111111111, "Toyota", "admintoyota1", "toyota@service"));
		companies.add(new Company(222222222, "IBM", "admintoyota1", "toyota@service"));
		companies.add(new Company(333333333, "Lenovo", "admintoyota1", "toyota@service"));
		companies.add(new Company(444444444, "Acer", "admintoyota1", "toyota@service"));
		companies.add(new Company(555555555, "Honda", "admintoyota1", "toyota@service"));
		companies.add(new Company(666666666, "Ford", "admintoyota1", "toyota@service"));
		companies.add(new Company(777777777, "Subaro", "admintoyota1", "toyota@service"));
		companies.add(new Company(888888888, "Mercedes", "admintoyota1", "toyota@service"));
		companies.add(new Company(999999999, "Hunday", "admintoyota1", "toyota@service"));
		companies.add(new Company(123456789, "Mazda", "admintoyota1", "toyota@service"));
		companies.add(new Company(987654321, "Pego", "admintoyota1", "toyota@service"));
		companies.add(new Company(333333339, "Lenovo", "admintoyota1", "toyota@service"));
		companies.add(new Company(1111199992, "Toyota1", "admintoyota1", "toyota@service"));
		return companies;
	}

	public static List<Customer> getDummyCustomerData() {
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(998877665, "Yossef", "yosefyosef1"));
		customers.add(new Customer(999877665, "Dana", "yosefyosef1"));
		customers.add(new Customer(998977665, "Eli", "yosefyosef1"));
		customers.add(new Customer(598877665, "Efi", "yosefyosef1"));
		customers.add(new Customer(998877675, "Shlomi", "yosefyosef1"));
		customers.add(new Customer(908877665, "Oren", "yosefyosef1"));
		customers.add(new Customer(998877663, "Oleg", "yosefyosef1"));
		customers.add(new Customer(998877662, "David", "yosefyosef1"));
		customers.add(new Customer(998877661, "Eran", "yosefyosef1"));
		customers.add(new Customer(998877660, "Hana", "yosefyosef1"));
		customers.add(new Customer(598877663, "Efi", "yosefyosef1"));
		customers.add(new Customer(333333333, "Efi", "yosefyosef1"));
		return customers;

	}

	Coupon coupon1 = (new Coupon(109999998, "p15pooooopp",
			(new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime()),
			(new GregorianCalendar(2018, Calendar.DECEMBER, 15).getTime()), 85, CouponType.FOOD, "verygoodcoupon",
			15.99, "verygoodcoupon"));
	Coupon coupon2 = (new Coupon(10000010, "ajaxsport", (new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime()),
			(new GregorianCalendar(2017, Calendar.DECEMBER, 22).getTime()), 85, CouponType.SPORTS, "verygoodcoupon",
			15.99, "verygoodcoupon"));
	Coupon coupon3 = (new Coupon(100000175, "q2", (new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime()),
			(new GregorianCalendar(2018, Calendar.DECEMBER, 15).getTime()), 85, CouponType.FOOD, "verygoodcoupon",
			15.99, "verygoodcoupon"));
	Coupon coupon4 = (new Coupon(10000008, "sports", (new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime()),
			(new GregorianCalendar(2018, Calendar.DECEMBER, 15).getTime()), 85, CouponType.SPORTS, "verygoodcoupon",
			15.99, "verygoodcoupon"));
	Coupon coupon5 = (new Coupon(109999978, "p15pp", (new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime()),
			(new GregorianCalendar(2018, Calendar.DECEMBER, 15).getTime()), 85, CouponType.FOOD, "verygoodcoupon",
			15.99, "verygoodcoupon"));
	Coupon coupon6 = (new Coupon(10000010, "ajaxsport9", (new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime()),
			(new GregorianCalendar(2017, Calendar.DECEMBER, 22).getTime()), 85, CouponType.SPORTS, "verygoodcoupon",
			15.99, "verygoodcoupon"));
	Coupon coupon7 = (new Coupon(100000175, "q2", (new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime()),
			(new GregorianCalendar(2018, Calendar.DECEMBER, 15).getTime()), 85, CouponType.FOOD, "verygoodcoupon",
			15.99, "verygoodcoupon"));
	Coupon coupon8 = (new Coupon(10000008, "sports", (new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime()),
			(new GregorianCalendar(2018, Calendar.DECEMBER, 15).getTime()), 85, CouponType.SPORTS, "verygoodcoupon",
			15.99, "verygoodcoupon"));

	public static List<Coupon> getDummyCouponData() {
		List<Coupon> coupons = new ArrayList<>();
		coupons.add(new Coupon(10000006, "blackcoupn", (new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime()),
				(new GregorianCalendar(2017, Calendar.DECEMBER, 15).getTime()), 85, CouponType.FOOD, "verygoodcoupon",
				15.99, "verygoodcoupon"));
		coupons.add(
				new Coupon(10000001, "vacationcoupon", (new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime()),
						(new GregorianCalendar(2018, Calendar.FEBRUARY, 10)).getTime(), 20, CouponType.TRAVELLING,
						"verygoodcoupon", 15.99, "verygoodcoupon"));
		coupons.add(new Coupon(10000002, "videogames", (new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime()),
				(new GregorianCalendar(2018, Calendar.DECEMBER, 10)).getTime(), 20, CouponType.ELECTRICITY,
				"verygoodcoupon", 95.99, "verygoodcoupon"));
		coupons.add(new Coupon(10000003, "videogames", (new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime()),
				(new GregorianCalendar(2018, Calendar.DECEMBER, 10)).getTime(), 20, CouponType.ELECTRICITY,
				"verygoodcoupon", 95.99, "verygoodcoupon"));
		coupons.add(new Coupon(10000004, "pizahutcoupon", (new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime()),
				(new GregorianCalendar(2017, Calendar.DECEMBER, 10)).getTime(), 20, CouponType.FOOD, "verygoodcoupon",
				95.99, "verygoodcoupon"));
		coupons.add(new Coupon(10000003, "Iphone7", new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime(),
				new GregorianCalendar(2017, Calendar.JULY, 31).getTime(), 35, CouponType.ELECTRICITY, "verygoodcoupon",
				15.99, "verygoodcoupon"));
		coupons.add(new Coupon(10000004, "bbbboupn", new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime(),
				new GregorianCalendar(2018, Calendar.AUGUST, 10).getTime(), 25, CouponType.FOOD, "verygoodcoupon",
				15.99, "verygoodcoupon"));
		coupons.add(new Coupon(10000005, "pizahutcoupon", new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime(),
				new GregorianCalendar(2017, Calendar.DECEMBER, 10).getTime(), 5, CouponType.FOOD, "verygoodcoupon",
				15.99, "verygoodcoupon"));
		coupons.add(new Coupon(10000006, "blackcoupn", new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime(),
				new GregorianCalendar(2017, Calendar.DECEMBER, 15).getTime(), 85, CouponType.FOOD, "verygoodcoupon",
				15.99, "verygoodcoupon"));
		coupons.add(new Coupon(10000007, "pizahutcoupon", new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime(),
				new GregorianCalendar(2019, Calendar.MARCH, 10).getTime(), 45, CouponType.FOOD, "verygoodcoupon", 15.99,
				"verygoodcoupon"));
		coupons.add(new Coupon(10000008, "sportscoupn", new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime(),
				new GregorianCalendar(2018, Calendar.FEBRUARY, 10).getTime(), 70, CouponType.FOOD, "verygoodcoupon",
				15.99, "verygoodcoupon"));
		coupons.add(new Coupon(10000009, "bugcomputers", new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime(),
				new GregorianCalendar(2017, Calendar.DECEMBER, 10).getTime(), 75, CouponType.ELECTRICITY,
				"verygoodcoupon", 15.99, "verygoodcoupon"));
		coupons.add(new Coupon(10000010, "varnavacation", new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime(),
				new GregorianCalendar(2018, Calendar.OCTOBER, 10).getTime(), 100, CouponType.TRAVELLING,
				"verygoodcoupon", 15.99, "verygoodcoupon"));
		coupons.add(new Coupon(10000011, "bugcomputers", new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime(),
				new GregorianCalendar(2018, Calendar.NOVEMBER, 10).getTime(), 105, CouponType.ELECTRICITY,
				"verygoodcoupon", 15.99, "verygoodcoupon"));
		coupons.add(new Coupon(10000012, "megacoupon", new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime(),
				new GregorianCalendar(2017, Calendar.DECEMBER, 25).getTime(), 12, CouponType.HEALTH, "verygoodcoupon",
				15.99, "verygoodcoupon"));
		coupons.add(new Coupon(10000013, "tvcomputers", new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime(),
				new GregorianCalendar(2019, Calendar.DECEMBER, 10).getTime(), 39, CouponType.ELECTRICITY,
				"verygoodcoupon", 15.99, "verygoodcoupon"));
		coupons.add(new Coupon(10000014, "cofix", new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime(),
				new GregorianCalendar(2018, Calendar.MARCH, 10).getTime(), 28, CouponType.FOOD, "verygoodcoupon", 15.99,
				"verygoodcoupon"));
		coupons.add(new Coupon(10000016, "vipcoupon", new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime(),
				new GregorianCalendar(2019, Calendar.SEPTEMBER, 11).getTime(), 36, CouponType.RESTURANTS,
				"verygoodcoupon", 15.99, "verygoodcoupon"));
		coupons.add(new Coupon(10000017, "alamcoupon", new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime(),
				new GregorianCalendar(2019, Calendar.SEPTEMBER, 11).getTime(), 140, CouponType.ELECTRICITY,
				"verygoodcoupon", 15.99, "verygoodcoupon"));
		coupons.add(new Coupon(10000018, "goactive", new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime(),
				new GregorianCalendar(2019, Calendar.SEPTEMBER, 11).getTime(), 120, CouponType.SPORTS, "verygoodcoupon",
				15.99, "verygoodcoupon"));
		coupons.add(new Coupon(10000019, "foodcoupon", new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime(),
				new GregorianCalendar(2019, Calendar.SEPTEMBER, 11).getTime(), 80, CouponType.FOOD, "verygoodcoupon",
				15.99, "verygoodcoupon"));
		coupons.add(new Coupon(10000020, "h&ocoupn", new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime(),
				new GregorianCalendar(2019, Calendar.SEPTEMBER, 11).getTime(), 95, CouponType.SPORTS, "verygoodcoupon",
				15.99, "verygoodcoupon"));
		coupons.add(new Coupon(10000021, "h&ocoupn", new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime(),
				new GregorianCalendar(2019, Calendar.SEPTEMBER, 11).getTime(), 110, CouponType.CAMPING,
				"verygoodcoupon", 15.99, "verygoodcoupon"));
		coupons.add(new Coupon(10000022, "vacationcoupon", new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime(),
				new GregorianCalendar(2019, Calendar.SEPTEMBER, 11).getTime(), 129, CouponType.TRAVELLING,
				"verygoodcoupon", 15.99, "verygoodcoupon"));
		coupons.add(new Coupon(10000023, "bbbcoupn", new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime(),
				new GregorianCalendar(2019, Calendar.SEPTEMBER, 11).getTime(), 175, CouponType.FOOD, "verygoodcoupon",
				15.99, "verygoodcoupon"));
		coupons.add(new Coupon(10000024, "vacationcoupon", new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime(),
				new GregorianCalendar(2019, Calendar.SEPTEMBER, 11).getTime(), 7, CouponType.TRAVELLING,
				"verygoodcoupon", 15.99, "verygoodcoupon"));
		coupons.add(new Coupon(10000025, "bbbcoupn", new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime(),
				new GregorianCalendar(2019, Calendar.SEPTEMBER, 11).getTime(), 9, CouponType.FOOD, "verygoodcoupon",
				15.99, "verygoodcoupon"));
		coupons.add(new Coupon(10000026, "vacationcoupon", new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime(),
				new GregorianCalendar(2019, Calendar.SEPTEMBER, 11).getTime(), 17, CouponType.TRAVELLING,
				"verygoodcoupon", 15.99, "verygoodcoupon"));
		coupons.add(new Coupon(10000027, "h&ocoupn", new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime(),
				new GregorianCalendar(2019, Calendar.SEPTEMBER, 11).getTime(), 99, CouponType.SPORTS, "verygoodcoupon",
				15.99, "verygoodcoupon"));
		coupons.add(new Coupon(10000028, "vacationcoupon", new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime(),
				new GregorianCalendar(2019, Calendar.SEPTEMBER, 11).getTime(), 21, CouponType.TRAVELLING,
				"verygoodcoupon", 15.99, "verygoodcoupon"));
		coupons.add(new Coupon(10000015, "bugcomputers", new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime(),
				new GregorianCalendar(2019, Calendar.MAY, 02).getTime(), 33, CouponType.ELECTRICITY, "verygoodcoupon",
				15.99, "verygoodcoupon"));
		coupons.add(new Coupon(10000002, "bbbcoupn", new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime(),
				new GregorianCalendar(2019, Calendar.JANUARY, 10).getTime(), 15, CouponType.FOOD, "verygoodcoupon",
				15.99, "verygoodcoupon"));

		return coupons;
	}
}
