package ast.nodes;

import SymbolTable.SymbolTable;
import ast.nodes.elements.*;

import java.util.List;

public class HtmlDocument extends Node {
    private List<Scriptlet> scriptletElements;
    private XML XMLElement;
    private DTD DTDElement;
    private List<Element> elements;

    public HtmlDocument(List<Scriptlet> scriptletElements, XML XMLElement,
                        DTD DTDElement, List<Element> elements) {
        this.scriptletElements = scriptletElements;
        this.XMLElement = XMLElement;
        this.DTDElement = DTDElement;
        this.elements = elements;

    }

    @Override
    public String toString() {
        String textRepresentation = "";
        for (Scriptlet scriptlet : this.scriptletElements)
            textRepresentation += scriptlet + "\n";
        if (this.XMLElement != null)
            textRepresentation += this.XMLElement + "\n";
        if (this.DTDElement != null)
            textRepresentation += this.DTDElement + "\n";

        for (Element element : this.elements)
            textRepresentation += element + "\n";


        return textRepresentation;
    }

    public String toString(int col) {
        String indent = PrintUtil.getIndent(col);
        String textRepresentation = "";
        if (this.scriptletElements != null && this.scriptletElements.size() != 0) {
            textRepresentation += PrintUtil.HORIZONTAL_LINE +
                    "Scriptlets:\n";
            for (Scriptlet scriptlet : this.scriptletElements) {
                textRepresentation += scriptlet.toString(col) + "\n";
            }
        }
        if (this.XMLElement != null) {
            textRepresentation += PrintUtil.HORIZONTAL_LINE +
                    "XML Element:\n" + this.XMLElement.toString(col) + "\n";
        }
        if (this.DTDElement != null) {
            textRepresentation += PrintUtil.HORIZONTAL_LINE +
                    "DTD Element:\n" + this.DTDElement.toString(col) + "\n";
        }
        if (this.elements != null && this.elements.size() != 0) {
            textRepresentation += PrintUtil.HORIZONTAL_LINE +
                    "Elements:\n";
            for (Element element : this.elements)
                textRepresentation += element.toString(col) + "\n";
        }
        return textRepresentation;
    }
}
