package Bank.DataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabase {
	private static ConnectDatabase _instance;

	public static ConnectDatabase getInstance() throws SQLException {
		if (_instance == null) {
			_instance = new ConnectDatabase();
		}
		return _instance;
	}

	private ConnectDatabase() {
	}

	public Connection connect() throws SQLException {
		String url = "jdbc:sqlserver://DANGKIET\\SQLEXPRESS; databaseName=Bank";
		String username = "kiet";
		String password = "5071";
		return DriverManager.getConnection(url, username, password);
	}
}
