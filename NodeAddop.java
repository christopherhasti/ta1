/**
 * Represents an addition or subtraction operator node (+ or -).
 */
public class NodeAddop extends Node {

	private String addop;

	/**
	 * Constructs an addop node.
	 * @param pos The position in the source code.
	 * @param addop The specific operator ("+" or "-").
	 */
	public NodeAddop(int pos, String addop) {
		this.pos=pos;
		this.addop=addop;
	}

	/**
	 * Performs the arithmetic operation.
	 * @param o1 The first operand (double).
	 * @param o2 The second operand (double).
	 * @return The result of the operation (o1 + o2 or o1 - o2).
	 * @throws EvalException If the operator is not recognized.
	 */
	public double op(double o1, double o2) throws EvalException {
		if (addop.equals("+"))
			return o1+o2;
		if (addop.equals("-"))
			return o1-o2;
		throw new EvalException(pos,"bogus addop: "+addop);
	}

	/**
	 * Generates the C code for the operator.
	 * @return "+" or "-".
	 */
	public String code() { return addop; }

}