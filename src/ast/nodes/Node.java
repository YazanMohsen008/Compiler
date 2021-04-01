package ast.nodes;

public class Node {
    private int line;
    private int column;

    public void setLine(int line) {
        this.line = line;
    }
    public void setColumn(int column) {
        this.column = column;
    }
    public int getLine() {
        return line;
    }
    public int getColumn() {
        return column;
    }
}
