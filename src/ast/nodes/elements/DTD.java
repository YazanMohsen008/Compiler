package ast.nodes.elements;

import ast.nodes.PrintUtil;

public class DTD {
    private String definition;

    public DTD(String definition) {
        this.definition = definition;
    }

    @Override
    public String toString() {
        return "Document Type Definition:\n" + this.definition;
    }

    public String toString(int col) {
        String indent = PrintUtil.getIndent(col);
        return indent + "Document Type Definition:\n" +
               indent + this.definition.replaceAll("\n", "\n" + indent);
    }
}
