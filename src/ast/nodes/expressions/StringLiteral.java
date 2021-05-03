package ast.nodes.expressions;

import ast.nodes.PrintUtil;
import ast.nodes.contents.Content;

public class StringLiteral implements Expression, Content {
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
        //TODO fix indent in Content
        String indent = PrintUtil.getIndent(col);
        return this.text.replaceAll("\n", "\n" + indent);
    }
}
