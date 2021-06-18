parser grammar HTMLParser;

options { tokenVocab=HTMLLexer; }

htmlDocument : SCRIPTLET* XML? SCRIPTLET* DTD? SCRIPTLET* element* ;

element
    :  TAG_OPEN TAG_NAME attribute*
       (TAG_CLOSE (content* TAG_OPEN TAG_SLASH TAG_NAME TAG_CLOSE)? | TAG_SLASH_CLOSE)   #htmlElement
    | switchE                                                                            #switchElement
    | SCRIPTLET                                                                          #scriptletElement
    | SCRIPT_OPEN (SCRIPT_BODY | SCRIPT_SHORT_BODY)                                      #scriptElement
    | STYLE_OPEN (STYLE_BODY | STYLE_SHORT_BODY)                                         #styleElement
    ;

attribute
    :
      CP_INCLUDE    ATTRIBUTE_EQUALS   DOUBLE_QUOTE_OPEN   objectChainedMembers   DQ   #cp_includeAttribute
    | CP_PARAMETERS ATTRIBUTE_EQUALS   DOUBLE_QUOTE_OPEN   objectChainedMembers   DQ   #cp_parametersAttribute
    | CHANGE        ATTRIBUTE_EQUALS   DOUBLE_QUOTE_OPEN   objectChainedMembers   DQ   #changeAttribute
    | FOCUS         ATTRIBUTE_EQUALS   DOUBLE_QUOTE_OPEN   objectChainedMembers   DQ   #focusAttribute

    | CP_APP        ATTRIBUTE_EQUALS   DOUBLE_QUOTE_OPEN   IDENTIFIER             DQ   #cp_appAttribute
    | CP_SHOW       ATTRIBUTE_EQUALS   DOUBLE_QUOTE_OPEN   booleanExpression      DQ   #cp_showAttribute
    | CP_HIDE       ATTRIBUTE_EQUALS   DOUBLE_QUOTE_OPEN   booleanExpression      DQ   #cp_hideAttribute
    | CP_IF         ATTRIBUTE_EQUALS   DOUBLE_QUOTE_OPEN   booleanExpression      DQ   #cp_ifAttribute
    | CP_FOR        ATTRIBUTE_EQUALS   DOUBLE_QUOTE_OPEN   forLoop                DQ   #cp_forAttribute
    | CP_MODEL      ATTRIBUTE_EQUALS   DOUBLE_QUOTE_OPEN   numericValue           DQ   #cp_modelAttribute
    | CLICK         ATTRIBUTE_EQUALS   DOUBLE_QUOTE_OPEN   objectChainedMembers   DQ   #clickAttribute
    | MOUSEOVER     ATTRIBUTE_EQUALS   DOUBLE_QUOTE_OPEN   objectChainedMembers   DQ   #mouseoverAttribute
    | TAG_NAME     (TAG_EQUALS        ATTVALUE_VALUE)?                                 #non_cpAttribute
    ;

booleanExpression
    : booleanValue                                                                 #primitiveBooleanExpression
    | comparisonExpression                                                         #binaryComparisonExpression
    | booleanExpression (booleanOperator | equalityOperator) booleanExpression     #binaryBooleanExpression
    | NOT booleanExpression                                                        #unaryBooleanExpression
    | OPENING_PARENTHESES booleanExpression CLOSING_PARENTHESES                    #parenthesizedBooleanExpression
    ;

booleanOperator
    : AND
    | OR
    | XOR
    ;

    equalityOperator
    : EQUALITY
    | INEQUALITY
    ;


// TRUE & FALSE Are Boolean Literals & We Prevent Their Use In Places Where A Number Is Expected
// Like (11 > true)
booleanValue
    : TRUE                   #booleanLiteral
    | FALSE                  #booleanLiteral
    | objectChainedMembers   #booleanObjectAccessedMember
    ;

// This Rule Is A Special Case Of A Boolean Expression
// As It Boils Down To A Boolean Value But Its Operands Must Be Arithmetic (A Number Eventually)
comparisonExpression
    : arithmeticExpression (comparisonOperator | equalityOperator) arithmeticExpression
    ;

comparisonOperator
    : GREATER
    | SMALLER
    | GREATER_EQUALS
    | SMALLER_EQUALS
    ;

