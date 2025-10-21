/**
 * Represents a variable (ID) used as a fact (e.g., "x").
 */
public class NodeFactId extends NodeFact {

	private String id;

	public NodeFactId(int pos, String id) {
		this.pos=pos;
		this.id=id;
	}

	/**
	 * Evaluates the node by retrieving the variable's value from the environment.
	 * @param env The environment to read from.
	 * @return The double value of the variable.
	 * @throws EvalException If the variable is not defined.
	 */
	public double eval(Environment env) throws EvalException {
		return env.get(pos,id);
	}

	/**
	 * Generates C code for the variable.
	 * @return The variable name (e.g., "x").
	 */
	public String code() { return id; }

}