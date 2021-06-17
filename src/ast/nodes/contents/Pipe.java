package ast.nodes.contents;

import ast.nodes.PrintUtil;

public class Pipe {

    private String name;
    private String parameter;

    static final String DATE = "date";
    static final String CURRENCY = "currency";
    static final String UPPER = "upper";
    static final String LOWER = "lower";

    public Pipe(String name) {
        this.name = name;
    }

    public Pipe(String name, String parameter) {
        this.name = name;
        this.parameter = parameter.substring(1,parameter.length()-1);
    }

    public String validatePipe() {

        switch (name) {
            case DATE:
                return dateValidation();
            case CURRENCY:
                return (parameter.length() == 1) ? null : "Currency pipe should receive one character only.";
            case UPPER:
                return (parameter == null) ? null : "Upper pipe should not receive any parameter";
            case LOWER:
                return (parameter == null) ? null : "Lower pipe should not receive any parameter";
        }
        return null;
    }

    private String dateValidation() {

        if(!(
                  parameter.equalsIgnoreCase("dd/mm/yyyy h:m:s")
                ||parameter.equalsIgnoreCase("dd/mm/yyyy h:m")

                ||parameter.equalsIgnoreCase("mm/dd/yyyy h:m:s")
                ||parameter.equalsIgnoreCase("mm/dd/yyyy h:m")

                ||parameter.equalsIgnoreCase("dd-mm-yyyy h:m:s")
                ||parameter.equalsIgnoreCase("dd-mm-yyyy h:m")

                ||parameter.equalsIgnoreCase("mm-dd-yyyy h:m:s")
                ||parameter.equalsIgnoreCase("mm-dd-yyyy h:m")

                ||parameter.equalsIgnoreCase("mm-yyyy")
                ||parameter.equalsIgnoreCase("mm-yy"))
        )
        return  "Date pipe should only receive date format";

        return null;
    }

    public String toString() {
        String textRepresentation = "";
        textRepresentation += "pipe: ";
        textRepresentation += name;
        if(parameter!=null){
            textRepresentation += " : ";
            textRepresentation += parameter;
        }
        return textRepresentation;
    }
}
