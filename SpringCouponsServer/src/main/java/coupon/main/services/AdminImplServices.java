package coupon.main.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coupon.main.beans.Company;
import coupon.main.beans.Coupon;
import coupon.main.beans.Customer;
import coupon.main.beans.User;
import coupon.main.enums.ClientType;
import coupon.main.exception.CouponSystemException;
import coupon.main.repositories.CompanyRepository;
import coupon.main.repositories.CustomerRepository;

@Service
public class AdminImplServices implements AdminServices {
	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public void createCompany(Company company) throws CouponSystemException{
		try {
		Company c = companyRepository.findBycompName(company.getCompName());
		if(c==null) {
		companyRepository.save(company);
	}else {
		throw new CouponSystemException("Error. company name " + company.getCompName() + " already exist");
	}
}catch (CouponSystemException e) {
	throw new CouponSystemException("Error. couldn't create company", e);
}
}

	@Override
	public void removeCompany(int companyId) throws CouponSystemException {
		try {
			Optional<Company> c = companyRepository.findById(companyId);
			if(c!=null) {
		companyRepository.deleteById(companyId);
			}else {
				throw new CouponSystemException("Error. company  not exist - can't remove");
			}
		} catch (CouponSystemException e) {
			throw new CouponSystemException("Error. couldn't remove company", e);
		}
	}
	
	@Override
	public void updateCompany(Company company) throws CouponSystemException {
	try {
		Optional<Company> c = companyRepository.findById(company.getcompanyId());
		if(c!=null) {
		companyRepository.updateCompany(company.getEmail(),company.getPassword(),company.getcompanyId());		
		}else {
			throw new CouponSystemException("Error. can't update because company  not exist");
		} 
	} catch (CouponSystemException e) {
		throw new CouponSystemException("Error. couldn't update company", e);
	}
}
	@Override
	public Company getCompanyByID(int companyId) throws CouponSystemException {
		try {
			Optional<Company> c = companyRepository.findById(companyId);
            if(c!=null) {
		return companyRepository.findById(companyId).get();
	}else {
		throw new CouponSystemException("Error. company " + companyId + " not exist");

	}
            }catch (CouponSystemException e) {
    			throw new CouponSystemException("Error. couldn't show company", e);
    		}
    	}

	@Override
	public Company getCompanyByName(String compName) throws CouponSystemException {
		try {
			Company c = companyRepository.findBycompName(compName);
            if(c!=null) {
		return companyRepository.findBycompName(compName);
	}else {
		throw new CouponSystemException("Error. company " + compName + " not exist");

	}
            }catch (CouponSystemException e) {
    			throw new CouponSystemException("Error. couldn't show company", e);
    		}
    	}


	@Override
	public List<Company> getAllCompanies() throws CouponSystemException {
		try {
		List<Company> c = companyRepository.findAll();
		if(c!=null) {
		return companyRepository.findAll();
		}else {
			throw new CouponSystemException("Error. theres no company to show ");

		}
	}catch (CouponSystemException e) {
		throw new CouponSystemException("Error. couldn't show all companys", e);
	}
}

	@Override
	public List<Coupon> getAllCoupons(int companyId) throws CouponSystemException {
	try {
		List<Coupon> c = companyRepository.findAllCompanyCoupons(companyId);
		if(c!=null) {
		return companyRepository.findAllCompanyCoupons(companyId);
		}else {
			throw new CouponSystemException("Error. company dont have coupon yet ");

		}
	}catch (CouponSystemException e) {
		throw new CouponSystemException("Error. couldn't show all coupon of company" + companyId, e);
	}
}
	@Override
	public User login(String userName, String password,ClientType clientType) throws CouponSystemException {
			if (userName.equals("admin")  &&password.equals("1234")&& clientType.equals(ClientType.valueOf("ADMIN"))) {
				return new User(userName, password, ClientType.ADMIN);
			}
		return null;
	}

	@Override
	public void createCustomer(Customer customer) throws CouponSystemException {
		try {
			Customer c = customerRepository.findByCustName(customer.getCustName());
			if(c==null) {
				customerRepository.save(customer);
		}else {
			throw new CouponSystemException("Error. customer name " + customer.getCustName() + " already exist");
		}
	}catch (CouponSystemException e) {
		throw new CouponSystemException("Error. couldn't create customer", e);
	}
	}

	@Override
	public void removeCustomer(int customerId) throws CouponSystemException {
		try {
			Optional<Customer> c = customerRepository.findById(customerId);
			if(c!=null) {
				customerRepository.deleteById(customerId);
			}else {
				throw new CouponSystemException("Error. customer  not exist - can't remove");
			}
		} catch (CouponSystemException e) {
			throw new CouponSystemException("Error. couldn't remove customer", e);
		}
	}
		
	

	@Override
	public void updateCustomer(Customer customer) throws CouponSystemException {
		try {
			Optional<Customer> c = customerRepository.findById(customer.getId());
			if(c!=null) {
				customerRepository.updateCustomer(customer.getPassword(),customer.getId());	
			}else {
				throw new CouponSystemException("Error. can't update because customer  not exist");
			} 
		} catch (CouponSystemException e) {
			throw new CouponSystemException("Error. couldn't update customer", e);
		}
	}
		
	

	@Override
	public Customer getCustomerById(int customerID) throws CouponSystemException {
		try {
			Optional<Customer> c = customerRepository.findById(customerID);
            if(c!=null) {
            	return customerRepository.findById(customerID).get();
	}else {
		throw new CouponSystemException("Error. customer " + customerID + " not exist");

	}
            }catch (CouponSystemException e) {
    			throw new CouponSystemException("Error. couldn't show customer", e);
    		}
    	}
		
	

	@Override
	public Customer getCustomerByName(String cust_name) throws CouponSystemException {
		try {
			Customer c = customerRepository.findByCustName(cust_name);
            if(c!=null) {
            	return customerRepository.findByCustName(cust_name);
	}else {
		throw new CouponSystemException("Error. customer " + cust_name + " not exist");

	}
            }catch (CouponSystemException e) {
    			throw new CouponSystemException("Error. couldn't show customer", e);
    		}
    	}
		
	

	@Override
	public List<Customer> getAllCustomers() throws CouponSystemException {
		try {
			List<Customer> c = customerRepository.findAll();
			if(c!=null) {
				return customerRepository.findAll();
			}else {
				throw new CouponSystemException("Error. theres no customer to show ");

			}
		}catch (CouponSystemException e) {
			throw new CouponSystemException("Error. couldn't show all customers", e);
		}
	}
		
	

	@Override
	public List<Coupon> getAllCustomerCoupons(Customer customer) throws CouponSystemException {
		try {
			List<Coupon> c = customerRepository.findAllCustomerCoupons(customer.getId());
			if(c!=null) {
			return customerRepository.findAllCustomerCoupons(customer.getId());
			}else {
				throw new CouponSystemException("Error. customer dont have coupon yet ");

			}
		}catch (CouponSystemException e) {
			throw new CouponSystemException("Error. couldn't show all coupon of customer" + customer.getCustName(), e);
		}
	}
	}


