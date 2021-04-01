package ast.nodes.elements;

import ast.nodes.PrintUtil;

public class Scriptlet implements Element {
    private String code;

    public Scriptlet(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "The Java Code Is:\n" + this.code;
    }

    public String toString(int col) {
        String indent = PrintUtil.getIndent(col);
        return indent + "The Java Code Is:\n" +
                indent + this.code.replaceAll("\n", "\n" + indent);
    }
}
