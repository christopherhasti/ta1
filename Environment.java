// This class provides a stubbed-out environment.
// You are expected to implement the methods.
// Accessing an undefined variable should throw an exception.

// Hint!
// Use the Java API to implement your Environment.
// Browse:
//   https://docs.oracle.com/javase/tutorial/tutorialLearningPaths.html
// Read about Collections.
// Focus on the Map interface and HashMap implementation.
// Also:
//   https://www.tutorialspoint.com/java/java_map_interface.htm
//   http://www.javatpoint.com/java-map
// and elsewhere.

import java.util.HashMap;
import java.util.Map;

public class Environment {

	private Map<String, Double> map;

	/**
	 * Constructs a new, empty Environment.
	 */
	public Environment() {
		map = new HashMap<>();
	}

	/**
	 * Stores a variable and its value in the environment.
	 * @param var The name of the variable (e.g., "x").
	 * @param val The double value to store.
	 * @return The value that was stored.
	 */
	public double put(String var, double val) {
		map.put(var, val);
		return val;
	}

	/**
	 * Retrieves the value of a variable from the environment.
	 * @param pos The position in the source code (for error reporting).
	 * @param var The name of the variable to retrieve.
	 * @return The double value of the variable.
	 * @throws EvalException if the variable is not defined.
	 */
	public double get(int pos, String var) throws EvalException {
		Double val = map.get(var);
		if (val == null) {
			throw new EvalException(pos, "undefined variable: " + var);
		}
		return val;
	}

	/**
	 * Generates C code to declare all variables used in this environment.
	 * @return A string of C code, e.g., "double x,y;".
	 */
	public String toC() {
		String s = "";
		String sep = " ";
		for (String v : map.keySet()) {
			s += sep + v;
			sep = ",";
		}
		return s == "" ? "" : "double" + s + ";\n";
	}

}