package ast.nodes.arithmetic_expressions;

import ast.nodes.PrintUtil;
import ast.nodes.contents.Content;

public class NumberLiteral implements ArithmeticExpression,Content {
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

    @Override
    public String toString(int col) {
        String indent = PrintUtil.getIndent(col);
        return indent +toString();
    }
}
