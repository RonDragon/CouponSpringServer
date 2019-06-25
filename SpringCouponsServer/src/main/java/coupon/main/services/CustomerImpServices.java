package coupon.main.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coupon.main.beans.Coupon;
import coupon.main.beans.CouponType;
import coupon.main.beans.Customer;
import coupon.main.beans.User;
import coupon.main.enums.ClientType;
import coupon.main.exception.CouponSystemException;
import coupon.main.repositories.CouponRepository;
import coupon.main.repositories.CustomerRepository;
import coupon.main.services.CustomerServices;

@Service
public class CustomerImpServices implements CustomerServices {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CouponRepository couponRepository;
	
	


	

	@Override
	public User login(String custName, String password,ClientType clientType) throws CouponSystemException {
		if (!custName.isEmpty() && !password.isEmpty() && custName != null && password != null) {
			Customer CustToGet = customerRepository.findByCustNameAndPassword(custName, password);
			if (CustToGet != null) {
				System.out.println("Login Succes!");
				
				return new User(CustToGet.getId(),CustToGet.getCustName(), password, ClientType.CUSTOMER);
			}
			else {
				System.err.println("Login Faild!");
			}
		}

		return null;

	}


	@Transactional
	@Override
	public void purchaseCoupon(Coupon coupon,int customerId) throws CouponSystemException {
		if (couponRepository.getCustomer(coupon.getId(), customerId).size() > 0) {
			try {
				throw new Exception("you allready purchase this coupon ");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			if(coupon.getAmount()<=0) {
				try {
					throw new Exception("this coupon "+coupon.getTitle()+" is sold out");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else  if(coupon.getEndDate().before(new Date(System.currentTimeMillis()))) {
				try {
					throw new Exception("this coupon "+coupon.getTitle()+" is expiered");
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}else {
				List<Customer> customers = (List<Customer>) coupon.getCustomer();
				System.err.println(customerId);
				Customer cust = customerRepository.findById(customerId).get();
				List<Coupon> customersCoupon = (List<Coupon>) cust.getCoupons();
				customersCoupon.add(coupon);
				coupon.setAmount(coupon.getAmount()-1);
				customers.add(cust);
				customerRepository.save(cust);
				couponRepository.save(coupon);
				
			}
		}
}

	@Override
	public Coupon getCouponById(int couponId) throws CouponSystemException {
		try {
			Optional<Coupon> a = couponRepository.findById(couponId);
			if (a == null) {
				throw new CouponSystemException("Error. coupon " + couponId + " not exist");
			} else {
				return couponRepository.findById(couponId).get();
			}
		} catch (CouponSystemException e) {
			throw new CouponSystemException("Error. couldn't show coupon", e);
		}
	}

	@Override
	public List<Coupon> getCouponByType(CouponType couponType) throws CouponSystemException {
		try {
			List<Coupon> a = couponRepository.getCouponsByCouponType(couponType);	
				if	(a!=null) {
					return couponRepository.getCouponsByCouponType(couponType);					}else {
					throw new CouponSystemException("Error. there no coupons to show");

				}
			} catch (CouponSystemException e) {
				throw new CouponSystemException("Error. couldn't show coupons", e);
			}
		
	}

	@Override
	public List<Coupon> getCouponsByName(String title) throws CouponSystemException {
		try {
			List<Coupon> a = couponRepository.findByTitle( title );
				if	(a!=null) {
					return couponRepository.findByTitle( title );
					}else {
					throw new CouponSystemException("Error. there no coupons to show");

				}
			} catch (CouponSystemException e) {
				throw new CouponSystemException("Error. couldn't show coupons", e);
			}
		}

	@Override
	public List<Coupon> getCouponsByKeyword(String keyword) throws CouponSystemException {
		try {
			List<Coupon> a = couponRepository.getAllCouponsWithKeyword(keyword);
				if	(a!=null) {
					return couponRepository.getAllCouponsWithKeyword(keyword);
					}else {
					throw new CouponSystemException("Error. there no coupons to show");

				}
			} catch (CouponSystemException e) {
				throw new CouponSystemException("Error. couldn't show coupons", e);
			}
		}
	@Override
	public List<Coupon> getAllCoupons() throws CouponSystemException {
		try {
		List<Coupon> a = couponRepository.findAll();
			if	(a!=null) {
			return couponRepository.findAll();
			}else {
				throw new CouponSystemException("Error. there no coupons to show");

			}
		} catch (CouponSystemException e) {
			throw new CouponSystemException("Error. couldn't show coupons", e);
		}
	}
	
	@Override
	public List<Coupon> getAllCustomerCoupons(int customerID) throws CouponSystemException {
		try {
			List<Coupon> a = customerRepository.getAllCustomerCoupons(customerID);
				if	(a!=null) {
					return customerRepository.getAllCustomerCoupons(customerID);
				}else {
					throw new CouponSystemException("Error. there no coupons to show");

				}
			} catch (CouponSystemException e) {
				throw new CouponSystemException("Error. couldn't show coupons", e);
			}
		
	}
	}
