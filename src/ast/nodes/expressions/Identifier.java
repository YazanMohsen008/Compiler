package ast.nodes.expressions;

import SymbolTable.Symbol;
import ast.nodes.arithmetic_expressions.ArithmeticExpression;
import ast.nodes.boolean_expressions.BooleanExpression;

public class Identifier implements BooleanExpression, ArithmeticExpression,
        ObjectMember {
    private String name;
    private Symbol symbol;

    public Identifier(String name) {
        this.name = name;
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

}
