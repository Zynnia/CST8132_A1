package test;
import amazonsystem.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
class TestCredits {

	String[] data;
    
	@Test
    @Order(1)
	public void testCreateItem() {
		System.out.println("[Test2.1: Creating items]...");
		AmazonProduct product;
		// Creating products
		data = new String[] { "1", "Prod1" };
		product = AmazonProduct.createAmazonProduct(data);
		assertNull(product);
		data = new String[] { "2", "    ", "", "     ", "Img2", "URL2", "2", "20", "2.2", "22.2" };
		product = AmazonProduct.createAmazonProduct(data);
		assertNull(product);
		data = new String[] { "3", "Prod3", "Cat3", "Subcat3", "Img3", "URL3", "3", "30", "3.3", "33.3" };
		product = AmazonProduct.createAmazonProduct(data);
		assertNotNull(product);
	}
	
	@Test
    @Order(2)    
	public void testCreateCustomer() {
		System.out.println("[Test2.2: Creating customers]...");
		AmazonCustomer customer;
		String[] data;
		// Creating users
		data = new String[] { "User1" };
		customer = AmazonCustomer.createAmazonCustomer(data);
		assertNull(customer);
		data = new String[] { "2", "", "    " };
		customer = AmazonCustomer.createAmazonCustomer(data);
		assertNull(customer);
		data = new String[] { "3", "User3", "Address" };
		customer = AmazonCustomer.createAmazonCustomer(data);
		assertNotNull(customer);
	}
	
	@Test
    @Order(3)    
	public void testPayment() {
		System.out.println("[Test2.3: Testing payments]...");
		String[] data;
		AmazonCredit credit;
		AmazonCustomer customer;
		data = new String[] {"1", "Howard", "Address"};
		customer = AmazonCustomer.createAmazonCustomer(data);
		// Payment 1
		data = new String[] {"Howard", " "};
		credit = AmazonCheck.createCheck(data);
		assertNull(credit);
		// Payment 2
		data = new String[] {"100.0"};
		credit = AmazonCash.createCash(data);
		assertNotNull(credit);
		customer.addCredit(credit);
		int numPayments = customer.getNumberOfCredits();
		System.out.println("  * Number of payments from user 0: " + numPayments);
		assertEquals(1, numPayments);
	}

}
