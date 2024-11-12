package amazonsystem;

public class AmazonProductSubCategory {
	
	private String subCategoryName;
	private AmazonProductCategory category;
	
	public AmazonProductSubCategory(String subCategoryName, AmazonProductCategory category) {
		this.subCategoryName = subCategoryName;
		this.category = category;
	}
	
	public AmazonProductCategory getCategory() {
		return category;
	}
	
	public void setCategory(AmazonProductCategory category) {
		this.category = category;
	}
	
	public String getSubCategory() {
		return subCategoryName;
	}
	public void setSubCategory(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
}
