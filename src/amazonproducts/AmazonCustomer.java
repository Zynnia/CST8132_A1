package amazonproducts;

import java.util.ArrayList;
import java.util.Date;


public class AmazonCustomer {
	private int id;
	private String name;
	private String address;
	private ArrayList<AmazonCredit> payments = new ArrayList<AmazonCredit>();
	private ArrayList<AmazonProduct> wishList = new ArrayList<AmazonProduct>();
	private ArrayList<AmazonComment> comments = new ArrayList<AmazonComment>();
	private AmazonCart cart;
	
	private float totalCredit;
	
	private AmazonCustomer(int id, String name, String address) {
		this.id = id;
		this.name = name;
		this.address = address;
	}
	
	public static AmazonCustomer createAmazonCustomer(String[] data) {
		
		if (!AmazonUtil.isStringEmpty(data)) {
			int customerID = Integer.parseInt(data[0]);
			/**
			 * Verify if the customerID has been used before
			 */
			
			AmazonCustomer customer =  new AmazonCustomer(customerID, data[1], data[2]);
			return customer;
		
		}
		
		return null;
	}
	

	public void addCredit(AmazonCredit credit) {
		payments.add(credit);
	}
	
	public void showCredit() {
		
		for (int i  = 0; i < payments.size(); ++i) {
			String form = "Credit[" + i + "]: ";
			System.out.println(form + payments.get(i).toString());
		}
	}
	
	public void addProductInWishList(AmazonProduct e) {
		wishList.add(e);
	}
	
	public void removeProductFromWishList(int idx) {
		if (wishList.isEmpty()) return;
		
		if (idx >= wishList.size() || idx < 0) return;
		wishList.remove(idx - 1);
	}
	
	public boolean isProductInWishList(AmazonProduct e) {
		for (AmazonProduct amazon: wishList) {
			if (amazon.toString().equals(e.toString())) {
				return true;
			}
		}
		return false;
	}
	
	public void showWishList() {
		
		for (int i = 0; i < wishList.size(); ++i) {
			String val = "Item[" + i + "]=";
			System.out.println(val + wishList.get(i).toString());
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
		
		if (cart.pay(p.getAmount())) {
			moveFromCartToComments();
			float diff = p.getAmount() - cart.calcSubTotal();
			p.setAmount(diff);
			System.out.println(p.update());
			System.out.println("Cart empty - you can comment products now");
		} else {
			System.out.println("You broke.");
		}
	}
	
	public void moveFromCartToComments() {
		for (AmazonCartItem it : cart.getCart()) {
			AmazonComment comm = new AmazonComment(it.getProduct());
			comments.add(comm);
		}
		cart.emptyList();
	}
	
	public boolean hasProductToComments() { return false;}
	
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
		return payments.size() - 1;
	}
	public AmazonCredit payment(int idx) {
		return payments.get(idx);
	}
	
}
