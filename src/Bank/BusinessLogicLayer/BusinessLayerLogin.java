package Bank.BusinessLogicLayer;

import Bank.DataAccessLayer.DataAccessLogin;
import java.security.*;
import java.sql.SQLException;

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
	public boolean checkConfirm(String username, String phone, String userID, String creditCardID) throws SQLException {
		return DataAccessLogin.getInstance().checkConfirm(username, phone, userID, creditCardID);
	}

	// change password
	public void changePassword(String creditCardID, String newPassword) throws SQLException, NoSuchAlgorithmException {
		DataAccessLogin.getInstance().changePassword(creditCardID, encrypt(newPassword));
	}
	
	// account number
	public String accountNumber(String username, String password_pin) throws SQLException, NoSuchAlgorithmException {
		return DataAccessLogin.getInstance().accountNumber(username, encrypt(password_pin));
	}
	
	// balance
	public float balance(String accountNumber) throws SQLException {
		return DataAccessLogin.getInstance().balance(accountNumber);
	}
}
