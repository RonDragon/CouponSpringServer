package coupon.main.services;

import coupon.main.beans.*;
import coupon.main.enums.ClientType;
import coupon.main.exception.CouponSystemException;


import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.validation.annotation.Validated;
@Validated
public interface CompanyServices {
	
	List<Coupon> getAllCoupons(@Positive int companyId) throws CouponSystemException;

	User login (@NotNull String compName , @NotNull String password,@NotNull ClientType ClientType) throws CouponSystemException ;


	void createCoupon(@Positive int companyId,@NotNull Coupon coupon) throws CouponSystemException;

	void removeCoupon(@Positive int couponId,@Positive int companyId)throws CouponSystemException;
	
	void updateCoupon(@NotNull Coupon coupon) throws CouponSystemException;

	Coupon getCouponById(@Positive int couponId)throws CouponSystemException;
	
	List<Coupon> getCouponByType(@NotNull CouponType couponType)throws CouponSystemException;

	List<Coupon> getAllCoupons()throws CouponSystemException;

	List<Coupon> getCouponsByName(@NotNull String name)throws CouponSystemException;

	List<Coupon> getCouponsByKeyword(@NotNull String keyword)throws CouponSystemException;
}
