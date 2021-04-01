package ast.nodes.contents;

import ast.nodes.PrintUtil;
import ast.nodes.boolean_expressions.BooleanExpression;
import ast.nodes.expressions.Expression;

public class TernaryOperator implements  Content, Expression {
    private BooleanExpression leftOperand;
    private Expression middleOperand;
    private Expression rightOperand;

    public TernaryOperator(BooleanExpression leftOperand, Expression middleOperand,
                           Expression rightOperand) {
        this.leftOperand = leftOperand;
        this.middleOperand = middleOperand;
        this.rightOperand = rightOperand;
    }

    @Override
    public String toString() {
       String textRepresentation = "";
       textRepresentation += this.leftOperand + "?";
       textRepresentation += this.middleOperand + " : ";
       textRepresentation += this.rightOperand + "\n";
       return textRepresentation;
    }

    public String toString(int col) {
        String indent = PrintUtil.getIndent(col);
        return indent + "Ternary Operator: " + this.leftOperand + "?" + this.middleOperand + " : " + this.rightOperand + "\n";
    }
}
