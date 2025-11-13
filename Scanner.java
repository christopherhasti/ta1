import java.util.*;

/**
 * A lexical analyzer (scanner) for the source language.
 * It tokenizes the input program string, handling identifiers, numbers (doubles),
 * operators, whitespace, and comments.
 */
public class Scanner {

	private String program;		// source program being interpreted
	private int pos;			// index of next char in program
	private Token token;		// last/current scanned token

	// sets of various characters and lexemes
	private Set<String> whitespace=new HashSet<String>();
	private Set<String> digits=new HashSet<String>();
	private Set<String> letters=new HashSet<String>();
	private Set<String> legits=new HashSet<String>();
	private Set<String> keywords=new HashSet<String>();
	private Set<String> operators=new HashSet<String>();

	// initializers for previous sets

	private void fill(Set<String> s, char lo, char hi) {
		for (char c=lo; c<=hi; c++)
			s.add(c+"");
	}

	private void initWhitespace(Set<String> s) {
		s.add(" ");
		s.add("\n");
		s.add("\t");
	}

	private void initDigits(Set<String> s) {
		fill(s,'0','9');
	}

	private void initLetters(Set<String> s) {
		fill(s,'A','Z');
		fill(s,'a','z');
	}

	private void initLegits(Set<String> s) {
		s.addAll(letters);
		s.addAll(digits);
	}

	private void initOperators(Set<String> s) {
		// TA1 operators
		s.add("=");
		s.add("+");
		s.add("-");
		s.add("*");
		s.add("/");
		s.add("(");
		s.add(")");
		s.add(";");
		
		// TA2 operators
		s.add("<");
		s.add("<=");
		s.add(">");
		s.add(">=");
		s.add("<>");
		s.add("==");
	}

	private void initKeywords(Set<String> s) {
		// TA2 keywords
		s.add("rd");
		s.add("wr");
		s.add("if");
		s.add("then");
		s.add("else");
		s.add("while");
		s.add("do");
		s.add("begin");
		s.add("end");
	}

	/**
	 * Constructs a new Scanner for the given program string.
	 * @param program The source code to be scanned.
	 */
	public Scanner(String program) {
		this.program=program;
		pos=0;
		token=null;
		initWhitespace(whitespace);
		initDigits(digits);
		initLetters(letters);
		initLegits(legits);
		initKeywords(keywords);
		initOperators(operators);
	}

	// handy string-processing methods

	public boolean done() {
		return pos>=program.length();
	}

	private void many(Set<String> s) {
		while (!done()&&s.contains(program.charAt(pos)+""))
			pos++;
	}

	/**
	 * This method advances the scanner,
	 * until the current input character
	 * is just after the first occurrence of a particular character.
	 * @param c the character to search for
	 */
	private void past(char c) {
		while (!done()&&c!=program.charAt(pos))
			pos++;
		if (!done()&&c==program.charAt(pos))
			pos++;
	}

	// scan various kinds of lexeme

	private void nextNumber() {
		int old=pos;
		many(digits);
		if (!done() && program.charAt(pos) == '.') {
			pos++;
			many(digits);
		}
		token=new Token("num",program.substring(old,pos));
	}

	private void nextKwId() {
		int old=pos;
		many(letters);
		many(legits);
		String lexeme=program.substring(old,pos);
		token=new Token((keywords.contains(lexeme) ? lexeme : "id"),lexeme);
	}

	private void nextOp() {
		int old=pos;
		pos=old+2;
		if (pos <= program.length()) { // Check 2-char operators
			String lexeme=program.substring(old,pos);
			if (operators.contains(lexeme)) {
				token=new Token(lexeme); // two-char operator
				return;
			}
		}
		pos=old+1; // Fallback to 1-char operator
		String lexeme=program.substring(old,pos);
		token=new Token(lexeme);
	}

	/**
	 * Advances the scanner to the next token in the program string.
	 * It skips over whitespace and comments.
	 * @return true if a token was found, false if the end of the program is reached.
	 */
	public boolean next() {
		boolean ateComment;
		do {
			ateComment = false;
			many(whitespace);
			if (done()) {
				token=new Token("EOF");
				return false;
			}
			// Check for '//' comment
			if (program.startsWith("//", pos)) {
				ateComment = true;
				past('\n'); // Scan until past the newline
			}
		} while (ateComment); // Loop to handle multiple comment lines or whitespace

		String c=program.charAt(pos)+"";
		if (digits.contains(c))
			nextNumber();
		else if (letters.contains(c))
			nextKwId();
		else if (operators.contains(c))
			nextOp();
		else {
			System.err.println("illegal character at position "+pos);
			pos++;
			return next();
		}
		return true;
	}

	/**
	 * Matches the current token against an expected token.
	 * If they match, it advances the scanner. If not, it throws a SyntaxException.
	 * @param t The expected token.
	 * @throws SyntaxException if the current token does not match the expected one.
	 */
	public void match(Token t) throws SyntaxException {
		if (!t.equals(curr()))
			throw new SyntaxException(pos,t,curr());
		next();
	}

	/**
	 * Returns the current token.
	 * @return The current token.
	 * @throws SyntaxException if no token has been scanned yet.
	 */
	public Token curr() throws SyntaxException {
		if (token==null)
			throw new SyntaxException(pos,new Token("ANY"),new Token("EMPTY"));
		return token;
	}

	/**
	 * Returns the current position (index) in the program string.
	 * @return the current character position.
	 */
	public int pos() {
		return pos;
	}
}