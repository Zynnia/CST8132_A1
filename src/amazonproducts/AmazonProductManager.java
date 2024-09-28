package amazonproducts;
import java.util.Scanner;

public class AmazonProductManager {
	
	private AmazonProductList productList = new AmazonProductList();
	
	public AmazonProductManager() {}
	
	public void exit() {}
	
	public void createProductList() {
		Scanner input = new Scanner(System.in);
		System.out.print("Name of file to create Productlist: ");
		String path  = input.next();
		//Testing purposes.
		//path = "Sample-Amazon-Products-v2.csv";
		productList.createList(path);
		System.out.println("Product List created successfully!");
	}
	
	public void displayProductList() {
		productList.printList();
	}
	
	public void addProduct() {}
	
	public void editProduct() {
		String s = productList.findProductByIndex(7).toString();
		System.out.println(s);
	}
	
	public void deleteProduct() {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a position to delete: ");
		int pos = input.nextInt();
		productList.delete(pos);
		
		input.close();
	}
	
	public void saveProductList() {}
	
	public void search() {}
	
	
	public void showMenu() {
		Scanner input = new Scanner(System.in);
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
		
		int option = -1;
		while (option != 0) {
			System.out.println("================================");
			System.out.println("|| Menu - Amazon Products: A1 ||");
			System.out.println("================================");
			
			for (String menuItem: menu) {
				System.out.println(menuItem);
			}
			System.out.print("Choose an option: ");
			option = input.nextInt();
			
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
				break;
			}
			
		}
		System.out.println("================================");
		System.out.println("||     [Application ended]    ||");
		System.out.println("================================");
		
		input.close();
		
	}
	
	public static void main(String[] args) {
		AmazonProductManager manager = new AmazonProductManager();
		manager.showMenu();
	}
}
