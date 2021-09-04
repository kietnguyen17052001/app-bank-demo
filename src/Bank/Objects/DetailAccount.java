package Bank.Objects;

import java.time.LocalDateTime;

public class DetailAccount {
	public int creditCardID;
	public String transactionType;
	public LocalDateTime dayTrading;
	public float transactionAmount;
	public float balance;
	public String accountNumber;
	public String accountName;
	public String transactionContent;

	public DetailAccount(int creditCardID, String transactionType, LocalDateTime dayTrading, float transactionAmount,
			float balance, String accountNumber, String accountName, String transactionContent) {
		this.creditCardID = creditCardID;
		this.transactionType = transactionType;
		this.dayTrading = LocalDateTime.now();
		this.transactionAmount = transactionAmount;
		this.balance = balance;
		this.accountNumber = accountNumber;
		this.accountName = accountName;
		this.transactionContent = transactionContent;
	}
}
