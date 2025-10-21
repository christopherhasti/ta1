import java.io.*;

/**
 * Manages the generation of the output C code file.
 * It writes a standard C prologue, the translated code from the AST,
 * variable declarations from the environment, and a standard epilogue.
 * The output file name is determined by the "Code" environment variable.
 */
public class Code {

	private final String[] prologue={
		"#include <stdio.h>",
		"int main() {",
	};

	private final String[] epilogue={
		"return 0;",
		"}",
	};

	/**
	 * Constructs a Code object and writes the complete C program to a file.
	 * If the "Code" environment variable is not set, it does nothing.
	 * @param code The translated C code generated from the AST nodes.
	 * @param env The environment containing the variables to be declared.
	 */
	public Code(String code, Environment env) {
		String fn=System.getenv("Code");
		if (fn==null)
			return;
		try {
			BufferedWriter f=new BufferedWriter(new FileWriter(fn+".c"));
			for (String s: prologue)
				f.write(s+"\n");
			f.write(env.toC());
			f.write(code);
			for (String s: epilogue)
				f.write(s+"\n");
			f.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

}
