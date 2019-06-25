package coupon.main.services;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.validation.annotation.Validated;

import coupon.main.beans.Company;
import coupon.main.beans.Coupon;
import coupon.main.beans.Customer;
import coupon.main.beans.User;
import coupon.main.enums.ClientType;
import coupon.main.exception.CouponSystemException;
@Validated
public interface AdminServices {
	void createCompany (@NotNull Company company) throws CouponSystemException, Exception ;

	void removeCompany (@Positive int companyId) throws CouponSystemException ;

	void updateCompany (@NotNull Company company) throws CouponSystemException ;

	Company getCompanyByID (@Positive int companyId) throws CouponSystemException ;

	Company getCompanyByName (@NotNull String compName) throws CouponSystemException ;

	List<Company> getAllCompanies () throws CouponSystemException ;

	List<Coupon> getAllCoupons (@Positive int companyId) throws CouponSystemException ;

	User login (@NotNull String Admin , @NotNull String password,@NotNull ClientType ClientType) throws CouponSystemException ;
	
	void createCustomer (@NotNull Customer customer) throws CouponSystemException ;

	void removeCustomer (@NotNull int customerId) throws CouponSystemException ;

	void updateCustomer (@NotNull Customer customer) throws CouponSystemException ;

	Customer getCustomerById (@Positive int customerID) throws CouponSystemException ;

	Customer getCustomerByName (@NotNull String cust_name) throws CouponSystemException ;
	
	List<Customer> getAllCustomers () throws CouponSystemException ;

	List<Coupon> getAllCustomerCoupons (@NotNull Customer customer) throws CouponSystemException ;

	


}
