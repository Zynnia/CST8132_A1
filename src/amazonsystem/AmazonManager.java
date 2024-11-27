package amazonsystem;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class AmazonManager {
	
	public static Scanner input = new Scanner(System.in);
	private AmazonProductList productList = new AmazonProductList();
	private ArrayList<AmazonCustomer> customer = new ArrayList<AmazonCustomer>();
	private final String DEFAULTPATH = "Sample-Amazon-Products-v2.csv";
	
	
	public AmazonManager() {}
	
	/**
	 * Prompt user to enter a file to open.
	 * Load the file into AmazonProductList
	 * @throws AmazonException 
	 */
	public void createProductList() throws AmazonException {
		
		System.out.print("Name of file to load products [empty = default file]: ");
		String path  = input.nextLine();
		//Testing purposes.
		
		//path = "Amazon-Products.csv";
		if (path.isEmpty() || path.isBlank()) path = DEFAULTPATH;
		
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
	 * @throws AmazonException
	 */
	public void addProduct() throws AmazonException {
		
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
				throw new AmazonException("Invalid Data!");
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
					throw new AmazonException("Invalid Data!");
				}
			} catch (NumberFormatException e) {
				throw new AmazonException("Invalid Data!");
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
	
	
	public void editProduct() throws AmazonException {
		
		System.out.print("Enter product index to edit: ");
		String idx = input.nextLine();
		int val = -1;
		try {
			val = Integer.parseInt(idx);
			AmazonProduct product = productList.findProductByIndex(val);
			productList.edit(val, product);
		} catch (AmazonException m) {
			throw m;
		} catch (NumberFormatException e) {
			throw new AmazonException("Invalid index");
		}
		
	}
	
	/**
	 * Prompts user for index position to delete
	 * 
	 * @throws AmazonException Invalid input
	 */
	public void deleteProduct() throws AmazonException {
		
		System.out.print("Enter a position to delete: ");
		
		try {
			String usrInput = input.nextLine();
			
			int pos = Integer.parseInt(usrInput);
			productList.delete(pos);
		} catch (AmazonException m) {
			throw m;
		} catch (NumberFormatException e) {
			throw new AmazonException("Invalid input");
		}
		System.out.println("Deletion Successful");
		
	}
	
	/**
	 * Write the bestseller entries to an csv file
	 * @throws AmazonException
	 */
	public void saveProductList() throws AmazonException {
		System.out.print("Enter filename to save: ");
		String usrInput = input.nextLine();
		productList.saveList(usrInput);
		
		System.out.println("File Written Successfully!");
	}
	
	/**
	 * Prompt user for term to search
	 * @throws AmazonException
	 */
	public void search() throws AmazonException {
		System.out.print("Enter term to search: ");
		String usrInput = input.nextLine();
		productList.search(usrInput);
	}
	
	public void exit() {
		System.out.println("===========================================================================");
		System.out.println("||    [End of Application (Author: Brian Huynh - 041165733)]             ||");
		System.out.println("===========================================================================");
		
		input.close();
		System.exit(0);
	}
	
	public void manageProductList() {
		
		
		String usrInput;
		
		while (true) {
			showMenu();
			char option = ' ';
			System.out.print("Choose an option: ");
			try {
				usrInput = input.nextLine();
				
				if (usrInput.isEmpty() || usrInput.length() > 1) {
					throw new AmazonException("Invalid entry: enter a character between A and Q");
				}
				usrInput = usrInput.toUpperCase();
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
					throw new AmazonException("Invalid entry: enter a char between A and Q");
				}
			} catch (AmazonException m) {
				System.err.println("AmazonException: " + m.getMessage());
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
	public void addCustomer() throws AmazonException {
		
		String id = promptCustomerID();
		System.out.print("Enter the Customer name: ");
		String name = input.nextLine();
		System.out.print("Enter the Customer address: ");
		String address = input.nextLine();
		
		if (!AmazonUtil.isValidInt(id)) {
			throw new AmazonException("The Customer ID is invalid!");
		}
		
		//Check if the Customer id is unique
		int idx = findCustomerById(Integer.parseInt(id));
		
		if (idx >= 0) {
			throw new AmazonException("Customer ID already exist!");
		} else {
			String[] data = new String[] {id, name, address};
			AmazonCustomer cust = AmazonCustomer.createAmazonCustomer(data);
			if (cust != null) {
				customer.add(cust);
				cust.setCart(new AmazonCart(cust, new Date()));
				System.out.println("Result: Customer added with success!");
			} else {
				throw new AmazonException("Failed to create Customer!");
			}
		}
	}
	
	public void showCustomers() {
		System.out.println("[Printing customers ...]");
		for (AmazonCustomer cust: customer) {
			System.out.println(cust.toString());
		}
	}
	
	
	public void addCreditToCustomer() throws AmazonException {
	/*
	 * Add the error checking later	
	 */
		if (customer.size() == 0) {
			throw new AmazonException("Cannot add credit to Customer. No Customers");
		} 
		
		String id = promptCustomerID();
		
		if (!AmazonUtil.isValidInt(id)) {
			throw new AmazonException("The Customer ID is invalid!");
		}
		int idx = findCustomerById(Integer.parseInt(id));
		
		if (idx < 0) throw new AmazonException("Customer does not exist!");
		
		System.out.print("Enter the type of credit ([1]: Cash, [2]: Check, [3]: Card): ");
		String type = input.nextLine();
		
		String cashValue = ""; 
		AmazonCredit cred = null;
		
		if (type.equals("1")) {
			System.out.print("Enter Cash value: ");
			cashValue = input.nextLine();
			String[] data = new String[] {cashValue};
			cred = AmazonCash.createCash(data);
		} 
		
		if (type.equals("2")) {
			String accountNumber = "";
			System.out.print("Enter the account number: ");
			accountNumber = input.nextLine();
			System.out.print("Enter Cash value: ");
			cashValue = input.nextLine();
			String[] data = new String[] {accountNumber, cashValue};
			cred = AmazonCheck.createCheck(data);
		}
		
		if (type.equals("3")) {
			String number = "";
			String expiration = "";
			System.out.print("Enter card number: ");
			number = input.nextLine();
			System.out.print("Enter expiration date: ");
			expiration = input.nextLine();
			System.out.print("Enter Cash value: ");
			cashValue = input.nextLine();
			String[] data = new String[] {number, expiration, cashValue};
			cred = AmazonCard.createCredit(data);
		}
		
		if (cred != null) {
			customer.get(idx).addCredit(cred);
			System.out.println("Result: Credit added with success!");
		}
		else throw new AmazonException("Result: Failed to add Credit!");
		
	}
	
	public void showCreditFromCustomer() throws AmazonException {
		
		String id = promptCustomerID();
		
		if (!AmazonUtil.isValidInt(id)) throw new AmazonException("Invalid Customer ID");
		
		int idx = findCustomerById(Integer.parseInt(id));
		
		if (idx >= 0) {
			customer.get(idx).showCredit();
		} else {
			throw new AmazonException("Customer does not exist!");
		}
			
	}
	
	public void addProductInWishList() throws AmazonException {
		if (customer.size() == 0) {
			throw new AmazonException("Cannot add to Wish List. No Customers");
		} 
		if (productList.getSize() == 0) {
			throw new AmazonException("Cannot add to Wish List. Product List is empty");
		}
		String id = promptCustomerID();
		int idx = -1;
		
		if (AmazonUtil.isValidInt(id)) {
			idx = findCustomerById(Integer.parseInt(id));
		} else {
			throw new AmazonException("Invalid ID");
		}
		
		if (idx < 0) throw new AmazonException("Customer does not exist!");
		
		System.out.print("Enter the product ID to include in the Wishlist: ");
		String productID = input.nextLine();
		try {
			AmazonProduct product = productList.findProductByID(Integer.parseInt(productID));
			if (product == null) {
				String errMessage = String.format("Cannot find Product with productID: %s", productID);
				throw new AmazonException(errMessage);
			}
			customer.get(idx).addProductInWishList(product);
			System.out.printf("[Product %s added to customer %s wish list] %n", productID, id);
		} catch (NumberFormatException e) {
			throw new AmazonException("Invalid Product ID");
		} catch (AmazonException m) {
			throw m;
		}
	}
	
	public void removeProductFromWishList() throws AmazonException {
		
		/**
		 * if customer exist then check if the list is empty;
		 */
		if (customer.size() == 0) throw new AmazonException("Cannot remove from wishlist. No Customer!");
		String id = promptCustomerID();
		
		if (AmazonUtil.isValidInt(id)) {
			int idx = findCustomerById(Integer.parseInt(id));
			
			if (idx < 0) throw new AmazonException("Customer does not exist!");
			
			System.out.print("Enter the Product ID to remove in the Wishlist: ");
			String productID = input.nextLine();
			
			if (AmazonUtil.isValidInt(productID)) {
				customer.get(idx).removeProductFromWishList(Integer.parseInt(productID));
				System.out.printf("[Product %s removed from customer %s wish list] %n", productID, id);
			} else {
				throw new AmazonException("Invalid Product ID");
			}
		} else {
			throw new AmazonException("Invalid Customer ID");
		}
		
	}
	
	public void showWishList() throws AmazonException {
		String id = promptCustomerID();
		
		if (!AmazonUtil.isValidInt(id)) {
			throw new AmazonException("Invalid ID entered! ");
		} else {
			int idx = findCustomerById(Integer.parseInt(id));
			
			if (idx >= 0) {
				customer.get(idx).showWishList();
			} else {
				throw new AmazonException("Customer does not exist!");
			}
		}
	}
	
	public void addProductInCart() throws AmazonException {
		
		if (customer.size() == 0) {
			throw new AmazonException("Cannot add to Cart. No Customers");
		} 
		if (productList.getSize() == 0) {
			throw new AmazonException("Cannot add to Cart. Product List is empty");
		}
		/*
		 * error checking here
		 */
		String id = promptCustomerID();
		
		//validate customer id
		if (!AmazonUtil.isValidInt(id)) {
			throw new AmazonException("Invalid Customer ID");
		}
		// If id is valid see if we can find the customer by id
		int idx = findCustomerById(Integer.parseInt(id));
		if (idx < 0) throw new AmazonException("Cannot find Customer");
		
		System.out.print("Enter the Product ID to buy from you cart: ");
		String productID = input.nextLine();
		//validate product id
		if (!AmazonUtil.isValidInt(productID)) {
			throw new AmazonException("Invalid Product ID");
		}
		AmazonProduct prod = productList.findProductByID(Integer.parseInt(productID));
		
		// Check if we can find the product by id if the id enter is a valid int
		if (prod == null) throw new AmazonException("Cannot find product by ID");
		
		System.out.print("Enter the number of items to put in cart: ");
		String quantity = input.nextLine();
		
		//validate quantity entered
		if (!AmazonUtil.isValidInt(quantity)) {
			throw new AmazonException("Invalid quantity entered!");
		}
		
		// Should we check if the cart-item is null? 
		AmazonCartItem item = new AmazonCartItem(prod, Integer.parseInt(quantity));

		customer.get(idx).addItemInCart(item);
		
		System.out.printf("Cart updated: [ %s of %s added for customer %s ] %n", quantity, productID, id);
		 
	}
	
	public void removeProductFromCart() throws AmazonException {
		/*
		 * Check if cart is empty or not
		 */
		String id = promptCustomerID();
		
		//Is the id a valid integer?
		if (!AmazonUtil.isValidInt(id)) throw new AmazonException("Invalid Customer ID");
		
		int idx = findCustomerById(Integer.parseInt(id));
		
		//Does the customer exist?
		if (idx < 0) throw new AmazonException("Customer does not exist!");
		
		System.out.print("Enter the product ID: ");
		String productID = input.nextLine();
		
		//ID valid?
		if (!AmazonUtil.isValidInt(productID)) throw new AmazonException("Invalid product ID enter");

		
		AmazonProduct prod = productList.findProductByID(Integer.parseInt(productID)); 
		
		//Were we able to find the product?
		if (prod == null) throw new AmazonException("Cannot find Product by ID");
		customer.get(idx).removeProductFromCart(prod);
				
	}
	
	public void showProductsInCart() throws AmazonException {
		String id = promptCustomerID();
		
		if (!AmazonUtil.isValidInt(id)) throw new AmazonException("Invalid Customer ID");
		
		int idx = findCustomerById(Integer.parseInt(id));
		
		//Does the customer exist?
		if (idx < 0) throw new AmazonException("Customer does not exist!");
		
		customer.get(idx).showCart();
		
	}
	
	public void payCart() throws AmazonException {
		
		String id = promptCustomerID();
		if (!AmazonUtil.isValidInt(id)) throw new AmazonException("Invalid Customer ID");
		
		int idx = findCustomerById(Integer.parseInt(id));
		
		//Does the customer exist?
		if (idx < 0) throw new AmazonException("Customer does not exist!");
		
		AmazonCustomer cust = customer.get(idx);
		int n = cust.getSize();
		
		if (n == 0) {
			throw new AmazonException("No Payment methods available");
		}
		System.out.printf("Select the payment method [from 0 to %d]: ", (n - 1));
		String choice = input.nextLine();
		
		//Verify if the choice is a valid integer
		if (!AmazonUtil.isValidInt(choice)) throw new AmazonException("Invalid Payment Choice");
		
		//Verify if it is in range of our payment methods	
		if (Integer.parseInt(choice) >= 0 && Integer.parseInt(choice) <= n) {
			cust.pay(cust.payment(Integer.parseInt(choice)));	
		} else {
			throw new AmazonException("Invalid Payment Choice");
		}
	}	
	
	
	/**
	 * Methods add comments to amazon products
	 * Ask for user inputs and then verify validity
	 * If valid then add to customer comment list else display error message
	 */
	public void addCommentToProduct() throws AmazonException {
		
		
		String id = promptCustomerID();
		
		if (!AmazonUtil.isValidInt(id)) throw new AmazonException("Invalid Customer ID");
		int idx = findCustomerById(Integer.parseInt(id));
		
		if (idx < 0) throw new AmazonException("Customer does not exist");
		
		if (customer.get(idx).getNumberOfComments() == 0) {
			throw new AmazonException("No products to comment.");
		}
		
		
		System.out.print("Enter the product ID to comment: ");
		
		String productId = input.nextLine();
		if (!AmazonUtil.isValidInt(id)) throw new AmazonException("Invalid Product ID");
		
		System.out.print("Enter the comment: ");
		String comment = input.nextLine();
		
		System.out.print("Enter the stars: ");
		String stars = input.nextLine();
		
		if (!AmazonUtil.isValidFloat(stars)) throw new AmazonException("Invalid Rating enters");
		

		float rating = -1.f;
		
		try {
			rating = Float.parseFloat(stars);
			if (rating < 0 || rating > 5.f) throw new AmazonException("Rating must be between 0.0 and 5.0");
		} catch (NumberFormatException e) {
			throw new AmazonException("Rating must be between 0.0 and 5.0");
		} catch (AmazonException m) {
			throw m;
		}
		
		
		
		int productidx = customer.get(idx).productToCommentsID(productId);
		
		//Check if product was made successfully
		if (productidx >= 0) {

			//return the comment object and set the comments and rating
			AmazonComment c = customer.get(idx).getComments(productidx);
			c.setComment(comment);
			c.setStars(Float.parseFloat(stars));
			
		} else {
			throw new AmazonException(String.format("Error cannot find the product with product ID: %s %n", productId));
		}
		
	}

	public void showComments() throws AmazonException {
		
		String customerID = promptCustomerID();
		
		if (!AmazonUtil.isValidInt(customerID)) throw new AmazonException("Invalid Customer ID");
		
		int idx = findCustomerById(Integer.parseInt(customerID));
	
		if (idx >= 0) customer.get(idx).showComments();
		else throw new AmazonException("Customer not found!");
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
		AmazonManager manager = new AmazonManager();
		manager.manageProductList();		
				
	}
}
