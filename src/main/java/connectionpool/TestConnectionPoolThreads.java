package connectionpool;

import java.sql.Connection;

import exception.CouponException;

public class TestConnectionPoolThreads {
	static ConnectionPool connectionPool;

	public static void main(String[] args) {
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (CouponException e1) {
			e1.printStackTrace();
		}

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Connection con = connectionPool.getConnection();
					} catch (CouponException e1) {
						System.out.println(e1.getMessage());
					}

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					Connection con = null;
					try {
						con = connectionPool.getConnection();
					} catch (CouponException e1) {
						System.out.println(e1.getMessage());
					}
					try {
						Thread.sleep(500);
						connectionPool.returnConnection(con);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (CouponException e) {
						e.printStackTrace();
					}
				}
			}
		});

		t1.start();
		t2.start();

	}

}
