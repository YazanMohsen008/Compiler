package ast.visitor;

import SymbolTable.*;
import ast.nodes.*;
import ast.nodes.arithmetic_expressions.*;
import ast.nodes.attributes.*;
import ast.nodes.boolean_expressions.*;
import ast.nodes.contents.*;
import ast.nodes.elements.*;
import ast.nodes.expressions.*;
import generated.*;

import java.util.ArrayList;
import java.util.List;

public class ASTBuilder extends HTMLParserBaseVisitor<Object> {
    /**
     * htmlDocument Rule
     */
    SymbolTable GlobalSymbolTableReference = new SymbolTable("Global");
    SymbolTable CurrentTable = GlobalSymbolTableReference;
    SymbolTable ParentTable = GlobalSymbolTableReference;
    boolean ForceDeclaration = false;
    boolean newDeclaration = false;
    boolean fromObjectChained = false;



    @Override
    public Object visitHtmlDocument(HTMLParser.HtmlDocumentContext ctx) {
        XML xml = null;
        DTD dtd = null;
        if (ctx.XML() != null)
            xml = new XML(ctx.XML().getText());

        if (ctx.DTD() != null)
            dtd = new DTD(ctx.DTD().getText());

        List<Scriptlet> scriptlets = new ArrayList<>();
        for (int i = 0; i < ctx.SCRIPTLET().size(); i++)
            scriptlets.add(new Scriptlet(ctx.SCRIPTLET(i).getText()));

        List<Element> elements = new ArrayList<>();
        for (int i = 0; i < ctx.element().size(); i++) {
//            ParentTable = GlobalSymbolTableReference;
            Element element = (Element) visit(ctx.element(i));
            elements.add(element);
        }


        printSymbolTableTree(GlobalSymbolTableReference);

        return new HtmlDocument(scriptlets, xml, dtd, elements);
    }

