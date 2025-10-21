// This class, and its subclasses,
// collectively model parse-tree nodes.
// Each kind of node can be eval()-uated,
// and/or code()-generated.

public abstract class Node {

	protected int pos=0;

	/**
	 * Evaluates the node, producing a double value.
	 * This is the "interpreter" part of the translator.
	 * @param env The environment (symbol table) to use for evaluation.
	 * @return The double result of the evaluation.
	 * @throws EvalException If an error occurs during evaluation (e.g., undefined variable).
	 */
	public double eval(Environment env) throws EvalException {
		throw new EvalException(pos,"cannot eval() node!");
	}

	/**
	 * Generates C code for this node.
	 * This is the "compiler" part of the translator.
	 * @return A string representing the C code for this node.
	 */
	public String code() { return ""; }

}
