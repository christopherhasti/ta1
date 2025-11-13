/**
 * Represents a while-do statement.
 */
public class NodeWhile extends Node {

	private NodeBoolexpr boolexpr;
	private NodeStmt stmt;

	public NodeWhile(NodeBoolexpr boolexpr, NodeStmt stmt) {
		this.boolexpr = boolexpr;
		this.stmt = stmt;
	}

	/**
	 * Evaluates the 'while' loop.
	 * @param env The environment to evaluate in.
	 * @return 0.0.
	 * @throws EvalException If evaluation fails.
	 */
	@Override
	public double eval(Environment env) throws EvalException {
		// 1.0 is true, 0.0 is false
		while (boolexpr.eval(env) == 1.0) {
			stmt.eval(env);
		}
		return 0.0; // Loops don't return a value
	}

	/**
	 * Generates C code for the while loop.
	 * @return A string of C code.
	 */
	@Override
	public String code() {
		return "while " + boolexpr.code() + " { " + stmt.code() + " }";
	}
}