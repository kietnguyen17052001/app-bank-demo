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

	// check login
	public boolean checkLogin(String username, boolean byPassword, String password_bin)
			throws SQLException, NoSuchAlgorithmException {
		return DataAccessLogin.getInstance().checkLogin(username, byPassword, encrypt(password_bin));
	}

	// check confirm - forgot password
	public boolean checkConfirm(String username, String phone, String userID, int creditCardID) throws SQLException {
		return DataAccessLogin.getInstance().checkConfirm(username, phone, userID, creditCardID);
	}

	// get password
	public String password(String accountNumber) throws SQLException {
		return DataAccessLogin.getInstance().password(accountNumber);
	}

	// change password
	public void changePassword(int creditCardID, String newPassword) throws SQLException, NoSuchAlgorithmException {
		DataAccessLogin.getInstance().changePassword(creditCardID, encrypt(newPassword));
	}

	// account number
	public String accountNumber(String username, String password_pin) throws SQLException, NoSuchAlgorithmException {
		return DataAccessLogin.getInstance().accountNumber(username, encrypt(password_pin));
	}

	// account name
	public String accountName(String accountNumber) throws SQLException {
		return DataAccessLogin.getInstance().accountName(accountNumber);
	}

	// balance
	public float balance(String accountNumber) throws SQLException {
		return DataAccessLogin.getInstance().balance(accountNumber);
	}

	// list bank
	public List<String> listBank() throws SQLException {
		return DataAccessLogin.getInstance().listBank();
	}

	// credit card ID
	public int creditCardID(String accountNumber) throws SQLException {
		return DataAccessLogin.getInstance().creditCardID(accountNumber);
	}

	// get account
	public Account getAccount(String accountNumber) throws SQLException {
		return DataAccessLogin.getInstance().getAccount(accountNumber);
	}

	// get recipient account name by account number
	public String recipientAccountName(String recipientAccountNumber, String bank) throws SQLException {
		return DataAccessLogin.getInstance().recipientAccountName(recipientAccountNumber, bank);
	}

	// confirm tranfer
	public void confirmTransfer(String accountNumber, String recipientAccountNumber, float amount, float balance,
			String content) throws SQLException {
		DataAccessLogin.getInstance().confirmTransfer(accountNumber, recipientAccountNumber, amount, balance, content);
	}

	// data transaction
	public Object[][] dataTransaction(String accountNumber, Object[] columns) throws SQLException {
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
					? "CHUYEN DEN " + list.get(i).accountNumber + " ND " + list.get(i).transactionContent
					: "NHAN TU " + list.get(i).accountNumber + " ND " + list.get(i).transactionContent;
		}
		return data;
	}
}
