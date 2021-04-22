package SymbolTable;

import java.util.List;

public class Symbol {

    List<String> names;

    public Symbol(List<String> names) {
        this.names = names;
    }

    public List<String> getNames() {
        return names;
    }


    @Override
    public String toString() {
        String textRepresentation = "";
        for (int i = 0; i < this.names.size(); i++)
            textRepresentation +=
                    this.names.get(i) + ((this.names.size() != 1 && i != this.names.size() - 1) ? "." : "");
        return textRepresentation;
    }
}
