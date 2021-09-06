package Bank.BusinessLogicLayer;

import Bank.DataAccessLayer.DataAccessLogin;
import Bank.Objects.Account;
import Bank.Objects.DetailAccount;

import java.security.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BusinessLayerLogin {
	private static BusinessLayerLogin _instance;

	public static BusinessLayerLogin getInstance() {
		if (_instance == null) {
			_instance = new BusinessLayerLogin();
		}
		return _instance;
	}

	private BusinessLayerLogin() {

	}

	// encrypt password and pin
	public String encrypt(String password_pin) throws NoSuchAlgorithmException {
		StringBuilder sBuilder = new StringBuilder();
		MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
		mDigest.update(password_pin.getBytes());
		byte[] toByte = mDigest.digest();
		for (byte _byte : toByte) {
			sBuilder.append(Integer.toHexString(0xFF & _byte));
		}
		return sBuilder.toString().toUpperCase();
	}

	// check username
	public boolean checkUsername(String username) throws SQLException {
		return DataAccessLogin.getInstance().checkUsername(username);
	}

	// get account if account != null when login and use for UIMain
	public Account getAccount(String username, boolean byPassword, String password_pin)
			throws SQLException, NoSuchAlgorithmException {
		return DataAccessLogin.getInstance().getAccount(username, byPassword, encrypt(password_pin));
	}

	// get account by account number
	public Account getAccountByAccountNumber(String accountNumber) throws SQLException {
		return DataAccessLogin.getInstance().getAccountByAccountNumber(accountNumber);
	}

	// check confirm - forgot password
	public String accountNumber(String username, String phone, String userID, int creditCardID) throws SQLException {
		return DataAccessLogin.getInstance().accountNumber(username, phone, userID, creditCardID);
	}

	// change password
	public void changePassword(String accountNumber, String newPassword) throws SQLException, NoSuchAlgorithmException {
		DataAccessLogin.getInstance().changePassword(accountNumber, encrypt(newPassword));
	}

	// account number
	public String accountNumber(String username, String password_pin) throws SQLException, NoSuchAlgorithmException {
		return DataAccessLogin.getInstance().accountNumber(username, encrypt(password_pin));
	}

	// list bank
	public List<String> listBank() throws SQLException {
		return DataAccessLogin.getInstance().listBank();
	}

	// get recipient account name by account number
	public String recipientAccountName(String recipientAccountNumber, String bank) throws SQLException {
		return DataAccessLogin.getInstance().recipientAccountName(recipientAccountNumber, bank);
	}

	// confirm tranfer
	public void confirmTransfer(Account sender, Account receiver, float amount, String content) throws SQLException {
		DataAccessLogin.getInstance().confirmTransfer(sender, receiver, amount, content);
	}

	// data transaction
	public Object[][] dataTransaction(String accountNumber) throws SQLException {
		List<DetailAccount> list = DataAccessLogin.getInstance().dataTransaction(accountNumber);
		Object[][] data = new Object[list.size()][5];
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String[] splitDateTime;
		for (int i = 0; i < list.size(); i++) {
			splitDateTime = sdf.format(list.get(i).dayTrading).split(" ");
			data[i][0] = splitDateTime[0];
			data[i][1] = splitDateTime[1];
			data[i][2] = (list.get(i).transactionType.equals("Transfer")) ? "-" + list.get(i).transactionAmount
					: "+" + list.get(i).transactionAmount;
			data[i][3] = list.get(i).balance;
			data[i][4] = (list.get(i).transactionType.equals("Transfer"))
					? "CHUYEN DEN " + list.get(i).accountNumberSecond + " ND " + list.get(i).transactionContent
					: "NHAN TU " + list.get(i).accountNumberSecond + " ND " + list.get(i).transactionContent;
		}
		return data;
	}
}
