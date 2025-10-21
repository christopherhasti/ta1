/**
 * Represents a unary minus operation on a fact (e.g., "-x" or "-(1+2)").
 */
public class NodeFactUnary extends NodeFact {

	private NodeFact fact;

	/**
	 * Constructor for a unary minus fact.
	 * @param pos The position in the source code.
	 * @param fact The fact to be negated.
	 */
	public NodeFactUnary(int pos, NodeFact fact) {
		this.pos = pos;
		this.fact = fact;
	}

	/**
	 * Evaluates the underlying fact and returns its negation.
	 * @param env The environment to evaluate in.
	 * @return The negated double value.
	 * @throws EvalException if the underlying fact cannot be evaluated.
	 */
	public double eval(Environment env) throws EvalException {
		return -fact.eval(env); // Negate the result
	}

	/**
	 * Generates C code for the unary minus expression.
	 * @return A string of C code, e.g., "(-fact.code())".
	 */
	public String code() {
		return "(-" + fact.code() + ")"; // Add parens for safety
	}

}