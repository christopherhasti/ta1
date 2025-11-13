/**
 * Represents a relational operator (e.g., "<", "==").
 */
public class NodeRelop extends Node {

	private String relop;

	public NodeRelop(int pos, String relop) {
		this.pos = pos;
		this.relop = relop;
	}

	/**
	 * Performs the relational operation.
	 * @param o1 The first operand.
	 * @param o2 The second operand.
	 * @return 1.0 for true, 0.0 for false.
	 * @throws EvalException If the operator is not recognized.
	 */
	public double op(double o1, double o2) throws EvalException {
		// Use a small tolerance for double comparison
		double epsilon = 1e-9;

		if (relop.equals("<"))  return (o1 < o2) ? 1.0 : 0.0;
		if (relop.equals("<=")) return (o1 <= o2) ? 1.0 : 0.0;
		if (relop.equals(">"))  return (o1 > o2) ? 1.0 : 0.0;
		if (relop.equals(">=")) return (o1 >= o2) ? 1.0 : 0.0;
		if (relop.equals("<>")) return (Math.abs(o1 - o2) > epsilon) ? 1.0 : 0.0; // Not equal
		if (relop.equals("==")) return (Math.abs(o1 - o2) < epsilon) ? 1.0 : 0.0; // Equal

		throw new EvalException(pos, "bogus relop: " + relop);
	}

	/**
	 * Generates the C code for the operator.
	 * @return The operator as a string.
	 */
	@Override
	public String code() {
		// C uses != instead of <>
		if (relop.equals("<>")) {
			return "!=";
		}
		return relop;
	}
}