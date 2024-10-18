package amazonproducts;
import java.util.Scanner;

public class AmazonProductManager {
	
	public static Scanner input = new Scanner(System.in);
	private AmazonProductList productList = new AmazonProductList();
	
	public AmazonProductManager() {}
	
	/**
	 * Prompt user to enter a file to open.
	 * Load the file into AmazonProductList
	 * @throws AmazonProductException 
	 */
	public void createProductList() throws AmazonProductException {
		
		System.out.print("Name of file to create Productlist: ");
		String path  = input.nextLine();
		//Testing purposes.
		//path = "Sample-Amazon-Products-v2.csv";
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
		System.out.println("================================");
		System.out.println("||     [Application ended]    ||");
		System.out.println("================================");
		
		input.close();
		System.exit(0);
	}
	
	public void manageProductList() {
		
		
		String usrInput;
	
		while (true) {
			showMenu();
			int option = 0;
			System.out.print("Choose an option: ");
			try {
				usrInput = input.nextLine();
				
				if (usrInput.isEmpty()) {
					throw new AmazonProductException("Invalid entry: enter an integer between 1 and 8");
				}
				try {
					option = Integer.parseInt(usrInput);
				} catch (NumberFormatException e) {
					throw new AmazonProductException("Invalid entry: enter an integer between 1 and 8");
				}
				//throw exception if none of case match
				//ie the input is a valid number but is outside acceptable
				//range
				switch (option) {
				case 1:
					createProductList();
					break;
				case 2:
					displayProductList();
					break;
				case 3:
					addProduct();
					break;
				case 4:
					editProduct();
					break;
				case 5:
					deleteProduct();
					break;
				case 6:
					saveProductList();
					break;
				case 7:
					search();
					break;
				case 8:
					exit();
					break;	
				default:
					throw new AmazonProductException("Invalid entry: enter an integer between 1 and 8");
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
				"1. Load product list",
				"2. Show product list",
				"3. Add product",
				"4. Edit a product",
				"5. Delete a product",
				"6. Save product list",
				"7. Search in the list",
				"8. Exit"
		};
		
		System.out.println("================================");
		System.out.println("|| Menu - Amazon Products: A1 ||");
		System.out.println("================================");
		
		//Print menu entries
		for (String menuItem: menu) {
			System.out.println(menuItem);
		}
	}
	
	//Entry point
	public static void main(String[] args) {
		AmazonProductManager manager = new AmazonProductManager();
		manager.manageProductList();	
	}
}
