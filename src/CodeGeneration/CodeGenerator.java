package CodeGeneration;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CodeGenerator {

    /*
        1- at start: CodeGenerator codeGenerator = new CodeGenerator();
        2- set the cpAppVariable when finding cp-app attribute
        3- generate some code according to the cp-attribute
        4- at the end: call "saveGeneratedCode"
    */

    String cpAppVariable = "";
    String GeneratedCode = "";
                
    public CodeGenerator() {
        this.openScript();
    }

    public void setCpAppVariable(String cpAppVariable) {
        this.cpAppVariable = cpAppVariable;
    }

    public void saveGeneratedCode(String sourceFilename) {
        this.closeScript();

        try {
            String Content = "";
            FileInputStream file = new FileInputStream(sourceFilename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                Content += scanner.nextLine() + "\n";
            }

            FileWriter fileWriter = new FileWriter(sourceFilename + "_GeneratedCode.html");
            fileWriter.write(Content);
            fileWriter.write(GeneratedCode);
            fileWriter.close();

            System.out.println("Code Generated Successfully !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
                
    public void openScript() {
        GeneratedCode += "<script type=\"text/javascript\">\n";
    }
    public void closeScript() {
        GeneratedCode += "</script>";
    }

    public void cpModelBinder(String elementID, String variableName)
    {
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("cpAppVariable", cpAppVariable);
        valuesMap.put("elementID", elementID);
        valuesMap.put("variableName", variableName);

        GeneratedCode += replaceTemplate(Templates.CP_MODEL_TEMPLATE, valuesMap);
    }

    public String replaceTemplate(String Template,  Map<String, String> map)
    {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            Template = Template.replace("{--"+key+"--}" , value);
        }
        return Template;
    }
}