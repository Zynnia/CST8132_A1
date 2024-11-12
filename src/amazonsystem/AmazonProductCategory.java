package amazonsystem;

public class AmazonProductCategory {
	private String categoryName;
	
	public AmazonProductCategory(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryName() {
		return categoryName;
	}
}
