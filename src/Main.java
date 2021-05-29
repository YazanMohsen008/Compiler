
import ast.nodes.HtmlDocument;
import ast.visitor.ASTBuilder;
import generated.HTMLLexer;
import generated.HTMLParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import static org.antlr.v4.runtime.CharStreams.fromFileName;

public class Main {


    public static void main(String[] args) {
        String source;
        CharStream cs = null;
        try {
            source = "samples//samples.txt";
            cs = fromFileName(source);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HTMLLexer lexer = new HTMLLexer(cs);
        CommonTokenStream token = new CommonTokenStream(lexer);
        HTMLParser parser = new HTMLParser(token);
        ParseTree tree = parser.htmlDocument();
        HtmlDocument AST = (HtmlDocument) new ASTBuilder().visit(tree);
        System.out.println(AST.toString(0));


    }
}