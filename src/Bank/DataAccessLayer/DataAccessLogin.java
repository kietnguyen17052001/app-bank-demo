package Bank.DataAccessLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Bank.Objects.Account;
import Bank.Objects.DetailAccount;

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
	public boolean checkConfirm(String username, String phone, String userID, int creditCardID) throws SQLException {
		if (!username.equals(username.toLowerCase()))
			return false;
		ResultSet rSet = stmt
				.executeQuery("Select * from Account where Username = '" + username + "' and PhoneNumber = '" + phone
						+ "' and UserID = '" + userID + "' and CreditCardID = '" + creditCardID + "'");
		return rSet.next() ? true : false;
	}

	// get password by account number
	public String password(String accountNumber) throws SQLException {
		String query = "Select * from Account where AccountNumber = '" + accountNumber + "'";
		ResultSet rSet = stmt.executeQuery(query);
		return (rSet.next()) ? rSet.getString("Password") : null;
	}

	// change password
	public void changePassword(int creditCardID, String newPassword) throws SQLException {
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

	// account name
	public String accountName(String accountNumber) throws SQLException {
		String query = "Select * from Account where AccountNumber = '" + accountNumber + "'";
		ResultSet rSet = stmt.executeQuery(query);
		return (rSet.next()) ? rSet.getString("AccountName") : null;
	}

	// balance
	public float balance(String accountNumber) throws SQLException {
		String query = "Select * from Account where AccountNumber = '" + accountNumber + "'";
		ResultSet rSet = stmt.executeQuery(query);
		return (rSet.next()) ? rSet.getFloat("Balance") : 0;
	}

	// list bank
	public List<String> listBank() throws SQLException {
		List<String> list = new ArrayList<>();
		String query = "Select distinct Account.Bank from Account";
		ResultSet rSet = stmt.executeQuery(query);
		while (rSet.next()) {
			list.add(rSet.getString("Bank"));
		}
		return list;
	}

	// get recipient account name by account number
	public String recipientAccountName(String recipientAccountNumber, String bank) throws SQLException {
		String query = (bank == null)
				? "Select Account.AccountName from Account where AccountNumber = '" + recipientAccountNumber + "'"
				: "Select Account.AccountName from Account where AccountNumber = '" + recipientAccountNumber
						+ "' and Bank = '" + bank + "'";
		ResultSet rSet = stmt.executeQuery(query);
		return (rSet.next()) ? rSet.getString("AccountName") : null;
	}

	// credit card id
	public int creditCardID(String accountNumber) throws SQLException {
		String query = "Select * from Account where AccountNumber = '" + accountNumber + "'";
		ResultSet rSet = stmt.executeQuery(query);
		return (rSet.next()) ? rSet.getInt("CreditCardID") : 0;
	}

	// balance after transfer
	public void updateBalance(boolean isTransfer, String accountNumber, float amount) throws SQLException {
		String query = (isTransfer)
				? "Update Account set Balance = '" + (balance(accountNumber) - amount) + "' where AccountNumber = '"
						+ accountNumber + "'"
				: "Update Account set Balance = '" + (balance(accountNumber) + amount) + "' where AccountNumber = '"
						+ accountNumber + "'";
		stmt.executeUpdate(query);
	}

	// get information account
	public Account getAccount(String accountNumber) throws SQLException {
		Account account = null;
		String query = "Select * from Account where AccountNumber = '" + accountNumber + "'";
		ResultSet rSet = stmt.executeQuery(query);
		while (rSet.next()) {
			account = new Account(rSet.getString("AccountName"), rSet.getString("PhoneNumber"),
					rSet.getString("UserID"), rSet.getString("UserName"), rSet.getString("Bank"),
					rSet.getDate("DateCreate"));
		}
		return account;
	}

	// confirm transfer
	public void confirmTransfer(String accountNumber, String recipientAccountNumber, float amount, float balance,
			String content) throws SQLException {
		int creditCardID = creditCardID(accountNumber);
		int tradingCreditCardID = creditCardID(recipientAccountNumber);
		LocalDateTime dateTime = LocalDateTime.now();
		String recipientAccountName = recipientAccountName(recipientAccountNumber, null);
		String query = "Insert into DetailAccount values (?,?,?,?,?,?,?,?,?,?)";
		updateBalance(true, accountNumber, amount);
		PreparedStatement pStmtSender = connect.prepareStatement(query);
		pStmtSender.setInt(1, creditCardID);
		pStmtSender.setString(2, "Transfer");
		pStmtSender.setObject(3, dateTime);
		pStmtSender.setFloat(4, amount);
		pStmtSender.setFloat(5, balance(accountNumber));
		pStmtSender.setString(6, "Java");
		pStmtSender.setInt(7, tradingCreditCardID);
		pStmtSender.setString(8, recipientAccountNumber);
		pStmtSender.setString(9, recipientAccountName);
		pStmtSender.setString(10, content);
		pStmtSender.executeUpdate();
		updateBalance(false, recipientAccountNumber, amount);
		PreparedStatement pStmtReceiver = connect.prepareStatement(query);
		pStmtReceiver.setInt(1, tradingCreditCardID);
		pStmtReceiver.setString(2, "Receive");
		pStmtReceiver.setObject(3, dateTime);
		pStmtReceiver.setFloat(4, amount);
		pStmtReceiver.setFloat(5, balance(recipientAccountNumber));
		pStmtReceiver.setString(6, "Java");
		pStmtReceiver.setInt(7, creditCardID);
		pStmtReceiver.setString(8, accountNumber);
		pStmtReceiver.setString(9, accountName(accountNumber));
		pStmtReceiver.setString(10, content);
		pStmtReceiver.executeUpdate();
	}

	// data transaction
	public List<DetailAccount> dataTransaction(String accountNumber) throws SQLException {
		List<DetailAccount> list = new ArrayList<DetailAccount>();
		String query = "Select * from DetailAccount where CreditCardID = '" + creditCardID(accountNumber)
				+ "' order by DayTrading DESC";
		ResultSet rSet = stmt.executeQuery(query);
		DetailAccount detailAccount = null;
		while (rSet.next()) {
			detailAccount = new DetailAccount(rSet.getInt("CreditCardID"), rSet.getString("TransactionType"),
					rSet.getObject("DayTrading"), rSet.getFloat("TransactionAmount"), rSet.getFloat("Balance"),
					rSet.getString("AccountNumber"), rSet.getString("AccountName"), rSet.getString("TransactionContent"));
			list.add(detailAccount);
		}
		return list;
	}
}
