package amazonproducts;

public class AmazonCard extends AmazonCredit {
	private String number;
	private String expiration;
	
	private AmazonCard(String number, String expiration, float amount) {
		
		super(amount);
		super.card();
		this.setNumber(number);
		this.setExpiration(expiration);
		
	}
	
	public static AmazonCard createCredit(String[] data) {
		if (!AmazonUtil.isStringEmpty(data)) {
			float amount = Float.parseFloat(data[2]);
			
			if (amount >= 0) return new AmazonCard(data[0], data[1], amount);
			
		}
		
		return null;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}
}
