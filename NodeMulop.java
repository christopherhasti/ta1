/**
 * Represents a multiplication or division operator node (* or /).
 */
public class NodeMulop extends Node {

	private String mulop;

	public NodeMulop(int pos, String mulop) {
		this.pos=pos;
		this.mulop=mulop;
	}

	/**
	 * Performs the arithmetic operation.
	 * @param o1 The first operand (double).
	 * @param o2 The second operand (double).
	 * @return The result of the operation (o1 * o2 or o1 / o2).
	 * @throws EvalException If the operator is not recognized.
	 */
	public double op(double o1, double o2) throws EvalException {
		if (mulop.equals("*"))
			return o1*o2;
		if (mulop.equals("/"))
			return o1/o2;
		throw new EvalException(pos,"bogus mulop: "+mulop);
	}

	/**
	 * Generates the C code for the operator.
	 * @return "*" or "/".
	 */
	public String code() { return mulop; }

}