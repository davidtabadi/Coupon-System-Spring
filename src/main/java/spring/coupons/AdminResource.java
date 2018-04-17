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
import facades.AdminFacade;
import facades.ClientType;
import javabeans.Company;
import javabeans.Customer;

@RestController
public class AdminResource {

	private AdminFacade getAadminFacadeFromSession(HttpSession session) {
		AdminFacade adminFacade = (AdminFacade) session.getAttribute("admin");
		String sessionName = session.getId();
		// System.out.println(sessionName);
		return adminFacade;
	}

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public Message handleException(Exception e) {
		String message = e.getMessage();
		if (message == null) {
			message = "Error while invoking request - please login as Admin";
		}
		return new Message(message);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/api/admin/login/{username}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ClientDTO adminLogin(@PathVariable("username") String username, @PathVariable("password") String password,
			HttpSession session) throws CouponException {
		CouponSystem couponSystem = CouponSystem.getInstance();
		AdminFacade adminFacade = (AdminFacade) couponSystem.login(username, password, ClientType.ADMIN);
		String sessionName = session.getId();
		// System.out.println(sessionName);
		session.setAttribute("admin", adminFacade);
		// System.out.println("AdminService.adminLogin()");
		ClientDTO clientDTO = new ClientDTO(ClientType.ADMIN, username, password);
		// System.out.println("AdminService.adminLogin() " + sessionName);
		return clientDTO;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/api/admin/logout")
	public void adminLogout(HttpSession session) {
		if (session != null) {
			// System.out.println("AdminService.adminLogout()");
			session.invalidate();
		}
	}

	@RequestMapping(method = RequestMethod.POST, path = "/api/admin/companies", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Company createCompany(@RequestBody Company newCompanyToCreate, HttpSession session) throws CouponException {
		getAadminFacadeFromSession(session).createCompany(newCompanyToCreate);
		// System.out.println("AdminService.createCompany() " +
		// newCompanyToCreate.getCompName());
		return newCompanyToCreate;
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/api/admin/companies/{companyId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateCompany(@PathVariable("companyId") long companyId, @RequestBody Company companyToUpdate,
			HttpSession session) throws CouponException {
		if (companyId != companyToUpdate.getId()) {
			throw new CouponException("The Company selected must match to the Company in Database. ");
		} else {
			getAadminFacadeFromSession(session).updateCompany(companyToUpdate);
			// System.out.println("AdminService.updateCompany() " +
			// companyToUpdate.getCompName());
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/api/admin/companies", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void removeCompany(@RequestBody Company companyToRemove, HttpSession session) throws CouponException {
		// System.out.println("AdminService.removeCompany() " +
		// companyToRemove.getCompName());
		getAadminFacadeFromSession(session).removeCompany(companyToRemove);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/api/admin/companies/{companyId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Company getCompany(@PathVariable("companyId") long companyId, HttpSession session) throws CouponException {
		Company company = getAadminFacadeFromSession(session).getCompany(companyId);
		// System.out.println("AdminService.getCompany() " + companyId);
		return company;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/api/admin/companies", produces = MediaType.APPLICATION_JSON_VALUE)
	public Company[] getAllCompanies(HttpSession session) throws CouponException {
		Company[] allCompanies = getAadminFacadeFromSession(session).getAllCompanies().toArray(new Company[0]);
		// System.out.println("AdminService.getAllCompanies() " +
		// Arrays.toString(allCompanies));
		return allCompanies;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/api/admin/customers", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Customer createCustomer(@RequestBody Customer customerToCreate, HttpSession session) throws CouponException {
		getAadminFacadeFromSession(session).createCustomer(customerToCreate);
		// System.out.println("AdminService.createCustomer() " +
		// customerToCreate.getCustName());
		return customerToCreate;
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/api/admin/customers/{customerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateCustomer(@PathVariable("customerId") long customerId, @RequestBody Customer customerToUpdate,
			HttpSession session) throws CouponException {
		if (customerId != customerToUpdate.getId()) {
			throw new CouponException("The Customer selected must match to the Customer in Database. ");
		} else {
			getAadminFacadeFromSession(session).updateCustomer(customerToUpdate);
			// System.out.println("AdminService.updateCustomer() " +
			// customerToUpdate.getCustName());
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/api/admin/customers", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void removeCustomer(@RequestBody Customer customerToRemove, HttpSession session) throws CouponException {
		// System.out.println("AdminService.removeCustomer() " +
		// customerToRemove.getCustName());
		getAadminFacadeFromSession(session).removeCustomer(customerToRemove);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/api/admin/customers/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer getCustomer(@PathVariable("customerId") long customerId, HttpSession session)
			throws CouponException {
		Customer customer = getAadminFacadeFromSession(session).getCustomer(customerId);
		// System.out.println("AdminService.getCustomer() " + customerId);
		return customer;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/api/admin/customers", produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer[] getAllCustomers(HttpSession session) throws CouponException {
		Customer[] allCustomers = getAadminFacadeFromSession(session).getAllCustomers().toArray(new Customer[0]);
		// System.out.println("AdminService.getAllCustomers() " +
		// Arrays.toString(allCustomers));
		return allCustomers;
	}

}
