/**
 * The main entry point for the interpreter and compiler.
 * It processes each command-line argument as a separate program,
 * sharing a single environment among them. This allows variables
 * defined in one program to be used in subsequent ones.
 */
public class Main {

	/**
	 * The main method.
	 * @param args An array of strings, where each string is a source program to be translated.
	 */
	public static void main(String[] args) {
		Parser parser=new Parser();
		Environment env=new Environment();
		String code="";
		for (String prog: args)
			try {
				Node node=parser.parse(prog);
				node.eval(env);
				code+=node.code();
			} catch (Exception e) {
				System.err.println(e);
			}
		new Code(code,env);
	}

}
