package entrypoint;

import db_creating.DatabaseCreator;
import exception.CouponException;

public class MainEntryPoint {
	public static void main(String[] args) throws CouponException {
		// creator is in charge of creating DB and tables
		final DatabaseCreator creator = new DatabaseCreator();
		try {
			creator.createDbAndTables();
		} catch (CouponException e) {
			String msg = "wait for a connection to be available";
			CouponException couponException = new CouponException(msg);
			throw couponException;
		}
		// at this point we should have DB and tables initialized

	}
}
