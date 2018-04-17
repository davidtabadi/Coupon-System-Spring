package entrypoint;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import exception.CouponException;

public class DatabaseHelperUtilities {
	public static final String url = "jdbc:derby://localhost:1527/DBCoupon";

	public static void createTableIfNotExists(Connection con, String tableName, String query) throws CouponException {
		try {
			String[] names = { tableName };
			ResultSet tableNames = con.getMetaData().getTables(null, null, null, names);

			while (tableNames.next()) {
				String tab = tableNames.getString("TABLE_NAME");
				if (!tableName.equals(tab)) {
					con.createStatement().executeLargeUpdate(query);
				}
			}
		} catch (SQLException e) {
			CouponException couponException = new CouponException("table exists", e);
			throw couponException;

		}
	}

	public static void printColumnNames(Connection con, String tableName) throws CouponException {
		String selectAllQuery = "select * from " + tableName;
		ResultSet resultSet;
		try {
			resultSet = con.createStatement().executeQuery(selectAllQuery);
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			int columnCount = resultSetMetaData.getColumnCount();
			System.out.println("column count = " + columnCount);

			for (int i = 1; i < columnCount; i++) {
				String columnName = resultSetMetaData.getColumnName(i);
				System.out.print(columnName + " , ");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
