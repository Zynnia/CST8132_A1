package test;
import amazonsystem.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.MethodName.class)
class TestComments {

	/**
	 * Create products
	 */
	@Test
	@Order(1)
	public void test1CreateProducts() {
		// Creating products
		AmazonProduct ap;
		String[] data;
		System.out.println("[Test4.1: Create products]...");
		data = new String[] {};
		ap = AmazonProduct.createAmazonProduct(data);
		assertNull(ap);
		data = new String[] { "Wrong data", "0" };
		ap = AmazonProduct.createAmazonProduct(data);
		assertNull(ap);
		data = new String[] { "1", "Prod1", "Cat1", "Subcat1", "Img1", "URL1", "1", "10", "1.1", "10.1" };
		ap = AmazonProduct.createAmazonProduct(data);
		assertNotNull(ap);
	}
	
	/**
	 * Create users
	 */
	@Test
	@Order(2)
	public void test2CreateConsumers() {
		// Creating users
		System.out.println("[Test4.2: Create consumer]...");
		AmazonCustomer customer;
		String[] data;
		data = new String[] { };
		customer = AmazonCustomer.createAmazonCustomer(data);
		assertNull(customer);
		data = new String[] { "Invalid user" };
		customer = AmazonCustomer.createAmazonCustomer(data);
		assertNull(customer);
		data = new String[] { "1", "User1" , "Road A"};
		customer = AmazonCustomer.createAmazonCustomer(data);
		assertNotNull(customer);
	}

	/**
	 * Create wish list
	 */
	@Test
	@Order(3)
	public void test3CreateCommentList() {
		System.out.println("[Test4.3: Create comment...]");
		AmazonProduct product;
		AmazonCustomer customer;
		AmazonComment comment;
		List<AmazonProduct> productList = new ArrayList<AmazonProduct>();
		List<AmazonCustomer> consumerList = new ArrayList<AmazonCustomer>();
		String[] data;
		// Creating products
		data = new String[] { "1", "Prod1", "Cat1", "Subcat1", "Img1", "URL1", "1", "10", "1.1", "11.1" };
		product = AmazonProduct.createAmazonProduct(data);
		productList.add(product);
		data = new String[] { "2", "Prod2", "Cat2", "Subcat2", "Img2", "URL2", "2", "20", "2.2", "22.2" };
		product = AmazonProduct.createAmazonProduct(data);
		productList.add(product);
		data = new String[] { "3", "Prod3", "Cat3", "Subcat3", "Img3", "URL3", "3", "30", "3.3", "33.3" };
		product = AmazonProduct.createAmazonProduct(data);
		productList.add(product);
		// Creating users
		data = new String[] { "1", "User1", "Address 1" };
		customer = AmazonCustomer.createAmazonCustomer(data);
		consumerList.add(customer);
		data = new String[] { "2", "User2", "Address 2"  };
		customer = AmazonCustomer.createAmazonCustomer(data);
		consumerList.add(customer);
		data = new String[] { "3", "User3", "Address 3"  };
		customer = AmazonCustomer.createAmazonCustomer(data);
		consumerList.add(customer);
		// Comments from user 0
		customer = consumerList.get(0);
		product = productList.get(0);
		comment = new AmazonComment(product);
		comment.setComment("Nice product");
		comment.setRating(5.0f);
		assertNotNull(comment);
		customer.addComment(comment);
		product = productList.get(1);
		comment = new AmazonComment(product);
		comment.setComment("Poor product");
		comment.setRating(1.0f);
		customer.addComment(comment);
		System.out.println("  * Number of comments from user 0: " + customer.getNumberOfComments());
		assertEquals(2, customer.getNumberOfComments());
		

	}

}
