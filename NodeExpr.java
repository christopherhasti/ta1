/**
 * Represents an expression node, which is a sequence of terms
 * separated by addops (e.g., "a + b - c").
 */
public class NodeExpr extends Node {

	private NodeTerm term;
	private NodeAddop addop;
	private NodeExpr expr;

	public NodeExpr(NodeTerm term, NodeAddop addop, NodeExpr expr) {
		this.term=term;
		this.addop=addop;
		this.expr=expr;
	}

	/**
	 * Appends a new term/addop to the expression, maintaining
	 * left-associativity for evaluation.
	 * @param expr The expression part to append.
	 */
	public void append(NodeExpr expr) {
		if (this.expr==null) {
			this.addop=expr.addop;
			this.expr=expr;
			expr.addop=null;
		} else
			this.expr.append(expr);
	}

	/**
	 * Evaluates the expression (handles left-associativity).
	 * @param env The environment to evaluate in.
	 * @return The double result of the expression.
	 * @throws EvalException If evaluation fails.
	 */
	public double eval(Environment env) throws EvalException {
		return expr==null
			? term.eval(env)
			: addop.op(expr.eval(env),term.eval(env));
	}

	/**
	 * Generates C code for the expression.
	 * @return A string of C code.
	 */
	public String code() {
		return (expr==null ? "" : expr.code()+addop.code())+term.code();
	}

}