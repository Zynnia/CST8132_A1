package amazonsystem;

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
		if (data == null) return null;
		if (data.length != 3) return null;
		
		if (!AmazonUtil.isStringEmpty(data)) {
			
			//verify if the float value is valid
			if (!AmazonUtil.isValidFloatInput(data[2])) return null;
			
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
	
	@Override
	public String toString() {
		String line = "Type: " + super.getType() + ", Credit Number: " + number +", Expiration: " + expiration + ", value: " + super.getAmount();
		return line;
	}
}
