package ast.nodes.elements;

import ast.nodes.PrintUtil;

public class XML {
    private String document;

    public XML(String document) {
        this.document = document;
    }

    @Override
    public String toString() {
        return "XML Document:\n" + this.document;
    }

    public String toString(int col) {
        String indent = PrintUtil.getIndent(col);
        return indent + "XML Document:\n" +
                indent + this.document.replaceAll("\n", "\n" + indent);
    }
}
