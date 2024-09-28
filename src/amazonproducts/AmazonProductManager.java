package amazonproducts;
import java.util.Scanner;

public class AmazonProductManager {
	
	static Scanner input = new Scanner(System.in);
	private AmazonProductList productList = new AmazonProductList();
	
	public AmazonProductManager() {}
	
	public void exit() {}
	
	/**
	 * Prompt user to enter a file to open.
	 * Load the file into AmazonProductList
	 * @throws AmazonProductException 
	 */
	public void createProductList() throws AmazonProductException {
		
		System.out.print("Name of file to create Productlist: ");
		String path  = input.next();
		//Testing purposes.
		//path = "Sample-Amazon-Products-v2.csv";
		try {
		productList.createList(path);
		
		} catch (AmazonProductException m) {
			throw m;
		}
		System.out.println("Product List created successfully!");
	}
	
	/**
	 * Prints the current entries in the product list
	 */
	public void displayProductList() {
		productList.printList();
	}
	
	//TODO WIll do this later
	// Need to add error checking 
	public void addProduct() throws AmazonProductException {
		String name;
		String imageURL;
		String link;
		AmazonProductCategory category;
		AmazonProductSubCategory subCategory;
		int id;
		int nRatings;
		float rating;
		float discountPrice;
		float actualPrice;
		
		String usrInput = "";
		
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
		String[] ps5 = new String[10];
		
		for (int i = 0; i < prompts.length; ++i) {
			System.out.print(prompts[i]);
			ps5[i] = input.next();
		}
		AmazonProduct product = new AmazonProduct(ps5);
		
		
		productList.add(product);
	}
	
	//TODO
	public void editProduct() throws AmazonProductException {
		
	}
	
	public void deleteProduct() throws AmazonProductException {
		
		System.out.print("Enter a position to delete: ");
		
		try {
			String usrInput = input.next();
			for (int i = 0; i < usrInput.length(); ++i) {
				if (!Character.isDigit(usrInput.charAt(i))) {
					throw new AmazonProductException("Invalid Input");
				}
			}
			int pos = Integer.parseInt(usrInput);
			productList.delete(pos);
		} catch (AmazonProductException m) {
			throw m;
		}
		
	}
	
	public void saveProductList() throws AmazonProductException {}
	
	public void search() throws AmazonProductException {}
	
	
	public void manageProductList() {
		//Scanner input = new Scanner(System.in);
		
		int option = -1;
		String usrInput;
		while (option != 0) {
		showMenu();
		
		System.out.print("Choose an option: ");
		try {
			usrInput = input.next();
			
			//Check if input is a number
			for (int i = 0; i < usrInput.length(); ++i) {
				if (!Character.isDigit(usrInput.charAt(i))) {
					throw new AmazonProductException("Invalid Input");
				}
			}
			option = Integer.parseInt(usrInput);
			
			//throw exception if none of case match
			//ie the input is a valid number but is outside acceptable
			//range
			switch (option) {
			case 0:
				exit();
				break;
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
			default:
				throw new AmazonProductException("Invalid Input");
			}
		} catch (AmazonProductException m) {
			//Empty Block
		}
	}
		
	}
	/**
	 * Display the available commands the user can do
	 */
	public void showMenu() {
		
		String[] menu = {
				"0. Exit",
				"1. Load product list",
				"2. Show product list",
				"3. Add product",
				"4. Edit a product",
				"5. Delete a product",
				"6. Save product list",
				"7. Search in the list"
		};
		
		System.out.println("================================");
		System.out.println("|| Menu - Amazon Products: A1 ||");
		System.out.println("================================");
		
		//Print menu entries
		for (String menuItem: menu) {
			System.out.println(menuItem);
		}
	}
	
	public static void main(String[] args) {
		AmazonProductManager manager = new AmazonProductManager();
		manager.manageProductList();
		
		System.out.println("================================");
		System.out.println("||     [Application ended]    ||");
		System.out.println("================================");
		
		input.close();
	}
}
