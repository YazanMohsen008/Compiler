package ast.nodes.expressions;

import ast.nodes.PrintUtil;

public class StringLiteral implements Expression {
    private String text;

    public StringLiteral(String text) {
        // TODO trim the ""
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }

    public String toString(int col) {
        String indent = PrintUtil.getIndent(col);
        return this.text.replaceAll("\n", "\n" + indent);
    }
}
