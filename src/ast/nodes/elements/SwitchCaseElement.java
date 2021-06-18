package ast.nodes.elements;

import ast.nodes.PrintUtil;
import ast.nodes.attributes.Attribute;
import ast.nodes.contents.Content;
import java.util.List;

public class SwitchCaseElement extends HTMLElement {

    public SwitchCaseElement() {
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString());

        //if(expression != null) {
          //  builder.append("Expression value: ");
            //builder.append(expression).append("\n\n");
        //}
        //else {
          //  builder.append("Default");
            //builder.append("\n\n");
        //}
        return builder.toString();
    }

    public String toString(int col) {
        return super.toString(col);
    }
}
