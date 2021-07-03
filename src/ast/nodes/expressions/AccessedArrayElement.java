package ast.nodes.expressions;

import SymbolTable.Symbol;
import ast.nodes.PrintUtil;
import ast.nodes.arithmetic_expressions.ArithmeticExpression;
import ast.nodes.boolean_expressions.BooleanExpression;
import ast.nodes.contents.Content;

public class AccessedArrayElement implements BooleanExpression, ArithmeticExpression,
        ObjectMember, Content {
    private Identifier identifier;
    private ArithmeticExpression index;
    private ObjectMember parent;
    private Symbol symbol;
    private String CpAppVariable="";
    ;

    public AccessedArrayElement(Identifier identifier, ArithmeticExpression index) {
        this.identifier = identifier;
        this.index = index;
    }



    @Override
    public void setParent(ObjectMember parent) {
        this.parent=parent;
    }

    @Override
    public ObjectMember getParent() {
        return parent;
    }

    @Override
    public String getName() {
        return identifier.getName();
    }

    @Override
    public Symbol getSymbol() {
        return symbol;
    }

    @Override
    public void setSymbol(Symbol symbol) {
        this.symbol=symbol;
    }

    @Override
    public void setGlobal(String CpAppVariable) {
        this.CpAppVariable=CpAppVariable+".";

    }
    @Override
    public String toString(int col) {
        String indent = PrintUtil.getIndent(col);
        return indent +toString();
    }
    @Override
    public String toString() {
        String textRepresentation = CpAppVariable;
        textRepresentation += this.identifier;
        textRepresentation += "[" + this.index + "]";
        return textRepresentation;
    }
}
