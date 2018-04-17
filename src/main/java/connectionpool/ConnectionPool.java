package connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import exception.CouponException;

public class ConnectionPool {

	String url = "jdbc:derby://localhost:1527/DBCoupon;create=true";
	private static ConnectionPool instance;
	public static final int MAX_CON = 10;
	private static final Set<Connection> availebleConnections = new HashSet<Connection>();
	private static final Object lock = new Object();

	private ConnectionPool() throws CouponException {
		while (availebleConnections.size() < MAX_CON) {
			try {
				Connection connection = DriverManager.getConnection(url);
				availebleConnections.add(connection);
			} catch (SQLException e) {
				String msg = "The maximum connections available is " + MAX_CON;
				CouponException couponException = new CouponException(msg, e);
				throw couponException;
			}
		}
	}

	public static ConnectionPool getInstance() throws CouponException {
		if (instance == null) {
			try {
				instance = new ConnectionPool();
			} catch (CouponException e) {
				throw new CouponException("not available connection yet", e);
			}
		}
		return instance;
	}

	public synchronized Connection getConnection() throws CouponException {
		synchronized (lock) {
			if (availebleConnections.size() == 0) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					String msg = "wait until connection will be available";
					throw new CouponException(msg, e);
				}
				Connection connection = availebleConnections.iterator().next();
				availebleConnections.remove(connection);
				return connection;
			}
		}
		Connection connection = availebleConnections.iterator().next();
		availebleConnections.remove(connection);

		// System.out.println("you got connection and available conections are " +
		// availebleConnections.size());
		return connection;
	}

	public synchronized void returnConnection(Connection connection) throws CouponException {
		if (availebleConnections.size() == MAX_CON) {
			throw new CouponException("The max amount of connectiions cannot extend " + MAX_CON);
		}
		availebleConnections.add(connection);
		synchronized (lock) {
			lock.notify();
		}

	}

	public void closeAllConnections() {
		ConnectionPool.availebleConnections.clear();
	}
}
