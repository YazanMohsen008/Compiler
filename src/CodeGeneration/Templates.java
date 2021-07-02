package CodeGeneration;

public class Templates {

        // To make a template variable use this formula: {--variableToReplace--}

        public static String CP_MODEL_TEMPLATE = "    //cp model template \n"
                        + "    if ({--cpAppVariable--}.{--variableName--}) {\n"
                        + "      document.getElementById(\"{--elementID--}\").value =\n"
                        + "        {--cpAppVariable--}.{--variableName--};\n" + "    }\n"
                        + "    document.getElementById(\"{--elementID--}\").addEventListener(\"input\", (event) => {\n"
                        + "      {--cpAppVariable--}.{--variableName--} = event.target.value;\n" + "    });\n\n";

        public static String CURLY_TEMPLATE = "   //Curly Braces Renderer\n "
                        + "   const curly_render_function_{--elementID--}_{--variableName--} = function() {\n"
                        + "    const defaultText = \"{--defaultText--}\";\n"
                        + "    if({--cpAppVariable--}.{--variableName--} !== undefined) {\n"
                        + "      document.getElementById(\"{--elementID--}\").innerHTML = defaultText.replace(\n"
                        + "        `{{{--variableName--}}}`,\n" + "        {--cpAppVariable--}.{--variableName--}\n"
                        + "      )\n" + "    }\n" + "  }\n"
                        + "  renders.push(curly_render_function_{--elementID--}_{--variableName--});\n\n";


        public static String CP_SHOW_TEMPLATE =
            " //cp-show template \n" +
            "   const cp_show_function_{--elementID--} = function() {\n" +
            "    const showElement = document.getElementById(\"{--elemnetID--}\");\n" +
            "    if ({--condition--})\n" +
            "      showElement.hidden = true;\n" +
            "    else\n" +
            "      showElement.hidden = false;" +
            "  }\n" +
            "  renders.push(cp_show_function_{--elementID--});\n\n";


        public static String CP_HIDE_TEMPLATE =
            " //cp-hide template \n" +
            "  const cp_hide_function_{--elementID--} = function() {\n" +
            "    const hideElement = document.getElementById(\"{--elementID--}\");\n" +
            "    if ({--condition--})\n" +
            "      hideElement.hidden = true;\n" +
            "    else\n" +
            "      hideElement.hidden = false"+
            "  }\n" +
            "  renders.push(cp_hide_function_{--elementID--});\n\n";



        public static String CP_IF_TEMPLATE = 
            "	// cp-if generated code \n" +
            "   const cp_if_function_{--elementID--} = function() {\n" +
            "    const ifElement = document.getElementById(\"{--elementID--}\");\n" +
            "    if (!{--condition--}) {\n" +
            "      ifElement.hidden = true;\n" +
            "    }\n" +
            "    else{\n" +
            "      ifElement.hidden = false;\n" +
            "    }\n" +
            "  }\n" +
            "  renders.push(cp_if_function_{--elementID--});\n\n";


        public static String EVENT_TEMPLATE = 
        "	 // cp-event \n" +
        "      const event_handler_{--elementID--}_{--eventName--} = function() {\n" +
        "        document.getElementById(\"{--elementID--}\").addEventListener(\"{--eventName--}\", (event) => {\n" +
        "            {--functionName--}({--parameters--});\n"+
        "        });\n" +
        "    }\n" +
        "    changes.push(event_handler_{--elementID--}_{--eventName--});\n\n";



        public static String CP_SWITCH_TEMPLATE = "  //cp switch\n"
                        + "  const switchFunc_{--elementID--} = function () {\n"
                        + "    const switchElement = document.getElementById(\"{--elementID--}\");\n"
                        + "    const value = {--cpAppVariable--}.{--variableName--};\n" + "\n"
                        + "    let matchFound = false;\n"
                        + "    for (let i = 0; i < switchElement.children.length - 1; i++) {\n"
                        + "      const child = switchElement.children[i];\n"
                        + "      const switchCaseValue = child.getAttribute(\"cp-switch-case\");\n" + "\n"
                        + "      //Switch Case\n" + "      if (switchCaseValue !== null) {\n" + "         //match case\n"
                        + "         if (!matchFound && switchCaseValue == value) {\n"
                        + "           child.hidden = false;\n" + "           matchFound = true;\n"
                        + "         } else {\n" + "           child.hidden = true;\n" + "         }\n" + "      }\n"
                        + "    }\n" + "    const lastChildren =\n"
                        + "      switchElement.children[switchElement.children.length - 1];\n"
                        + "    if (!matchFound && lastChildren.getAttribute(\"cp-switchDefault\") !== null) {\n"
                        + "      lastChildren.hidden = false;\n" + "    } else {\n"
                        + "      lastChildren.hidden = true;\n" + "    }\n" + "  };\n"
                        + "  renders.push(switchFunc_{--elementID--});\n\n";

