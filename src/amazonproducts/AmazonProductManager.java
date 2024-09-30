package amazonproducts;
import java.util.Scanner;

public class AmazonProductManager {
	
	public static Scanner input = new Scanner(System.in);
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
		String path  = input.nextLine();
		//Testing purposes.
		path = "Sample-Amazon-Products-v2.csv";
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
		String[] usrInput = new String[10];
		
		for (int i = 0; i < prompts.length; ++i) {
			
			System.out.print(prompts[i]);
			usrInput[i] = input.nextLine();
		}
		
		/*
		 * Here we are verifying if the data is valid. 
		 * If not throw Amazon product exception
		 */
		for (int i = 0; i < prompts.length; ++i) {
			if (usrInput[i].isEmpty()) {
				throw new AmazonProductException("Invalid Data!");
			}
			try {
				if (i == 0 || i == 7) {
					Integer.parseInt(usrInput[i]);
				}
				if (i == 6 || i == 8 || i == 9) {
					Float.parseFloat(usrInput[i]);
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
	
	public void deleteProduct() throws AmazonProductException {
		
		System.out.print("Enter a position to delete: ");
		
		try {
			String usrInput = input.nextLine();
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
	
	//TODO
	public void saveProductList() throws AmazonProductException {}
	
	public void search() throws AmazonProductException {
		System.out.print("What to search: ");
		String p = input.nextLine();
		productList.search(p);
	}
	
	
	public void manageProductList() {
		//Scanner input = new Scanner(System.in);
		
		int option = -1;
		String usrInput;
		while (option != 0) {
			showMenu();
			
			System.out.print("Choose an option: ");
			try {
				usrInput = input.nextLine();
				
				if (usrInput.isEmpty()) {
					throw new AmazonProductException("Invalid Input");
				}
				try {
					option = Integer.parseInt(usrInput);
				} catch (NumberFormatException e) {
					throw new AmazonProductException("Invalid Input");
				}
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
