// Generated from D:/College/Compiler/compiler/src\HTMLParser.g4 by ANTLR 4.9.1
package generated;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HTMLParser}.
 */
public interface HTMLParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HTMLParser#htmlDocument}.
	 * @param ctx the parse tree
	 */
	void enterHtmlDocument(HTMLParser.HtmlDocumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#htmlDocument}.
	 * @param ctx the parse tree
	 */
	void exitHtmlDocument(HTMLParser.HtmlDocumentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code htmlElement}
	 * labeled alternative in {@link HTMLParser#element}.
	 * @param ctx the parse tree
	 */
	void enterHtmlElement(HTMLParser.HtmlElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code htmlElement}
	 * labeled alternative in {@link HTMLParser#element}.
	 * @param ctx the parse tree
	 */
	void exitHtmlElement(HTMLParser.HtmlElementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code switchElement}
	 * labeled alternative in {@link HTMLParser#element}.
	 * @param ctx the parse tree
	 */
	void enterSwitchElement(HTMLParser.SwitchElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code switchElement}
	 * labeled alternative in {@link HTMLParser#element}.
	 * @param ctx the parse tree
	 */
	void exitSwitchElement(HTMLParser.SwitchElementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code scriptletElement}
	 * labeled alternative in {@link HTMLParser#element}.
	 * @param ctx the parse tree
	 */
	void enterScriptletElement(HTMLParser.ScriptletElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code scriptletElement}
	 * labeled alternative in {@link HTMLParser#element}.
	 * @param ctx the parse tree
	 */
	void exitScriptletElement(HTMLParser.ScriptletElementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code scriptElement}
	 * labeled alternative in {@link HTMLParser#element}.
	 * @param ctx the parse tree
	 */
	void enterScriptElement(HTMLParser.ScriptElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code scriptElement}
	 * labeled alternative in {@link HTMLParser#element}.
	 * @param ctx the parse tree
	 */
	void exitScriptElement(HTMLParser.ScriptElementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code styleElement}
	 * labeled alternative in {@link HTMLParser#element}.
	 * @param ctx the parse tree
	 */
	void enterStyleElement(HTMLParser.StyleElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code styleElement}
	 * labeled alternative in {@link HTMLParser#element}.
	 * @param ctx the parse tree
	 */
	void exitStyleElement(HTMLParser.StyleElementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cp_includeAttribute}
	 * labeled alternative in {@link HTMLParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterCp_includeAttribute(HTMLParser.Cp_includeAttributeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cp_includeAttribute}
	 * labeled alternative in {@link HTMLParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitCp_includeAttribute(HTMLParser.Cp_includeAttributeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cp_parametersAttribute}
	 * labeled alternative in {@link HTMLParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterCp_parametersAttribute(HTMLParser.Cp_parametersAttributeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cp_parametersAttribute}
	 * labeled alternative in {@link HTMLParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitCp_parametersAttribute(HTMLParser.Cp_parametersAttributeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code changeAttribute}
	 * labeled alternative in {@link HTMLParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterChangeAttribute(HTMLParser.ChangeAttributeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code changeAttribute}
	 * labeled alternative in {@link HTMLParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitChangeAttribute(HTMLParser.ChangeAttributeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code focusAttribute}
	 * labeled alternative in {@link HTMLParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterFocusAttribute(HTMLParser.FocusAttributeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code focusAttribute}
	 * labeled alternative in {@link HTMLParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitFocusAttribute(HTMLParser.FocusAttributeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cp_appAttribute}
	 * labeled alternative in {@link HTMLParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterCp_appAttribute(HTMLParser.Cp_appAttributeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cp_appAttribute}
	 * labeled alternative in {@link HTMLParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitCp_appAttribute(HTMLParser.Cp_appAttributeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cp_showAttribute}
	 * labeled alternative in {@link HTMLParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterCp_showAttribute(HTMLParser.Cp_showAttributeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cp_showAttribute}
	 * labeled alternative in {@link HTMLParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitCp_showAttribute(HTMLParser.Cp_showAttributeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cp_hideAttribute}
	 * labeled alternative in {@link HTMLParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterCp_hideAttribute(HTMLParser.Cp_hideAttributeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cp_hideAttribute}
	 * labeled alternative in {@link HTMLParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitCp_hideAttribute(HTMLParser.Cp_hideAttributeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cp_ifAttribute}
	 * labeled alternative in {@link HTMLParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterCp_ifAttribute(HTMLParser.Cp_ifAttributeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cp_ifAttribute}
	 * labeled alternative in {@link HTMLParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitCp_ifAttribute(HTMLParser.Cp_ifAttributeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cp_forAttribute}
	 * labeled alternative in {@link HTMLParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterCp_forAttribute(HTMLParser.Cp_forAttributeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cp_forAttribute}
	 * labeled alternative in {@link HTMLParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitCp_forAttribute(HTMLParser.Cp_forAttributeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cp_modelAttribute}
	 * labeled alternative in {@link HTMLParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterCp_modelAttribute(HTMLParser.Cp_modelAttributeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cp_modelAttribute}
	 * labeled alternative in {@link HTMLParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitCp_modelAttribute(HTMLParser.Cp_modelAttributeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code clickAttribute}
	 * labeled alternative in {@link HTMLParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterClickAttribute(HTMLParser.ClickAttributeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code clickAttribute}
	 * labeled alternative in {@link HTMLParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitClickAttribute(HTMLParser.ClickAttributeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mouseoverAttribute}
	 * labeled alternative in {@link HTMLParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterMouseoverAttribute(HTMLParser.MouseoverAttributeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mouseoverAttribute}
	 * labeled alternative in {@link HTMLParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitMouseoverAttribute(HTMLParser.MouseoverAttributeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code non_cpAttribute}
	 * labeled alternative in {@link HTMLParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterNon_cpAttribute(HTMLParser.Non_cpAttributeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code non_cpAttribute}
	 * labeled alternative in {@link HTMLParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitNon_cpAttribute(HTMLParser.Non_cpAttributeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primitiveBooleanExpression}
	 * labeled alternative in {@link HTMLParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveBooleanExpression(HTMLParser.PrimitiveBooleanExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primitiveBooleanExpression}
	 * labeled alternative in {@link HTMLParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveBooleanExpression(HTMLParser.PrimitiveBooleanExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryComparisonExpression}
	 * labeled alternative in {@link HTMLParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryComparisonExpression(HTMLParser.BinaryComparisonExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryComparisonExpression}
	 * labeled alternative in {@link HTMLParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryComparisonExpression(HTMLParser.BinaryComparisonExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenthesizedBooleanExpression}
	 * labeled alternative in {@link HTMLParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterParenthesizedBooleanExpression(HTMLParser.ParenthesizedBooleanExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenthesizedBooleanExpression}
	 * labeled alternative in {@link HTMLParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitParenthesizedBooleanExpression(HTMLParser.ParenthesizedBooleanExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryBooleanExpression}
	 * labeled alternative in {@link HTMLParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryBooleanExpression(HTMLParser.BinaryBooleanExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryBooleanExpression}
	 * labeled alternative in {@link HTMLParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryBooleanExpression(HTMLParser.BinaryBooleanExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryBooleanExpression}
	 * labeled alternative in {@link HTMLParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryBooleanExpression(HTMLParser.UnaryBooleanExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryBooleanExpression}
	 * labeled alternative in {@link HTMLParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryBooleanExpression(HTMLParser.UnaryBooleanExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#booleanOperator}.
	 * @param ctx the parse tree
	 */
	void enterBooleanOperator(HTMLParser.BooleanOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#booleanOperator}.
	 * @param ctx the parse tree
	 */
	void exitBooleanOperator(HTMLParser.BooleanOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#equalityOperator}.
	 * @param ctx the parse tree
	 */
	void enterEqualityOperator(HTMLParser.EqualityOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#equalityOperator}.
	 * @param ctx the parse tree
	 */
	void exitEqualityOperator(HTMLParser.EqualityOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code booleanLiteral}
	 * labeled alternative in {@link HTMLParser#booleanValue}.
	 * @param ctx the parse tree
	 */
	void enterBooleanLiteral(HTMLParser.BooleanLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code booleanLiteral}
	 * labeled alternative in {@link HTMLParser#booleanValue}.
	 * @param ctx the parse tree
	 */
	void exitBooleanLiteral(HTMLParser.BooleanLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code booleanObjectAccessedMember}
	 * labeled alternative in {@link HTMLParser#booleanValue}.
	 * @param ctx the parse tree
	 */
	void enterBooleanObjectAccessedMember(HTMLParser.BooleanObjectAccessedMemberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code booleanObjectAccessedMember}
	 * labeled alternative in {@link HTMLParser#booleanValue}.
	 * @param ctx the parse tree
	 */
	void exitBooleanObjectAccessedMember(HTMLParser.BooleanObjectAccessedMemberContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void enterComparisonExpression(HTMLParser.ComparisonExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void exitComparisonExpression(HTMLParser.ComparisonExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void enterComparisonOperator(HTMLParser.ComparisonOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void exitComparisonOperator(HTMLParser.ComparisonOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primitiveArithmeticExpression}
	 * labeled alternative in {@link HTMLParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveArithmeticExpression(HTMLParser.PrimitiveArithmeticExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primitiveArithmeticExpression}
	 * labeled alternative in {@link HTMLParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveArithmeticExpression(HTMLParser.PrimitiveArithmeticExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryArithmeticExpression}
	 * labeled alternative in {@link HTMLParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryArithmeticExpression(HTMLParser.UnaryArithmeticExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryArithmeticExpression}
	 * labeled alternative in {@link HTMLParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryArithmeticExpression(HTMLParser.UnaryArithmeticExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenthesizedArithmeticExpression}
	 * labeled alternative in {@link HTMLParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void enterParenthesizedArithmeticExpression(HTMLParser.ParenthesizedArithmeticExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenthesizedArithmeticExpression}
	 * labeled alternative in {@link HTMLParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void exitParenthesizedArithmeticExpression(HTMLParser.ParenthesizedArithmeticExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryArithmeticExpression}
	 * labeled alternative in {@link HTMLParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryArithmeticExpression(HTMLParser.BinaryArithmeticExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryArithmeticExpression}
	 * labeled alternative in {@link HTMLParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryArithmeticExpression(HTMLParser.BinaryArithmeticExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#arithmeticOperator}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticOperator(HTMLParser.ArithmeticOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#arithmeticOperator}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticOperator(HTMLParser.ArithmeticOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numericLiteral}
	 * labeled alternative in {@link HTMLParser#numericValue}.
	 * @param ctx the parse tree
	 */
	void enterNumericLiteral(HTMLParser.NumericLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numericLiteral}
	 * labeled alternative in {@link HTMLParser#numericValue}.
	 * @param ctx the parse tree
	 */
	void exitNumericLiteral(HTMLParser.NumericLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numericObjectAccessedMember}
	 * labeled alternative in {@link HTMLParser#numericValue}.
	 * @param ctx the parse tree
	 */
	void enterNumericObjectAccessedMember(HTMLParser.NumericObjectAccessedMemberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numericObjectAccessedMember}
	 * labeled alternative in {@link HTMLParser#numericValue}.
	 * @param ctx the parse tree
	 */
	void exitNumericObjectAccessedMember(HTMLParser.NumericObjectAccessedMemberContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#objectChainedMembers}.
	 * @param ctx the parse tree
	 */
	void enterObjectChainedMembers(HTMLParser.ObjectChainedMembersContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#objectChainedMembers}.
	 * @param ctx the parse tree
	 */
	void exitObjectChainedMembers(HTMLParser.ObjectChainedMembersContext ctx);
	/**
	 * Enter a parse tree produced by the {@code identifier}
	 * labeled alternative in {@link HTMLParser#objectMember}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(HTMLParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by the {@code identifier}
	 * labeled alternative in {@link HTMLParser#objectMember}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(HTMLParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by the {@code accessedElement}
	 * labeled alternative in {@link HTMLParser#objectMember}.
	 * @param ctx the parse tree
	 */
	void enterAccessedElement(HTMLParser.AccessedElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code accessedElement}
	 * labeled alternative in {@link HTMLParser#objectMember}.
	 * @param ctx the parse tree
	 */
	void exitAccessedElement(HTMLParser.AccessedElementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionProvoke}
	 * labeled alternative in {@link HTMLParser#objectMember}.
	 * @param ctx the parse tree
	 */
	void enterFunctionProvoke(HTMLParser.FunctionProvokeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionProvoke}
	 * labeled alternative in {@link HTMLParser#objectMember}.
	 * @param ctx the parse tree
	 */
	void exitFunctionProvoke(HTMLParser.FunctionProvokeContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#accessedArrayElement}.
	 * @param ctx the parse tree
	 */
	void enterAccessedArrayElement(HTMLParser.AccessedArrayElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#accessedArrayElement}.
	 * @param ctx the parse tree
	 */
	void exitAccessedArrayElement(HTMLParser.AccessedArrayElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(HTMLParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(HTMLParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#functionArguments}.
	 * @param ctx the parse tree
	 */
	void enterFunctionArguments(HTMLParser.FunctionArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#functionArguments}.
	 * @param ctx the parse tree
	 */
	void exitFunctionArguments(HTMLParser.FunctionArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringLiteral}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteral(HTMLParser.StringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringLiteral}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteral(HTMLParser.StringLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code booleanExpressionArgument}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanExpressionArgument(HTMLParser.BooleanExpressionArgumentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code booleanExpressionArgument}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanExpressionArgument(HTMLParser.BooleanExpressionArgumentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arithmeticExpressionArgument}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticExpressionArgument(HTMLParser.ArithmeticExpressionArgumentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arithmeticExpressionArgument}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticExpressionArgument(HTMLParser.ArithmeticExpressionArgumentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code forLoop1}
	 * labeled alternative in {@link HTMLParser#forLoop}.
	 * @param ctx the parse tree
	 */
	void enterForLoop1(HTMLParser.ForLoop1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code forLoop1}
	 * labeled alternative in {@link HTMLParser#forLoop}.
	 * @param ctx the parse tree
	 */
	void exitForLoop1(HTMLParser.ForLoop1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code forLoop2}
	 * labeled alternative in {@link HTMLParser#forLoop}.
	 * @param ctx the parse tree
	 */
	void enterForLoop2(HTMLParser.ForLoop2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code forLoop2}
	 * labeled alternative in {@link HTMLParser#forLoop}.
	 * @param ctx the parse tree
	 */
	void exitForLoop2(HTMLParser.ForLoop2Context ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#array}.
	 * @param ctx the parse tree
	 */
	void enterArray(HTMLParser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#array}.
	 * @param ctx the parse tree
	 */
	void exitArray(HTMLParser.ArrayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expressionArrayElement}
	 * labeled alternative in {@link HTMLParser#arrayElement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionArrayElement(HTMLParser.ExpressionArrayElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expressionArrayElement}
	 * labeled alternative in {@link HTMLParser#arrayElement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionArrayElement(HTMLParser.ExpressionArrayElementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nestedArrayElement}
	 * labeled alternative in {@link HTMLParser#arrayElement}.
	 * @param ctx the parse tree
	 */
	void enterNestedArrayElement(HTMLParser.NestedArrayElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nestedArrayElement}
	 * labeled alternative in {@link HTMLParser#arrayElement}.
	 * @param ctx the parse tree
	 */
	void exitNestedArrayElement(HTMLParser.NestedArrayElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#switchE}.
	 * @param ctx the parse tree
	 */
	void enterSwitchE(HTMLParser.SwitchEContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#switchE}.
	 * @param ctx the parse tree
	 */
	void exitSwitchE(HTMLParser.SwitchEContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#switchAttribute}.
	 * @param ctx the parse tree
	 */
	void enterSwitchAttribute(HTMLParser.SwitchAttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#switchAttribute}.
	 * @param ctx the parse tree
	 */
	void exitSwitchAttribute(HTMLParser.SwitchAttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#switchCase}.
	 * @param ctx the parse tree
	 */
	void enterSwitchCase(HTMLParser.SwitchCaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#switchCase}.
	 * @param ctx the parse tree
	 */
	void exitSwitchCase(HTMLParser.SwitchCaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#switchCaseAttribute}.
	 * @param ctx the parse tree
	 */
	void enterSwitchCaseAttribute(HTMLParser.SwitchCaseAttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#switchCaseAttribute}.
	 * @param ctx the parse tree
	 */
	void exitSwitchCaseAttribute(HTMLParser.SwitchCaseAttributeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code elementContent}
	 * labeled alternative in {@link HTMLParser#content}.
	 * @param ctx the parse tree
	 */
	void enterElementContent(HTMLParser.ElementContentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code elementContent}
	 * labeled alternative in {@link HTMLParser#content}.
	 * @param ctx the parse tree
	 */
	void exitElementContent(HTMLParser.ElementContentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code textContent}
	 * labeled alternative in {@link HTMLParser#content}.
	 * @param ctx the parse tree
	 */
	void enterTextContent(HTMLParser.TextContentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code textContent}
	 * labeled alternative in {@link HTMLParser#content}.
	 * @param ctx the parse tree
	 */
	void exitTextContent(HTMLParser.TextContentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code curlyContent}
	 * labeled alternative in {@link HTMLParser#content}.
	 * @param ctx the parse tree
	 */
	void enterCurlyContent(HTMLParser.CurlyContentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code curlyContent}
	 * labeled alternative in {@link HTMLParser#content}.
	 * @param ctx the parse tree
	 */
	void exitCurlyContent(HTMLParser.CurlyContentContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#text}.
	 * @param ctx the parse tree
	 */
	void enterText(HTMLParser.TextContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#text}.
	 * @param ctx the parse tree
	 */
	void exitText(HTMLParser.TextContext ctx);
	/**
	 * Enter a parse tree produced by the {@code curlyVariables}
	 * labeled alternative in {@link HTMLParser#curly}.
	 * @param ctx the parse tree
	 */
	void enterCurlyVariables(HTMLParser.CurlyVariablesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code curlyVariables}
	 * labeled alternative in {@link HTMLParser#curly}.
	 * @param ctx the parse tree
	 */
	void exitCurlyVariables(HTMLParser.CurlyVariablesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code curlyExpression}
	 * labeled alternative in {@link HTMLParser#curly}.
	 * @param ctx the parse tree
	 */
	void enterCurlyExpression(HTMLParser.CurlyExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code curlyExpression}
	 * labeled alternative in {@link HTMLParser#curly}.
	 * @param ctx the parse tree
	 */
	void exitCurlyExpression(HTMLParser.CurlyExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code basicTernaryOperator}
	 * labeled alternative in {@link HTMLParser#ternaryOperator}.
	 * @param ctx the parse tree
	 */
	void enterBasicTernaryOperator(HTMLParser.BasicTernaryOperatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code basicTernaryOperator}
	 * labeled alternative in {@link HTMLParser#ternaryOperator}.
	 * @param ctx the parse tree
	 */
	void exitBasicTernaryOperator(HTMLParser.BasicTernaryOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenthesizedTernaryOperator}
	 * labeled alternative in {@link HTMLParser#ternaryOperator}.
	 * @param ctx the parse tree
	 */
	void enterParenthesizedTernaryOperator(HTMLParser.ParenthesizedTernaryOperatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenthesizedTernaryOperator}
	 * labeled alternative in {@link HTMLParser#ternaryOperator}.
	 * @param ctx the parse tree
	 */
	void exitParenthesizedTernaryOperator(HTMLParser.ParenthesizedTernaryOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ternaryExprToExecute}
	 * labeled alternative in {@link HTMLParser#exprToExecute}.
	 * @param ctx the parse tree
	 */
	void enterTernaryExprToExecute(HTMLParser.TernaryExprToExecuteContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ternaryExprToExecute}
	 * labeled alternative in {@link HTMLParser#exprToExecute}.
	 * @param ctx the parse tree
	 */
	void exitTernaryExprToExecute(HTMLParser.TernaryExprToExecuteContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenthesizedExprToExecute}
	 * labeled alternative in {@link HTMLParser#exprToExecute}.
	 * @param ctx the parse tree
	 */
	void enterParenthesizedExprToExecute(HTMLParser.ParenthesizedExprToExecuteContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenthesizedExprToExecute}
	 * labeled alternative in {@link HTMLParser#exprToExecute}.
	 * @param ctx the parse tree
	 */
	void exitParenthesizedExprToExecute(HTMLParser.ParenthesizedExprToExecuteContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exp}
	 * labeled alternative in {@link HTMLParser#exprToExecute}.
	 * @param ctx the parse tree
	 */
	void enterExp(HTMLParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exp}
	 * labeled alternative in {@link HTMLParser#exprToExecute}.
	 * @param ctx the parse tree
	 */
	void exitExp(HTMLParser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#variables}.
	 * @param ctx the parse tree
	 */
	void enterVariables(HTMLParser.VariablesContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#variables}.
	 * @param ctx the parse tree
	 */
	void exitVariables(HTMLParser.VariablesContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(HTMLParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(HTMLParser.VariableContext ctx);
}