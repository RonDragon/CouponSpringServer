package coupon.main.beans;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Coupon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	

	@NotNull
	private String title;
	

	@NotNull
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date startDate;
	

	@NotNull
	@Temporal(value = TemporalType.TIMESTAMP)
	@Future
	private Date endDate;
	
	@Positive
	@NotNull
	private int amount;

	@NotNull
	@Enumerated(EnumType.STRING)
	private CouponType couponType;

	@NotNull
	private String message;

	@NotNull
	private double price;

	@NotNull
	private String image;
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "coupons")
	private List<Customer> customer = new ArrayList<>();
	
	@JsonIgnore
	@ManyToOne
	private Company company;

	// const
	public Coupon() {}

	public Coupon(String title, Date startDate, Date endDate, int amount, CouponType couponType, String message,
			double price, String image) {
		super();
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.couponType = couponType;
		this.message = message;
		this.price = price;
		this.image = image;
	}
	
	public Coupon(int id, Date endDate, double price) {
		super();
		this.id = id;
		this.endDate = endDate;
		this.price = price;
	}
	// get&set
	


	public int getId() {
		return id;
	}

	public List<Customer> getCustomer() {
		return customer;
	}

	public void setCustomer(List<Customer> customer) {
		this.customer = customer;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public CouponType getCouponType() {
		return couponType;
	}

	public void setCouponType(CouponType couponType) {
		this.couponType = couponType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	// methods
	public void addCustomerToCoupon(Customer customer) {
		this.addCustomerToCoupon(customer);
	}

	public void addCompanyToCoupon(Company company) {
		this.addCompanyToCoupon(company);
	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", amount=" + amount + ", couponType=" + couponType + ", message=" + message + ", price=" + price
				+ ", image=" + image + "]";
	}

}
