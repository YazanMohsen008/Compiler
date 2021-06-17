package ast.nodes.contents;

import ast.nodes.PrintUtil;

import java.util.List;

public class PipedVariable implements Content {
    private Variable variable;
    private List<Pipe> pipes;

    public PipedVariable(Variable variable,List<Pipe> pipes) {
        this.variable = variable;
        this.pipes = pipes;
    }

    @Override
    public String toString() {
        String textRepresentation = "";
        textRepresentation+=variable;
        for(Pipe pipe: this.pipes)
            textRepresentation += pipe + "\n";
        return textRepresentation;
    }

    public String toString(int col) {
        String indent = PrintUtil.getIndent(col);
        String textRepresentation = "";
        textRepresentation+=variable;
        for(Pipe pipe : this.pipes)
            textRepresentation += indent + pipe + "\n";
        return textRepresentation;
    }
}
