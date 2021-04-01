package ast.nodes.boolean_expressions;

public class BinaryBooleanExpression implements BooleanExpression {
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
        String textRepresentation = "Binary Boolean Expression: ";
        textRepresentation += "(" + this.leftOperand + ")";
        textRepresentation += " " + this.operator + " ";
        textRepresentation += "(" + this.rightOperand + ")";
        return textRepresentation;
    }
}
