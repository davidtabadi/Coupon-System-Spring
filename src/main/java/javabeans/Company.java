package javabeans;

import java.util.ArrayList;

public class Company {

	private long companyId;
	private String compName;
	private String password;
	private String email;
	private ArrayList<Coupon> coupons;

	public Company() {
		super();
	}

	public Company(long compId, String compName, String password, String email) {
		super();
		this.companyId = compId;
		this.compName = compName;
		this.password = password;
		this.email = email;
	}

	public long getId() {
		return companyId;
	}

	public void setId(long id) {
		this.companyId = id;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getPassward() {
		return password;
	}

	public void setPassward(String passward) {
		this.password = passward;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(ArrayList<Coupon> coupons) {
		this.coupons = coupons;
	}

	@Override
	public String toString() {
		return "Company [companyId=" + companyId + ", compName=" + compName + ", password=" + password + ", email="
				+ email + "]";
	}

}
