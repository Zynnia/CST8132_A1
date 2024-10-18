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
		//Skip the currency sign at the front of the string
		if (!Character.isDigit(str.charAt(0))) {
			num = str.substring(1);
		} else {
			num = str;
		}
		
		return Float.parseFloat(num.replace(",", ""));
	}
	
	/**HELPER METHOD
	 * This method verifies if the input string is a valid integer.
	 * @param str String representing a integer
	 * @return true if it is a valid in else false
	 */
	public static boolean isValidInt(String str) {
		str = str.replace(",", "");
		
		//if string is empty return false
		if (str.isEmpty() || str == null || str.isBlank()) return false;
		for (int i = 0; i < str.length(); ++i) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	/**HELPER METHOD
	 * This method verify if a string is a valid float
	 * @param str String representing a number
	 * @return true if a valid float else false
	 */
	public static boolean isValidFloat(String str) {
		str = str.replace(",","");
		
		//if string is empty return false
		if (str.isEmpty() || str == null) return false;
		
		//decimal counter. Count the number of decimal in the string
		int decimal = 0;
		
		for (int i = 0; i < str.length(); ++i) {
			if (i == 0 && str.charAt(i) != '.' && !Character.isDigit(str.charAt(0))) {
		        continue;
		    }
			if (str.charAt(i) == '.') {
				decimal++;
			} else if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		//Cannot be a valid the string contains more than 1 decimal
		if (decimal > 1) return false;
		
		return true;
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
	                
	                //Counter used to check for balanced quotes 
	                //Push pop method
	                int counter = 1;
	                for (end = start; end < line.length(); ++end) {
	                    if (line.charAt(end) == '"') {
	                        if (counter == 1) counter = 0;
	                        else counter = 1;
	                    }
	                    if (line.charAt(end) == ',' && counter == 0) {
	                    	break;
	                    }
	                }
	                /*
	                 * Remove the trailing whitespaces
	                 * and add it to the sol array
	                 */
	                String value = (line.substring(start,end - 1)).trim();
	                sol[idx] = value;
	                idx++;
	                start = end + 1;
	           
	            } else if (line.charAt(start) == ',' || line.charAt(start) == ' ') {
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
