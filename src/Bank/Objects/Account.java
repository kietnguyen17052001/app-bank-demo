package Bank.Objects;

import java.util.Date;

public class Account {
	public String accountName;
	public String phone;
	public String userID;
	public String userName;
	public String bank;
	public Date dayCreate;
	public Account(String accountName, String phone, String userID, String userName, String bank, Date dayCreate) {
		this.accountName = accountName;
		this.phone = phone;
		this.userID = userID;
		this.userName = userName;
		this.bank = bank;
		this.dayCreate = dayCreate;
	}
}
