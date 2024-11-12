package amazonsystem;

import java.util.ArrayList;
import java.util.Date;

public class AmazonCart implements AmazonPayable{
	private AmazonCustomer customer;
	private Date date; //this is date change later
	
	private ArrayList<AmazonCartItem> items = new ArrayList<AmazonCartItem>();
	private float totalValue;
	
	public AmazonCart() {}
	
	public AmazonCart(AmazonCustomer customer, Date date) {
		this.setCustomer(customer);
		this.setDate(date);
	}
	
	public float calcSubTotal() {
		float val = 0f;
		for (AmazonCartItem item : items) {
			val += item.calcSubTotal();
		}
		return val;
	}
	
	public AmazonCartItem getItem(int idx) {
		return items.get(idx);
	}
	//Implement later
	public boolean hasItem(AmazonProduct e) {
		return false;
	}

	//Implement later
	public boolean pay(float amount) {
		totalValue = calcSubTotal();
		if (amount - totalValue >= 0) {
			return true;
		}
		return false;
	}
	
	public void addItem(AmazonCartItem e) {
		items.add(e);
	}
	
	public void removeItem(AmazonProduct e) {
		if (items.size() == 0) {
			System.out.println("Cannot Remove AmazonProduct. Cart is empty!");
			return;
		}
		int i = 0;
		for (AmazonCartItem item : items) {
			if (item.getProduct().getId() == e.getId()) {
				items.remove(i);
				break;
			}
			i++;
		}
	}
	
	
	public void getCartDetails() {
		System.out.printf("[Customer: %s]%n", customer.getName());
		for (AmazonCartItem it : items) {
			System.out.println(it.toString());
		}
		
		System.out.println("* Total value: " + calcSubTotal());
	}
	
	
	public float getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(float totalValue) {
		this.totalValue = totalValue;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public AmazonCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(AmazonCustomer customer) {
		this.customer = customer;
	}
	
	public ArrayList<AmazonCartItem> getCart() {
		return items;
	}
	
	public void emptyList() {
		items.clear();
	}
	public int getSize() {
		return items.size();
	}
}
