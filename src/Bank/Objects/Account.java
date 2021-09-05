package Bank.Objects;

import java.util.Date;

public class Account {
	public String accountNumber;
	public String accountName;
	public float balance;
	public String phone;
	public String userID;
	public String userName;
	public String bank;
	public Date dayCreate;

	public Account(String accountNumber, String accountName, float balance, String phone, String userID,
			String userName, String bank, Date dayCreate) {
		this.accountNumber = accountNumber;
		this.accountName = accountName;
		this.balance = balance;
		this.phone = phone;
		this.userID = userID;
		this.userName = userName;
		this.bank = bank;
		this.dayCreate = dayCreate;
	}
}
