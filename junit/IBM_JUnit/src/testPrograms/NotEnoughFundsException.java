package testPrograms;

public class NotEnoughFundsException extends Exception {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotEnoughFundsException(Integer amount, Integer balance) {
        super("Attempted to withdraw " + amount + " with a balance of " + balance);
    }
 
}
