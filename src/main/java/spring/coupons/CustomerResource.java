package spring.coupons;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import coupon_system.CouponSystem;
import exception.CouponException;
import facades.ClientType;
import facades.CustomerFacade;
import javabeans.Coupon;
import javabeans.CouponType;

@RestController
public class CustomerResource {

	private CustomerFacade getCustomerFacadeFromSession(HttpSession session) {
		CustomerFacade customerFacade = (CustomerFacade) session.getAttribute("customer");
		String sessionName = session.getId();
		// System.out.println(sessionName);
		return customerFacade;
	}

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public Message handleException(Exception e) {
		String message = e.getMessage();
		if (message == null) {
			message = "Error while invoking request - please login as Customer";
		}
		return new Message(message);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/api/customer/login/{username}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ClientDTO customerLogin(@PathVariable("username") String username, @PathVariable("password") String password,
			HttpSession session) throws CouponException {
		CouponSystem couponSystem = CouponSystem.getInstance();
		CustomerFacade customerFacade = (CustomerFacade) couponSystem.login(username, password, ClientType.CUSTOMER);
		String sessionName = session.getId();
		// System.out.println(sessionName);
		session.setAttribute("customer", customerFacade);
		// System.out.println("CstomerService.login()");
		ClientDTO clientDTO = new ClientDTO(ClientType.CUSTOMER, username, password);
		// System.out.println("CstomerService.customerLogin() " + sessionName);
		return clientDTO;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/api/customer/logout")
	public void customerLogout(HttpSession session) {
		if (session != null) {
			// System.out.println("CstomerService.customerLogout()");
			session.invalidate();
		}
	}

	@RequestMapping(method = RequestMethod.POST, path = "/api/customer/purchasecoupon", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Coupon purchaseCoupon(@RequestBody Coupon couponToPurchase, HttpSession session) throws CouponException {
		getCustomerFacadeFromSession(session).purchaseCoupon(couponToPurchase);
		// System.out.println("CstomerService.purchaseCoupon() " +
		// couponToPurchase.getTitle());
		return couponToPurchase;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/api/customer/getallpurchasedcoupons", produces = MediaType.APPLICATION_JSON_VALUE)
	public Coupon[] getAllPurchasedCoupons(HttpSession session) throws CouponException {
		Coupon[] purchasedCoupons;
		purchasedCoupons = getCustomerFacadeFromSession(session).getAllPurchasedCoupons().toArray(new Coupon[0]);
		// System.out.println("CstomerService.getAllPurchasedCoupons() " +
		// Arrays.toString(purchasedCoupons));
		return purchasedCoupons;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/api/customer/getallpurchasedcoupons/bytype/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Coupon[] getAllPurchasedCouponsByType(@PathVariable("type") CouponType type, HttpSession session)
			throws CouponException {
		Coupon[] purchasedCouponsByType = getCustomerFacadeFromSession(session).getAllPurchasedCouponsByType(type)
				.toArray(new Coupon[0]);
		// System.out.println("CstomerService.getAllPurchasedCouponsByType() " +
		// Arrays.toString(purchasedCouponsByType));
		return purchasedCouponsByType;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/api/customer/getallpurchasedcoupons/byprice/{price}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Coupon[] getAllPurchasedCouponsByPrice(@PathVariable("price") double price, HttpSession session)
			throws CouponException {
		Coupon[] purchasedCouponsByPrice = getCustomerFacadeFromSession(session).getAllPurchasedCouponsByPrice(price)
				.toArray(new Coupon[0]);
		// System.out
		// .println("CstomerService.getAllPurchasedCouponsByPrice() " +
		// Arrays.toString(purchasedCouponsByPrice));
		return purchasedCouponsByPrice;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/api/customer/getallavailabledcoupons", produces = MediaType.APPLICATION_JSON_VALUE)
	public Coupon[] getAllAvailableCoupons(HttpSession session) throws CouponException {
		Coupon[] allCoupons;
		allCoupons = getCustomerFacadeFromSession(session).getAllCouponsForSale().toArray(new Coupon[0]);
		// System.out.println("CstomerService.getAllAvailableCoupons() " +
		// Arrays.toString(allCoupons));
		return allCoupons;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/api/customer/getallavailabledcoupons/bytype/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Coupon[] getAllAvailableCouponsByType(@PathVariable("type") CouponType type, HttpSession session)
			throws CouponException {
		Coupon[] allCouponsByType = getCustomerFacadeFromSession(session).getAllCouponsForSaleByType(type)
				.toArray(new Coupon[0]);
		// System.out.println("CstomerService.getAllAvailableCouponsByType() " +
		// Arrays.toString(allCouponsByType));
		return allCouponsByType;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/api/customer/getallavailabledcoupons/byprice/{price}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Coupon[] getAllAvailableCouponsByPrice(@PathVariable("price") double price, HttpSession session)
			throws CouponException {
		Coupon[] allCouponsByPrice = getCustomerFacadeFromSession(session).getAllCouponsForSaleByPrice(price)
				.toArray(new Coupon[0]);
		// System.out.println("CstomerService.getAllAvailableCouponsByPrice() " +
		// Arrays.toString(allCouponsByPrice));
		return allCouponsByPrice;
	}

}
