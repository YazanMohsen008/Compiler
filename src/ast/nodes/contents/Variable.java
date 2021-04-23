package ast.nodes.contents;

import ast.nodes.PrintUtil;
import ast.nodes.expressions.CustomObject;
import ast.nodes.expressions.ObjectMember;
import ast.nodes.expressions.StringLiteral;

public class Variable {
    private ObjectMember field;
    private StringLiteral value;

    public Variable(ObjectMember field, StringLiteral value) {
        this.field = field;
        this.value = value;
    }

    @Override
    public String toString() {
        String textRepresentation = this.field.toString();
        if(this.value != null)
            textRepresentation += ": " + this.value;
        return textRepresentation;
    }

    public String toString(int col) {
        String indent = PrintUtil.getIndent(col);
        String textRepresentation = indent + this.field.toString();
        if(this.value != null)
            textRepresentation +=  ": " + this.value;
        return textRepresentation;
    }
}
