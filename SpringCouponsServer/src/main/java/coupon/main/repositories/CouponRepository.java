package coupon.main.repositories;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import coupon.main.beans.Coupon;
import coupon.main.beans.CouponType;
import coupon.main.beans.Customer;



@Repository // Our DAO component
public interface CouponRepository extends JpaRepository<Coupon, Integer>  {

	List<Coupon> findByTitle( String title );
	
	// Error: List<Coupon> findByCouponName( String name );
	
	@Query("select c from Coupon c where c.title like %?1%")
	// ?1 = the first parameter of the method (keyword)
	List<Coupon> getAllCouponsWithKeyword( String keyword );

	List<Coupon> getCouponsByCouponType(CouponType couponType);

	@Query("select c from Coupon c where c.endDate like %?1%")
	void removeExpiredCoupons(Date date);

	

	@Transactional
	@Modifying(clearAutomatically = true)	
	@Query(value = "update Coupon  SET price = ?1 , end_Date = ?2 where id = ?3", nativeQuery = true)
	void updateCoupon( @Param("price")double price,@Param("end_Date")Date date,@Param("id")int id);

	@Query("select c from Coupon c join c.customer cc where cc.id = :customerId and c.id = :couponId")
	List<Customer> getCustomer(@Param("couponId")int couponId,@Param("customerId") int customerId);

	@Query("select c from Coupon c join c.company cc where cc.id = :companyId and c.id = :couponId")
	public List<Coupon> findByCompanyId(@Param("companyId") int compId, @Param("couponId") int couponId);
}
