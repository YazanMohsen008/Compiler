package ast.nodes.expressions;

import SymbolTable.Symbol;
import ast.nodes.arithmetic_expressions.ArithmeticExpression;
import ast.nodes.boolean_expressions.BooleanExpression;

import java.util.List;

public class FunctionCall implements BooleanExpression, ArithmeticExpression,
        ObjectMember {
    private Identifier identifier;
    private List<Expression> arguments;
    private Symbol symbol;
    private ObjectMember parent;

    public FunctionCall(Identifier identifier, List<Expression> arguments) {
        this.identifier = identifier;
        this.arguments = arguments;
}

    public int getArgumentsCount() {
        return arguments.size();
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public List<Expression> getArguments() {
        return arguments;
    }

    @Override
    public String toString() {
        String textRepresentation = "";
        textRepresentation += this.identifier + "(";
        if(this.arguments != null && arguments.size()>0) {
            for (int i = 0; i < this.arguments.size() - 1; i++)
                textRepresentation += this.arguments.get(i) + ", ";
            textRepresentation += this.arguments.get(this.arguments.size() - 1);
        }
        textRepresentation += ")";
        return textRepresentation;
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

    }
