package ast.nodes.expressions;

import SymbolTable.Symbol;
import ast.nodes.arithmetic_expressions.ArithmeticExpression;
import ast.nodes.boolean_expressions.BooleanExpression;

public class Identifier implements BooleanExpression, ArithmeticExpression,
        ObjectMember {
    private String name;
    private Symbol symbol;
    private ObjectMember parent;
    public Identifier(String name) {
        this.name = name;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public Identifier(Symbol symbol) {
        this.symbol = symbol;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        if (symbol != null)
            return symbol.toString();
        else
            return name;

    }

    @Override
    public void setParent(ObjectMember parent) {
       this.parent=parent;
    }

    @Override
    public ObjectMember getParent() {
        return parent;
    }


}