        public static String RENDER_FUNCTION = "  function render() {\n" + "    setInterval(() => {\n"
                        + "      for(let i = 0; i < changes.length; i++) {\n" + "          changes[i]();\n"
                        + "      }\n" + "      changes = [];\n" + "      for (let i = 0; i < renders.length; i++) {\n"
                        + "         renders[i]();\n" + "      }\n" + "    }, 1000);\n" + "  }\n" + "  render();\n\n";



        // built in function

        // to lower
        public static String LOWER_FUNCTION = 
                        "function lower(str) {\n" +
                        "  return str.toLowerCase();\n" +
                        "}";

        // to upper 
        public static String UPPER_FUNCTION = 
                        "function upper(str) {\n" +
                        "  return str.toUpperCase();\n" +
                        "}";
                        
        // currency
        public static String CURRENCY_FUNCTION = 
                        "function currency(value, curr) {\n" +
                        "  let symbol;\n" +
                        "  switch (curr) {\n" +
                        "    case \"en\":\n" +
                        "      symbol = \"$\";\n" +
                        "      break;\n" +
                        "  }\n" +
                        "  return sybmol + value;\n" +
                        "}\n";

        // date
        public static String DATE_FUNCTION = 
                    "function dateFormatter(date, format) {\n" +
                    "  const year = new Intl.DateTimeFormat(\"en\", { year: \"numeric\" }).format(date);\n" +
                    "  const month = new Intl.DateTimeFormat(\"en\", { month: \"2-digit\" }).format(\n" +
                    "    date\n" +
                    "  );\n" +
                    "  const day = new Intl.DateTimeFormat(\"en\", { day: \"2-digit\" }).format(date);\n" +
                    "  const hour = date.getHours();\n" +
                    "  const min = date.getMinutes();\n" +
                    "  const sec = date.getSeconds();\n" +
                    "\n" +
                    "  switch (format) {\n" +
                    "    case \"mm-yyyy\":\n" +
                    "      return `${month}-${year}`;\n" +
                    "    case \"mm-yy\":\n" +
                    "      return `${month}-${year.slice(2)}`;\n" +
                    "    case \"dd/mm/yyyy h:m:s\":\n" +
                    "      return `${day}/${month}/${year} ${hour}:${min}:${sec}`;\n" +
                    "    case \"dd/mm/yyyy h:m\":\n" +
                    "      return `${day}/${month}/${year} ${hour}:${min}`;\n" +
                    "    case \"mm/dd/yyyy h:m:s\":\n" +
                    "      return `${month}/${day}/${year} ${hour}:${min}:${sec}`;\n" +
                    "    case \"mm/dd/yyyy h:m\":\n" +
                    "      return `${month}/${day}/${year} ${hour}:${min}`;\n" +
                    "    case \"mm-dd-yyyy h:m\":\n" +
                    "      return `${month}-${day}-${year} ${hour}:${min}`;\n" +
                    "    case \"dd-mm-yyyy h:m:s\":\n" +
                    "      return `${day}-${month}-${year} ${hour}:${min}:${sec}`;\n" +
                    "    case \"dd-mm-yyyy h:m\":\n" +
                    "      return `${day}-${month}-${year} ${hour}:${min}`;\n" +
                    "    case \"mm-dd-yyyy h:m:s\":\n" +
                    "      return `${month}-${day}-${year} ${hour}:${min}:${sec}`;\n" +
                    "    default:\n" +
                    "      return `${day}/${month}/${year}`;\n" +
                    "  }\n" +
                    "}\n";        

}

    
