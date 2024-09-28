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
		
		if (val >= n|| val < 0) {
			throw new AmazonProductException("Invalid input");
		}
		
		bestsellers.remove(val);
	}
	
	public void edit(int idx, AmazonProduct prod) {
		//TODO
	}
		
	
	/*
	 * remeber to add exceptions here
	 */
	public AmazonProduct findProductByIndex(int idx) {
	
		return bestsellers.get(idx);
	}
	
	public int getSize() {
		return bestsellers.size();
	}
	
	public void printList() {
		for (AmazonProduct s: bestsellers) {
			System.out.println(s.toString());
		}
	}
	
	public void saveList(String str) {
		//TODO
	}
	
	public void search(String str) {
		//TODO
		for (AmazonProduct obj: bestsellers) {
			
		}
	}
	
}
