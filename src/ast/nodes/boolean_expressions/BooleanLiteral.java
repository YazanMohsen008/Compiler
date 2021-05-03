package ast.nodes.boolean_expressions;

import ast.nodes.PrintUtil;
import ast.nodes.contents.Content;

public class BooleanLiteral implements BooleanExpression, Content {
    private String booleanLiteral;

    public BooleanLiteral(String booleanLiteral) {
        this.booleanLiteral = booleanLiteral;
    }

    @Override
    public String toString() {
        return this.booleanLiteral;
    }


    @Override
    public String toString(int col) {
        String indent = PrintUtil.getIndent(col);
        return indent +toString();
    }
}
