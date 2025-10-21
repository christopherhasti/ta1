/**
 * Represents a statement, which in TA1 is just an assignment.
 */
public class NodeStmt extends Node {

	private NodeAssn assn;

	public NodeStmt(NodeAssn assn) {
		this.assn=assn;
	}

	/**
	 * Evaluates the underlying assignment.
	 * @param env The environment to evaluate in.
	 * @return The result of the assignment expression.
	 * @throws EvalException If the assignment fails.
	 */
	public double eval(Environment env) throws EvalException {
		return assn.eval(env);
	}

	/**
	 * Generates C code for the statement.
	 * @return C code for the assignment.
	 */
	public String code() { return assn.code(); }

}