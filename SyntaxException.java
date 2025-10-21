/**
 * A custom exception class for handling errors that occur during the
 * parsing phase of the translator (i.e., syntax errors).
 */
public class SyntaxException extends Exception {

	private int pos;
	private Token expected;
	private Token found;

	/**
	 * Constructs a new SyntaxException.
	 * @param pos The character position in the source code where the error occurred.
	 * @param expected The token the parser was expecting to find.
	 * @param found The token that was actually found.
	 */
	public SyntaxException(int pos, Token expected, Token found) {
		this.pos=pos;
		this.expected=expected;
		this.found=found;
	}

	/**
	 * Returns a string representation of the exception.
	 * @return A formatted error message including the position, expected token, and found token.
	 */
	public String toString() {
		return "syntax error"
			+", pos="+pos
			+", expected="+expected
			+", found="+found;
	}

}