    private void printSymbolTableTree(SymbolTable symbolTable) {
        symbolTable.print();
        for (int index = 0; index < symbolTable.getChildrenCount(); index++) {
            SymbolTable temp = symbolTable.getChild(index);
            printSymbolTableTree(temp);
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * element Rule
     */
    @Override
    public HTMLElement visitHtmlElement(HTMLParser.HtmlElementContext ctx) {
        HTMLElement htmlElement = new HTMLElement();

        String openingTagName = ctx.TAG_NAME(0).getText();
        htmlElement.setOpeningTagName(openingTagName);

        boolean ScopeOpened=false;
        List<Attribute> attributes = new ArrayList<>();
        for (int i = 0; i < ctx.attribute().size(); i++) {
            Attribute attribute =(Attribute) visit(ctx.attribute(i));
            //Check if This attribute had opened New Scope ,,, the OR is to make Sure that if Scope Opened not to make it false
            ScopeOpened= ScopeOpened || attribute.ScopeOpened();
            attributes.add(attribute);
        }
        htmlElement.setAttributes(attributes);

        List<Content> contents = null;
        // If It's An Empty Element, Then It Has No Content.
        String closingTagName = null;
        if (ctx.TAG_NAME(1) != null) {
            closingTagName = ctx.TAG_NAME(1).getText();

            contents = new ArrayList<>();
            //TO Get its Children
            for (int i = 0; i < ctx.content().size(); i++) {
                Content content = (Content) visit(ctx.content(i));
                contents.add(content);
            }
        }
        //Pop the Opened Scope
        if(CurrentTable.getParent()!=null && ScopeOpened) {
            CurrentTable = CurrentTable.getParent();
            ParentTable = ParentTable.getParent();
        }
        htmlElement.setClosingTagName(closingTagName);
        htmlElement.setContents(contents);
        return htmlElement;
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

    private SymbolTable  openScope (String ScopeName){
        SymbolTable symbolTable = new SymbolTable(ScopeName);
        CurrentTable = symbolTable;
        symbolTable.setParent(ParentTable);
        ParentTable.addChild(symbolTable);
        ParentTable = symbolTable;
        return  symbolTable;
    }

    /**
     * attribute Rule
     */

    @Override
    public Object visitCp_includeAttribute(HTMLParser.Cp_includeAttributeContext ctx) {
        ForceDeclaration = false;
        newDeclaration = false;
        return new Attribute(ctx.CP_INCLUDE().getText(), (AttributeValue) visit(ctx.objectChainedMembers()));

    }

    @Override
    public Object visitCp_parametersAttribute(HTMLParser.Cp_parametersAttributeContext ctx) {
        ForceDeclaration = false;
        newDeclaration = false;
        return new Attribute(ctx.CP_PARAMETERS().getText(), (AttributeValue) visit(ctx.objectChainedMembers()));

    }

    @Override
    public Object visitChangeAttribute(HTMLParser.ChangeAttributeContext ctx) {
        ForceDeclaration = false;
        newDeclaration = false;
        return new Attribute(ctx.CHANGE().getText(), (AttributeValue) visit(ctx.objectChainedMembers()));
    }

    @Override
    public Object visitFocusAttribute(HTMLParser.FocusAttributeContext ctx) {
        ForceDeclaration = false;
        newDeclaration = false;
        return new Attribute(ctx.FOCUS().getText(), (AttributeValue) visit(ctx.objectChainedMembers()));
    }

    @Override
    public Object visitCp_appAttribute(HTMLParser.Cp_appAttributeContext ctx) {
        ForceDeclaration = false;
        newDeclaration = false;
        Identifier identifier = new Identifier(ctx.IDENTIFIER().getText());
        SymbolTable symbolTable=openScope(ctx.CP_APP().getText());
        identifier.setSymbol(getObjectMemberSymbol(identifier));
        return new Attribute(ctx.CP_APP().getText(), identifier,symbolTable,HTMLLexer.CP_APP);
    }

    @Override
    public Object visitCp_showAttribute(HTMLParser.Cp_showAttributeContext ctx) {
        ForceDeclaration = false;
        newDeclaration = false;

                return new Attribute(ctx.CP_SHOW().getText(),
                (AttributeValue) visit(ctx.booleanExpression()));
    }

    @Override
    public Object visitCp_hideAttribute(HTMLParser.Cp_hideAttributeContext ctx) {
        ForceDeclaration = false;
        newDeclaration = false;
        return new Attribute(ctx.CP_HIDE().getText(),
                (AttributeValue) visit(ctx.booleanExpression()));
    }

    @Override
    public Object visitCp_ifAttribute(HTMLParser.Cp_ifAttributeContext ctx) {
        ForceDeclaration = false;
        newDeclaration = false;
        SymbolTable symbolTable=openScope(ctx.CP_IF().getText());
        return new Attribute(ctx.CP_IF().getText(),
                (AttributeValue) visit(ctx.booleanExpression()),symbolTable,HTMLLexer.CP_IF);
    }

    @Override
    public Object visitCp_forAttribute(HTMLParser.Cp_forAttributeContext ctx) {
        ForceDeclaration = true;
        newDeclaration = false;
        SymbolTable symbolTable=openScope(ctx.CP_FOR().getText());
        return new Attribute(ctx.CP_FOR().getText(),
                (AttributeValue) visit(ctx.forLoop()),symbolTable,HTMLLexer.CP_FOR);
    }

    @Override
    public Object visitCp_modelAttribute(HTMLParser.Cp_modelAttributeContext ctx) {
        ForceDeclaration = false;
        newDeclaration = true;
        return new Attribute(ctx.CP_MODEL().getText(),
                (AttributeValue) visit(ctx.numericValue()));
    }

    @Override
    public Object visitClickAttribute(HTMLParser.ClickAttributeContext ctx) {
        ForceDeclaration = false;
        newDeclaration = false;
        return new Attribute(ctx.CLICK().getText(),
                (AttributeValue) visit(ctx.objectChainedMembers()));
    }

    @Override
    public Object visitMouseoverAttribute(HTMLParser.MouseoverAttributeContext ctx) {
        ForceDeclaration = false;
        newDeclaration = false;
        return new Attribute(ctx.MOUSEOVER().getText(),
                (AttributeValue) visit(ctx.objectChainedMembers()));
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
        if (ctx.booleanOperator() != null)
            operator = ctx.booleanOperator().getText();
        else
            operator = ctx.equalityOperator().getText();

        return new BinaryBooleanExpression((BooleanExpression) visit(ctx.booleanExpression(0)),
                operator, (BooleanExpression) visit(ctx.booleanExpression(1)));
    }

    @Override
    public Object visitUnaryBooleanExpression(HTMLParser.UnaryBooleanExpressionContext ctx) {
        String operator = ctx.NOT().getText();
        return new UnaryBooleanExpression(operator,
                (BooleanExpression) visit(ctx.booleanExpression()));
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
        if (ctx.TRUE() != null)
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
        if (ctx.comparisonOperator() != null)
            operator = ctx.comparisonOperator().getText();
        else
            operator = ctx.equalityOperator().getText();
        return new ComparisonExpression((ArithmeticExpression) visit(ctx.arithmeticExpression(0)),
                operator, (ArithmeticExpression) visit(ctx.arithmeticExpression(1)));
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

        return new BinaryArithmeticExpression((ArithmeticExpression) visit(ctx.arithmeticExpression(0)),
                operator, (ArithmeticExpression) visit(ctx.arithmeticExpression(1)));
    }

    @Override
    public Object visitUnaryArithmeticExpression(HTMLParser.UnaryArithmeticExpressionContext ctx) {
        String operator;
        if (ctx.PLUS() != null)
            operator = ctx.PLUS().getText();
        else
            operator = ctx.MINUS().getText();
        return new UnaryArithmeticExpression(operator,
                (ArithmeticExpression) visit(ctx.arithmeticExpression()));
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
        fromObjectChained = true;
        ObjectMember parentObjectMember = null;
        for (int i = 0; i < ctx.objectMember().size(); i++) {
            ObjectMember objectMember = (ObjectMember) visit(ctx.objectMember(i));
            objectMember.setParent(parentObjectMember);
            objectMember.setSymbol(getObjectMemberSymbol(objectMember));
            parentObjectMember = objectMember;
        }
        return parentObjectMember;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private Symbol getObjectMemberSymbol(ObjectMember objectMember) {
        boolean found = false;
        Symbol symbol = null;
        SymbolTable temp = CurrentTable;
//        cp-model put the Symbol in Current Scope
        if (newDeclaration) {
            symbol = temp.insert(objectMember);
        }
        else {
//        Getting Reference for old Symbol not new Scope
            if (!ForceDeclaration) {
                while (temp != null) {

                    symbol = temp.getSymbol(objectMember);
                    if (symbol != null)
                        break;
                    //Check if Variable parent is in this Scope
                    ObjectMember objectMemberParent = objectMember.getParent();
                    while (objectMemberParent != null) {
                        Symbol symbolParent = temp.getSymbol(objectMemberParent);
                        if (symbolParent != null) {
                            found = true;
                            break;
                        }
                        objectMemberParent = objectMemberParent.getParent();
                    }
                    if (found) break;
                    temp = temp.getParent();
                }
                if (temp == null)
                    symbol = GlobalSymbolTableReference.insert(objectMember);
                else
                    symbol = temp.insert(objectMember);
            }
            else
                symbol = temp.insert(objectMember);
        }
        return symbol;
    }

    /**
     * objectMember Rule
     */

    @Override
    public Object visitIdentifier(HTMLParser.IdentifierContext ctx) {
        if (fromObjectChained)
            return new Identifier(ctx.IDENTIFIER().getText());
        else {
            Identifier identifier = new Identifier(ctx.getText());
            identifier.setSymbol(getObjectMemberSymbol(identifier));
            return identifier;
        }
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
        ArithmeticExpression index = (ArithmeticExpression) visit(ctx.arithmeticExpression());
        return new AccessedArrayElement(identifier, index);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * functionCall Rule
     */
    @Override
    public Object visitFunctionCall(HTMLParser.FunctionCallContext ctx) {
        List<Expression> arguments = new ArrayList<>();
        if (ctx.functionArguments() != null)
            arguments = (List<Expression>) visit(ctx.functionArguments());

        Identifier identifier = new Identifier(ctx.IDENTIFIER().getText());

        return new FunctionCall(identifier, arguments);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * functionArguments Rule
     */
    @Override
    public Object visitFunctionArguments(HTMLParser.FunctionArgumentsContext ctx) {
        List<Expression> arguments = new ArrayList<>();
        for (int i = 0; i < ctx.expression().size(); i++)
            arguments.add((Expression) visit(ctx.expression(i)));
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

        ForceDeclaration = true;
        Identifier value = new Identifier(ctx.IDENTIFIER(0).getText());
        value.setSymbol(getObjectMemberSymbol(value));

        Expression object = null;
        Identifier index = null;
        ForceDeclaration = false;
        if (ctx.objectChainedMembers() != null) {
            object = (Expression) visit(ctx.objectChainedMembers());
        }

        if (ctx.array() != null) {
            object = (Expression) visit(ctx.array());
        }
        ForceDeclaration = true;
        if (ctx.ASSIGNMENT() != null) {
            index = new Identifier(ctx.IDENTIFIER(1).getText());
            value.setSymbol(getObjectMemberSymbol(index));
        }
        return new ForLoop(value, object, index);
    }

    @Override
    public Object visitForLoop2(HTMLParser.ForLoop2Context ctx) {
        ForceDeclaration = true;
        Identifier key = new Identifier(ctx.IDENTIFIER(0).getText());
        key.setSymbol(getObjectMemberSymbol(key));


        Identifier value = new Identifier(ctx.IDENTIFIER(1).getText());
        value.setSymbol(getObjectMemberSymbol(value));

        ForceDeclaration = false;
        Expression object = (Expression) visit(ctx.objectChainedMembers());
        return new ForLoop(key, value, object);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * array Rule
     */
    @Override
    public Object visitArray(HTMLParser.ArrayContext ctx) {
        ArrayList<Expression> elements = new ArrayList<>();
        if (ctx.arrayElement() != null) {
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
            attributes.add((Attribute) visit(attribute));

        attributes.add((Attribute) visit(ctx.switchAttribute()));

        List<SwitchCaseElement> elements = new ArrayList<>();
        for (HTMLParser.SwitchCaseContext item : ctx.switchCase()) {
            elements.add((SwitchCaseElement) visit(item));
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
        return new Attribute(ctx.CP_SWITCH().getText(), (Expression) visit(ctx.expression()));
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * switchCase
     */

    @Override
    public Object visitSwitchCase(HTMLParser.SwitchCaseContext ctx) {
        String openingTagName = ctx.TAG_NAME(0).getText();
        String closingTagName = ctx.TAG_NAME(1).getText();

        List<Attribute> attributes = new ArrayList<>();
        for (HTMLParser.AttributeContext attribute : ctx.attribute()) {
            Attribute attributeObject =(Attribute) visit(attribute);
            attributes.add(attributeObject);
        }
        attributes.add((Attribute) visit(ctx.switchCaseAttribute()));

        List<Content> contents = new ArrayList<>();
        for (int i = 0; i < ctx.content().size(); i++)
            contents.add((Content) visit(ctx.content(i)));

        if(ParentTable.getParent()!=null)
            ParentTable=ParentTable.getParent();
        return new SwitchCaseElement(openingTagName, closingTagName,
                attributes, contents);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * switchCaseAttribute Rule
     */
    @Override
    public Object visitSwitchCaseAttribute(HTMLParser.SwitchCaseAttributeContext ctx) {

        if (ctx.CP_SWITCH_CASE() != null) {
            SymbolTable symbolTable=openScope(ctx.CP_SWITCH_CASE().getText());
            return new Attribute(ctx.CP_SWITCH_CASE().getText(), (Expression) visit(ctx.expression()),symbolTable,HTMLLexer.CP_SWITCH_CASE);
        }
        SymbolTable symbolTable=openScope(ctx.CP_SWITCH_DEFAULT().getText());
        return new Attribute(ctx.CP_SWITCH_DEFAULT().getText(), null,symbolTable,HTMLLexer.CP_SWITCH_DEFAULT);
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
        newDeclaration = false;
        ForceDeclaration = false;
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
    public Object visitCurlyVariables(HTMLParser.CurlyVariablesContext ctx) {
        return visit(ctx.variables());
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * ternaryOperator Rule
     */
    @Override
    public Object visitBasicTernaryOperator(HTMLParser.BasicTernaryOperatorContext ctx) {
        BooleanExpression leftOperand = (BooleanExpression) visit(ctx.booleanExpression());
        Expression middleOperand = (Expression) visit(ctx.exprToExecute(0));
        Expression rightOperand = (Expression) visit(ctx.exprToExecute(1));
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
    public Object visitTernaryExprToExecute(HTMLParser.TernaryExprToExecuteContext ctx) {
        return visit(ctx.ternaryOperator());
    }

    @Override
    public Object visitParenthesizedExprToExecute(HTMLParser.ParenthesizedExprToExecuteContext ctx) {
        return visit(ctx.exprToExecute());
    }

    @Override
    public Object visitCurlyExpression(HTMLParser.CurlyExpressionContext ctx) {
        return visit(ctx.exprToExecute());
    }

    @Override
    public Object visitExp(HTMLParser.ExpContext ctx) {
        return visit(ctx.expression());
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * variables Rule
     */
    @Override
    public Object visitVariables(HTMLParser.VariablesContext ctx) {
        List<Variable> variables = new ArrayList<>();
        for (int i = 0; i < ctx.variable().size(); i++)
            variables.add((Variable) visit(ctx.variable(i)));
        return new Variables(variables);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * variable Rule
     */
    @Override
    public Object visitVariable(HTMLParser.VariableContext ctx) {
        ObjectMember field = (ObjectMember) visit(ctx.objectChainedMembers());
        StringLiteral value = null;
        if (ctx.STRING_LITERAL() != null)
            value = new StringLiteral(ctx.STRING_LITERAL().getText());
        return new Variable(field, value);
    }

}
