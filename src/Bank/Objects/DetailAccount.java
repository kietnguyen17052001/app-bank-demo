package Bank.Objects;

import java.time.LocalDateTime;
import java.util.Date;

public class DetailAccount {
	public int creditCardID;
	public int tradingCode;
	public String accountNumberFirst;
	public String transactionType;
	public Object dayTrading;
	public float transactionAmount;
	public float balance;
	public String ATM;
	public String accountNumberSecond;
	public String accountNameSecond;
	public String transactionContent;

	public DetailAccount(int creditCardID, int tradingCode, String accountNumberFirst, String transactionType, Object dayTrading,
			float transactionAmount, float balance, String ATM, String accountNumberSecond,
			String accountNameSecond, String transactionContent) {
		this.creditCardID = creditCardID;
		this.tradingCode = tradingCode;
		this.accountNumberFirst = accountNumberFirst;
		this.transactionType = transactionType;
		this.dayTrading = dayTrading;
		this.transactionAmount = transactionAmount;
		this.balance = balance;
		this.ATM = ATM;
		this.accountNumberSecond = accountNumberSecond;
		this.accountNameSecond = accountNameSecond;
		this.transactionContent = transactionContent;
	}
}
