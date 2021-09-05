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

	//
	public Account getAccountByResultSet(ResultSet rSet) throws SQLException {
		Account account = null;
		while (rSet.next()) {
			account = new Account(rSet.getInt("CreditCardID"), rSet.getString("AccountNumber"),
					rSet.getString("AccountName"), rSet.getFloat("Balance"), rSet.getString("PhoneNumber"),
					rSet.getString("UserID"), rSet.getString("UserName"), rSet.getString("Password"),
					rSet.getString("Pin"), rSet.getString("Bank"), rSet.getDate("DateCreate"));
		}
		return account;
	}

	// check login
	public Account getAccount(String username, boolean byPassword, String password_pin) throws SQLException {
		if (!username.equals(username.toLowerCase())) {
			return null;
		}
		String query = "Select * from Account where Username = '" + username + "' and Bank = 'DemoBank'";
		String newquery = byPassword ? query + " and Password = '" + password_pin + "'"
				: query + " and Pin = '" + password_pin + "'";
		ResultSet rSet = stmt.executeQuery(newquery);
		return getAccountByResultSet(rSet);
	}

	// get account by username
	public Account getAccountByAccountNumber(String accountNumber) throws SQLException {
		String query = "Select * from Account where AccountNumber = '" + accountNumber + "'";
		ResultSet rSet = stmt.executeQuery(query);
		return getAccountByResultSet(rSet);
	}

	// check username
	public boolean checkUsername(String username) throws SQLException {
		if (!username.equals(username.toLowerCase()))
			return false;
		ResultSet rSet = stmt
				.executeQuery("Select * from Account where Username = '" + username + "' and Bank = 'DemoBank'");
		return rSet.next();
	}

	// check confirm - forgot password
	public String accountNumber(String username, String phone, String userID, int creditCardID) throws SQLException {
		if (!username.equals(username.toLowerCase()))
			return null;
		ResultSet rSet = stmt.executeQuery("Select * from Account where Username = '" + username
				+ "' and PhoneNumber = '" + phone + "' and UserID = '" + userID + "' and CreditCardID = '"
				+ creditCardID + "' and Bank = 'DemoBank'");
		return rSet.next() ? rSet.getString("AccountNumber") : null;
	}

	// get password by account number
	public String password(String accountNumber) throws SQLException {
		String query = "Select * from Account where AccountNumber = '" + accountNumber + "'";
		ResultSet rSet = stmt.executeQuery(query);
		return (rSet.next()) ? rSet.getString("Password") : null;
	}

	// change password
	public void changePassword(String accountNumber, String newPassword) throws SQLException {
		String queryUpdatePassword = "Update Account set Password = '" + newPassword + "' where AccountNumber = '"
				+ accountNumber + "'";
		stmt.executeUpdate(queryUpdatePassword);
	}

	// account number
	public String accountNumber(String username, String password_pin) throws SQLException {
		String query = "Select * from Account where Username = '" + username + "' and  Password = '" + password_pin
				+ "' or Pin = '" + password_pin + "'";
		ResultSet rSet = stmt.executeQuery(query);
		return (rSet.next()) ? rSet.getString("AccountNumber") : null;
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
		String query = "Select Account.AccountName from Account where AccountNumber = '" + recipientAccountNumber + "'";
		String newQuery = (bank == null) ? query : query + " and Bank = '" + bank + "'";
		ResultSet rSet = stmt.executeQuery(newQuery);
		return (rSet.next()) ? rSet.getString("AccountName") : null;
	}

	// balance after transfer
	public void updateBalance(Account account, float newBalance) throws SQLException {
		String query = "Update Account set Balance = '" + newBalance + "' where AccountNumber = '"
				+ account.accountNumber + "'";
		stmt.executeUpdate(query);
	}

	// confirm transfer
	public void confirmTransfer(Account sender, Account receiver, float amount, String content) throws SQLException {
		String query = "Insert into DetailAccount values (?,?,?,?,?,?,?,?,?,?)";
		// update balance sender
		updateBalance(sender, sender.balance - amount);
		PreparedStatement pStmtSender = connect.prepareStatement(query);
		pStmtSender.setInt(1, sender.creditCardID);
		pStmtSender.setString(2, sender.accountNumber);
		pStmtSender.setString(3, "Transfer");
		pStmtSender.setObject(4, LocalDateTime.now());
		pStmtSender.setFloat(5, amount);
		pStmtSender.setFloat(6, sender.balance - amount);
		pStmtSender.setString(7, "Java");
		pStmtSender.setString(8, receiver.accountNumber);
		pStmtSender.setString(9, receiver.accountName);
		pStmtSender.setString(10, content);
		pStmtSender.executeUpdate();
		updateBalance(receiver, receiver.balance + amount);
		PreparedStatement pStmtReceiver = connect.prepareStatement(query);
		pStmtReceiver.setInt(1, receiver.creditCardID);
		pStmtReceiver.setString(2, receiver.accountNumber);
		pStmtReceiver.setString(3, "Receive");
		pStmtReceiver.setObject(4, LocalDateTime.now());
		pStmtReceiver.setFloat(5, amount);
		pStmtReceiver.setFloat(6, receiver.balance + amount);
		pStmtReceiver.setString(7, "none");
		pStmtReceiver.setString(8, sender.accountNumber);
		pStmtReceiver.setString(9, sender.accountName);
		pStmtReceiver.setString(10, content);
		pStmtReceiver.executeUpdate();
	}

	// data transaction
	public List<DetailAccount> dataTransaction(String accountNumber) throws SQLException {
		List<DetailAccount> list = new ArrayList<DetailAccount>();
		String query = "Select * from DetailAccount where AccountNumberFirst = '" + accountNumber
				+ "' order by DayTrading DESC";
		ResultSet rSet = stmt.executeQuery(query);
		DetailAccount detailAccount = null;
		while (rSet.next()) {
			detailAccount = new DetailAccount(rSet.getInt("CreditCardID"), rSet.getInt("TradingCode"),
					rSet.getString("AccountNumberFirst"), rSet.getString("TransactionType"),
					rSet.getObject("DayTrading"), rSet.getFloat("TransactionAmount"), rSet.getFloat("Balance"),
					rSet.getString("ATM"), rSet.getString("AccountNumberSecond"), rSet.getString("AccountNameSecond"),
					rSet.getString("TransactionContent"));
			list.add(detailAccount);
		}
		return list;
	}
}
