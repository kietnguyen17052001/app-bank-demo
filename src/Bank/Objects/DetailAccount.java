package Bank.Objects;

import java.time.LocalDateTime;
import java.util.Date;

public class DetailAccount {
	public int creditCardID;
	public int tradingCode;
	public String accountNumberSender;
	public String transactionType;
	public Object dayTrading;
	public float transactionAmount;
	public float balance;
	public String ATM;
	public String accountNumberReceiver;
	public String accountNameReceiver;
	public String transactionContent;

	public DetailAccount(int creditCardID, int tradingCode, String accountNumberSender, String transactionType, Object dayTrading,
			float transactionAmount, float balance, String ATM, int tradingCreditCardID, String accountNumberReceiver,
			String accountNameReceiver, String transactionContent) {
		this.creditCardID = creditCardID;
		this.tradingCode = tradingCode;
		this.accountNumberSender = accountNumberSender;
		this.transactionType = transactionType;
		this.dayTrading = dayTrading;
		this.transactionAmount = transactionAmount;
		this.balance = balance;
		this.ATM = ATM;
		this.accountNumberReceiver = accountNumberReceiver;
		this.accountNameReceiver = accountNameReceiver;
		this.transactionContent = transactionContent;
	}
}
