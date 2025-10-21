/**
 * Models a token, which is the smallest meaningful unit of a program.
 * A token has two parts: a token type (e.g., "id" or "num") and
 * the actual lexeme (e.g., "foo" or "123.45").
 */
public class Token {

	private String token;
	private String lexeme;

	/**
	 * Constructs a new Token with a specified type and lexeme.
	 * @param token The type of the token (e.g., "id").
	 * @param lexeme The string value of the token (e.g., "foo").
	 */
	public Token(String token, String lexeme) {
		this.token=token;
		this.lexeme=lexeme;
	}

	/**
	 * Constructs a new Token where the type and lexeme are the same
	 * (e.g., for operators like "+").
	 * @param token The token string (e.g., "+").
	 */
	public Token(String token) {
		this(token,token);
	}

	/**
	 * Returns the token type.
	 * @return the token type.
	 */
	public String tok() { return token; }

	/**
	 * Returns the token's lexeme.
	 * @return the token's lexeme.
	 */
	public String lex() { return lexeme; }

	/**
	 * Compares this token to another, based only on the token type.
	 * @param t The other token to compare against.
	 * @return true if the token types are equal.
	 */
	public boolean equals(Token t) {
		return token.equals(t.token);
	}

	/**
	 * Returns a string representation of the token.
	 * @return a string in the format "<type,lexeme>".
	 */
	public String toString() {
		return "<"+tok()+","+lex()+">";
	}

}
