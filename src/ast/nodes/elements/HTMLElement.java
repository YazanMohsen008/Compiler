package ast.nodes.elements;

import SymbolTable.SymbolTable;
import ast.nodes.PrintUtil;
import ast.nodes.attributes.Attribute;
import ast.nodes.contents.Content;
import java.util.List;

public class HTMLElement implements Element {
    private String openingTagName;
    private String closingTagName;
    private List<Attribute> attributes;
    private List<Content> contents;
    private SymbolTable symbolTable;

    public HTMLElement() {
    }

    public HTMLElement(String openingTagName, String closingTagName, List<Attribute> attributes,
                       List<Content> contents) {
        this.openingTagName = openingTagName;
        this.closingTagName = closingTagName;
        this.attributes = attributes;
        this.contents = contents;
    }

    public String getOpeningTagName() {
        return openingTagName;
    }

    public void setOpeningTagName(String openingTagName) {
        this.openingTagName = openingTagName;
    }

    public String getClosingTagName() {
        return closingTagName;
    }

    public void setClosingTagName(String closingTagName) {
        this.closingTagName = closingTagName;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    @Override
    public String toString() {
        String textRepresentation = "";
        textRepresentation += "Opening Tag Name: " + this.openingTagName + "\n";
        textRepresentation += "Closing Tag Name: " + this.closingTagName + "\n";
        textRepresentation += "Attributes:\n";
        for(Attribute attribute: this.attributes)
            textRepresentation += attribute + "\n";
        if(this.contents != null) {
            for (Content content : this.contents)
                textRepresentation += "Content:\n" + content + "\n";
        }
        return textRepresentation;
    }

    public String toString(int col) {
        String textRepresentation = "";
        String indent = PrintUtil.getIndent(col);
        textRepresentation += indent + "Opening Tag Name: " + this.openingTagName + "\n";
        if(this.attributes != null && this.attributes.size() != 0) {
            textRepresentation += indent + "Attributes:\n";
            for(Attribute attribute : this.attributes)
                textRepresentation += indent + attribute + "\n";
        }
        if(this.contents != null && this.contents.size() != 0) {
            for(Content content : this.contents)
                textRepresentation += indent + "Content:\n"
                            + content.toString(col + 1) + "\n";
        }
        if(this.closingTagName != null)
            textRepresentation += indent + "Closing Tag Name: " + this.closingTagName + "\n";
        textRepresentation += indent + "-----------------------\n";
        return textRepresentation;
    }
}
