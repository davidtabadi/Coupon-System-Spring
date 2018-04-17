package javabeans;

import java.util.ArrayList;

public class Customer {

	private long customerId;
	private String custName;
	private String password;
	private ArrayList<Coupon> coupons;

	public Customer() {
		super();
	}

	public Customer(long customerId, String custName, String password) {
		super();
		this.customerId = customerId;
		this.custName = custName;
		this.password = password;
	}

	public long getId() {
		return customerId;
	}

	public void setId(long id) {
		this.customerId = id;
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

	public ArrayList<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(ArrayList<Coupon> coupons) {
		this.coupons = coupons;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", custName=" + custName + ", password=" + password + "]";
	}

}
