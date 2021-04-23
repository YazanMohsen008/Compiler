package ast.nodes.expressions;

import SymbolTable.Symbol;
import ast.nodes.arithmetic_expressions.ArithmeticExpression;
import ast.nodes.attributes.AttributeValue;
import ast.nodes.boolean_expressions.BooleanExpression;

import java.util.List;

public class CustomObject implements BooleanExpression, ArithmeticExpression,
        AttributeValue {
    private List<CustomObject> members;
    Symbol symbol;
    CustomObject parentObject;
    ObjectMember objectMember; //identifier or function


    public CustomObject(ObjectMember objectMember, CustomObject parentObject) {
        this.objectMember = objectMember;
        this.parentObject= parentObject ;

    }

    public CustomObject getParentObject() {
        return parentObject;
    }

    public void setParentObject(CustomObject parentObject) {
        this.parentObject = parentObject;
    }

    public CustomObject(List<CustomObject> members) {
        this.members = members;
    }
    public CustomObject(List<CustomObject> members,Symbol symbol) {
        this(members);
        this.symbol = symbol;

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
