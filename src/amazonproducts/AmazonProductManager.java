package amazonproducts;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class AmazonProductManager {
	
	public static Scanner input = new Scanner(System.in);
	private AmazonProductList productList = new AmazonProductList();
	private ArrayList<AmazonCustomer> customer = new ArrayList<AmazonCustomer>();
	
	
	public AmazonProductManager() {}
	
	/**
	 * Prompt user to enter a file to open.
	 * Load the file into AmazonProductList
	 * @throws AmazonProductException 
	 */
	public void createProductList() throws AmazonProductException {
		
		System.out.print("Name of file to create Productlist: ");
		//String path  = input.nextLine();
		//Testing purposes.
		String path = "Sample-Amazon-Products-v2.csv";
		//path = "Amazon-Products.csv";
		productList.createList(path);
		
		System.out.println("Product List created successfully!");
	}
	
	/**
	 * Prints the current entries in the product list
	 */
	public void displayProductList() {
		productList.printList();
	}
	
	/**
	 *  Method to add products manually
	 *  ask user a series of prompt
	 * @throws AmazonProductException
	 */
	public void addProduct() throws AmazonProductException {
		
		String[] prompts = {
				"Enter product id: ",
				"Enter product name: ",
				"Enter product category: ",
				"Enter product subCategory: ",
				"Enter product imageURL: ",
				"Enter product link: ",
				"Enter product rating: ",
				"Enter product number ratings: ",
				"Enter product discount price: ",
				"Enter product actual price: " 
				};
		
		//Array to store user responses
		String[] usrInput = new String[10];
		
		//Prompt user
		for (int i = 0; i < prompts.length; ++i) {
			System.out.print(prompts[i]);
			usrInput[i] = input.nextLine();
		}
		
		/*
		 * Here we are verifying if the data is valid. 
		 * If not throw Amazon product exception
		 */
		for (int i = 0; i < prompts.length; ++i) {
			//check if the input is empty
			if (usrInput[i].isEmpty()) {
				throw new AmazonProductException("Invalid Data!");
			}
			try {
				boolean valid = false;
				//verify if the numerical inputs are valid
				if (i == 0 || i == 7) {
					valid = Integer.parseInt(usrInput[i]) < 0;
				}
				if (i == 6 || i == 8 || i == 9) {
					valid = Float.parseFloat(usrInput[i]) < 0;
				}
				if (valid) {
					throw new AmazonProductException("Invalid Data!");
				}
			} catch (NumberFormatException e) {
				throw new AmazonProductException("Invalid Data!");
			}
		}
		
		//Assign the input to the correct fields
		int id = Integer.parseInt(usrInput[0]);
		String name = usrInput[1];
		AmazonProductCategory category = new AmazonProductCategory(usrInput[2]);
		AmazonProductSubCategory subCategory = new AmazonProductSubCategory(usrInput[3], category);
		String imageURL = usrInput[4];
		String link = usrInput[5];
		float rating = Float.parseFloat(usrInput[6]);
		int nRatings = Integer.parseInt(usrInput[7]);		
		float discountPrice = Float.parseFloat(usrInput[8]);
		float actualPrice = Float.parseFloat(usrInput[9]);
		
		//Make a new amazon product with the given field
		AmazonProduct product = new AmazonProduct(id, name, category,
				subCategory, imageURL, link, rating, nRatings, discountPrice,
				actualPrice);
		
		
		productList.add(product);
	}
	
	
	public void editProduct() throws AmazonProductException {
		
		System.out.print("Enter product index to edit: ");
		String idx = input.nextLine();
		int val = -1;
		try {
			val = Integer.parseInt(idx);
			AmazonProduct product = productList.findProductByIndex(val);
			productList.edit(val, product);
		} catch (AmazonProductException m) {
			throw m;
		} catch (NumberFormatException e) {
			throw new AmazonProductException("Invalid index");
		}
		
	}
	
	/**
	 * Prompts user for index position to delete
	 * 
	 * @throws AmazonProductException Invalid input
	 */
	public void deleteProduct() throws AmazonProductException {
		
		System.out.print("Enter a position to delete: ");
		
		try {
			String usrInput = input.nextLine();
			
			int pos = Integer.parseInt(usrInput);
			productList.delete(pos);
		} catch (AmazonProductException m) {
			throw m;
		} catch (NumberFormatException e) {
			throw new AmazonProductException("Invalid input");
		}
		System.out.println("Deletion Successful");
		
	}
	
	/**
	 * Write the bestseller entries to an csv file
	 * @throws AmazonProductException
	 */
	public void saveProductList() throws AmazonProductException {
		System.out.print("Enter filename to save: ");
		String usrInput = input.nextLine();
		productList.saveList(usrInput);
		
		System.out.println("File Written Successfully!");
	}
	
	/**
	 * Prompt user for term to search
	 * @throws AmazonProductException
	 */
	public void search() throws AmazonProductException {
		System.out.print("Enter term to search: ");
		String usrInput = input.nextLine();
		productList.search(usrInput);
	}
	
	public void exit() {
		System.out.println("===========================================================================");
		System.out.println("||    [End of Application (Author: Brian Huynh Student number here]      ||");
		System.out.println("===========================================================================");
		
		input.close();
		System.exit(0);
	}
	
	public void manageProductList() {
		
		
		String usrInput;
		showMenu();
		while (true) {
			
			char option = ' ';
			System.out.print("Choose an option: ");
			try {
				usrInput = input.nextLine();
				
				if (usrInput.isEmpty() || usrInput.length() > 1) {
					throw new AmazonProductException("Invalid entry: enter a character between A and Q");
				}
				
				option = usrInput.charAt(0);
				
				
				switch (option) {
				case 'A':
					createProductList();
					break;
				case 'B':
					displayProductList();
					break;
				case 'C':
					search();
					break;
				case 'D':
					addCustomer();
					break;
				case 'E':
					showCustomers();
					break;
				case 'F':
					addCreditToCustomer();
					break;
				case 'G':
					showCreditFromCustomer();
					break;
				case 'H':
					addProductInWishList();
					break;
				case 'I':
					removeProductFromWishList();
					break;
				case 'J':
					showWishList();
					break;
				case 'K':
					addProductInCart();
					break;
				case 'L':
					removeProductFromCart();
					break;
				case 'M':
					showProductsInCart();
					break;
				case 'N':
					payCart();
					break;
				
				case 'O':
					addCommentToProduct();
					break;
				case 'P':
					showComments();
					break;
				case 'Q':
					exit();
					break;	
				default:
					throw new AmazonProductException("Invalid entry: enter a char between A and Q");
				}
			} catch (AmazonProductException m) {
				System.err.println("AmazonProductException: " + m.getMessage());
			} 
		}
		
	}
	/**
	 * Display the available commands the user can do
	 */
	public void showMenu() {
		
		String[] menu = {
		        "===========================================================================",
				"||    ****    ****           ****    ****   *****      ALGONQUIN COLLEGE ||",
				"||   **  **  **       **    **  **  **  **  **  **   COURSE: OOP/CST8132 ||",
				"||   ******  **       **    **  **  **  **  *****            PROF: PAULO ||",
				"||   **  **   ****           ****    ****   **         TERM: FALL / 2024 ||",
				"===========================================================================",
				"||                        [Menu A2 - Amazon Manager]                     ||",
				"===========================================================================",
				"||                                  || USER                              ||",
				"||                                  || Credit options .................. ||",
				"|| ADMIN                            || [F] Add credit to customer        ||",
				"||                                  || [G] Show credits from customer    ||",
				"|| Product options ................ || Wishlist options ................ ||",
				"|| [A] Load product list            || [H] Add product in wishlist       ||",
				"|| [B] Show product list            || [I] Remove product from wishlist  ||",
				"|| [C] Search products              || [J] Show products from wishlist   ||",
				"||                                  || Cart options .................... ||",
				"|| Customer options ............... || [K] Add product in cart           ||",
				"|| [D] Add customer                 || [L] Remove product from cart      ||",
				"|| [E] Show customers               || [M] Show products from cart       ||",
				"||                                  || [N] Buy products from cart        ||",
				"||                                  || Comment options ................. ||",
				"|| ................................ || [O] Comment products bought       ||",
				"||            [Q] Exit              || [P] List comments from products   ||",
				"==========================================================================="
		};
		//Print menu entries
		for (String menuItem: menu) {
			System.out.println(menuItem);
		}
	}
	
	/**
	 * 
	 * 
	 * 
	 * ASSIGNMENT 2 STARTS HERE
	 * 
	 * 
	 * 
	 */
	public void addCustomer() {
		
		String id = promptCustomerID();
		System.out.print("Enter the Customer name: ");
		String name = input.nextLine();
		System.out.print("Enter the Customer address: ");
		String address = input.nextLine();
		
		if (!AmazonUtil.isValidInt(id)) {
			System.out.println("Error No ID given");
			return;
		}
		
		if (Integer.parseInt(id) < 0) {
			System.out.println("Error Customer ID must be positive!");
			return;
		}
		
		int idx = findCustomerById(Integer.parseInt(id));
		
		if (idx >= 0) {
			System.out.println("Customer ID already exist!");
		} else {
			String[] data = new String[] {id, name, address};
			AmazonCustomer cust = AmazonCustomer.createAmazonCustomer(data);
			if (cust != null) {
				customer.add(cust);
				cust.setCart(new AmazonCart(cust, new Date()));
			} else {
				System.out.println("Error Failed to create Customer!");
			}
		}
	}
	
	public void showCustomers() {
		for (AmazonCustomer cust: customer) {
			System.out.println(cust.toString());
		}
	}
	
	
	public void addCreditToCustomer() {
	/*
	 * Add the error checking later	
	 */
		
		
		String id = promptCustomerID();
		System.out.print("Enter the type of credit ([1]: Cash, [2]: Check, [3]: Card): ");
		String type = input.nextLine();
		System.out.print("Enter Cash value: ");
		String cashValue = input.nextLine();
		AmazonCredit cred = null;
		
		if (type.equals("1")) {
			String[] data = new String[] {cashValue};
			cred = AmazonCash.createCash(data);
		} 
		
		if (type.equals("2")) {
			String accountNumber = "";
			System.out.print("Enter the account number: ");
			accountNumber = input.nextLine();
			String[] data = new String[] {accountNumber, cashValue};
			cred = AmazonCheck.createCheck(data);
		}
		
		if (type.equals("3")) {
			String number = "";
			String expiration = "";
			System.out.print("Enter card number: ");
			number = input.nextLine();
			System.out.print("Enter expiration date");
			expiration = input.nextLine();
			
			String[] data = new String[] {number, expiration, cashValue};
			cred = AmazonCard.createCredit(data);
		}
		
		
		int idx = findCustomerById(Integer.parseInt(id));
		
		if (idx >= 0) {
			customer.get(idx).addCredit(cred);
			System.out.println("Result: Credit added with success!");
		}
		else System.out.println("Result: Credit added with failure!");
		
	}
	
	public void showCreditFromCustomer() {
		
		String id  = promptCustomerID();
		int idx = findCustomerById(Integer.parseInt(id));
		
		if (idx >= 0) {
			customer.get(idx).showCredit();
		} else {
			System.out.println("Customer does not exist!");
		}
		
		
	}
	
	public void addProductInWishList() throws AmazonProductException {
		
		String id = promptCustomerID();
		System.out.print("Enter the product ID to include in the Wishlist: ");
		String productID = input.nextLine();
		AmazonProduct product = productList.findProductByID(Integer.parseInt(productID));
		//Error for non valid idx
		int idx = findCustomerById(Integer.parseInt(id));
		customer.get(idx).addProductInWishList(product);
	}
	
	public void removeProductFromWishList() {
		
		String id = promptCustomerID();
		System.out.print("Enter the Product ID to remove in the Wishlist: ");
		String productID = input.nextLine();
		
		int idx = findCustomerById(Integer.parseInt(id));
		
		if (idx != -1) {
			customer.get(idx).removeProductFromWishList(Integer.parseInt(productID));
		}
		
		
	}
	
	public void showWishList() {
		String id = promptCustomerID();
		
		if (!AmazonUtil.isValidInt(id)) {
			System.out.println("Invalid ID entered! ");
		} else {
			int idx = findCustomerById(Integer.parseInt(id));
			
			if (idx >= 0) {
				customer.get(idx).showWishList();
			} else {
				System.out.println("Customer does not exist!");
			}
		}
	}
	/*
	 * SKIP THE CART STUFF FOR NOW
	 */
	public void addProductInCart() throws AmazonProductException {
		
		String id = promptCustomerID();
		System.out.print("Enter the Product ID to buy from you cart: ");
		String productID = input.nextLine();
		System.out.print("Enter the number of items to put in cart: ");
		String quantity = input.nextLine();
		
		//throw here for null object
		AmazonProduct prod = productList.findProductByID(Integer.parseInt(productID));
		AmazonCartItem item = new AmazonCartItem(prod, Integer.parseInt(quantity));
		
		int idx = findCustomerById(Integer.parseInt(id));
		if (idx >= 0) {
			customer.get(idx).addItemInCart(item);
		
			System.out.printf("Cart updated: [ %s of %s added for customer %s ] %n", quantity, productID, id);
		}
	}
	
	public void removeProductFromCart() {
		String id = promptCustomerID();
		System.out.print("Enter the product ID");
		String productID = input.nextLine();
		int idx = findCustomerById(Integer.parseInt(id));
		if (idx >= 0) {
			AmazonProduct prod = productList.findProductByID(Integer.parseInt(productID)); 
			if (prod != null) {
				customer.get(idx).removeProductFromCart(prod);
			}
		}
	}
	
	public void showProductsInCart() {
		String id = promptCustomerID();
		int idx = findCustomerById(Integer.parseInt(id));
		if (idx >= 0) {
			customer.get(idx).showCart();
		} else {
			System.out.println("Cannot find customer");
		}
	}
	
	public void payCart() {
		
		String id = promptCustomerID();
		int idx = findCustomerById(Integer.parseInt(id));

		AmazonCustomer cust = customer.get(idx);
		//Grab the payment size
		int n = cust.getSize() - 1;
		System.out.printf("Select the payment method [from 0 to %d]: ", n);
		String choice = input.nextLine();
		/*
		 * FIX THIS: HANDLING USER INPUT AND ERROR CHECKING FOR INVALID INPUT
		 */
		cust.pay(cust.payment(Integer.parseInt(choice)));	
	}
	
	
	/**
	 * Methods add comments to amazon products
	 * Ask for user inputs and then verify validity
	 * If valid then add to customer comment list else display error message
	 */
	public void addCommentToProduct() {
		
		String id = promptCustomerID();
		System.out.print("Enter the product ID to comment: ");
		String productId = input.nextLine();
		
		System.out.print("Enter the comment: ");
		String comment = input.nextLine();
		
		System.out.print("Enter the stars: ");
		String stars = input.nextLine();
		
		
		
		int idx = findCustomerById(Integer.parseInt(id));
		
		//Check if customer exist
		if (idx < 0) {
			System.out.printf("Cannot Find Customer with ID: %s%n", id);
		}
		int productidx = customer.get(idx).productToCommentsID(productId);
		
		//Check if product was made successfully
		if (productidx >= 0) {

			//return the comment object and set the comments and rating
			AmazonComment c = customer.get(idx).getComments(productidx);
			c.setComment(comment);
			c.setStars(Float.parseFloat(stars));
			
		} else {
			System.out.printf("Error cannot find the product with product ID: %s %n", productId);
		}
		
	}
	/**
	 * 
	 */
	public void showComments() {
		
		String customerID = promptCustomerID();
		
		int idx = findCustomerById(Integer.parseInt(customerID));
		
		if (idx >= 0) customer.get(idx).showComments();
		else System.out.println("Customer not found!");
	}
	
	/**
	 * Helper function to find the customer ID
	 * @param idx the customer ID the user input
	 * @return an integer index where the Customer can be found
	 */
	public int findCustomerById(int idx) {
		for (int i = 0; i < customer.size(); ++i) {
			if (customer.get(i).getID() == idx) {
				return i;
			}
		}
		return -1;
	}
	/**
	 * Helper Function to prompt the user for Customer ID
	 * @return a string containing the customer ID
	 */
	public String promptCustomerID() {
		System.out.print("Enter the Customer ID: ");
		String usrInput = input.nextLine();
		
		return usrInput;
	}
	//Entry point
	public static void main(String[] args) {
		AmazonProductManager manager = new AmazonProductManager();
		manager.manageProductList();		
				
	}
}
