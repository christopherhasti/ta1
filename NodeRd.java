import java.util.Scanner;

/**
 * Represents a 'rd' (read) statement.
 */
public class NodeRd extends Node {

	private String id;
	// Static scanner for System.in, as recommended by TA2
	private static Scanner sysIn = new Scanner(System.in);

	public NodeRd(String id) {
		this.id = id;
	}

	/**
	 * Evaluates the 'rd' statement by reading a double from
	 * System.in and storing it in the environment.
	 * @param env The environment to store the variable in.
	 * @return The double value that was read.
	 * @throws EvalException (Not thrown here, but part of signature).
	 */
	@Override
	public double eval(Environment env) throws EvalException {
		double val = sysIn.nextDouble();
		env.put(id, val);
		return val;
	}

	/**
	 * Generates C code for the 'rd' statement.
	 * @return A string of C code, e.g., "scanf(\"%lf\", &x);".
	 */
	@Override
	public String code() {
		return "scanf(\"%lf\", &" + id + ");";
	}
}