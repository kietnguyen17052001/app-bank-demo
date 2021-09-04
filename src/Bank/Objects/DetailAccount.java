package Bank.Objects;

import java.time.LocalDateTime;
import java.util.Date;

public class DetailAccount {
	public int creditCardID;
	public String transactionType;
	public Object dayTrading;
	public float transactionAmount;
	public float balance;
	public String accountNumber;
	public String accountName;
	public String transactionContent;

	public DetailAccount(int creditCardID, String transactionType, Object dayTrading, float transactionAmount,
			float balance, String accountNumber, String accountName, String transactionContent) {
		this.creditCardID = creditCardID;
		this.transactionType = transactionType;
		this.dayTrading = dayTrading;
		this.transactionAmount = transactionAmount;
		this.balance = balance;
		this.accountNumber = accountNumber;
		this.accountName = accountName;
		this.transactionContent = transactionContent;
	}
}
