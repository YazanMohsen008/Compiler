package ast.nodes.expressions;

import ast.nodes.attributes.AttributeValue;

public class ForLoop implements AttributeValue {
    private Identifier key;
    private Identifier value;
    private Expression object;
    private Identifier index;

    public ForLoop(Identifier value, Expression object) {
        this.value = value;
        this.object = object;
    }

    public ForLoop(Identifier value, Expression object, Identifier index) {
        this.value = value;
        this.object = object;
        this.index = index;
    }

    public ForLoop(Identifier key, Identifier value, Expression object) {
        this.key = key;
        this.value = value;
        this.object = object;
    }

    public String toString() {
        String textRepresentation = "";
        if(this.key != null) {
            textRepresentation+="\tKey: ";
            textRepresentation += this.key.toString();
        }
        if(this.value != null) {
            textRepresentation += "\tValue: ";
            textRepresentation += this.value.toString();
        }
        if(this.object != null) {
            textRepresentation += "\tExp: ";
            textRepresentation += this.object.toString();
        }
        if(this.index != null) {
            textRepresentation += "\tIndex: ";
            textRepresentation += this.index.toString();
        }
        return textRepresentation;
    }
}
