package coupon.main.webServices;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import coupon.main.beans.Coupon;
import coupon.main.beans.CouponType;
import coupon.main.beans.User;
import coupon.main.exception.CouponSystemException;
import coupon.main.services.CustomerImpServices;

@RestController
@RequestMapping("rest/customer")
public class CustomerWebService {

	@Autowired
	private CustomerImpServices CustomerService;

	public User getLoggedUser(HttpServletRequest req) {
		return (User) req.getSession(false).getAttribute("user");	
	}
	
	@RequestMapping(path = "getAllCustomerCoupons", method = RequestMethod.GET)
	public List<Coupon> getAllCustomerCoupons( HttpServletRequest req) throws CouponSystemException {
		List<Coupon> coupons = CustomerService.getAllCustomerCoupons(getLoggedUser(req).getUserId());
		return coupons;
	}
	
	@RequestMapping(path = "getAllCoupons", method = RequestMethod.GET)
	public List<Coupon> getAllCoupons() throws CouponSystemException {
		List<Coupon> coupons = CustomerService.getAllCoupons();
		return coupons;
	}

	@RequestMapping(path = "getCouponById", method = RequestMethod.GET)
	public Coupon getCouponById(@RequestBody int companyID) throws CouponSystemException {
		Coupon coupon = CustomerService.getCouponById(companyID);
		return coupon;
	}

	@RequestMapping(path = "getCouponsByName", method = RequestMethod.GET)
	public List<Coupon> getCouponsByName(@RequestBody String cuopon_name) throws CouponSystemException {
		List<Coupon> coupon = CustomerService.getCouponsByName(cuopon_name);
		return coupon;
	}

	@RequestMapping(path = "getCouponsByKeyword", method = RequestMethod.GET)
	public List<Coupon> getCouponsByKeyword(@RequestBody String keyword) throws CouponSystemException {
		List<Coupon> coupon = CustomerService.getCouponsByKeyword(keyword);
		return coupon;
	}

	@RequestMapping(path = "getCouponByType", method = RequestMethod.GET)
	public List<Coupon> getCouponByType(CouponType couponType) throws CouponSystemException {
		List<Coupon> coupon = CustomerService.getCouponByType(couponType);
		return coupon;
	}
	
	@RequestMapping(path = "purchaseCoupon", method = RequestMethod.POST)
	public void purchaseCoupon(@RequestBody Coupon coupon,HttpServletRequest req) throws CouponSystemException {
		System.err.println(coupon);
		System.err.println(req.toString());
		CustomerService.purchaseCoupon(coupon,getLoggedUser(req).getUserId());
	}
	
}