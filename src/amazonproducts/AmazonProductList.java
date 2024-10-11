package amazonproducts;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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
		//FileReader fr;
		try {
			
			/*
			 * Need to check for the following still
			 * What if the file is blank.
			 * What if the file is partially incomplete
			 * ie Some entries are null. Do we discard?
			 * 
			 */
			
			
			/* This code is used to fix microsoft issues
			 */ 
			FileInputStream fr = new FileInputStream(csvFile);
			InputStreamReader isr = new InputStreamReader(fr, StandardCharsets.UTF_8);
			BufferedReader br = new BufferedReader(isr);
			
			
			
			//fr = new FileReader(csvFile);
			//BufferedReader br = new BufferedReader(fr);
			String line = "";
		
			//First line contains the header of the csv file. (Assume it always exists)
			line = br.readLine();
			
			if (line != null && title == null)
				title = AmazonProductUtil.lineReader(line);
			
			line = br.readLine(); //skip the first line for testing purposes
			
			while (line != null) {
				
				String[] lineInfo = AmazonProductUtil.lineReader(line);
				
				boolean isValid = true;
				
				
				//Check first if the entries are not empty
				 
				for (String entry: lineInfo) {
					
					if (entry == null || entry.isEmpty() || entry.isBlank()) {
						isValid = false;
						break;
					}
				}
				
				if (isValid) {
					// Verify if the numerical inputs are valid
					if (AmazonProductUtil.isValidInt(lineInfo[0]) &&
						AmazonProductUtil.isValidInt(lineInfo[7]) &&
						AmazonProductUtil.isValidFloat(lineInfo[6]) &&
						AmazonProductUtil.isValidFloat(lineInfo[8]) &&
						AmazonProductUtil.isValidFloat(lineInfo[9])
						) {
						AmazonProduct item = new AmazonProduct(lineInfo);
						bestsellers.add(item);
					}
				}
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
		
		//Check if the bestsellers 
		if (n == 0) {
			throw new AmazonProductException("List is Empty");
		}
		
		//validate if idx is within the size of the bestsellers list
		if (val >= n || val < 0) {
			throw new AmazonProductException("Input value between 0 and " + (n - 1));
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
		
		//Manually assign the properties
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
		
		if (n == 0) {
			throw new AmazonProductException("List is Empty");
		}
		
		if (idx >= n || idx < 0) {
			throw new AmazonProductException("Input value between 0 and " + (n - 1));
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
			System.out.println("[" + s.toString() + "]");
		}
		System.out.println("............................");
	}
	
	/**
	 *  This method writes the information stored in bestsellers
	 *  to a csv file.
	 * @param str : the name of the file to write to.
	 * @throws AmazonProductException : Throws exception if the filename is empty, list is empty, or failure to write
	 */
	public void saveList(String str) throws AmazonProductException {
		//check validity of the filename str
		if (str.isEmpty() || str == null || str.isBlank()) {
			throw new AmazonProductException("Filename is Empty!");
		}
		
		//Make sure the bestseller list is not empty
		if (getSize() == 0) {
			throw new AmazonProductException("List is Empty. Nothing to write");
		}
		try {
			FileWriter fr = new FileWriter(str);
			BufferedWriter br = new BufferedWriter(fr);
			
			//Write the title to the csv file
			if (title != null) {
				String header = "";
				for (int i = 0; i < NUMCOLS; ++i) {
					if (i != NUMCOLS - 1) {
						header += title[i] + ",";
					} else {
						header += title[i];
					}
				
				}	
				br.write(header);
				br.newLine();
			}
			//write content of the bestsellers arraylist to the csv files
			for (AmazonProduct obj : bestsellers) {
				String name =  "\"" + obj.getName() + "\"";
				String strToWrite = obj.getId() + "," 
						+ name + "," 
						+ "\"" + obj.getMain_category().getCategoryName() + "\""+ ","
						+ "\"" + obj.getSub_category().getSubCategory()+ "\"" + ","
						+ obj.getUrlImage() + ","
						+ obj.getLink() + ","
						+ obj.getRatings() + "," 
						+ obj.getNo_of_rating() + "," 
						+ obj.getDiscount_price() + ","
						+ obj.getActual_price();
				br.write(strToWrite);
				br.newLine();
			}
			br.close();
		} catch (IOException e) {
			throw new AmazonProductException("Fail to perform Write operation!");
		}
		
	}
	
	public void search(String str) throws AmazonProductException {
		
		if (str.isEmpty() || str.isBlank() || str == null) {
			throw new AmazonProductException("Search Term Cannot Be Empty!");
		}
		
		boolean found = false;
		
		for (AmazonProduct obj: bestsellers) {
			
			//Convert All entries into a single string
			String objEntries = obj.toString().toLowerCase();
			
			if (objEntries.contains(str.toLowerCase())) {
				System.out.println("[" + obj.toString() + "]");
				found = true;
			}
			
			
		}
		if (!found) {
			System.out.println("No match!");
		}
	}
	
}
