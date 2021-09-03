package Bank.Objects;

import java.time.LocalDateTime;

public class DetailAccount {
	public int creditCardID;
	public String transactionType;
	public LocalDateTime dayTrading;
	public float transactionAmount;
	public float balance;
	public String ATM;
	public int tradingCreditCardID;
	public String accountName;
	public String transactionContent;

	public DetailAccount(int creditCardID, String transactionType, float transactionAmount, float balance, String ATM,
			int tradingCreditCardID, String accountName, String transactionContent) {
		this.creditCardID = creditCardID;
		this.transactionType = transactionType;
		this.dayTrading = LocalDateTime.now();
		this.transactionAmount = transactionAmount;
		this.balance = balance;
		this.ATM = ATM;
		this.tradingCreditCardID = tradingCreditCardID;
		this.accountName = accountName;
		this.transactionContent = transactionContent;
	}
}
