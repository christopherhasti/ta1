import java.util.ArrayList;
import java.util.List;

/**
 * Represents a block of statements (e.g., the whole program
 * or a 'begin...end' block).
 */
public class NodeBlock extends Node {

	private List<NodeStmt> stmts;

	public NodeBlock() {
		this.stmts = new ArrayList<>();
	}

	/**
	 * Adds a statement to this block.
	 * @param stmt The statement to add.
	 */
	public void addStmt(NodeStmt stmt) {
		stmts.add(stmt);
	}

	/**
	 * Evaluates all statements in this block in order.
	 * @param env The environment to evaluate in.
	 * @return The result of the *last* statement evaluated.
	 * @throws EvalException If any statement fails.
	 */
	@Override
	public double eval(Environment env) throws EvalException {
		double ret = 0.0;
		for (NodeStmt stmt : stmts) {
			ret = stmt.eval(env);
		}
		return ret; // Return value of last statement
	}

	/**
	 * Generates C code for all statements in this block.
	 * @return A string of concatenated C code.
	 */
	@Override
	public String code() {
		String s = "";
		for (NodeStmt stmt : stmts) {
			s += stmt.code();
		}
		// Add curly braces for 'begin...end' blocks
		// The top-level block doesn't need them, as they
		// are inside main() already.
		if (pos != 0) { // 'pos' is 0 for root node
			return "{" + s + "}";
		}
		return s;
	}
}