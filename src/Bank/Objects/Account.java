package Bank.Objects;

import java.util.Date;

public class Account {
	public int creditCardID;
	public String accountNumber;
	public String accountName;
	public float balance;
	public String phone;
	public String userID;
	public String userName;
	public String password;
	public String pin;
	public String bank;
	public Date dayCreate;

	public Account(int creditCardID, String accountNumber, String accountName, float balance, String phone, String userID,
			String userName, String password, String pin, String bank, Date dayCreate) {
		this.creditCardID = creditCardID;
		this.accountNumber = accountNumber;
		this.accountName = accountName;
		this.balance = balance;
		this.phone = phone;
		this.userID = userID;
		this.userName = userName;
		this.password = password;
		this.pin = pin;
		this.bank = bank;
		this.dayCreate = dayCreate;
	}
}
