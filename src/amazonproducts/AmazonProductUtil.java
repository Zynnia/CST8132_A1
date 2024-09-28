package amazonproducts;

public class AmazonProductUtil {
	
	
	/**
	 * The method returns a cleaned float.
	 * Parses string for commas and currency symbols.
	 * @param str : String represented the monetary value
	 * @return a cleaned converted float
	 */
	public static float convertToFloat(String str) {
		String num = "";
		if (!Character.isDigit(str.charAt(0))) {
			num = str.substring(1);
		} else {
			num = str;
		}
		
		return Float.parseFloat(num.replace(",", ""));
	}
	
	/**
	 * Line reader code from Lab 2
	 * @param line : CSV String to parse
	 * @return An array of strings containing the parsed words
	 */
	public static String[] lineReader(String line) {
		 	
			//Max Array size is limited to 10;
			final int ARRAYSIZE = 10;
			//Create an array of max size
			String[] sol = new String[ARRAYSIZE];
			
	        int idx = 0;  //Index of the solution array
	        int start = 0;
	        int end = 0;
	        
	        while (start < line.length()) {
	        	/*
	        	 * If the first entry is a quote then
	        	 * find the end quote.
	        	 */
	            if (line.charAt(start) == '"') {
	                start++;
	              
	                for (end = start; end < line.length(); ++end) {
	                    if (line.charAt(end) == '"') {
	                        break;
	                    }
	                }
	                /*
	                 * Remove the trailing whitespaces
	                 * and add it to the sol array
	                 */
	                String value = (line.substring(start,end)).trim();
	                sol[idx] = value;
	                idx++;
	                start = end + 1;
	            }
	            /*
	             * Check if see if index i is still valid if not return the
	             * arraylist 
	             */
	           
	            if (start >= line.length()) return sol;
	       
	            if (line.charAt(start) == ',' || line.charAt(start) == ' ') {
	                start++;
	            } else  {
	            	/*
	            	 * If character at i is not a comma then find the next
	            	 * comma and split
	            	 */
	                end = start;
	                while (end < line.length()) {
	                    if (line.charAt(end) == ','){
	                      break;
	                    } 
	                    end++;
	                }
	                String value = line.substring(start, end).trim();
	                sol[idx] = value;
	                idx++;
	                start = end + 1;
	            }
	        }
	        return sol;
	}
	
}
