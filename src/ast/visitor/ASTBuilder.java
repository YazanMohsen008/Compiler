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
   private SymbolTable GlobalSymbolTableReference = new SymbolTable("Global");
   private SymbolTable CurrentTable = GlobalSymbolTableReference;
   private SymbolTable ParentTable = GlobalSymbolTableReference;
   private boolean ForceDeclaration = false;
   private boolean newDeclaration = false;
   private boolean fromObjectChained = false;
   private List<String> semanticErrors =new ArrayList<>();
   private ArrayList<String> HTMLDocIDs = new ArrayList<>();

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
            Element element = (Element) visit(ctx.element(i));
            elements.add(element);
        }
        if(semanticErrors.size()==0)
        printSymbolTableTree(GlobalSymbolTableReference);

        return new HtmlDocument(scriptlets, xml, dtd, elements, HTMLDocIDs,semanticErrors);
    }

    private void printSymbolTableTree(SymbolTable symbolTable) {
        symbolTable.print();
        for (int index = 0; index < symbolTable.getChildrenCount(); index++) {
            SymbolTable temp = symbolTable.getChild(index);
            printSymbolTableTree(temp);
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    HTMLElement ParentElement;

    /**
     * element Rule
     */
    @Override
    public HTMLElement visitHtmlElement(HTMLParser.HtmlElementContext ctx) {
        HTMLElement htmlElement = new HTMLElement();
        String openingTagName = ctx.TAG_NAME(0).getText();
        htmlElement.setOpeningTagName(openingTagName);

        boolean ScopeOpened = false;
        boolean validElement = false;
        List<Attribute> attributes = new ArrayList<>();
        for (int i = 0; i < ctx.attribute().size(); i++) {
            Attribute attribute = (Attribute) visit(ctx.attribute(i));
            //Check if This attribute had opened New Scope ... the OR is to make Sure that if Scope Opened not to make it false
            ScopeOpened = ScopeOpened || attribute.ScopeOpened();

            if (htmlElement.isA() && attribute.isHref())
                validElement = true;

            if (htmlElement.isImg() && attribute.isSrc())
                validElement = true;

            if (attribute.isStructural()) {
                if (htmlElement.hasStructuralAttribute()) {
                    // Semantic 5:. Each html element has at most one structural attribute (one statement) (if/show/hide/for/case).
                    semanticErrors.add("error in Line " + ctx.start.getLine() + " multiple structural attributes in Element");
//                    System.exit(-1);
                } else
                    htmlElement.setStructuralAttribute();
            }
            attributes.add(attribute);
        }

        //  Semantic 3: li tag should not be outside ul/ol.
        if (htmlElement.isLi() && !(ParentElement.isUl() || ParentElement.isOl())) {
            semanticErrors.add("error in Line " + ctx.start.getLine() + "  the <li>  tag  should not be outside ul/ol. ");
//            System.exit(-1);
        }

        //  Semantic 6: img tag must has src attribute.
        if (htmlElement.isImg() && !validElement) {
            semanticErrors.add("error in Line " + ctx.start.getLine() + " The img tag  <img> Must have src attribute ");
//            System.exit(-1);
        }
        //  Semantic 7: Anchor tag must has href attribute (anchor tag is <a>).
        if (htmlElement.isA() && !validElement) {
            semanticErrors.add("error in Line " + ctx.start.getLine() + " The anchor  Tag <a> Must have href attribute ");
//            System.exit(-1);
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
                ParentElement = htmlElement;
                Content content = (Content) visit(ctx.content(i));
                contents.add(content);
            }
        }
        //Pop the Opened Scope
        if (CurrentTable.getParent() != null && ScopeOpened) {
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

    private SymbolTable openScope(String ScopeName) {
        SymbolTable symbolTable = new SymbolTable(ScopeName);
        CurrentTable = symbolTable;
        symbolTable.setParent(ParentTable);
        ParentTable.addChild(symbolTable);
        ParentTable = symbolTable;
        return symbolTable;
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
        SymbolTable temp = CurrentTable;
        while (temp != null) {
            //Semantic 4 : Nested cp-app is forbidden.
            if (temp.getName().equals(ctx.CP_APP().getText())) {
                semanticErrors.add("error in line " + ctx.start.getLine() + " Nested cp-app is forbidden");
//                System.exit(-1);
            }
            temp = temp.getParent();
        }
        SymbolTable symbolTable = openScope(ctx.CP_APP().getText());
        identifier.setSymbol(getObjectMemberSymbol(identifier));
        return new Attribute(ctx.CP_APP().getText(), identifier, symbolTable, HTMLLexer.CP_APP);
    }

    @Override
    public Object visitCp_showAttribute(HTMLParser.Cp_showAttributeContext ctx) {
        ForceDeclaration = false;
        newDeclaration = false;
        return new Attribute(ctx.CP_SHOW().getText(),
                (AttributeValue) visit(ctx.booleanExpression()), HTMLLexer.CP_SHOW);
    }

    @Override
    public Object visitCp_hideAttribute(HTMLParser.Cp_hideAttributeContext ctx) {
        ForceDeclaration = false;
        newDeclaration = false;
        return new Attribute(ctx.CP_HIDE().getText(),
                (AttributeValue) visit(ctx.booleanExpression()), HTMLLexer.CP_HIDE);
    }

    @Override
    public Object visitCp_ifAttribute(HTMLParser.Cp_ifAttributeContext ctx) {
        ForceDeclaration = false;
        newDeclaration = false;
        SymbolTable symbolTable = openScope(ctx.CP_IF().getText());
        return new Attribute(ctx.CP_IF().getText(),
                (AttributeValue) visit(ctx.booleanExpression()), symbolTable, HTMLLexer.CP_IF);
    }

    @Override
    public Object visitCp_forAttribute(HTMLParser.Cp_forAttributeContext ctx) {
        ForceDeclaration = true;
        newDeclaration = false;
        SymbolTable symbolTable = openScope(ctx.CP_FOR().getText());
        return new Attribute(ctx.CP_FOR().getText(),
                (AttributeValue) visit(ctx.forLoop()), symbolTable, HTMLLexer.CP_FOR);
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
        if (ctx.TAG_NAME().getText().equals(HtmlDocument.ID)) {
            //  Semantic 1: Html id attribute MUST be unique on document level.
            if (HTMLDocIDs.contains(ctx.ATTVALUE_VALUE().getText())) {
                semanticErrors.add("error in line " + ctx.start.getLine() + " this id Value " + ctx.ATTVALUE_VALUE() + " had been used before");
//                System.exit(-1);
            } else
                HTMLDocIDs.add(ctx.ATTVALUE_VALUE().getText());
        }
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
        } else {
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
            } else {
                //Semantic 2:  The iterator variable should not repeat.
                while (temp != null) {

                    //Check if the Scope is For Scope
                    while (temp != null && !temp.getName().equals("cp-for"))
                        temp = temp.getParent();

                    //Check  if we Reach the root to break from outside while Loop
                    if (temp == null) break;

                    //Search for variable
                    symbol = temp.getSymbol(objectMember);

                    if (symbol != null && symbol.isIterator())
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
                if (temp == null) {
                    symbol = CurrentTable.insert(objectMember);
                    symbol.setIterator();
                } else {
                    if(symbol != null && symbol.isIterator())
                    semanticErrors.add("error in Line " + forErrorLine +"  " + objectMember.getName() + " iterator should not repeat");
//                    System.exit(-1);
                }

            }
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
    int forErrorLine;

    @Override
    public Object visitForLoop1(HTMLParser.ForLoop1Context ctx) {

        forErrorLine = ctx.start.getLine();
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
        forErrorLine = ctx.start.getLine();
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

        SwitchElement switchElement=new SwitchElement();
        switchElement.setOpeningTagName(openingTagName);
        switchElement.setClosingTagName(closingTagName);
        boolean ScopeOpened = false;
        boolean validElement = false;
        List<Attribute> attributes = new ArrayList<>();

        for (HTMLParser.AttributeContext attribute : ctx.attribute()) {
            Attribute CurrentAttribute = (Attribute) visit(attribute);
            ScopeOpened = ScopeOpened || CurrentAttribute.ScopeOpened();
            if (switchElement.isA() && CurrentAttribute.isHref())
                validElement = true;

            if (switchElement.isImg() && CurrentAttribute.isSrc())
                validElement = true;

            if (CurrentAttribute.isStructural()) {
                if (switchElement.hasStructuralAttribute()) {
                    // Semantic 5:. Each html element has at most one structural attribute (one statement) (if/show/hide/for/case).
                    semanticErrors.add("error in Line " + ctx.start.getLine() + " multiple structural attributes in Element");
//                    System.exit(-1);
                } else
                    switchElement.setStructuralAttribute();
            }

            attributes.add(CurrentAttribute);
        }
        //  Semantic 3: li tag should not be outside ul/ol.
        if (switchElement.isLi() && !(ParentElement.isUl() || ParentElement.isOl())) {
            semanticErrors.add("error in Line " + ctx.start.getLine() + "  the <li>  tag  should not be outside ul/ol. ");
        }

        //  Semantic 6: img tag must has src attribute.
        if (switchElement.isImg() && !validElement) {
            semanticErrors.add("error in Line " + ctx.start.getLine() + " The img tag  <img> Must have src attribute ");
        }
        //  Semantic 7: Anchor tag must has href attribute (anchor tag is <a>).
        if (switchElement.isA() && !validElement) {
            semanticErrors.add("error in Line " + ctx.start.getLine() + " The anchor  Tag <a> Must have href attribute ");
        }

        attributes.add((Attribute) visit(ctx.switchAttribute()));

        List<SwitchCaseElement> elements = new ArrayList<>();
        for (HTMLParser.SwitchCaseContext item : ctx.switchCase()) {
            ParentElement=switchElement;
            elements.add((SwitchCaseElement) visit(item));
        }
        //Pop the Opened Scope
        if (CurrentTable.getParent() != null && ScopeOpened) {
            CurrentTable = CurrentTable.getParent();
            ParentTable = ParentTable.getParent();
        }
        switchElement.setClosingTagName(closingTagName);
        switchElement.setSwitchCaseElements(elements);
        return switchElement;
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
        SwitchCaseElement SwitchCaseElement=new SwitchCaseElement();

        SwitchCaseElement.setOpeningTagName(openingTagName);
        SwitchCaseElement.setClosingTagName(closingTagName);
        SwitchCaseElement.setStructuralAttribute();
        boolean ScopeOpened = false;
        boolean validElement = false;
        List<Attribute> attributes = new ArrayList<>();
        for (HTMLParser.AttributeContext attribute : ctx.attribute()) {
            Attribute CurrentAttribute = (Attribute) visit(attribute);
            ScopeOpened = ScopeOpened || CurrentAttribute.ScopeOpened();
            if (SwitchCaseElement.isA() && CurrentAttribute.isHref())
                validElement = true;

            if (SwitchCaseElement.isImg() && CurrentAttribute.isSrc())
                validElement = true;
            if (CurrentAttribute.isStructural()) {
                if (SwitchCaseElement.hasStructuralAttribute())
                    // Semantic 5:. Each html element has at most one structural attribute (one statement) (if/show/hide/for/case).
                    semanticErrors.add("error in Line " + ctx.start.getLine() + " multiple structural attributes in Element");
//                    System.exit(-1);
            }
            attributes.add(CurrentAttribute);
        }
        //  Semantic 3: li tag should not be outside ul/ol.
        if (SwitchCaseElement.isLi() && !(ParentElement.isUl() || ParentElement.isOl())) {
            semanticErrors.add("error in Line " + ctx.start.getLine() + "  the <li>  tag  should not be outside ul/ol. ");
        }

        //  Semantic 6: img tag must has src attribute.
        if (SwitchCaseElement.isImg() && !validElement) {
            semanticErrors.add("error in Line " + ctx.start.getLine() + " The img tag  <img> Must have src attribute ");
        }
        //  Semantic 7: Anchor tag must has href attribute (anchor tag is <a>).
        if (SwitchCaseElement.isA() && !validElement) {
            semanticErrors.add("error in Line " + ctx.start.getLine() + " The anchor  Tag <a> Must have href attribute ");
        }

        attributes.add((Attribute) visit(ctx.switchCaseAttribute()));
        SwitchCaseElement.setAttributes(attributes);

        List<Content> contents = new ArrayList<>();
        for (int i = 0; i < ctx.content().size(); i++) {
            ParentElement=SwitchCaseElement;
            contents.add((Content) visit(ctx.content(i)));
        }
        //Pop the Opened Scope
        if (CurrentTable.getParent() != null && ScopeOpened) {
            CurrentTable = CurrentTable.getParent();
            ParentTable = ParentTable.getParent();
        }
        if (ParentTable.getParent() != null)
            ParentTable = ParentTable.getParent();
        SwitchCaseElement.setContents(contents);
        return SwitchCaseElement;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * switchCaseAttribute Rule
     */
    @Override
    public Object visitSwitchCaseAttribute(HTMLParser.SwitchCaseAttributeContext ctx) {

        if (ctx.CP_SWITCH_CASE() != null) {
            SymbolTable symbolTable = openScope(ctx.CP_SWITCH_CASE().getText());
            return new Attribute(ctx.CP_SWITCH_CASE().getText(), (Expression) visit(ctx.expression()), symbolTable, HTMLLexer.CP_SWITCH_CASE);
        }
        SymbolTable symbolTable = openScope(ctx.CP_SWITCH_DEFAULT().getText());
        return new Attribute(ctx.CP_SWITCH_DEFAULT().getText(), null, symbolTable, HTMLLexer.CP_SWITCH_DEFAULT);
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
        return visit(ctx.pipedVariable());
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




    @Override public Object visitPipe(HTMLParser.PipeContext ctx) {
        StringBuilder PipeName= new StringBuilder();
        for(int i= 0; i< ctx.IDENTIFIER().size();i++)
            PipeName.append(ctx.IDENTIFIER(i).getText());
        Pipe pipe=new Pipe(PipeName.toString());
        if(ctx.STRING_LITERAL()!=null)
            pipe.setParameter( ctx.STRING_LITERAL().toString());
        return pipe;
    }
    @Override
    public Object visitPipedVariable(HTMLParser.PipedVariableContext ctx) {
        Variable variable=(Variable) visit(ctx.variable());
        List<Pipe> pipes = new ArrayList<>();
        for (int i = 0; i < ctx.pipes().size(); i++) {
            Pipe pipe=(Pipe) visit(ctx.pipes(i));
            String errorMessage=pipe.validatePipe();
            if(errorMessage!=null)
            {
                // Semantic : 8,9,10. pipe Validation
                semanticErrors.add("error in Line "+ ctx.start.getLine()+"  "+errorMessage);
//                System.exit(-1);
            }
            pipes.add((Pipe) visit(ctx.pipes(i)));
        }
        return new PipedVariable(variable,pipes);
    }


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
