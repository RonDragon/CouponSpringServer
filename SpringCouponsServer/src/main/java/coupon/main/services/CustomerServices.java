package coupon.main.services;

import coupon.main.beans.*;
import coupon.main.enums.ClientType;
import coupon.main.exception.CouponSystemException;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public interface CustomerServices {

	
	
	List<Coupon> getAllCustomerCoupons(@Positive int customerID) throws CouponSystemException;

	User login (@NotNull String Admin , @NotNull String password,@NotNull ClientType ClientType) throws CouponSystemException ;


	void purchaseCoupon(@NotNull Coupon coupon, @Positive int customerID) throws CouponSystemException;

	Coupon getCouponById(@Positive int couponId) throws CouponSystemException;

	List<Coupon> getCouponByType(@NotNull CouponType couponType) throws CouponSystemException;

	List<Coupon> getCouponsByName(@NotNull String name) throws CouponSystemException;

	List<Coupon> getCouponsByKeyword(@NotNull String keyword) throws CouponSystemException;

	List<Coupon> getAllCoupons() throws CouponSystemException;
	
	
}
