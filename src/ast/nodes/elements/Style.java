package ast.nodes.elements;

import ast.nodes.PrintUtil;

public class Style implements Element {
    private String style;

    public Style(String style) {
        this.style = style;
    }

    @Override
    public String toString() {
        return "CSS:\n" + this.style;
    }

    public String toString(int col) {
        String indent = PrintUtil.getIndent(col);
        return indent + "CSS:\n" +
                indent + this.style.replaceAll("\n", "\n" + indent);
    }
}
