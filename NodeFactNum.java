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
	 * @return The number as a string.
	 */
	public String code() { return num; }

}