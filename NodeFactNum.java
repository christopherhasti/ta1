/**
 * Represents a number literal used as a fact (e.g., "3.14").
 */
public class NodeFactNum extends NodeFact {

	private String num;

	public NodeFactNum(String num) {
		this.num=num;
	}

	/**
	 * Evaluates the node by parsing the number string as a double.
	 * @param env The environment (unused).
	 * @return The double value of the number.
	 */
	public double eval(Environment env) throws EvalException {
		return Double.parseDouble(num);
	}

	/**
	 * Generates C code for the number literal.
	 * Appends ".0" to integers to force floating-point division in C.
	 * @return The number as a string (e.g., "5.0" or "3.14").
	 */
	public String code() {
		if (num.contains(".")) {
			return num;
		}
		return num + ".0";
	}

}