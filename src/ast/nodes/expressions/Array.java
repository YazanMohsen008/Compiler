package ast.nodes.expressions;

import java.util.List;

public class Array implements Expression {
    private List<Expression> elements;

    public Array(List<Expression> elements) {
        this.elements = elements;
    }

    @Override
    public String toString () {
        String textRepresentation = "Array[";
        for(Expression element: this.elements)
            textRepresentation += element.toString() + ", ";
        return textRepresentation + "]";
    }


}
