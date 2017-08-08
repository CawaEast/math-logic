// Generated from D:/projects/mathLog/math/2PM/src/parser\CLP.g4 by ANTLR 4.7
package parser;

    import grammar.*;
    import java.util.Vector;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CLPParser}.
 */
public interface CLPListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CLPParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(CLPParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link CLPParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(CLPParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link CLPParser#axioms}.
	 * @param ctx the parse tree
	 */
	void enterAxioms(CLPParser.AxiomsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CLPParser#axioms}.
	 * @param ctx the parse tree
	 */
	void exitAxioms(CLPParser.AxiomsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CLPParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(CLPParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link CLPParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(CLPParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link CLPParser#header}.
	 * @param ctx the parse tree
	 */
	void enterHeader(CLPParser.HeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link CLPParser#header}.
	 * @param ctx the parse tree
	 */
	void exitHeader(CLPParser.HeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link CLPParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(CLPParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CLPParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(CLPParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CLPParser#disjunction}.
	 * @param ctx the parse tree
	 */
	void enterDisjunction(CLPParser.DisjunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CLPParser#disjunction}.
	 * @param ctx the parse tree
	 */
	void exitDisjunction(CLPParser.DisjunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CLPParser#conjunction}.
	 * @param ctx the parse tree
	 */
	void enterConjunction(CLPParser.ConjunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CLPParser#conjunction}.
	 * @param ctx the parse tree
	 */
	void exitConjunction(CLPParser.ConjunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CLPParser#negate}.
	 * @param ctx the parse tree
	 */
	void enterNegate(CLPParser.NegateContext ctx);
	/**
	 * Exit a parse tree produced by {@link CLPParser#negate}.
	 * @param ctx the parse tree
	 */
	void exitNegate(CLPParser.NegateContext ctx);
}