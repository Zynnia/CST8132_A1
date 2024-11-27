package amazonsystem;

public class AmazonCheck extends AmazonCredit{
	private String accountNumber;

	
	private AmazonCheck(String accountNumber, float amount) {
		super(amount);
		super.check();
		this.accountNumber = accountNumber;
	}
	
	public static AmazonCheck createCheck(String[] data) {
		if (data == null) return null;
		
		if (data.length != 2) return null;
		
		//Assume acccountNumber first then data
		if (!AmazonUtil.isStringEmpty(data)) {
			if (!AmazonUtil.isValidFloatInput(data[1])) return null;
			
			float amount = Float.parseFloat(data[1]);
			
			if (amount >= 0) return new AmazonCheck(data[0], amount);
			
		}
		
		return null;
	}
	
	
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	@Override
	public String toString() {
		String line = "Type: " + super.getType() + ", AccountNumber: " + accountNumber + ", value: " + super.getAmount();
		return line;
	}
	
}
