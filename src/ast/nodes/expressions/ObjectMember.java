package ast.nodes.expressions;

import SymbolTable.Symbol;
import ast.nodes.contents.Content;

/**
 * A Marker Interface To Treat All Kinds Of Accessed Identifiers
 * As Object Members.
 */

public interface ObjectMember {

    void setParent(ObjectMember objectMember);

    ObjectMember getParent();

    String getName();

    Symbol getSymbol();

    void setSymbol(Symbol symbol);

}
