package amazonsystem;

public class AmazonComment {
	private String comment;
	private AmazonProduct product;
	private float stars;
	
	public AmazonComment(AmazonProduct e) {
		this.setProduct(e);
	}
	
	public String toString() {
		String name =  product.getName();
		int len = name.length();
		String var = product.getId() + ", " + name.substring(0, Math.min(20, len));
		return String.format("- ProdId:[%s] - Comment: %s - rate: %.2f", var, comment, stars);
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public AmazonProduct getProduct() {
		return product;
	}

	public void setProduct(AmazonProduct product) {
		this.product = product;
	}

	public float getStars() {
		return stars;
	}

	public void setStars(float stars) {
		this.stars = stars;
	}
	public void setRating(float stars) {
		setStars(stars);
	}
}
