package ast.nodes.boolean_expressions;

import ast.nodes.PrintUtil;
import ast.nodes.contents.Content;

public class BinaryBooleanExpression implements BooleanExpression,Content {
    private BooleanExpression leftOperand;
    private String operator;
    private BooleanExpression rightOperand;

    public BinaryBooleanExpression(BooleanExpression leftOperand, String operator,
                                   BooleanExpression rightOperand) {
        this.leftOperand = leftOperand;
        this.operator = operator;
        this.rightOperand = rightOperand;
    }

    @Override
    public String toString() {
//        String textRepresentation = "Binary Boolean Expression: ";
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
