package amazonproducts;

public class AmazonCheck extends AmazonCredit{
	private String accountNumber;

	
	private AmazonCheck(String accountNumber, float amount) {
		super(amount);
		super.check();
		this.accountNumber = accountNumber;
	}
	
	public static AmazonCheck createCheck(String[] data) {
		//Assume acccountNumber first then data
		if (!AmazonUtil.isStringEmpty(data)) {
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
	
}
