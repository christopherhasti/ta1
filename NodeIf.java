/**
 * Represents an if-then-else statement.
 */
public class NodeIf extends Node {

	private NodeBoolexpr boolexpr;
	private NodeStmt thenStmt;
	private NodeStmt elseStmt; // Can be null

	public NodeIf(NodeBoolexpr boolexpr, NodeStmt thenStmt, NodeStmt elseStmt) {
		this.boolexpr = boolexpr;
		this.thenStmt = thenStmt;
		this.elseStmt = elseStmt;
	}

	/**
	 * Evaluates the 'if' statement.
	 * @param env The environment to evaluate in.
	 * @return The result of the executed branch, or 0.0 if no branch is taken.
	 * @throws EvalException If evaluation fails.
	 */
	@Override
	public double eval(Environment env) throws EvalException {
		// 1.0 is true, 0.0 is false
		if (boolexpr.eval(env) == 1.0) {
			return thenStmt.eval(env);
		} else if (elseStmt != null) {
			return elseStmt.eval(env);
		}
		return 0.0; // No 'else' and condition was false
	}

	/**
	 * Generates C code for the if-then-else statement.
	 * @return A string of C code.
	 */
	@Override
	public String code() {
		String s = "if " + boolexpr.code() + " { " + thenStmt.code() + " }";
		if (elseStmt != null) {
			s += " else { " + elseStmt.code() + " }";
		}
		return s;
	}
}