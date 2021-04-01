package ast.nodes.contents;

import ast.nodes.PrintUtil;

public class TextContent implements Content {
    private String text;

    public TextContent(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }

    public String toString(int col) {
        String indent = PrintUtil.getIndent(col);
        return indent + this.text.replaceAll("\n", "\n" + indent);
    }
}
