package SymbolTable;

import ast.nodes.expressions.AccessedArrayElement;
import ast.nodes.expressions.FunctionCall;
import ast.nodes.expressions.Identifier;
import ast.nodes.expressions.ObjectMember;

import java.util.ArrayList;
import java.util.List;

public class Symbol {

    String name;
    int argumentsCount;
    int symbolType;
    public static int FUNCTION = 0;
    public static int VARIABLE = 1;
    public static int AccessedArrayElement = 2;

    Symbol parent = null;

    public Symbol getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public Symbol(ObjectMember objectMember) {
        if (objectMember.getParent() != null)
            parent = objectMember.getParent().getSymbol();
        name = objectMember.getName();
        if (objectMember instanceof Identifier) {
            symbolType = Symbol.VARIABLE;
        }
        if (objectMember instanceof FunctionCall) {
            symbolType = Symbol.FUNCTION;
            argumentsCount=((FunctionCall)objectMember).getArgumentsCount();
        }
        if (objectMember instanceof AccessedArrayElement)
            symbolType = Symbol.AccessedArrayElement;
    }


    public int getArgumentsCount() {
        return argumentsCount;
    }

    public int getSymbolType() {
        return symbolType;
    }

    @Override
    public String toString() {
        String textRepresentation = "";
        List<String> vars = new ArrayList<>();
        Symbol temp = this;
        while (temp != null) {
            vars.add(temp.name);
            temp = temp.parent;
        }
        for (int i = vars.size() - 1; i > 0; i--) {
            textRepresentation += vars.get(i) + ".";
        }
        textRepresentation += vars.get(0);

        return textRepresentation;
    }
}
