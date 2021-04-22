import SymbolTable.Symbol;
import SymbolTable.SymbolTable;
import ast.nodes.HtmlDocument;
import ast.visitor.ASTBuilder;
import generated.HTMLLexer;
import generated.HTMLParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.antlr.v4.runtime.CharStreams.fromFileName;

public class Main {

    static Symbol A;
    static Symbol B;
    static Symbol C;

    public static void main(String[] args) {
        String source ;
        CharStream cs=null;
        try {
             source = "samples//samples.txt";
             cs = fromFileName(source);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        HTMLLexer lexer = new HTMLLexer(cs);
        CommonTokenStream token  = new CommonTokenStream(lexer);
        HTMLParser parser = new HTMLParser(token);
        ParseTree tree = parser.htmlDocument();
        HtmlDocument AST = (HtmlDocument)new ASTBuilder().visit(tree);
        System.out.println(AST.toString(0));

//        SymbolTable symbolTable =SymbolTable.getInstance();
//        System.out.println("Symbol Table");
//        symbolTable.print();
//        Symbol S =symbolTable.getSymbol("isAuthenticated");
//        S.setName("isAuthenticated Change");
//        System.out.println("Symbol Table After Change");
//        symbolTable.print();
    }

//    private static void   testSymbolTable(){
//        SymbolTable symbolTable =SymbolTable.getInstance();
//        symbolTable.insert("X",false);
//        symbolTable.insert("X",false);
//        symbolTable.print();
//
//
//        Symbol X=symbolTable.getSymbol("X");
//        X.setName("C");
//        System.out.println("after Change");
//        symbolTable.print();
//
//
//    }
}
