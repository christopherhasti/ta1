/**
 * Represents an assignment statement (e.g., "x = 1+2").
 */
public class NodeAssn extends Node {

	private String id;
	private NodeExpr expr;

	/**
	 * Constructs an assignment node.
	 * @param id The variable name to assign to.
	 * @param expr The expression to evaluate.
	 */
	public NodeAssn(String id, NodeExpr expr) {
		this.id = id;
		this.expr = expr;
	}

	/**
	 * Evaluates the expression, stores it in the environment,
	 * and returns the result.
	 * @param env The environment to store the variable in.
	 * @return The result of the expression.
	 * @throws EvalException If the expression cannot be evaluated.
	 */
	public double eval(Environment env) throws EvalException {
		// TA2: Just evaluate and store. Printing is handled by 'wr'.
		double val = expr.eval(env);
		return env.put(id, val);
	}

	/**
	 * Generates C code for the assignment.
	 * @return A string of C code, e.g., "x=1+2;".
	 */
	public String code() {
		// TA2: Just generate the assignment. Printing is handled by 'wr'.
		return id + "=" + expr.code() + ";";
	}

}