// Generated from /data/yazan/MEGASync/College/Fourth_Year/compiler/compiler_gitlab/compilerast/compilerast/src/HTMLParser.g4 by ANTLR 4.8
package generated;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link HTMLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface HTMLParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link HTMLParser#htmlDocument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHtmlDocument(HTMLParser.HtmlDocumentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code htmlElement}
	 * labeled alternative in {@link HTMLParser#element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHtmlElement(HTMLParser.HtmlElementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code switchElement}
	 * labeled alternative in {@link HTMLParser#element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchElement(HTMLParser.SwitchElementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code scriptletElement}
	 * labeled alternative in {@link HTMLParser#element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScriptletElement(HTMLParser.ScriptletElementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code scriptElement}
	 * labeled alternative in {@link HTMLParser#element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScriptElement(HTMLParser.ScriptElementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code styleElement}
	 * labeled alternative in {@link HTMLParser#element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStyleElement(HTMLParser.StyleElementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cp_appAttribute}
	 * labeled alternative in {@link HTMLParser#attribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCp_appAttribute(HTMLParser.Cp_appAttributeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cp_showAttribute}
	 * labeled alternative in {@link HTMLParser#attribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCp_showAttribute(HTMLParser.Cp_showAttributeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cp_hideAttribute}
	 * labeled alternative in {@link HTMLParser#attribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCp_hideAttribute(HTMLParser.Cp_hideAttributeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cp_ifAttribute}
	 * labeled alternative in {@link HTMLParser#attribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCp_ifAttribute(HTMLParser.Cp_ifAttributeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cp_forAttribute}
	 * labeled alternative in {@link HTMLParser#attribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCp_forAttribute(HTMLParser.Cp_forAttributeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cp_modelAttribute}
	 * labeled alternative in {@link HTMLParser#attribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCp_modelAttribute(HTMLParser.Cp_modelAttributeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code clickAttribute}
	 * labeled alternative in {@link HTMLParser#attribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClickAttribute(HTMLParser.ClickAttributeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mouseoverAttribute}
	 * labeled alternative in {@link HTMLParser#attribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMouseoverAttribute(HTMLParser.MouseoverAttributeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code non_cpAttribute}
	 * labeled alternative in {@link HTMLParser#attribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNon_cpAttribute(HTMLParser.Non_cpAttributeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primitiveBooleanExpression}
	 * labeled alternative in {@link HTMLParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveBooleanExpression(HTMLParser.PrimitiveBooleanExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryComparisonExpression}
	 * labeled alternative in {@link HTMLParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryComparisonExpression(HTMLParser.BinaryComparisonExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenthesizedBooleanExpression}
	 * labeled alternative in {@link HTMLParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesizedBooleanExpression(HTMLParser.ParenthesizedBooleanExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryBooleanExpression}
	 * labeled alternative in {@link HTMLParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryBooleanExpression(HTMLParser.BinaryBooleanExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryBooleanExpression}
	 * labeled alternative in {@link HTMLParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryBooleanExpression(HTMLParser.UnaryBooleanExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#booleanOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanOperator(HTMLParser.BooleanOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#equalityOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityOperator(HTMLParser.EqualityOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code booleanLiteral}
	 * labeled alternative in {@link HTMLParser#booleanValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanLiteral(HTMLParser.BooleanLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code booleanObjectAccessedMember}
	 * labeled alternative in {@link HTMLParser#booleanValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanObjectAccessedMember(HTMLParser.BooleanObjectAccessedMemberContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#comparisonExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonExpression(HTMLParser.ComparisonExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#comparisonOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonOperator(HTMLParser.ComparisonOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primitiveArithmeticExpression}
	 * labeled alternative in {@link HTMLParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveArithmeticExpression(HTMLParser.PrimitiveArithmeticExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryArithmeticExpression}
	 * labeled alternative in {@link HTMLParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryArithmeticExpression(HTMLParser.UnaryArithmeticExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenthesizedArithmeticExpression}
	 * labeled alternative in {@link HTMLParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesizedArithmeticExpression(HTMLParser.ParenthesizedArithmeticExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryArithmeticExpression}
	 * labeled alternative in {@link HTMLParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryArithmeticExpression(HTMLParser.BinaryArithmeticExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#arithmeticOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmeticOperator(HTMLParser.ArithmeticOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code numericLiteral}
	 * labeled alternative in {@link HTMLParser#numericValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericLiteral(HTMLParser.NumericLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code numericObjectAccessedMember}
	 * labeled alternative in {@link HTMLParser#numericValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericObjectAccessedMember(HTMLParser.NumericObjectAccessedMemberContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#objectChainedMembers}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectChainedMembers(HTMLParser.ObjectChainedMembersContext ctx);
	/**
	 * Visit a parse tree produced by the {@code identifier}
	 * labeled alternative in {@link HTMLParser#objectMember}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(HTMLParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code accessedElement}
	 * labeled alternative in {@link HTMLParser#objectMember}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccessedElement(HTMLParser.AccessedElementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionProvoke}
	 * labeled alternative in {@link HTMLParser#objectMember}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionProvoke(HTMLParser.FunctionProvokeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#accessedArrayElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccessedArrayElement(HTMLParser.AccessedArrayElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(HTMLParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#functionArguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionArguments(HTMLParser.FunctionArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringLiteral}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteral(HTMLParser.StringLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code booleanExpressionArgument}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanExpressionArgument(HTMLParser.BooleanExpressionArgumentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arithmeticExpressionArgument}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmeticExpressionArgument(HTMLParser.ArithmeticExpressionArgumentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code forLoop1}
	 * labeled alternative in {@link HTMLParser#forLoop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForLoop1(HTMLParser.ForLoop1Context ctx);
	/**
	 * Visit a parse tree produced by the {@code forLoop2}
	 * labeled alternative in {@link HTMLParser#forLoop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForLoop2(HTMLParser.ForLoop2Context ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#array}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray(HTMLParser.ArrayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionArrayElement}
	 * labeled alternative in {@link HTMLParser#arrayElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionArrayElement(HTMLParser.ExpressionArrayElementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nestedArrayElement}
	 * labeled alternative in {@link HTMLParser#arrayElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNestedArrayElement(HTMLParser.NestedArrayElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#switchE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchE(HTMLParser.SwitchEContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#switchAttribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchAttribute(HTMLParser.SwitchAttributeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#switchCase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchCase(HTMLParser.SwitchCaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#switchCaseAttribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchCaseAttribute(HTMLParser.SwitchCaseAttributeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code elementContent}
	 * labeled alternative in {@link HTMLParser#content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementContent(HTMLParser.ElementContentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code textContent}
	 * labeled alternative in {@link HTMLParser#content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTextContent(HTMLParser.TextContentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code curlyContent}
	 * labeled alternative in {@link HTMLParser#content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCurlyContent(HTMLParser.CurlyContentContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#text}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitText(HTMLParser.TextContext ctx);
	/**
	 * Visit a parse tree produced by the {@code curlyTernaryOperator}
	 * labeled alternative in {@link HTMLParser#curly}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCurlyTernaryOperator(HTMLParser.CurlyTernaryOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code curlyVariables}
	 * labeled alternative in {@link HTMLParser#curly}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCurlyVariables(HTMLParser.CurlyVariablesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code basicTernaryOperator}
	 * labeled alternative in {@link HTMLParser#ternaryOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBasicTernaryOperator(HTMLParser.BasicTernaryOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenthesizedTernaryOperator}
	 * labeled alternative in {@link HTMLParser#ternaryOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesizedTernaryOperator(HTMLParser.ParenthesizedTernaryOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printingExprToExecute}
	 * labeled alternative in {@link HTMLParser#exprToExecute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintingExprToExecute(HTMLParser.PrintingExprToExecuteContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ternaryExprToExecute}
	 * labeled alternative in {@link HTMLParser#exprToExecute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTernaryExprToExecute(HTMLParser.TernaryExprToExecuteContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenthesizedExprToExecute}
	 * labeled alternative in {@link HTMLParser#exprToExecute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesizedExprToExecute(HTMLParser.ParenthesizedExprToExecuteContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exp}
	 * labeled alternative in {@link HTMLParser#exprToExecute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp(HTMLParser.ExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#variables}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariables(HTMLParser.VariablesContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(HTMLParser.VariableContext ctx);
}