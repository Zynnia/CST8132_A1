package amazonproducts;

public class AmazonCash extends AmazonCredit {
	
	private AmazonCash(float amount) {
		super(amount);
		super.cash();
	}
	
	
	public static AmazonCash createCash(String[] data) {
		
		if (!AmazonUtil.isStringEmpty(data)) {
		
			float amount = Float.parseFloat(data[0]);
			
			if (amount >= 0) return new AmazonCash(amount);
		}
		
		return null;
	}
}
