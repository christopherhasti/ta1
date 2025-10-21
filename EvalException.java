/**
 * A custom exception class for handling errors that occur during the
 * evaluation (interpretation) phase of the translator.
 */
public class EvalException extends Exception {

	private int pos;
	private String msg;

	/**
	 * Constructs a new EvalException.
	 * @param pos The character position in the source code where the error occurred.
	 * @param msg A message describing the error.
	 */
	public EvalException(int pos, String msg) {
		this.pos=pos;
		this.msg=msg;
	}

	/**
	 * Returns a string representation of the exception.
	 * @return A formatted error message including the position and description.
	 */
	public String toString() {
		return "eval error"
			+", pos="+pos
			+", "+msg;
	}

}
