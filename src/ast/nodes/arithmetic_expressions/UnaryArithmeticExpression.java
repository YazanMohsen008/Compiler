package ast.nodes.arithmetic_expressions;

import ast.nodes.PrintUtil;
import ast.nodes.contents.Content;

public class UnaryArithmeticExpression implements ArithmeticExpression,Content {
    private String operator;
    private ArithmeticExpression operand;

    public UnaryArithmeticExpression(String operator, ArithmeticExpression operand) {
        this.operator = operator;
        this.operand = operand;
    }

    @Override
    public String toString() {
        String textRepresentation = "Unary Arithmetic Expression: ";
        textRepresentation += this.operator;
        textRepresentation += "(" + this.operand + ")";
        return textRepresentation;
    }

    @Override
    public String toString(int col) {
        String indent = PrintUtil.getIndent(col);
        return indent +toString();
    }
}
