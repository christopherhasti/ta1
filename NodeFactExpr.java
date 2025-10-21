/**
 * Represents a parenthesized expression used as a fact (e.g., "(1+2)").
 */
public class NodeFactExpr extends NodeFact {

	private NodeExpr expr;

	public NodeFactExpr(NodeExpr expr) {
		this.expr=expr;
	}

	/**
	 * Evaluates the wrapped expression.
	 * @param env The environment to evaluate in.
	 * @return The double result of the expression.
	 * @throws EvalException If the expression cannot be evaluated.
	 */
	public double eval(Environment env) throws EvalException {
		return expr.eval(env);
	}

	/**
	 * Generates C code for the parenthesized expression.
	 * @return A string of C code, e.g., "(1+2)".
	 */
	public String code() { return "("+expr.code()+")"; }

}