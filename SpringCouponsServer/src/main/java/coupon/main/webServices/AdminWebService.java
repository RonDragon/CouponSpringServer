package coupon.main.webServices;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import coupon.main.beans.Company;
import coupon.main.beans.Customer;
import coupon.main.exception.CouponSystemException;
import coupon.main.services.AdminImplServices;


@RestController
@RequestMapping("rest/admin")
public class AdminWebService {

	@Autowired
	private AdminImplServices AdminService;

	@RequestMapping(path = "createCompany", method = RequestMethod.POST)
	public Company createCompany(@RequestBody Company company) throws CouponSystemException {
		AdminService.createCompany(company);
		return company;
	}

	@RequestMapping(path = "removeCompany/{companyId}", method = RequestMethod.DELETE)
	public boolean removeCompany(@PathVariable int companyId) throws CouponSystemException {		
		AdminService.removeCompany(companyId);
		return true;
	}

	@RequestMapping(path = "updateCompany", method = RequestMethod.PATCH)
	public boolean updateCompany(@RequestBody Company company) throws CouponSystemException {	
		AdminService.updateCompany(company);
		return true;
	}

	@RequestMapping(path = "findCompanyById/{companyId}", method = RequestMethod.GET)
	public Company findCompanyById(@PathVariable int companyId) throws CouponSystemException {
		Company company = AdminService.getCompanyByID(companyId);
		return company;
	}

	@RequestMapping(path = "getCompanyByName", method = RequestMethod.GET)
	public Company getCompanyByName(@RequestBody String compName) throws CouponSystemException {
		Company company = AdminService.getCompanyByName(compName);
		return company;
	}

	@RequestMapping(path = "getAllCompanies", method = RequestMethod.GET)
	public List<Company> getAllCompanies() throws CouponSystemException {
		List<Company> companys = AdminService.getAllCompanies();
		return companys;
	}

	@RequestMapping(path = "createCustomer", method = RequestMethod.POST)
	public Customer createCustomer(@RequestBody Customer customer) throws CouponSystemException {
		AdminService.createCustomer(customer);
		return customer;
	}

	@RequestMapping(path = "removeCustomer/{cutomerId}", method = RequestMethod.DELETE)
	public boolean removeCustomer(@PathVariable int cutomerId) throws CouponSystemException {
		AdminService.removeCustomer(cutomerId);
		return true;
	}

	@RequestMapping(path = "updateCustomer", method = RequestMethod.PATCH)
	public boolean updateCustomer(@RequestBody Customer customer) throws CouponSystemException {
		AdminService.updateCustomer(customer);
		return true;
	}

	@RequestMapping(path = "getCustomerById", method = RequestMethod.GET)
	public Customer getCustomerById(@RequestBody int customerId) throws CouponSystemException {
		Customer customer = AdminService.getCustomerById(customerId);
		return customer;
	}

	@RequestMapping(path = "getCustomerByName", method = RequestMethod.GET)
	public Customer getCustomerByName(@RequestBody String cust_name) throws CouponSystemException {
		Customer customer = AdminService.getCustomerByName(cust_name);
		return customer;
	}

	@RequestMapping(path = "getAllCustomers", method = RequestMethod.GET)
	public List<Customer> getAllCustomers() throws CouponSystemException {
		List<Customer> customers = AdminService.getAllCustomers();
		return customers;
	}

	
	

}