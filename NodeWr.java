/**
 * This is a "wrapper" node, specific to TA1, that provides
 * a way to print the result of an expression.
 * It is implicitly used by NodeAssn.
 */
public class NodeWr extends Node {

	private NodeExpr expr;

	public NodeWr(NodeExpr expr) {
		this.expr=expr;
	}

	/**
	 * Evaluates the expression and prints the result to standard output.
	 * It attempts to print whole numbers as integers (e.g., "3")
	 * and fractional numbers as doubles (e.g., "3.5").
	 * @param env The environment to evaluate in.
	 * @return The double result of the expression.
	 * @throws EvalException If the expression cannot be evaluated.
	 */
	public double eval(Environment env) throws EvalException {
		double d = expr.eval(env);
		long i = (long) d;
		if (i == d) // Check if it's a whole number
			System.out.println(i);
		else
			System.out.println(d);
		return d;
	}

	/**
	 * Generates C code to print the result of the expression.
	 * @return A string of C code, e.g., "printf(\"%g\n\",(double)(expr.code()));".
	 */
	public String code() {
		return "printf(\"%g\\n\","
			+"(double)("
			+expr.code()
			+"));";
	}

}