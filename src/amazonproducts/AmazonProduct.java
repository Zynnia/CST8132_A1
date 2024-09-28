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
	

	/**
	 * Constructor recieves arrays of string
	 * Constructs amazon products based on the data receives
	 * @param items : Array of product properties
	 */
	AmazonProduct(String[] items) {
		
		id = Integer.parseInt(items[0]);
		
		name = items[1];
		category = new AmazonProductCategory(items[2]);
		subCategory = new AmazonProductSubCategory(items[3]);
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
		return null;
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
		return null;
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
	
	//Place holder for now
	public String toString() {
		
		String str = "[ " 
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
