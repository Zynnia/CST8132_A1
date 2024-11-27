package amazonsystem;

public class AmazonCash extends AmazonCredit {
	
	private AmazonCash(float amount) {
		super(amount);
		super.cash();
	}
	
	
	public static AmazonCash createCash(String[] data) {
		if (data == null) return null;
		if (data.length != 1) return null;
		
		if (!AmazonUtil.isStringEmpty(data)) {
			
			if (!AmazonUtil.isValidFloatInput(data[0])) return null;
			
			float amount = Float.parseFloat(data[0]);
			
			if (amount >= 0) return new AmazonCash(amount);
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		String val = "Type: " + super.getType() + ", value: " + super.getAmount();
		return val;
	}
}
