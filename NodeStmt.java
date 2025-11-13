/**
 * Represents a statement.
 * This is a variant class that can hold any one of the specific
 * statement types (Assn, Rd, Wr, If, While, or a Block).
 */
public class NodeStmt extends Node {

	private Node assn;     // Can be NodeAssn
	private Node rd;       // Can be NodeRd
	private Node wr;       // Can be NodeWr
	private Node ifStmt;   // Can be NodeIf
	private Node whileStmt;// Can be NodeWhile
	private Node block;    // Can be NodeBlock (for begin...end)

	// One constructor for each statement type
	public NodeStmt(NodeAssn assn) { this.assn = assn; }
	public NodeStmt(NodeRd rd) { this.rd = rd; }
	public NodeStmt(NodeWr wr) { this.wr = wr; }
	public NodeStmt(NodeIf ifStmt) { this.ifStmt = ifStmt; }
	public NodeStmt(NodeWhile whileStmt) { this.whileStmt = whileStmt; }
	public NodeStmt(NodeBlock block) { this.block = block; }


	/**
	 * Evaluates the specific statement this node holds.
	 * @param env The environment to evaluate in.
	 * @return The result of the statement's evaluation.
	 * @throws EvalException If the statement fails.
	 */
	public double eval(Environment env) throws EvalException {
		if (assn != null) return assn.eval(env);
		if (rd != null) return rd.eval(env);
		if (wr != null) return wr.eval(env);
		if (ifStmt != null) return ifStmt.eval(env);
		if (whileStmt != null) return whileStmt.eval(env);
		if (block != null) return block.eval(env);
		// Should not happen
		throw new EvalException(pos, "Empty NodeStmt");
	}

	/**
	 * Generates C code for the specific statement this node holds.
	 * @return C code for the statement.
	 */
	public String code() {
		if (assn != null) return assn.code();
		if (rd != null) return rd.code();
		if (wr != null) return wr.code();
		if (ifStmt != null) return ifStmt.code();
		if (whileStmt != null) return whileStmt.code();
		if (block != null) return block.code();
		// Should not happen
		return "";
	}

}