package ast.nodes.expressions;

import java.util.List;

public class Array implements Expression {
    private List<Expression> elements;

    public Array(List<Expression> elements) {
        this.elements = elements;
    }

    @Override
    public String toString () {
        String textRepresentation = "[";
        for (int i = 0; i < this.elements.size(); i++) {
            textRepresentation += elements.get(i).toString();
            if(i!=elements.size()-1)
            textRepresentation += ", ";
        }
        return textRepresentation + "]";
    }


}
