package ast.nodes.expressions;

import ast.nodes.arithmetic_expressions.ArithmeticExpression;
import ast.nodes.attributes.AttributeValue;
import ast.nodes.boolean_expressions.BooleanExpression;

import java.util.List;

public class CustomObject implements BooleanExpression, ArithmeticExpression,
        AttributeValue {
    private List<ObjectMember> members;

    public CustomObject(List<ObjectMember> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        String textRepresentation = "";
        for(int i = 0; i < this.members.size(); i++)
            textRepresentation +=
                    this.members.get(i) + ((this.members.size() != 1 && i != this.members.size() - 1)? "." : "");
        return textRepresentation;
    }
}
