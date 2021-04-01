package ast.nodes.elements;

import ast.nodes.PrintUtil;
import ast.nodes.attributes.Attribute;
import ast.nodes.contents.Content;
import java.util.List;

public class SwitchElement extends HTMLElement {
    private List<SwitchCaseElement> switchCaseElements;

    public SwitchElement(String openingTagName, String closingTagName,
                         List<Attribute> attributes, List<Content> contents,
                         List<SwitchCaseElement> elements) {
        super(openingTagName, closingTagName, attributes, contents);
        this.switchCaseElements = elements;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append("Expression value: ");
        //builder.append(expression).append("\n\n");

        for(SwitchCaseElement element : switchCaseElements)
            builder.append(element.toString());

        return builder.toString();
    }

    public String toString(int col) {
        String ret = "";
        String indent = PrintUtil.getIndent(col);
        ret += super.toString(col);

        for(SwitchCaseElement element : switchCaseElements)
            ret += element.toString(col + 1);

        return ret;
    }
}
