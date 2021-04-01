package ast.nodes.arithmetic_expressions;

public class NumberLiteral implements ArithmeticExpression {
    private double number;

    public NumberLiteral(double number) {
        this.number = number;
    }

    @Override
    public String toString() {
        if(this.number == Math.floor(this.number))
            return String.valueOf((int)this.number);
        return String.valueOf(this.number);
    }
}
