package ast.nodes.attributes;

public class Attribute {
    private String name;
    private AttributeValue value;

    public Attribute(String name, AttributeValue value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        String textRepresentation = this.name + ": ";
        if (this.value != null)
            textRepresentation += this.value;
        return textRepresentation;
    }
}
