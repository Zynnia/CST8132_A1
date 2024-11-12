package amazonsystem;

public abstract class AmazonCredit {
	public enum PaymentType{Cash, Check, Card};
	private float amount;
	private PaymentType type;
	
	public AmazonCredit(float amount) {
		this.amount = amount;
	}
	
	
	/*
	 * Set the Payment type
	 */
	public void cash() {
		setType(PaymentType.Cash);
	}
	public void check() {
		setType(PaymentType.Check);
	}
	public void card() {
		setType(PaymentType.Card);
	}
	
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}
	
	public String update() {
		String val = "Customer credit updated: Type: " + type + ", value:  " + amount + "].";
		return val;
	}
	public String toString() {
		String val = "Type: " + type + ", value: " + amount;
		return val;
	}
}
