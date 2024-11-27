package amazonsystem;

public class AmazonCartItem {
	private AmazonProduct product;
	private int quantity;
	
	public AmazonCartItem(AmazonProduct product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}
	
	public float calcSubTotal() {
		return product.getActual_price() * quantity;
	}
	
	public AmazonProduct getProduct() {
		return product;
	}
	
	public String toString() {
		String name = product.getName();
		int n = name.length();
		String var = product.getId() +", " + name.substring(0, Math.min(20, n));
		String output = "["+  var + "]"+ ", quantity = " + quantity;
		return output;
	}
}
