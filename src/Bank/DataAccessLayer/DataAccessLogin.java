package Bank.DataAccessLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataAccessLogin {
	private static DataAccessLogin _instance;

	public static DataAccessLogin getInstance() throws SQLException {
		if (_instance == null) {
			_instance = new DataAccessLogin();
		}
		return _instance;
	}

	public Statement stmt;
	public Connection connect;

	private DataAccessLogin() throws SQLException {
		connect = ConnectDatabase.getInstance().connect();
		stmt = connect.createStatement();
	}

	// check login
	public boolean checkLogin(String username, boolean byPassword, String password_pin) throws SQLException {
		String query;
		if (!username.equals(username.toLowerCase())) {
			return false;
		}
		query = byPassword
				? "Select * from Account where Username = '" + username + "' and Password = '" + password_pin + "'"
				: "Select * from Account where Username = '" + username + "' and Pin = '" + password_pin + "'";
		ResultSet rSet = stmt.executeQuery(query);
		return (rSet.next()) ? true : false;
	}

	// check username
	public boolean checkUsername(String username) throws SQLException {
		if (!username.equals(username.toLowerCase()))
			return false;
		ResultSet rSet = stmt.executeQuery("Select * from Account where Username = '" + username + "'");
		return rSet.next() ? true : false;
	}

	// check confirm - forgot password
	public boolean checkConfirm(String username, String phone, String userID, String creditCardID) throws SQLException {
		if (!username.equals(username.toLowerCase()))
			return false;
		ResultSet rSet = stmt
				.executeQuery("Select * from Account where Username = '" + username + "' and PhoneNumber = '" + phone
						+ "' and UserID = '" + userID + "' and CreditCardID = '" + creditCardID + "'");
		return rSet.next() ? true : false;
	}

	// change password
	public void changePassword(String creditCardID, String newPassword) throws SQLException {
		String queryUpdatePassword = "Update Account set Password = '" + newPassword + "' where CreditCardID = '"
				+ creditCardID + "'";
		stmt.executeUpdate(queryUpdatePassword);
	}

	// account number
	public String accountNumber(String username, String password_pin) throws SQLException {
		String query = "Select * from Account where Username = '" + username + "' and  Password = '" + password_pin
				+ "' or Pin = '" + password_pin + "'";
		ResultSet rSet = stmt.executeQuery(query);
		return (rSet.next()) ? rSet.getString("AccountNumber") : null;
	}
	
	// balance
	public float balance(String accountNumber) throws SQLException {
		String query = "Select * from Account where AccountNumber = '"+accountNumber+"'";
		ResultSet rSet = stmt.executeQuery(query);
		return (rSet.next()) ? rSet.getFloat("Balance") : 0;
	}
}
