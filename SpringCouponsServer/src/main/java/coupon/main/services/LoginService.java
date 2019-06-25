package coupon.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coupon.main.beans.User;
import coupon.main.enums.ClientType;
import coupon.main.exception.CouponSystemException;

@Service
public class LoginService {
	@Autowired
	public AdminImplServices admin;
	
	@Autowired
	public CompanyImpServices company ;
	
	@Autowired
	public CustomerImpServices customer;
	
	
	
	public  User login(String name , String password , ClientType type) {
		switch(type) {
		case ADMIN :
			try {
				return admin.login(name, password, type);
			} catch (CouponSystemException e) {
	
				e.printStackTrace();
			}
		case COMPANY :
			try {
				return company.login(name, password, type);
			} catch (CouponSystemException e) {
				e.printStackTrace();
			}
		case CUSTOMER :
			try {
				return customer.login(name, password, type);
			} catch (CouponSystemException e) {
				e.printStackTrace();
			}
		default :
			return null;
		}
	}
	
}