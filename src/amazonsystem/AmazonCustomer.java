package amazonsystem;

import java.util.ArrayList;


public class AmazonCustomer {
	private int id;
	private String name;
	private String address;
	private ArrayList<AmazonCredit> payments = new ArrayList<AmazonCredit>();
	private ArrayList<AmazonProduct> wishList = new ArrayList<AmazonProduct>();
	private ArrayList<AmazonComment> comments = new ArrayList<AmazonComment>();
	private AmazonCart cart = new AmazonCart();
	
	private float totalCredit;
	
	private AmazonCustomer(int id, String name, String address) {
		this.id = id;
		this.name = name;
		this.address = address;
	}
	
	public static AmazonCustomer createAmazonCustomer(String[] data) {
		if (data.length != 3) return null;
		
		
		if (!AmazonUtil.isStringEmpty(data)) {
			if (!AmazonUtil.isValidInt(data[0])) return null;
			
			int customerID = Integer.parseInt(data[0]);
			AmazonCustomer customer =  new AmazonCustomer(customerID, data[1], data[2]);
			return customer;
		}
		
		return null;
	}
	

	public void addCredit(AmazonCredit credit) {
		payments.add(credit);
	}
	
	public void showCredit() {
		
		if (payments.size() == 0) {
			System.out.println("Nothing to print! No payments on record.");
		}
		
		for (int i  = 0; i < payments.size(); ++i) {
			String form = "Credit[" + i + "]: ";
			System.out.println(form + payments.get(i).toString());
		}
	}
	
	public void addProductInWishList(AmazonProduct e) {
		wishList.add(e);
	}
	//FIXED THIS
	public void removeProductFromWishList(int productID) {
		if (wishList.isEmpty()) return;
		
		int valToRemove = -1;
		
		for (int i = 0; i < wishList.size(); ++i) {
			if (wishList.get(i).getId() == productID) {
				valToRemove = i;
			}
		}
		if (valToRemove != -1) {
			wishList.remove(valToRemove);
		} else {
			System.out.println("Product does not exist in the Wish List!");
		}
		
	}
	
	public boolean isProductInWishList(AmazonProduct e) {
		for (AmazonProduct amazon: wishList) {
			if (amazon.getId() == e.getId()) {
				return true;
			}
		}
		return false;
	}
	
	public void showWishList() {
		
		for (int i = 0; i < wishList.size(); ++i) {
			String val = "Item[" + i + "]=";
			System.out.println(val + "[" + wishList.get(i).toString() + "]");
		}
	}
	
	public void addItemInCart(AmazonCartItem e) {
		cart.addItem(e);
	}
	
	public void removeProductFromCart(AmazonProduct e) {
		cart.removeItem(e);
	}
	
	public void showCart() {
		cart.getCartDetails();
	}
	
	public void pay(AmazonCredit p) {
		if (cart.getSize() == 0) {
			System.out.println("Cart is empty!");
			return;
		}
		if (cart.pay(p.getAmount())) {
			float diff = p.getAmount() - cart.calcSubTotal();
			p.setAmount(diff);
			System.out.println(p.update());
			moveFromCartToComments();
			System.out.println("Cart empty - you can comment products now");
			
		} else {
			System.out.println("You broke.");
		}
	}
	
	public void moveFromCartToComments() {
		for (AmazonCartItem it : cart.getCart()) {
			AmazonComment comm = new AmazonComment(it.getProduct());
			addComment(comm);
		}
		cart.emptyList();
	}
	
	//Need to call this
	public int productToCommentsID(String productID) { 
		if (comments.size() == 0) {
			return -1;
		}
		int val = Integer.parseInt(productID);
		int i = 0;
		for (AmazonComment c : comments) {
			if (c.getProduct().getId() == val) {
				return i;
			}
			i++;
		}
		return -1;
	}
	
	public void addComment(AmazonComment comment) {
		comments.add(comment);
	}
	
	//public void setComment() {}
	
	public void showComments() {
		for (AmazonComment c: comments) {
			System.out.println(c.toString());
		}
	}
	
	public String toString() { 
		String val = "Customer: - Customer: [Id: " + id + "], [Name: "+ name + "], [Address: "+ address + "]";
		return val;
				
	}
	
	public int getID() {
		return id;
	}

	public float getTotalCredit() {
		return totalCredit;
	}

	public void setTotalCredit(float totalCredit) {
		this.totalCredit = totalCredit;
	}

	public AmazonCart getCart() {
		return cart;
	}

	public void setCart(AmazonCart cart) {
		this.cart = cart;
	}
	public String getName() {
		return name;
	}
	
	public int getSize() {
		return payments.size();
	}
	public AmazonCredit payment(int idx) {
		return payments.get(idx);
	}
	public AmazonComment getComments(int idx) {
		return comments.get(idx);
	}
	public int getCartSize() {
		return cart.getSize();
	}
	public int getWishlistSize() {
		return wishList.size();
	}
	public int getNumberOfCredits() {
		return payments.size();
	}
	public int getNumberOfComments() {
		return comments.size();
	}

}
