
import ast.nodes.HtmlDocument;
import ast.visitor.ASTBuilder;
import generated.HTMLLexer;
import generated.HTMLParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static org.antlr.v4.runtime.CharStreams.fromFileName;

public class Main {

    private static String FILE_PATH = "samples//samples.html";
    private static int COUNTER = 0;




    public static void main(String[] args) {

//        try {
//            loadTagsWithIds();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        String source;
        CharStream cs = null;
        try {
            cs = fromFileName(FILE_PATH);
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
    private static void loadTagsWithIds() throws IOException {

        // read file
        String Content = "";
        FileInputStream file = new FileInputStream(FILE_PATH);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            Content += scanner.nextLine() + "\n";
        }

        Document doc = Jsoup.parse(Content);
        Elements body = doc.select("body");

        // update ids
        loadTagsWithIdsForElement(body.first());
        Elements elements = doc.body().select("*");
        doc.body().children().select("*");

        // write to the same file
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        fileWriter.write(doc.toString());
        fileWriter.close();
    }

    private static void loadTagsWithIdsForElement(Element element) {
        for (Element child : element.children()) {

            // load id for the child
            String hasID = element.attr("id");
            if (hasID.equals("")) {
                String id = "id_";
                id += COUNTER++;
                element.attr("id", id);
            }

            if(child.childrenSize() > 0) {
                loadTagsWithIdsForElement(child);
            }
        }
    }



}
