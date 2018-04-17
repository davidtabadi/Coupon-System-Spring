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
import facades.CompanyFacade;
import javabeans.Coupon;
import javabeans.CouponType;

@RestController
public class CompanyResource {

	private CompanyFacade getCompanyFacadeFromSession(HttpSession session) {
		CompanyFacade companyFacade = (CompanyFacade) session.getAttribute("company");
		String sessionName = session.getId();
		// System.out.println(sessionName);
		return companyFacade;
	}

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public Message handleException(Exception e) {
		String message = e.getMessage();
		if (message == null) {
			message = "Error while invoking request - please login as Company";
		}
		return new Message(message);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/api/company/login/{username}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ClientDTO companyLogin(@PathVariable("username") String username, @PathVariable("password") String password,
			HttpSession session) throws CouponException {
		CouponSystem couponSystem = CouponSystem.getInstance();
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login(username, password, ClientType.COMPANY);
		String sessionName = session.getId();
		// System.out.println(sessionName);
		session.setAttribute("company", companyFacade);
		ClientDTO clientDTO = new ClientDTO(ClientType.COMPANY, username, password);
		// System.out.println("CompanyService.login() " + sessionName);
		return clientDTO;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/api/company/logout")
	public void companyLogout(HttpSession session) {
		if (session != null) {
			// System.out.println("CompanyService.companyLogout()");
			session.invalidate();
		}
	}

	@RequestMapping(method = RequestMethod.POST, path = "/api/company/createcoupon", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Coupon createCoupon(@RequestBody Coupon newCouponToCreate, HttpSession session) throws CouponException {
		getCompanyFacadeFromSession(session).createCuopon(newCouponToCreate);
		// System.out.println("CompanyService.createCoupon() " +
		// newCouponToCreate.getTitle());
		return newCouponToCreate;
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/api/company/updatecoupon/{couponId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateCoupon(@PathVariable("couponId") long couponId, @RequestBody Coupon couponToUpdate,
			HttpSession session) throws CouponException {
		if (couponId != couponToUpdate.getCouponId()) {
			throw new CouponException("The Coupon selected must match to the Coupon in Database.");
		} else {
			getCompanyFacadeFromSession(session).updateCoupon(couponToUpdate);
			// System.out.println("CompanyService.updateCoupon() " +
			// couponToUpdate.getTitle());
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/api/company/removecoupon", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void removeCoupon(@RequestBody Coupon couponToRemove, HttpSession session) throws CouponException {
		// System.out.println("CompanyService.removeCoupon() " +
		// couponToRemove.getTitle());
		getCompanyFacadeFromSession(session).removeCoupon(couponToRemove);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/api/company/getcoupon/{couponId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Coupon getCoupon(@PathVariable("couponId") long couponId, HttpSession session) throws CouponException {
		// System.out.println("CompanyService.getCoupon() " + couponId);
		return getCompanyFacadeFromSession(session).getCoupon(couponId);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/api/company/getallcoupons", produces = MediaType.APPLICATION_JSON_VALUE)
	public Coupon[] getCompanyCoupons(HttpSession session) throws CouponException {
		Coupon[] companyCoupons = getCompanyFacadeFromSession(session).getAllCompanyCoupons().toArray(new Coupon[0]);
		// System.out.println("CompanyService.getCompanyCoupons() " +
		// Arrays.toString(companyCoupons));
		return companyCoupons;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/api/company/getallcoupons/byprice/{price}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Coupon[] getCompanyCouponsByPrice(@PathVariable("price") double price, HttpSession session)
			throws CouponException {
		Coupon[] companyCouponsByPrice = getCompanyFacadeFromSession(session).getCouponsUntilPrice(price)
				.toArray(new Coupon[0]);
		// System.out.println("CompanyService.getCompanyCouponsByPrice() " +
		// Arrays.toString(companyCouponsByPrice));
		return companyCouponsByPrice;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/api/company/getallcoupons/bytype/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Coupon[] getCompanyCouponsByType(@PathVariable("type") CouponType type, HttpSession session)
			throws CouponException {
		Coupon[] companyCouponsByType = getCompanyFacadeFromSession(session).getCouponsByType(type)
				.toArray(new Coupon[0]);
		// System.out.println("CompanyService.getCompanyCouponsByType() " +
		// Arrays.toString(companyCouponsByType));
		return companyCouponsByType;
	}

	// There is no need for the show getCompanyCouponsByEndDate in angularjs

	// @RequestMapping(method = RequestMethod.GET, path =
	// "/api/company/getallcoupons/bydate/{date}", produces =
	// MediaType.APPLICATION_JSON_VALUE)
	// public Coupon[] getCompanyCouponsByEndDate(@PathVariable("date") String date,
	// HttpSession session)
	// throws CouponException, ParseException {
	// CompanyFacade companyfacade = getCompanyFacadeFromSession(session);
	// DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	// Date newDate = format.parse(date);
	// Coupon[] companyCouponsByEndDate =
	// companyfacade.getCouponsBeforeEndDate(newDate).toArray(new Coupon[0]);
	// System.out.println("CompanyService.getCouponByDate() " + newDate);
	// return companyCouponsByEndDate;
	// }

	// @RequestMapping(method = RequestMethod.GET, path =
	// "/api/company/getallcoupons/bydate/{date}", produces =
	// MediaType.APPLICATION_JSON_VALUE)
	// public Coupon[] getCompanyCouponsByEndDate(@PathParam("date") Date date,
	// @Context HttpServletRequest request)
	// throws CouponException {
	// Coupon[] companyCouponsByEndDate =
	// getCompanyFacadeFromSession(request).getCouponsBeforeEndDate(date)
	// .toArray(new Coupon[0]);
	// System.out.println("CompanyService.getCompanyCouponsByEndDate() " +
	// Arrays.toString(companyCouponsByEndDate));
	// return companyCouponsByEndDate;
	// }

}
