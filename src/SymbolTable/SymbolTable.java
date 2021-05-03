package SymbolTable;

import ast.nodes.expressions.AccessedArrayElement;
import ast.nodes.expressions.FunctionCall;
import ast.nodes.expressions.Identifier;
import ast.nodes.expressions.ObjectMember;

import java.util.ArrayList;
import java.util.List;

public class SymbolTable {
    String name;
    List<Symbol> symbols;
    List<SymbolTable> children;
    SymbolTable parent;

    public SymbolTable() {
        symbols = new ArrayList<>();
        children = new ArrayList<>();
    }
    public SymbolTable(String name) {
        this();
        this.name = name;
    }


    public SymbolTable getParent() {
        return parent;
    }

    public void setParent(SymbolTable parent) {
        this.parent = parent;
    }


    public String getName() {
        return name;
    }

    public List<Symbol> getSymbols() {
        return symbols;
    }



    public Symbol insert(ObjectMember objectMember) {
        Symbol symbol = getSymbol(objectMember);
        if (symbol == null) {
            symbol = new Symbol(objectMember);
            symbols.add(symbol);
        }
        return symbol;
    }


    public void print() {
        System.out.print("Symbol Table : " + name);
        if (parent != null)
            System.out.println(" SON OF " + parent.name);
        System.out.println();
        for (Symbol symbol : symbols)
            System.out.println("           " + symbol);
    }

    public void addChild(SymbolTable child) {
        children.add(child);
    }

    public SymbolTable getChild(int index) {
        return children.get(index);
    }

    public int getChildrenCount() {
        return children.size();
    }

    @Override
    public String toString() {
        String Symbols = "Symbols : ";
        for (Symbol symbol : symbols)
            Symbols += symbol;
        return Symbols;
    }


    public Symbol getSymbol(ObjectMember objectMember) {
        for (Symbol symbol : symbols) {
            if (checkSymbol(objectMember, symbol))
                return symbol;
        }
        return null;
    }

    public boolean checkSymbol(ObjectMember objectMember, Symbol symbol) {

        if (!objectMember.getName().equals(symbol.name)) {
            return false;
        }

        //Check to Store Function And Variable with same name
        if (!checkTypes(objectMember, symbol)) {
            return false;
        }

        if (symbol.getSymbolType()==Symbol.FUNCTION) {
            //Check For override methods
            if(!(((FunctionCall)objectMember).getArgumentsCount()==symbol.getArgumentsCount())) {
                return false;
            }
        }

        if (objectMember.getParent() == null && symbol.getParent() == null)
            return true;

        if (objectMember.getParent() != null && symbol.getParent() != null)
            if (checkSymbol(objectMember.getParent(), symbol.getParent()))
                return true;
        return false;
    }

    boolean checkTypes(ObjectMember objectMember, Symbol symbol) {

        if (objectMember instanceof Identifier && symbol.getSymbolType() == Symbol.VARIABLE)
            return true;
        if (objectMember instanceof FunctionCall && symbol.getSymbolType() == Symbol.FUNCTION)
            return true;
        if (objectMember instanceof AccessedArrayElement && symbol.getSymbolType() == Symbol.AccessedArrayElement)
            return true;

        return false;
    }

}

