/**
 * Represents a term node, which is a sequence of facts
 * separated by mulops (e.g., "a * b / c").
 */
public class NodeTerm extends Node {

	private NodeFact fact;
	private NodeMulop mulop;
	private NodeTerm term;

	public NodeTerm(NodeFact fact, NodeMulop mulop, NodeTerm term) {
		this.fact=fact;
		this.mulop=mulop;
		this.term=term;
	}

	/**
	 * Appends a new fact/mulop to the term, maintaining
	 * left-associativity for evaluation.
	 * @param term The term part to append.
	 */
	public void append(NodeTerm term) {
		if (this.term==null) {
			this.mulop=term.mulop;
			this.term=term;
			term.mulop=null;
		} else
			this.term.append(term);
	}

	/**
	 * Evaluates the term (handles left-associativity).
	 * @param env The environment to evaluate in.
	 * @return The double result of the term.
	 * @throws EvalException If evaluation fails.
	 */
	public double eval(Environment env) throws EvalException {
		return term==null
			? fact.eval(env)
			: mulop.op(term.eval(env),fact.eval(env));
	}

	/**
	 * Generates C code for the term.
	 * @return A string of C code.
	 */
	public String code() {
		return (term==null ? "" : term.code()+mulop.code())+fact.code();
	}

}