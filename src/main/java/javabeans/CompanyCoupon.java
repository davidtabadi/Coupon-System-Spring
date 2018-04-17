package javabeans;

public class CompanyCoupon {

	private long companyId;
	private long couponId;

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}

	public CompanyCoupon(long companyId, long couponId) {
		super();
		this.companyId = companyId;
		this.couponId = couponId;
	}

	public CompanyCoupon() {
		super();
	}

	@Override
	public String toString() {
		return "CompanyCoupon [companyId=" + companyId + ", couponId=" + couponId + "]";
	}

}
