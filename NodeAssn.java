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
	 * and (due to TA1 requirements) prints the result.
	 * @param env The environment to store the variable in.
	 * @return The result of the expression.
	 * @throws EvalException If the expression cannot be evaluated.
	 */
	public double eval(Environment env) throws EvalException {
		// The NodeWr is a TA1-specific hack to print the result of assignments.
		return env.put(id, new NodeWr(expr).eval(env));
	}

	/**
	 * Generates C code for the assignment.
	 * @return A string of C code, e.g., "x=1+2;printf(\"%g\n\",(double)(1+2));".
	 */
	public String code() {
		return id + "=" + expr.code() + ";" + new NodeWr(expr).code();
	}

}