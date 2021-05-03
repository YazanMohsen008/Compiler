package ast.nodes.arithmetic_expressions;

import ast.nodes.PrintUtil;
import ast.nodes.contents.Content;

public class BinaryArithmeticExpression implements ArithmeticExpression,Content {
    private ArithmeticExpression leftOperand;
    private String operator;
    private ArithmeticExpression rightOperand;

    public BinaryArithmeticExpression(ArithmeticExpression leftOperand, String operator,
                                      ArithmeticExpression rightOperand) {
        this.leftOperand = leftOperand;
        this.operator = operator;
        this.rightOperand = rightOperand;
    }

    @Override
    public String toString() {
        String textRepresentation = "";
        textRepresentation += "(" + this.leftOperand + ")";
        textRepresentation += " " + this.operator + " ";
        textRepresentation += "(" + this.rightOperand + ")";
        return textRepresentation;
    }

    @Override
    public String toString(int col) {
        String indent = PrintUtil.getIndent(col);
        return indent +toString();
    }
}
