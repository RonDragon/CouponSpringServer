package coupon.main.webServices;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import coupon.main.beans.Coupon;
import coupon.main.beans.CouponType;
import coupon.main.beans.User;
import coupon.main.exception.CouponSystemException;
import coupon.main.services.CompanyImpServices;

@RestController
@RequestMapping("rest/company")
public class CompanyWebService {

	@Autowired
	private CompanyImpServices CompanyServices;

	public User getLoggedUser(HttpServletRequest req) {
		return (User) req.getSession(false).getAttribute("user");	
	}
	
	@RequestMapping(path = "getAllCompanyCoupon", method = RequestMethod.GET)
	public List<Coupon> getAllCompanyCoupon(HttpServletRequest req) throws CouponSystemException {
		System.out.println(getLoggedUser(req).getUserId());
		List<Coupon> coupons = CompanyServices.getAllCoupons(getLoggedUser(req).getUserId());
		return coupons;
	}

	@RequestMapping(path = "createCoupon", method = RequestMethod.POST)
	public Coupon createCoupon(HttpServletRequest req,@RequestBody Coupon coupon) throws CouponSystemException {
		System.out.println(coupon);
		System.out.println(req);
		System.out.println(getLoggedUser(req).getUserId());
		CompanyServices.createCoupon(getLoggedUser(req).getUserId(),coupon);
		return coupon;
	}

	@RequestMapping(path = "removeCoupon/{couponId}", method = RequestMethod.DELETE)
	public void removeCoupon(@PathVariable int couponId,HttpServletRequest req) throws CouponSystemException {
		CompanyServices.removeCoupon(couponId,getLoggedUser(req).getUserId());
	}

	@RequestMapping(path = "updateCoupon", method = RequestMethod.PATCH)
	public Coupon updateCoupon(@RequestBody Coupon coupon) throws CouponSystemException {
		CompanyServices.updateCoupon(coupon);
		return coupon;
	}

	@RequestMapping(path = "getCouponById/{couponID}", method = RequestMethod.GET)
	public Coupon getCouponById(@PathVariable int couponID) throws CouponSystemException {
		Coupon coupon = CompanyServices.getCouponById(couponID);
		return coupon;
	}

	@RequestMapping(path = "getCouponsByName", method = RequestMethod.GET)
	public List<Coupon> getCouponsByName(@RequestBody String cuopon_name) throws CouponSystemException {
		List<Coupon> coupon = CompanyServices.getCouponsByName(cuopon_name);
		return coupon;
	}

	@RequestMapping(path = "getCouponsByKeyword", method = RequestMethod.GET)
	public List<Coupon> getCouponsByKeyword(@RequestBody String keyword) throws CouponSystemException {
		List<Coupon> coupon = CompanyServices.getCouponsByKeyword(keyword);
		return coupon;
	}

	@RequestMapping(path = "getAllCoupons", method = RequestMethod.GET)
	public List<Coupon> getAllCoupons() throws CouponSystemException {
		List<Coupon> coupon = CompanyServices.getAllCoupons();
		return coupon;
	}

	@RequestMapping(path = "getCouponByType", method = RequestMethod.GET)
	public List<Coupon> getCouponByType(CouponType couponType) throws CouponSystemException {
		List<Coupon> coupon = CompanyServices.getCouponByType(couponType);
		return coupon;
	}

}