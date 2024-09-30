package amazonproducts;


public class AmazonProduct {
	
	private String[] title;
	private String name;
	private String imageURL;
	private String link;
	private AmazonProductCategory category;
	private AmazonProductSubCategory subCategory;
	private int id;
	private int nRatings;
	private float rating;
	private float discountPrice;
	private float actualPrice;
	
	
	public AmazonProduct(int id, String name, AmazonProductCategory category,
			AmazonProductSubCategory subCategory, String imageURL, String link,
			float rating, int nRatings, float discountPrice, float actualPrice) {
		
		this.id = id;
		this.name = name;
		this.category = category;
		this.subCategory = subCategory;
		this.imageURL = imageURL;
		this.link = link;
		this.rating = rating;
		this.nRatings = nRatings;
		this.discountPrice = discountPrice;
		this.actualPrice = actualPrice;
		
	}
	
	
	/**
	 * Constructor receives arrays of string
	 * Constructs amazon products based on the data receives
	 * @param items : Array of product properties
	 */
	public AmazonProduct(String[] items) {
		
		id = Integer.parseInt(items[0]);
		
		name = items[1];
		category = new AmazonProductCategory(items[2]);
		subCategory = new AmazonProductSubCategory(items[3], category);
		imageURL = items[4];
		link = items[5];
		rating = Float.parseFloat(items[6].replace(",", ""));
		nRatings = Integer.parseInt(items[7].replace(",", ""));
		discountPrice = AmazonProductUtil.convertToFloat(items[8]);
	    actualPrice = AmazonProductUtil.convertToFloat(items[9]);
			
	}
	
	public float getActual_price() {
		return actualPrice;
	}
	
	public float getDiscount_price() {
		return discountPrice;
	}
	
	public int getId() {
		return id;
	}
	
	public String getLink() {
		return link;
	}
	
	public AmazonProductCategory getMain_category() {
		return category;
	}
	
	public String getName() {
		return name;
	}
	
	public int getNo_of_rating() {
		return nRatings;
	}
	
	public float getRatings() {
		return rating;
	}
	
	public AmazonProductSubCategory getSub_category() {
		return subCategory;
	}
	
	public String[] getTitle() {
		return title;
	}
	
	public String getUrlImage() {
		return imageURL;
	}
	
	public void setTitle(String[] title) {
		this.title = title;
	}
	
	public void setActual_price(float actualPrice) {
		 this.actualPrice = actualPrice;
	}
	
	public void setDiscount_price(float discountPrice) {
		this.discountPrice = discountPrice;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public void setMain_category(AmazonProductCategory category) {
		this.category = category;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setNo_of_rating(int nRatings) {
		this.nRatings = nRatings;
	}
	
	public void setRatings(float rating) {
		this.rating = rating;
	}
	
	public void setSub_category(AmazonProductSubCategory subCategory) {
		this.subCategory = subCategory;
	}
	
	public void setUrlImage(String imageURL) {
		this.imageURL = imageURL;
	}
	//Place holder for now
	public String toString() {
		
		String str = "["
				+ id + ", " 
				+ name + ", " 
				+ category.getCategoryName() + ", "
				+ subCategory.getSubCategory() + ", "
				+ imageURL + ", "
				+ rating + ", " 
				+ nRatings + ", " 
				+ discountPrice + ", "
				+ actualPrice + "]"; 
	
		return str;
	}
}
