package Bank.Objects;

import java.time.LocalDateTime;
import java.util.Date;

public class DetailAccount {
	public int creditCardID;
	public int tradingCode;
	public String transactionType;
	public Object dayTrading;
	public float transactionAmount;
	public float balance;
	public String ATM;
	public int tradingCreditCardID;
	public String accountNumber;
	public String accountName;
	public String transactionContent;

	public DetailAccount(int creditCardID, int tradingCode, String transactionType, Object dayTrading,
			float transactionAmount, float balance, String ATM, int tradingCreditCardID, String accountNumber,
			String accountName, String transactionContent) {
		this.creditCardID = creditCardID;
		this.tradingCode = tradingCode;
		this.transactionType = transactionType;
		this.dayTrading = dayTrading;
		this.transactionAmount = transactionAmount;
		this.balance = balance;
		this.ATM = ATM;
		this.tradingCreditCardID = tradingCreditCardID;
		this.accountNumber = accountNumber;
		this.accountName = accountName;
		this.transactionContent = transactionContent;
	}
}
