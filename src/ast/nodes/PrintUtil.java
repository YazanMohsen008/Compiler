package ast.nodes;

public class PrintUtil {

    public static final String HORIZONTAL_LINE = "===================================\n";

    public static String getIndent(int col) {
        String ret = "";
        for(int i = 1 ; i < col ; i++)
            ret += "\t\t";
        if(col != 0)
            ret += "\t|\t";
        return ret;
    }

}
