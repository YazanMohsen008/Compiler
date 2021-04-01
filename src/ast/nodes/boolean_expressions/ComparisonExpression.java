package ast.nodes.boolean_expressions;

import ast.nodes.arithmetic_expressions.ArithmeticExpression;

public class ComparisonExpression implements BooleanExpression {
    private ArithmeticExpression leftOperand;
    private String operator;
    private ArithmeticExpression rightOperand;

    public ComparisonExpression(ArithmeticExpression leftOperand, String operator,
                                ArithmeticExpression rightOperand) {
        this.leftOperand = leftOperand;
        this.operator = operator;
        this.rightOperand =rightOperand;
    }

    @Override
    public String toString() {
        String textRepresentation = "Comparison Expression: ";
        textRepresentation += "(" + this.leftOperand + ")";
        textRepresentation += " " + this.operator + " ";
        textRepresentation += "(" + this.rightOperand + ")";
        return textRepresentation;
    }
}
