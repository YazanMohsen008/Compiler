package ast.visitor;

import ast.nodes.*;
import ast.nodes.arithmetic_expressions.*;
import ast.nodes.attributes.*;
import ast.nodes.boolean_expressions.*;
import ast.nodes.contents.*;
import ast.nodes.elements.*;
import ast.nodes.expressions.*;
import generated.HTMLParser;
import generated.HTMLParserBaseVisitor;
import java.util.ArrayList;
import java.util.List;

public class ASTBuilder extends HTMLParserBaseVisitor<Object> {
    /**
     * htmlDocument Rule
     */
    @Override
    public Object visitHtmlDocument(HTMLParser.HtmlDocumentContext ctx) {
        XML xml = null;
        DTD dtd = null;

        if(ctx.XML() != null)
            xml = new XML(ctx.XML().getText());

        if(ctx.DTD() != null)
            dtd = new DTD(ctx.DTD().getText());

        List<Scriptlet> scriptlets = new ArrayList<>();
        for(int i = 0; i < ctx.SCRIPTLET().size(); i++)
            scriptlets.add(new Scriptlet(ctx.SCRIPTLET(i).getText()));

        List<Element> elements = new ArrayList<>();
        for (int i = 0; i < ctx.element().size(); i++) {
            elements.add((Element)visit(ctx.element(i)));
        }
        return new HtmlDocument(scriptlets, xml, dtd, elements);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * element Rule
     */
    @Override
    public HTMLElement visitHtmlElement(HTMLParser.HtmlElementContext ctx) {
        String openingTagName = ctx.TAG_NAME(0).getText();

        List<Attribute> attributes = new ArrayList<>();
        for (int i = 0; i < ctx.attribute().size(); i++)
            attributes.add((Attribute)visit(ctx.attribute(i)));

        List<Content> contents = null;
        String closingTagName = null;

        // If It's An Empty Element, Then It Has No Content.
        if(ctx.TAG_NAME(1) != null) {
            closingTagName = ctx.TAG_NAME(1).getText();

            contents = new ArrayList<>();
            for (int i = 0; i < ctx.content().size(); i++) {
                contents.add((Content) visit(ctx.content(i)));
            }
        }
        return new HTMLElement(openingTagName, closingTagName,
                attributes, contents);
    }

    @Override
    public Object visitSwitchElement(HTMLParser.SwitchElementContext ctx) {
        return visit(ctx.switchE());
    }

    @Override
    public Object visitScriptletElement(HTMLParser.ScriptletElementContext ctx) {
        return new Scriptlet(ctx.SCRIPTLET().getText());
    }

    @Override
    public Object visitScriptElement(HTMLParser.ScriptElementContext ctx) {
        return new Script(ctx.getText());
    }

    @Override
    public Object visitStyleElement(HTMLParser.StyleElementContext ctx) {
        return new Style(ctx.getText());
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * attribute Rule
     */
    @Override
    public Object visitCp_appAttribute(HTMLParser.Cp_appAttributeContext ctx) {
        return new Attribute(ctx.CP_APP().getText(),
                new Identifier(ctx.IDENTIFIER().getText()));
    }

    @Override
    public Object visitCp_showAttribute(HTMLParser.Cp_showAttributeContext ctx) {
        return new Attribute(ctx.CP_SHOW().getText(),
                (AttributeValue)visit(ctx.booleanExpression()));
    }

    @Override
    public Object visitCp_hideAttribute(HTMLParser.Cp_hideAttributeContext ctx) {
        return new Attribute(ctx.CP_HIDE().getText(),
                (AttributeValue)visit(ctx.booleanExpression()));
    }

    @Override
    public Object visitCp_ifAttribute(HTMLParser.Cp_ifAttributeContext ctx) {
        return new Attribute(ctx.CP_IF().getText(),
                (AttributeValue)visit(ctx.booleanExpression()));
    }

    @Override
    public Object visitCp_forAttribute(HTMLParser.Cp_forAttributeContext ctx) {
        return new Attribute(ctx.CP_FOR().getText(),
                (AttributeValue)visit(ctx.forLoop()));
    }

    @Override
    public Object visitCp_modelAttribute(HTMLParser.Cp_modelAttributeContext ctx) {
        return new Attribute(ctx.CP_MODEL().getText(),
                (AttributeValue)visit(ctx.objectChainedMembers()));
    }

    @Override
    public Object visitClickAttribute(HTMLParser.ClickAttributeContext ctx) {
        return new Attribute(ctx.CLICK().getText(),
                (AttributeValue)visit(ctx.functionCall()));
    }

    @Override
    public Object visitMouseoverAttribute(HTMLParser.MouseoverAttributeContext ctx) {
        return new Attribute(ctx.MOUSEOVER().getText(),
                (AttributeValue)visit(ctx.functionCall()));
    }

    @Override
    public Object visitNon_cpAttribute(HTMLParser.Non_cpAttributeContext ctx) {
        return new Attribute(ctx.TAG_NAME().getText(),
                new StringLiteral(ctx.ATTVALUE_VALUE().getText()));
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * booleanExpression Rule
     */
    @Override
    public Object visitPrimitiveBooleanExpression(HTMLParser.PrimitiveBooleanExpressionContext ctx) {
        return visit(ctx.booleanValue());
    }

    @Override
    public Object visitBinaryComparisonExpression(HTMLParser.BinaryComparisonExpressionContext ctx) {
        return visit(ctx.comparisonExpression());
    }

    @Override
    public Object visitBinaryBooleanExpression(HTMLParser.BinaryBooleanExpressionContext ctx) {
        String operator;
        if(ctx.booleanOperator() != null)
            operator = ctx.booleanOperator().getText();
        else
            operator = ctx.equalityOperator().getText();

        return new BinaryBooleanExpression((BooleanExpression)visit(ctx.booleanExpression(0)),
                operator, (BooleanExpression)visit(ctx.booleanExpression(1)));
    }

    @Override
    public Object visitUnaryBooleanExpression(HTMLParser.UnaryBooleanExpressionContext ctx) {
        String operator = ctx.NOT().getText();
        return new UnaryBooleanExpression(operator,
                (BooleanExpression)visit(ctx.booleanExpression()));
    }

    @Override
    public Object visitParenthesizedBooleanExpression(HTMLParser.ParenthesizedBooleanExpressionContext ctx) {
        return visit(ctx.booleanExpression());
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * booleanValue Rule
     */
    @Override
    public Object visitBooleanLiteral(HTMLParser.BooleanLiteralContext ctx) {
        if(ctx.TRUE() != null)
            return new BooleanLiteral(ctx.TRUE().getText());
        return new BooleanLiteral(ctx.FALSE().getText());
    }

    @Override
    public Object visitBooleanObjectAccessedMember(HTMLParser.BooleanObjectAccessedMemberContext ctx) {
        return visit(ctx.objectChainedMembers());
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * comparisonExpression Rule
     */
    @Override
    public Object visitComparisonExpression(HTMLParser.ComparisonExpressionContext ctx) {
        String operator;
        if(ctx.comparisonOperator() != null)
            operator = ctx.comparisonOperator().getText();
        else
            operator = ctx.equalityOperator().getText();
        return new ComparisonExpression((ArithmeticExpression)visit(ctx.arithmeticExpression(0)),
                operator, (ArithmeticExpression)visit(ctx.arithmeticExpression(1)));
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * arithmeticExpression Rule
     */
    @Override
    public Object visitPrimitiveArithmeticExpression(HTMLParser.PrimitiveArithmeticExpressionContext ctx) {
        return visit(ctx.numericValue());
    }

    @Override
    public Object visitBinaryArithmeticExpression(HTMLParser.BinaryArithmeticExpressionContext ctx) {
        String operator = ctx.arithmeticOperator().getText();

        return new BinaryArithmeticExpression((ArithmeticExpression)visit(ctx.arithmeticExpression(0)),
                operator, (ArithmeticExpression)visit(ctx.arithmeticExpression(1)));
    }

    @Override
    public Object visitUnaryArithmeticExpression(HTMLParser.UnaryArithmeticExpressionContext ctx) {
        String operator;
        if(ctx.PLUS() != null)
        operator = ctx.PLUS().getText();
        else
            operator = ctx.MINUS().getText();
        return new UnaryArithmeticExpression(operator,
                (ArithmeticExpression)visit(ctx.arithmeticExpression()));
    }

    @Override
    public Object visitParenthesizedArithmeticExpression(HTMLParser.ParenthesizedArithmeticExpressionContext ctx) {
        return visit(ctx.arithmeticExpression());
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * numericValue Rule
     */
    @Override
    public Object visitNumericLiteral(HTMLParser.NumericLiteralContext ctx) {
        return new NumberLiteral(Double.valueOf(ctx.NUMBER().getText()));
    }

    @Override
    public Object visitNumericObjectAccessedMember(HTMLParser.NumericObjectAccessedMemberContext ctx) {
        return visit(ctx.objectChainedMembers());
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * objectChainedMembers Rule
     */
    @Override
    public Object visitObjectChainedMembers(HTMLParser.ObjectChainedMembersContext ctx) {
        List<ObjectMember> members = new ArrayList<>();
        for(int i = 0; i < ctx.objectMember().size(); i++)
            members.add((ObjectMember)visit(ctx.objectMember(i)));
        return new CustomObject(members);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * objectMember Rule
     */
    @Override
    public Object visitIdentifier(HTMLParser.IdentifierContext ctx) {
        return new Identifier(ctx.IDENTIFIER().getText());
    }

    @Override
    public Object visitAccessedElement(HTMLParser.AccessedElementContext ctx) {
        return visit(ctx.accessedArrayElement());
    }

    @Override
    public Object visitFunctionProvoke(HTMLParser.FunctionProvokeContext ctx) {
        return visit(ctx.functionCall());
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * accessedArrayElement Rule
     */
    @Override
    public Object visitAccessedArrayElement(HTMLParser.AccessedArrayElementContext ctx) {
        Identifier identifier = new Identifier(ctx.IDENTIFIER().getText());
        ArithmeticExpression index = (ArithmeticExpression)visit(ctx.arithmeticExpression());
        return new AccessedArrayElement(identifier, index);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * functionCall Rule
     */
    @Override
    public Object visitFunctionCall(HTMLParser.FunctionCallContext ctx) {
        Identifier identifier = new Identifier(ctx.IDENTIFIER().getText());
        List<Expression> arguments = null;
        if(ctx.functionArguments() != null)
            arguments = (List<Expression>)visit(ctx.functionArguments());
        return new FunctionCall(identifier, arguments);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * functionArguments Rule
     */
    @Override
    public Object visitFunctionArguments(HTMLParser.FunctionArgumentsContext ctx) {
        List<Expression> arguments = new ArrayList<>();
        for(int i = 0; i < ctx.expression().size(); i++)
            arguments.add((Expression)visit(ctx.expression(i)));
        return arguments;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * expression Rule
     */
    @Override
    public Object visitStringLiteral(HTMLParser.StringLiteralContext ctx) {
        return new StringLiteral(ctx.STRING_LITERAL().getText());
    }

    @Override
    public Object visitBooleanExpressionArgument(HTMLParser.BooleanExpressionArgumentContext ctx) {
        return visit(ctx.booleanExpression());
    }

    @Override
    public Object visitArithmeticExpressionArgument(HTMLParser.ArithmeticExpressionArgumentContext ctx) {
        return visit(ctx.arithmeticExpression());
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * forLoop Rule
     */
    @Override
    public Object visitForLoop1(HTMLParser.ForLoop1Context ctx) {
        Identifier value = new Identifier(ctx.IDENTIFIER(0).getText());
        Expression object = null;
        Identifier index = null;

        if(ctx.objectChainedMembers() != null) {
            object = (Expression)visit(ctx.objectChainedMembers());
        }
        if(ctx.array() != null) {
            object = (Expression)visit(ctx.array());
        }
        if(ctx.ASSIGNMENT() != null) {
            index = new Identifier(ctx.IDENTIFIER(1).getText());
        }
        return new ForLoop(value, object, index);
    }

    @Override
    public Object visitForLoop2(HTMLParser.ForLoop2Context ctx) {
        Identifier key = new Identifier(ctx.IDENTIFIER(0).getText());
        Identifier value = new Identifier(ctx.IDENTIFIER(1).getText());
        Expression object = (Expression)visit(ctx.objectChainedMembers());
        return new ForLoop(key, value, object);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * array Rule
     */
    @Override public Object visitArray(HTMLParser.ArrayContext ctx) {
        ArrayList<Expression> elements = new ArrayList<>();
        if(ctx.arrayElement() != null) {
            for (int i = 0; i < ctx.arrayElement().size(); i++)
                elements.add((Expression) visit(ctx.arrayElement(i)));
        }
        return new Array(elements);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * arrayElement Rule
     */
    @Override
    public Object visitExpressionArrayElement(HTMLParser.ExpressionArrayElementContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Object visitNestedArrayElement(HTMLParser.NestedArrayElementContext ctx) {
        return visit(ctx.array());
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * switchE Rule
     */
    @Override
    public Object visitSwitchE(HTMLParser.SwitchEContext ctx) {
        String openingTagName = ctx.TAG_NAME(0).getText();
        String closingTagName = ctx.TAG_NAME(1).getText();

        List<Attribute> attributes = new ArrayList<>();
        for (HTMLParser.AttributeContext attribute : ctx.attribute())
            attributes.add((Attribute)visit(attribute));

        attributes.add((Attribute)visit(ctx.switchAttribute()));

        List<SwitchCaseElement> elements = new ArrayList<>();
        for(HTMLParser.SwitchCaseContext item: ctx.switchCase()) {
            elements.add((SwitchCaseElement)visit(item));
        }

        return new SwitchElement(openingTagName, closingTagName,
                attributes, null, elements);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * switchAttribute Rule
     */
    @Override
    public Object visitSwitchAttribute(HTMLParser.SwitchAttributeContext ctx) {
        return new Attribute(ctx.CP_SWITCH().getText(), (Expression)visit(ctx.expression()));
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * switchCase
     */
    @Override
    public Object visitSwitchCase(HTMLParser.SwitchCaseContext ctx) {
        String openingTagName = ctx.TAG_NAME(0).getText();
        String closingTagName = ctx.TAG_NAME(1).getText();

        List<Content> contents = new ArrayList<>();
        for(int i = 0; i < ctx.content().size(); i++)
            contents.add((Content)visit(ctx.content(i)));

        List<Attribute> attributes = new ArrayList<>();
        for (HTMLParser.AttributeContext attribute : ctx.attribute())
            attributes.add((Attribute)visit(attribute));

        attributes.add((Attribute)visit(ctx.switchCaseAttribute()));
        return new SwitchCaseElement(openingTagName, closingTagName,
                attributes, contents);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * switchCaseAttribute Rule
     */
    @Override
    public Object visitSwitchCaseAttribute(HTMLParser.SwitchCaseAttributeContext ctx) {
        if (ctx.CP_SWITCH_CASE() != null)
            return new Attribute(ctx.CP_SWITCH_CASE().getText(), (Expression) visit(ctx.expression()));
        return new Attribute(ctx.CP_SWITCH_DEFAULT().getText(), null);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * content Rule
     */
    @Override
    public Object visitElementContent(HTMLParser.ElementContentContext ctx) {
        return visit(ctx.element());
    }

    @Override
    public Object visitTextContent(HTMLParser.TextContentContext ctx) {
       return visit(ctx.text());
    }

    @Override
    public Object visitCurlyContent(HTMLParser.CurlyContentContext ctx) {
        return visit(ctx.curly());
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * text Rule
     */
    @Override
    public Object visitText(HTMLParser.TextContext ctx) {
        return new TextContent(ctx.getText());
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * curly Rule
     */
    @Override
    public Object visitCurlyTernaryOperator(HTMLParser.CurlyTernaryOperatorContext ctx) {
        return visit(ctx.ternaryOperator());
    }

    @Override
    public Object visitCurlyVariables(HTMLParser.CurlyVariablesContext ctx) {
        return visit(ctx.variables());
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * ternaryOperator Rule
     */
    @Override
    public Object visitBasicTernaryOperator(HTMLParser.BasicTernaryOperatorContext ctx) {
        BooleanExpression leftOperand = (BooleanExpression)visit(ctx.booleanExpression());
        Expression middleOperand = (Expression)visit(ctx.exprToExecute(0));
        Expression rightOperand = (Expression)visit(ctx.exprToExecute(1));
        return new TernaryOperator(leftOperand, middleOperand, rightOperand);
    }


    @Override
    public Object visitParenthesizedTernaryOperator(HTMLParser.ParenthesizedTernaryOperatorContext ctx) {
        return visit(ctx.ternaryOperator());
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * exprToExecute Rule
     */
    @Override
    public Object visitPrintingExprToExecute(HTMLParser.PrintingExprToExecuteContext ctx) {
        return new StringLiteral(ctx.STRING_LITERAL().getText());
    }

    @Override
    public Object visitTernaryExprToExecute(HTMLParser.TernaryExprToExecuteContext ctx) {
        return visit(ctx.ternaryOperator());
    }

    @Override
    public Object visitParenthesizedExprToExecute(HTMLParser.ParenthesizedExprToExecuteContext ctx) {
        return visit(ctx.exprToExecute());
    }
    @Override public Object visitExp(HTMLParser.ExpContext ctx) {
        return visit(ctx.expression());
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * variables Rule
     */
    @Override
    public Object visitVariables(HTMLParser.VariablesContext ctx) {
        List<Variable> variables = new ArrayList<>();
        for(int i = 0; i < ctx.variable().size(); i++)
            variables.add((Variable)visit(ctx.variable(i)));
        return new Variables(variables);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * variable Rule
     */
    @Override
    public Object visitVariable(HTMLParser.VariableContext ctx) {
        CustomObject field = (CustomObject)visit(ctx.objectChainedMembers());
        StringLiteral value = null;
        if(ctx.STRING_LITERAL() != null)
            value = new StringLiteral(ctx.STRING_LITERAL().getText());
        return new Variable(field, value);
    }
}
