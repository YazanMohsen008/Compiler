package SymbolTable;

import java.util.ArrayList;
import java.util.List;

public class SymbolTable {
    String name;
    List<Symbol> symbols;
    List<SymbolTable> children;
    SymbolTable parent;

    public SymbolTable(String name) {
        this();
        this.name = name;
    }

    public SymbolTable() {
        symbols = new ArrayList<>();
        children = new ArrayList<>();
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

    public void setName(String name) {
        this.name = name;
    }


    public List<Symbol> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<Symbol> symbols) {
        this.symbols = symbols;
    }


    public Symbol insert(List<String> symbolsName) {
        Symbol symbol = getSymbol(symbolsName);
        if (symbol == null) {
            symbol = new Symbol(symbolsName);
            symbols.add(symbol);
        }
        return symbol;
    }


    public void print() {
        System.out.print("Symbol Table : "+name);
        if (parent != null)
            System.out.println(" SON OF " + parent.name);
        System.out.println();
        for (Symbol symbol : symbols)
            System.out.println("           "+symbol);
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


    public Symbol getSymbol(List<String> symbolsName) {
        for (Symbol symbol : symbols) {
            if (symbolsName.size() != symbol.getNames().size())
                continue;
            for (int i = 0; i < symbolsName.size(); i++) {
                if (!symbolsName.get(i).equals(symbol.getNames().get(i)))
                    return null;
            }
            return symbol;
        }
        return null;
    }

}
