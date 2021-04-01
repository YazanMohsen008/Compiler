package ast.nodes.elements;

import ast.nodes.PrintUtil;

public class Script implements Element {
    private String script;

    public Script(String script) {
        this.script = script;
    }

    @Override
    public String toString() {
        return "Java Script:\n" + this.script;
    }

    public String toString(int col) {
        String indent = PrintUtil.getIndent(col);
        return indent + "Java Script:\n" +
                indent + this.script.replaceAll("\n", "\n" + indent);
    }
}
