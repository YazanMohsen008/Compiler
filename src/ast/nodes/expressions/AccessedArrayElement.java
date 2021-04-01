package ast.nodes.expressions;

import ast.nodes.arithmetic_expressions.ArithmeticExpression;
import ast.nodes.boolean_expressions.BooleanExpression;

public class AccessedArrayElement implements BooleanExpression, ArithmeticExpression,
        ObjectMember {
    private Identifier identifier;
    private ArithmeticExpression index;

    public AccessedArrayElement(Identifier identifier, ArithmeticExpression index) {
        this.identifier = identifier;
        this.index = index;
    }

    @Override
    public String toString() {
        String textRepresentation = "";
        textRepresentation += this.identifier;
        textRepresentation += "[" + this.index + "]";
        return textRepresentation;
    }
}
