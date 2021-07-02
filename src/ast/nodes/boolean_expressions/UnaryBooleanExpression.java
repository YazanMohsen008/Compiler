package ast.nodes.boolean_expressions;

import ast.nodes.PrintUtil;
import ast.nodes.contents.Content;

public class UnaryBooleanExpression implements BooleanExpression,Content {
    private String operator;
    private BooleanExpression operand;

    public UnaryBooleanExpression(String operator, BooleanExpression operand) {
        this.operator = operator;
        this.operand = operand;
    }

    public String getOperator() {
        return operator;
    }

    public BooleanExpression getOperand() {
        return operand;
    }

    @Override
    public String toString() {
        String textRepresentation = "";
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
