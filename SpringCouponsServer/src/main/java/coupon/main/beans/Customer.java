package coupon.main.beans;

import java.util.ArrayList;

import java.util.List;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int customerId;

	@NotNull
	private String custName;

	@NotNull
	private String password;
	
	@JsonIgnore
	@ManyToMany                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
	private List <Coupon> coupons =new ArrayList<>();
	//const
	public Customer() {	}
	public Customer( String custName, String password) {
		this.custName = custName;
		this.password = password;
	}
	//get&set
	public int getId() {
		return customerId;
	}
	public void setId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Coupon> getCoupons() {
		return coupons;
	}
	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}
	//methods
	public void addCouponToCustomer(Coupon coupon) {
		this.addCouponToCustomer(coupon);
	}
	
	@Override
	public String toString() {
		return "Customer [id=" + customerId + ", custName=" + custName + ", password=" + password ;
	}
	
	
}
