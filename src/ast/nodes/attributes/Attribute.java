package ast.nodes.attributes;

import SymbolTable.Symbol;
import SymbolTable.SymbolTable;
import generated.HTMLLexer;

public class Attribute {
    private String name;
    private AttributeValue value;
    private SymbolTable symbolTable;
    private int AttributeIndexedName = 0;
    public static final String HREF_ATTRIBUTE = "href";
    public static final String SRC_ATTRIBUTE = "src";
    public static final String CP_MODEL_ATTRIBUTE = "cp-model";
    public static final String ID_ATTRIBUTE = "id";
    public static final String CLICK_ATTRIBUTE = "@click";
    public static final String MOUSE_OVER_ATTRIBUTE = "@mouseover";
    public static final String FOCUS_ATTRIBUTE = "@focus";
    public static final String CHANGE_ATTRIBUTE = "@change";
    public static final String CP_SWITCH_ATTRIBUTE = "cp-switch";

    public Attribute(String name, AttributeValue value) {
        this.name = name;
        this.value = value;
    }

    public Attribute(String name, AttributeValue value, int AttributeIndexedName) {
        this(name, value);
        this.AttributeIndexedName = AttributeIndexedName;
    }

    public Attribute(String name, AttributeValue value, SymbolTable symbolTable, int AttributeIndexedName) {
        this(name, value, AttributeIndexedName);
        this.symbolTable = symbolTable;
    }

    public String getName() {
        return name;
    }

    public boolean ScopeOpened() {
        return (this.AttributeIndexedName == HTMLLexer.CP_IF
                || this.AttributeIndexedName == HTMLLexer.CP_FOR
                || this.AttributeIndexedName == HTMLLexer.CP_APP
                || this.AttributeIndexedName == HTMLLexer.CP_SWITCH_CASE
                || this.AttributeIndexedName == HTMLLexer.CP_SWITCH_DEFAULT
        );
    }

    @Override
    public String toString() {
        String textRepresentation = this.name + ": ";
        if (this.value != null)
            textRepresentation += this.value;
        return textRepresentation;
    }


    public boolean isHref() {
        return name.equals(HREF_ATTRIBUTE);
    }

    public boolean isSrc() {
        return name.equals(SRC_ATTRIBUTE);
    }

    public boolean isCpModel() {
        return name.equals(CP_MODEL_ATTRIBUTE);
    }

    public boolean isID() {
        return name.equals(ID_ATTRIBUTE);
    }
    public boolean isCpSwitch() {
        return name.equals(CP_SWITCH_ATTRIBUTE);
    }

    public boolean isEvent() {
        return (
                this.name.equals(CLICK_ATTRIBUTE)
                        || this.name.equals(FOCUS_ATTRIBUTE)
                        || this.name.equals(MOUSE_OVER_ATTRIBUTE)
                        || this.name.equals(CHANGE_ATTRIBUTE)
        );
    }


    public boolean isStructural() {
        return (this.AttributeIndexedName == HTMLLexer.CP_IF
                || this.AttributeIndexedName == HTMLLexer.CP_FOR
                || this.AttributeIndexedName == HTMLLexer.CP_SHOW
                || this.AttributeIndexedName == HTMLLexer.CP_HIDE
                || this.AttributeIndexedName == HTMLLexer.CP_SWITCH_CASE
                || this.AttributeIndexedName == HTMLLexer.CP_SWITCH_DEFAULT
        );
    }

    public AttributeValue getValue() {
        return value;
    }

}
