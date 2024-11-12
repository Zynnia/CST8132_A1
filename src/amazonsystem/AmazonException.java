package amazonsystem;

public class AmazonException extends Exception {
	
	
	private static final long serialVersionUID = 1L;

	public AmazonException(String errorMessage) {
		super(errorMessage);
		
	}
}
