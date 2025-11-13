/**
 * Represents a boolean expression (e.g., "expr relop expr").
 */
public class NodeBoolexpr extends Node {

	private NodeExpr expr1;
	private NodeRelop relop;
	private NodeExpr expr2;

	public NodeBoolexpr(NodeExpr expr1, NodeRelop relop, NodeExpr expr2) {
		this.expr1 = expr1;
		this.relop = relop;
		this.expr2 = expr2;
	}

	/**
	 * Evaluates the boolean expression.
	 * @param env The environment to evaluate in.
	 * @return 1.0 for true, 0.0 for false.
	 * @throws EvalException If evaluation fails.
	 */
	@Override
	public double eval(Environment env) throws EvalException {
		double v1 = expr1.eval(env);
		double v2 = expr2.eval(env);
		return relop.op(v1, v2);
	}

	/**
	 * Generates C code for the boolean expression.
	 * @return A string of C code, e.g., "(x > 5.0)".
	 */
	@Override
	public String code() {
		return "(" + expr1.code() + relop.code() + expr2.code() + ")";
	}
}