package CodeGeneration;
import CodeGeneration.Templates;
import ast.nodes.attributes.Attribute;
import ast.nodes.boolean_expressions.UnaryBooleanExpression;
import ast.nodes.contents.Content;
import ast.nodes.contents.Pipe;
import ast.nodes.contents.PipedVariable;
import ast.nodes.elements.HTMLElement;
import ast.nodes.expressions.*;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CodeGenerator {

    /*
    
     * 1- at start: CodeGenerator codeGenerator = new CodeGenerator(); 2- set the
     * cpAppVariable when finding cp-app attribute 3- generate some code according
     * to the cp-attribute 4- at the end: call "saveGeneratedCode"
     */

    String cpAppVariable = "";
    String GeneratedCode = "";

    int renders = 0;
    int changes = 0;
    boolean dateFormatterPipe = false;
    boolean currencyPipe = false;
    boolean lowerPipe = false;
    boolean upperPipe = false;

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

            int dotIndex = sourceFilename.indexOf(".");
            String fileName = sourceFilename.substring(0, dotIndex);
            String type = sourceFilename.substring(dotIndex+1);
            String outputFileName = fileName + "_GENERATED." + type;
            FileWriter fileWriter = new FileWriter(outputFileName);
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
        GeneratedCode += "  let renders = [];\n";
        GeneratedCode += "  let new_elements = [];\n";
        GeneratedCode += "  let changes = [];\n\n";
    }

    public void closeScript() {
        if (renders > 0 || changes > 0) {
            GeneratedCode += Templates.RENDER_FUNCTION;
        }
        if(dateFormatterPipe) GeneratedCode += Templates.DATE_FUNCTION;
        if(currencyPipe) GeneratedCode += Templates.CURRENCY_FUNCTION;
        if(lowerPipe) GeneratedCode += Templates.LOWER_FUNCTION;
        if(upperPipe) GeneratedCode += Templates.UPPER_FUNCTION;

        GeneratedCode += "</script>";
    }


    public void cpModelBinder(String elementID, String variableName) {
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("cpAppVariable", cpAppVariable);
        valuesMap.put("elementID", elementID);
        valuesMap.put("variableName", variableName);

        GeneratedCode += replaceTemplate(Templates.CP_MODEL_TEMPLATE, valuesMap);
    }

    public void cpSwitchGenerator(String elementID, String variableName) {
        changes++;
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("cpAppVariable", cpAppVariable);
        valuesMap.put("elementID", elementID);
        valuesMap.put("variableName", variableName);

        GeneratedCode += replaceTemplate(Templates.CP_SWITCH_TEMPLATE, valuesMap);
    }

    public void cpShowGenerator(String elementID, String condition) {
        renders++;
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("cpAppVariable", cpAppVariable);
        valuesMap.put("elementID", elementID);
        valuesMap.put("condition", condition);

        GeneratedCode += replaceTemplate(Templates.CP_SHOW_TEMPLATE, valuesMap);
    }
    public void cpHideGenerator(String elementID, String condition) {
        renders++;
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("cpAppVariable", cpAppVariable);
        valuesMap.put("elementID", elementID);
        valuesMap.put("condition", condition);

        GeneratedCode += replaceTemplate(Templates.CP_HIDE_TEMPLATE, valuesMap);
    }

    public String getFiltersTemplate(PipedVariable pipedVariable) {
        String variableName = pipedVariable.getVariable().toString();
        String filter = "\n\t   () => {\n" +
                        "\t\tlet value = " + cpAppVariable + "." + variableName + "\n";

        List<Pipe> pipes = pipedVariable.getPipes();
        for(Pipe pipe : pipes) {
            String pipeName = pipe.getName();
            if(pipeName.equals("lower")) lowerPipe = true;
            if(pipeName.equals("upper")) upperPipe = true;
            if(pipeName.equals("date")) dateFormatterPipe = true;
            if(pipeName.equals("currency")) currencyPipe = true;

            filter += "\t\tvalue = " + pipeName + "(value";
            if(pipe.getParameter() != null)
                filter += ", \"" + pipe.getParameter() +"\"";
            filter += ");\n";
        }

        filter += "\t\treturn value;\n    \t},\n";
        return filter;
    }

    public void curlyRenderer(String elementID, List<PipedVariable> pipedVariables, String defaultText)
    {
        renders++;
        String filters = "";
        String targetCurlys = "\n";
        for(PipedVariable pipedVariable : pipedVariables) {
            filters += getFiltersTemplate(pipedVariable);
            targetCurlys += "\t\t" + "'" + pipedVariable.stringify() + "',\n";
        }
        targetCurlys += "\t";
        filters += "\t";

        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("elementID", elementID);
        valuesMap.put("defaultText", defaultText);
        valuesMap.put("targetCurlys", targetCurlys);
        valuesMap.put("filters", filters);

        GeneratedCode += replaceTemplate(Templates.CURLY_TEMPLATE, valuesMap);
    }

    public void cpIfGenerator(String elementID, String condition) {
        renders++;
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("condition", condition);
        valuesMap.put("elementID", elementID);

        GeneratedCode += replaceTemplate(Templates.CP_IF_TEMPLATE, valuesMap);
    }

    public String stringifyFunctionParameters(List<Expression> arguments)
    {
        //handleChange(0, "string", varName , function(), !check)
        
        String parameters= "" ;
            for(Expression argument: arguments){
                
                if(argument instanceof Identifier)
                parameters+=cpAppVariable+"."+argument;

                if(argument instanceof FunctionCall)
                parameters+=cpAppVariable+"."+argument;

                if(argument instanceof AccessedArrayElement)
                parameters+=cpAppVariable+"."+argument;

                if(argument instanceof UnaryBooleanExpression)
                    parameters+=((UnaryBooleanExpression)argument).getOperator()
                    +cpAppVariable+"."+((UnaryBooleanExpression)argument).getOperand();

            }
            return parameters;
    }

    public void eventGenerator(String elementID, String eventName, FunctionCall eventFunction)
    {
        changes++;
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("elementID", elementID);
        valuesMap.put("eventName", eventName);
        valuesMap.put("functionName", cpAppVariable+"."+eventFunction.getIdentifier().toString());
        valuesMap.put("parameters", stringifyFunctionParameters(eventFunction.getArguments()));
        GeneratedCode += replaceTemplate(Templates.EVENT_TEMPLATE, valuesMap);
    }

    String templates="";
    public void cpForGenerator(HTMLElement forElement, Attribute forLoop) {
        renders++;
        Map<String, String> valuesMap = new HashMap<>();
        String elementID=forElement.getID();
        valuesMap.put("elementID", elementID);

        //<div cp-for="key,value in Array"></div>

        //<div cp-for="value in Array"></div>

        //<div cp-for="value in Array;i=index"></div>

        valuesMap.put("ArrayName",((ForLoop)forLoop.getValue()).getObject().toString());

        //value=Array[z]
        valuesMap.put("Value",((ForLoop)forLoop.getValue()).getValue().toString()+"="+valuesMap.get("ArrayName")+"[z]");

        //i=z
        if(((ForLoop)forLoop.getValue()).getIndex()!=null)
        valuesMap.put("Index",((ForLoop)forLoop.getValue()).getIndex().toString()+"=z");
        else valuesMap.put("index","");

        //key=z
        if(((ForLoop)forLoop.getValue()).getKey()!=null)
        valuesMap.put("Key",((ForLoop)forLoop.getValue()).getKey().toString()+"=z");
        else valuesMap.put("Key","");


        GeneratedCode += replaceTemplate(Templates.FOR_TEMPLATE_OPENING, valuesMap);
    }
    public void cpForGeneratorClose(String elementID) {
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("elementID", elementID);
        GeneratedCode += replaceTemplate(Templates.FOR_TEMPLATE_CLOSING, valuesMap);

    }

    public String replaceTemplate(String Template, Map<String, String> valuesMap) {
        for (Map.Entry<String, String> entry : valuesMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            Template = Template.replace("{--" + key + "--}", value);
        }
        return Template;
    }
}
 






