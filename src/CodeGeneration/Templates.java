package CodeGeneration;

public class Templates {

    //To make a template variable use this formula:   {--variableToReplace--}

    public static String CP_MODEL_TEMPLATE = 
        "    //cp model template \n" +
        "    if ({--cpAppVariable--}.{--variableName--}) {\n" +
        "      document.getElementById({--elementID--}).value =\n" +
        "        {--cpAppVariable--}.{--variableName--};\n" +
        "    }\n" +
        "    document.getElementById({--elementID--}).addEventListener(\"input\", (event) => {\n" +
        "      {--cpAppVariable--}.{--variableName--} = event.target.value;\n" +
        "    });\n";
}