/**
 * A recursive-descent parser for the source language (TA2).
 * It uses a Scanner to tokenize the input program and builds an
 * Abstract Syntax Tree (AST) composed of Node objects.
 */
public class Parser {

	private Scanner scanner;

	private void match(String s) throws SyntaxException {
		scanner.match(new Token(s));
	}

	private Token curr() throws SyntaxException {
		return scanner.curr();
	}

	private int pos() {
		return scanner.pos();
	}

	// === TA1 Expression Parsers (Unchanged) ===

	private NodeMulop parseMulop() throws SyntaxException {
		if (curr().equals(new Token("*"))) {
			match("*");
			return new NodeMulop(pos(), "*");
		}
		if (curr().equals(new Token("/"))) {
			match("/");
			return new NodeMulop(pos(), "/");
		}
		return null;
	}

	private NodeAddop parseAddop() throws SyntaxException {
		if (curr().equals(new Token("+"))) {
			match("+");
			return new NodeAddop(pos(), "+");
		}
		if (curr().equals(new Token("-"))) {
			match("-");
			return new NodeAddop(pos(), "-");
		}
		return null;
	}

	private NodeFact parseFact() throws SyntaxException {
		if (curr().equals(new Token("-"))) {
			match("-");
			NodeFact fact = parseFact();
			return new NodeFactUnary(pos(), fact);
		}
		if (curr().equals(new Token("("))) {
			match("(");
			NodeExpr expr = parseExpr();
			match(")");
			return new NodeFactExpr(expr);
		}
		if (curr().equals(new Token("id"))) {
			Token id = curr();
			match("id");
			return new NodeFactId(pos(), id.lex());
		}
		Token num = curr();
		match("num");
		return new NodeFactNum(num.lex());
	}

	private NodeTerm parseTerm() throws SyntaxException {
		NodeFact fact = parseFact();
		NodeMulop mulop = parseMulop();
		if (mulop == null)
			return new NodeTerm(fact, null, null);
		NodeTerm term = parseTerm();
		term.append(new NodeTerm(fact, mulop, null));
		return term;
	}

	private NodeExpr parseExpr() throws SyntaxException {
		NodeTerm term = parseTerm();
		NodeAddop addop = parseAddop();
		if (addop == null)
			return new NodeExpr(term, null, null);
		NodeExpr expr = parseExpr();
		expr.append(new NodeExpr(term, addop, null));
		return expr;
	}
	
	// === TA2 New Parsers ===

	private NodeRelop parseRelop() throws SyntaxException {
		Token op = curr();
		if (op.equals(new Token("<"))) { match("<"); }
		else if (op.equals(new Token("<="))) { match("<="); }
		else if (op.equals(new Token(">"))) { match(">"); }
		else if (op.equals(new Token(">="))) { match(">="); }
		else if (op.equals(new Token("<>"))) { match("<>"); }
		else if (op.equals(new Token("=="))) { match("=="); }
		else {
			throw new SyntaxException(pos(), new Token("RELOP"), op);
		}
		return new NodeRelop(pos(), op.tok());
	}

	private NodeBoolexpr parseBoolexpr() throws SyntaxException {
		NodeExpr expr1 = parseExpr();
		NodeRelop relop = parseRelop();
		NodeExpr expr2 = parseExpr();
		return new NodeBoolexpr(expr1, relop, expr2);
	}

	private NodeAssn parseAssn() throws SyntaxException {
		Token id = curr();
		match("id");
		match("=");
		NodeExpr expr = parseExpr();
		NodeAssn assn = new NodeAssn(id.lex(), expr);
		return assn;
	}

	private NodeRd parseRd() throws SyntaxException {
		match("rd");
		Token id = curr();
		match("id");
		return new NodeRd(id.lex());
	}

	private NodeWr parseWr() throws SyntaxException {
		match("wr");
		NodeExpr expr = parseExpr();
		return new NodeWr(expr); // Re-uses the existing NodeWr class
	}

	private NodeIf parseIf() throws SyntaxException {
		match("if");
		NodeBoolexpr boolexpr = parseBoolexpr();
		match("then");
		NodeStmt thenStmt = parseStmt();
		NodeStmt elseStmt = null;
		if (curr().equals(new Token("else"))) {
			match("else");
			elseStmt = parseStmt();
		}
		return new NodeIf(boolexpr, thenStmt, elseStmt);
	}

	private NodeWhile parseWhile() throws SyntaxException {
		match("while");
		NodeBoolexpr boolexpr = parseBoolexpr();
		match("do");
		NodeStmt stmt = parseStmt();
		return new NodeWhile(boolexpr, stmt);
	}

	private NodeBlock parseBeginEnd() throws SyntaxException {
		match("begin");
		NodeBlock block = parseBlock(); // Recursively parse the inner block
		match("end");
		return block;
	}

	/**
	 * Parses a single statement.
	 * This method checks the current token to decide which
	 * type of statement to parse (assn, rd, wr, if, while, begin).
	 */
	private NodeStmt parseStmt() throws SyntaxException {
		Token t = curr();

		if (t.equals(new Token("id"))) {
			return new NodeStmt(parseAssn());
		}
		if (t.equals(new Token("rd"))) {
			return new NodeStmt(parseRd());
		}
		if (t.equals(new Token("wr"))) {
			return new NodeStmt(parseWr());
		}
		if (t.equals(new Token("if"))) {
			return new NodeStmt(parseIf());
		}
		if (t.equals(new Token("while"))) {
			return new NodeStmt(parseWhile());
		}
		if (t.equals(new Token("begin"))) {
			return new NodeStmt(parseBeginEnd());
		}

		throw new SyntaxException(pos(), new Token("STMT"), t);
	}

	/**
	 * Parses a block, which is a sequence of statements
	 * separated by semicolons.
	 */
	private NodeBlock parseBlock() throws SyntaxException {
		NodeBlock block = new NodeBlock();
		// A block continues until 'EOF' or an 'end' token
		while (!curr().equals(new Token("EOF")) && !curr().equals(new Token("end"))) {
			NodeStmt stmt = parseStmt();
			block.addStmt(stmt);
			match(";");
		}
		return block;
	}

	/**
	 * Parses a complete source program string into an AST.
	 * The top-level construct is now a block.
	 * @param program The source program to parse.
	 * @return The root Node (a NodeBlock) of the generated AST.
	 * @throws SyntaxException if the program violates the grammar rules.
	 */
	public Node parse(String program) throws SyntaxException {
		scanner = new Scanner(program);
		scanner.next();
		NodeBlock block = parseBlock(); // Top-level rule is now parseBlock()
		match("EOF");
		return block;
	}

}