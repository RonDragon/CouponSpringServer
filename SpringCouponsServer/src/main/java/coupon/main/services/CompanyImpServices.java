package coupon.main.services;


import coupon.main.beans.*;
import coupon.main.enums.ClientType;
import coupon.main.exception.CouponSystemException;
import coupon.main.repositories.CompanyRepository;
import coupon.main.repositories.CouponRepository;
import coupon.main.services.CompanyServices;


import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class CompanyImpServices implements CompanyServices {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private CouponRepository couponRepository;

	
	
	@Override
	public List<Coupon> getAllCoupons(int companyId) throws CouponSystemException {
		return companyRepository.findAllCompanyCoupons(companyId);
	}

	@Override
	public User login(String compName, String password,ClientType clientType) throws CouponSystemException {
		if (!compName.isEmpty() && !password.isEmpty() && compName != null && password != null) {
			Company CompToGet = companyRepository.findBycompNameAndPassword(compName, password);
			if (CompToGet != null) {
				System.out.println("Login Succes!");
				return new User(CompToGet.getcompanyId(),compName, password, ClientType.COMPANY);
			}
			else {
				System.err.println("Login Faild!");
			}
		}

		return null;

	}

	@Override
	public void createCoupon(int companyId,Coupon coupon) throws CouponSystemException{	
		System.out.println(couponRepository.findByTitle(coupon.getTitle()));
		if(coupon.getStartDate().before(coupon.getEndDate())) {
			Optional<Company> company = companyRepository.findById(companyId);
			coupon.setCompany(company.get());
				couponRepository.save(coupon);
				System.out.println("Coupon" + coupon.getTitle() + " create on DB");
			}
	}
		
	

	@Override
	public void removeCoupon(int couponId,int companyId) throws CouponSystemException {
		try {
			Optional<Coupon> a = couponRepository.findById(couponId);
			if (a == null) {
				throw new CouponSystemException("Error. coupon   not exist - can't remove");
			}else if(couponRepository.findByCompanyId(couponId, companyId).size() > 0) {
				throw new CouponSystemException("You Cant Remove Coupon Thats Not Yours");
			}else {
			couponRepository.deleteById(couponId);
			}
		} catch (CouponSystemException e) {
			throw new CouponSystemException("Error. couldn't remove coupon", e);
		}
	}
		
	

	@Override
	public void updateCoupon(Coupon coupon) throws CouponSystemException {
		try {
			Optional<Coupon> a = couponRepository.findById(coupon.getId());
			if (a == null) {
				throw new CouponSystemException("Error. coupon  not exist");
			}
			if(couponRepository.existsById(coupon.getId())){
				couponRepository.updateCoupon(coupon.getPrice(),coupon.getEndDate(),coupon.getId());
			}else{
				throw new CouponSystemException("Error. you can update only: price ,date, image");
			}
		} catch (CouponSystemException e) {
			throw new CouponSystemException("Error. couldn't update coupon", e);
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
}
