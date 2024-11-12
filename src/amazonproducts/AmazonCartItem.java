package amazonproducts;

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
		String val = "["+  product.toString() + "]"+ ", quantity = " + quantity;
		return val;
	}
}
