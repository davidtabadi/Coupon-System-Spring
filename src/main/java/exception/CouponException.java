package exception;

public class CouponException extends Exception {

	private static final long serialVersionUID = -7274796598477370967L;

	public CouponException(String msg, Exception e) {
		super(msg, e);
	}

	public CouponException(String msg) {
		super(msg);
	}

	public CouponException(Exception e) {
		super(e);
	}
}
