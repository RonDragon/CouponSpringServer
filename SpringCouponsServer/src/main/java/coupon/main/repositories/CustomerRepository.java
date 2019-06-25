package coupon.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import coupon.main.beans.Coupon;
import coupon.main.beans.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>  {

	Customer findByCustName(String cust_name);

	Customer findByCustNameAndPassword(String cust_name, String password);


	@Transactional
	@Query("select c from Coupon c join c.customer cc where cc.id = ?1")
	List<Coupon> getAllCustomerCoupons(int customerID);

	
	//@Query(value = "select c from coupon c join c.customer cust where cust.customerId=:customerId and c.id =: id")
	//Coupon findByCustomerId(@Param("customerId") int customerId,@Param("id") int id);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update Customer c SET c.password = :password  where c.id = :id")
	void updateCustomer(@Param("password") String password,@Param("id") int id);

	@Query("select c from  Coupon c join c.customer cc where cc.id = :id")
	List<Coupon> findAllCustomerCoupons(int id);

	

}
