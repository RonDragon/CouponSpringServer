package coupon.main.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import coupon.main.beans.Company;
import coupon.main.beans.Coupon;



@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

	Company findBycompNameAndPassword(String compName, String password);


	Company findBycompName(String compName);
	
	@Query("select c from  Coupon c join c.company cc where cc.id = :companyId")
	List<Coupon> findAllCompanyCoupons(@Param("companyId")int companyId);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update Company c SET c.password = :password , c.email = :email where c.id = :id")
	public void updateCompany(@Param("password") String password,@Param("email")String email,@Param("id") int id);


	
}


