package test;
import amazonsystem.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

/*
 * Extra test cases 
 * 
 * 
 */

@TestMethodOrder(OrderAnnotation.class)
class miscTest {

	String[] data;
    
	@Test
    @Order(1)
	public void testCreateItem() {
		System.out.println("[Testing Product Creation]...");
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
		
		data = new String[] { "-1", "Prod1" };
		product = AmazonProduct.createAmazonProduct(data);
		assertNull(product);
	}
	
	@Test
    @Order(2)    
	public void testCreateCustomer() {
		System.out.println("[Testing Customer Creation]...");
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
		data = new String[] { "-1", "User3", "Address" };
		customer = AmazonCustomer.createAmazonCustomer(data);
		assertNull(customer);
		data = new String[] { "pop", "User3", "Address" };
		customer = AmazonCustomer.createAmazonCustomer(data);
		assertNull(customer);
		data = new String[] { "", "User3", "Address" };
		customer = AmazonCustomer.createAmazonCustomer(data);
		assertNull(customer);
		data = new String[] {};
		customer = AmazonCustomer.createAmazonCustomer(data);
		assertNull(customer);
		customer = AmazonCustomer.createAmazonCustomer(null);
		assertNull(customer);
	}
	
	@Test
    @Order(3)    
	public void testPayment() {
		System.out.println("[Testing the Payment Classes]...");
		String[] data;
		AmazonCredit credit;
		AmazonCustomer customer;
		data = new String[] {"1", "Howard", "Address"};
		customer = AmazonCustomer.createAmazonCustomer(data);
		// Payment 1
		data = new String[] {"Howard", " "};
		credit = AmazonCheck.createCheck(data);
		assertNull(credit);
		data = new String[] {"Howard", "lmao", "1"};
		credit = AmazonCheck.createCheck(data);
		assertNull(credit);
		credit = AmazonCheck.createCheck(null);
		assertNull(credit);
		data = new String[] {"", ""};
		credit = AmazonCheck.createCheck(data);
		assertNull(credit);
		data = new String[] {"Howard", "1000"};
		credit = AmazonCheck.createCheck(data);
		assertNotNull(credit);
		
		data = new String[] {"Howard", "a"};
		credit = AmazonCheck.createCheck(data);
		assertNull(credit);
		
		customer.addCredit(credit);
		int numPayments = customer.getNumberOfCredits();
		assertEquals(1, numPayments);
		// Testing credit
		
		data = new String[] {"Howard", "lmao", "1"};
		credit = AmazonCard.createCredit(data);
		assertNotNull(credit);
		
		customer.addCredit(credit);
		numPayments = customer.getNumberOfCredits();
		assertEquals(2, numPayments);
		
		credit = AmazonCard.createCredit(null);
		assertNull(credit);
		data = new String[] {"", ""};
		credit = AmazonCard.createCredit(data);
		assertNull(credit);
		data = new String[] {"Howard","oof", "-1000"};
		credit = AmazonCard.createCredit(data);
		assertNull(credit);
		data = new String[] {"     ", "           ", "        "};
		credit = AmazonCard.createCredit(data);
		assertNull(credit);
		
		data = new String[] {"Howard","oof", "o"};
		credit = AmazonCard.createCredit(data);
		assertNull(credit);
 		
		
		//Testing cash
		data = new String[] {"100.0"};
		credit = AmazonCash.createCash(data);
		assertNotNull(credit);
		
		credit = AmazonCash.createCash(null);
		assertNull(credit);
		
		data = new String[] {"-100.0"};
		credit = AmazonCash.createCash(data);
		assertNull(credit);
		
		data = new String[] {"fifft","100.0"};
		credit = AmazonCash.createCash(data);
		assertNull(credit);
		
		data = new String[] {""};
		credit = AmazonCash.createCash(data);
		assertNull(credit);
		
		data = new String[] {"1000000.0"};
		credit = AmazonCash.createCash(data);
		assertNotNull(credit);
		customer.addCredit(credit);
		numPayments = customer.getNumberOfCredits();
		assertEquals(3, numPayments);
	}

}
