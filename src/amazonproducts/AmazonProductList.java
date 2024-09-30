package amazonproducts;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AmazonProductList {
	private final int NUMCOLS = 10;
	private String[] title;
	private ArrayList<AmazonProduct> bestsellers = new ArrayList<AmazonProduct>();
	
	public AmazonProductList(){}
	
	public void add(AmazonProduct obj) {
		bestsellers.add(obj);
	}
	
	public void createList(String csvFile) throws AmazonProductException  {
		FileReader fr;
		try {
			fr = new FileReader(csvFile);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			//Note issue here
			line = br.readLine();
			line = br.readLine(); //skip the first line for testing purposes
			while (line != null) {
				
				String[] lineInfo = AmazonProductUtil.lineReader(line);
				AmazonProduct item = new AmazonProduct(lineInfo);
				bestsellers.add(item);
				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			throw new AmazonProductException("Invalid file");
		} catch (IOException e) {
			throw new AmazonProductException("Invalid file");
		}
		
		
		
	}
	
	public void delete(int val) throws AmazonProductException {
		int n = getSize();
		
		if (val >= n || val < 0) {
			throw new AmazonProductException("Invalid input");
		}
		
		bestsellers.remove(val);
	}
	
	public void edit(int idx, AmazonProduct prod) throws AmazonProductException {
		
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
		String[] usrInput = new String[NUMCOLS];
		
		for (int i = 0; i < NUMCOLS; ++i) {
			
			System.out.print(prompts[i]);
			usrInput[i] = AmazonProductManager.input.nextLine();
		}
		
		/*
		 * Here we are verifying if the data is valid. 
		 * If not throw Amazon product exception
		 * If entry is blank then leave old value in place.
		 */
		for (int i = 0; i < NUMCOLS; ++i) {
			try {
				if (!usrInput[i].isEmpty()) {
					if (i == 0 || i == 7) {
						Integer.parseInt(usrInput[i]);
					}
					if (i == 6 || i == 8 || i == 9) {
						Float.parseFloat(usrInput[i]);
					}
				}
			} catch (NumberFormatException e) {
				throw new AmazonProductException("Invalid Data!");
			}
		}
		
		for (int i = 0; i < NUMCOLS; ++i) {
			if (!usrInput[i].isEmpty()) {
				
				if (i == 0 ) {
					prod.setId(Integer.parseInt(usrInput[i]));
				}
				if (i == 1) {
					prod.setName(usrInput[i]);
				}
				if (i == 2) {
					AmazonProductCategory category = new AmazonProductCategory(usrInput[i]);
					prod.setMain_category(category);
				}
				if (i == 3) {
					AmazonProductSubCategory subCategory = new AmazonProductSubCategory(usrInput[i], prod.getMain_category());
					prod.setSub_category(subCategory);
				}
				if (i == 4) {
					prod.setUrlImage(usrInput[i]);
				}
				if (i == 5) {
					prod.setLink(usrInput[i]);
				}
				if (i == 6) {
					prod.setRatings(Float.parseFloat(usrInput[i]));
				}
				if (i == 7) {
					prod.setNo_of_rating(Integer.parseInt(usrInput[i]));
				}
				if (i == 8) {
					prod.setDiscount_price(Float.parseFloat(usrInput[i]));
				}
				if (i == 9) {
					prod.setActual_price(Float.parseFloat(usrInput[i]));
				}
			}
		}
	}
		
	public AmazonProduct findProductByIndex(int idx) throws AmazonProductException {
		int n = getSize();
		
		if (idx >= n || idx < 0) {
			throw new AmazonProductException("Invalid input");
		}
		return bestsellers.get(idx);
	}
	
	public int getSize() {
		return bestsellers.size();
	}
	
	public void printList() {
		System.out.println("PRODUCTLIST .................");
		System.out.println("BOOKLIST .................");
		for (AmazonProduct s: bestsellers) {
			System.out.println(s.toString());
		}
		System.out.println("............................");
	}
	
	public void saveList(String str) {
		//TODO
	}
	
	public void search(String str) throws AmazonProductException {
		
		if (str.isEmpty()) {
			throw new AmazonProductException("Search Term Cannot Be Empty!");
		}
		
		for (AmazonProduct obj: bestsellers) {
			boolean isSubstring = false;
			
			//Convert All entries into a single string
			String objEntries = obj.toString().toLowerCase();
			//Remove the square brackets
			String searchTerm = objEntries.substring(1, objEntries.length() - 1);
			
			if (searchTerm.contains(str.toLowerCase())) {
				isSubstring = true;
			}
			
			if (isSubstring) {
				System.out.println(obj.toString());
			}
			
		}
	}
	
}
