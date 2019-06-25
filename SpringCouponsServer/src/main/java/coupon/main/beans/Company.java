package coupon.main.beans;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;



import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int companyId;
	

	@NotNull
	private String compName;

	@NotNull
	private String password;

	@NotNull
	@Email
	private String email;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Coupon> coupons = new LinkedHashSet<>();

//const
	public Company() {
	}

	public Company(int companyId, String compName, String password, String email) {
		this.companyId = companyId;
		this.compName = compName;
		this.password = password;
		this.email = email;
	}

	public Company(int companyId, String password, String email) {
		this.companyId = companyId;
		this.password = password;
		this.email = email;
	}

//get&set
	public int getcompanyId() {
		return companyId;
	}

	public void setcompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(Set<Coupon> coupons) {
		this.coupons = coupons;
	}

//methods
	public void addCouponToComapny(Coupon coupon) {
		this.addCouponToComapny(coupon);
	}

	@Override
	public String toString() {
		return "Company [companyId=" + companyId + ", compName=" + compName + ", password=" + password + ", email="
				+ email;
	}

}