arithmeticExpression
    : numericValue                                                    #primitiveArithmeticExpression
    | arithmeticExpression arithmeticOperator arithmeticExpression    #binaryArithmeticExpression
    | (PLUS | MINUS) arithmeticExpression                             #unaryArithmeticExpression
    | OPENING_PARENTHESES arithmeticExpression CLOSING_PARENTHESES    #parenthesizedArithmeticExpression
    ;

arithmeticOperator
    : PLUS
    | MINUS
    | MULTIPLICATION
    | DIVISION
    | MODULUS
    ;

// Number Is A Numeric Literal & We Prevent Its Use In Places Where A Boolean Is Expected
// Like (2 && 3)
numericValue
    : NUMBER                 #numericLiteral
    | objectChainedMembers   #numericObjectAccessedMember
    ;

objectChainedMembers : objectMember (DOT objectMember)* ;

// IDENTIFIER, accessedArrayElement & functionCall Are Grouped In This Rule
// Because They're The Only Kind Of Expressions That Can
// Boil Down To A Boolean Value Or A Numeric Value
// (While TRUE, FALSE & NUMBER Cannot, So They're Separated In Their Own Rules)
objectMember
    : IDENTIFIER             #identifier
    | accessedArrayElement   #accessedElement
    | functionCall           #functionProvoke
    ;

accessedArrayElement : IDENTIFIER OPENING_BRACKET arithmeticExpression CLOSING_BRACKET ;

functionCall : IDENTIFIER OPENING_PARENTHESES functionArguments? CLOSING_PARENTHESES ;

// The Function Argument Can Be Any Type Of Expression,
// Boolean Or Arithmetic, Simple Or Complex.
functionArguments : expression (COMMA expression)* ;

expression
    : STRING_LITERAL         #stringLiteral
    | booleanExpression      #booleanExpressionArgument
    | arithmeticExpression   #arithmeticExpressionArgument
    ;

forLoop
    : IDENTIFIER IN (array | objectChainedMembers) (SEMICOLON IDENTIFIER ASSIGNMENT INDEX)?   #forLoop1
    | IDENTIFIER COMMA IDENTIFIER IN objectChainedMembers                                     #forLoop2
    ;

array : OPENING_BRACKET
        (arrayElement (COMMA arrayElement)*)?
        CLOSING_BRACKET
      ;

arrayElement
    : expression   #expressionArrayElement
    | array        #nestedArrayElement
    ;

// Do Not Erase The 'E', switch is like a keyword to ANTLR, so things are getting mixed up ..
switchE
    : TAG_OPEN TAG_NAME attribute* switchAttribute attribute* TAG_CLOSE
      (switchCase)*
      TAG_OPEN TAG_SLASH TAG_NAME TAG_CLOSE
    ;

switchAttribute : CP_SWITCH ATTRIBUTE_EQUALS DOUBLE_QUOTE_OPEN expression DQ ;

switchCase
    : TAG_OPEN TAG_NAME attribute* switchCaseAttribute attribute* TAG_CLOSE
      (content*)
      TAG_OPEN TAG_SLASH TAG_NAME TAG_CLOSE
    ;

switchCaseAttribute
    : CP_SWITCH_CASE ATTRIBUTE_EQUALS DOUBLE_QUOTE_OPEN expression DQ
    | CP_SWITCH_DEFAULT
    ;

content
    : element   #elementContent
    | text      #textContent
    | curly     #curlyContent
    ;

text : (HTML_TEXT | CURLY_OPEN)+ ;

curly
    : DOUBLE_CURLY_OPEN pipedVariable DOUBLE_CURLY_CLOSE         #curlyVariables
    | DOUBLE_CURLY_OPEN exprToExecute DOUBLE_CURLY_CLOSE         #curlyExpression
    ;

ternaryOperator
    : booleanExpression QUESTION_MARK exprToExecute COLON exprToExecute   #basicTernaryOperator
    | OPENING_PARENTHESES ternaryOperator CLOSING_PARENTHESES             #parenthesizedTernaryOperator
    ;

exprToExecute
    : ternaryOperator                                         #ternaryExprToExecute
    | OPENING_PARENTHESES exprToExecute CLOSING_PARENTHESES   #parenthesizedExprToExecute
    | expression                                              #exp
    ;


pipes
    :(PIPE IDENTIFIER+ (COLON STRING_LITERAL)?)              #pipe
    ;
pipedVariable : variable pipes*;

variable : objectChainedMembers (COLON STRING_LITERAL)? ;
