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
	
	public void createList(String csvFile) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void delete(int val) {
		int n = getSize();
		
		if (val >= n|| val < 0) {
			//Throw  error
		}
		
		bestsellers.remove(val);
	}
	
	public void edit(int idx, AmazonProduct prod) {}
		
	
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
	
	public void saveList(String str) {}
	
	public void search(String str) {
		for (AmazonProduct obj: bestsellers) {
			
		}
	}
	
}
