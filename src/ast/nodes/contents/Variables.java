package ast.nodes.contents;

import ast.nodes.PrintUtil;

import java.util.List;

public class Variables implements Content {
    private List<Variable> variables;

    public Variables(List<Variable> variables) {
        this.variables = variables;
    }

    @Override
    public String toString() {
        String textRepresentation = "";
        for(Variable variable: this.variables)
            textRepresentation += variable + "\n";
        return textRepresentation;
    }

    public String toString(int col) {
        String indent = PrintUtil.getIndent(col);
        String textRepresentation = "";
        for(Variable variable : this.variables)
            textRepresentation += indent + variable + "\n";
        return textRepresentation;
    }
}
