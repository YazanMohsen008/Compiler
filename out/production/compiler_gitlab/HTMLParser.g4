parser grammar HTMLParser;

options { tokenVocab=HTMLLexer; }

htmlDocument : SCRIPTLET* XML? SCRIPTLET* DTD? SCRIPTLET* element* ;

element
    :  (SEA_WS)*TAG_OPEN  TAG_NAME (TAG_WS)* attribute*
      (TAG_CLOSE (SEA_WS)*(content* (SEA_WS)* TAG_OPEN TAG_SLASH TAG_NAME TAG_CLOSE (SEA_WS)*)? | TAG_SLASH_CLOSE(SEA_WS)*)   #htmlElement
    | (SEA_WS)* switchE                                                                            #switchElement
    | (SEA_WS)* SCRIPTLET   (SEA_WS)*                                                                        #scriptletElement
    | (SEA_WS)* SCRIPT_OPEN (SCRIPT_BODY | SCRIPT_SHORT_BODY)                                      #scriptElement
    | (SEA_WS)* STYLE_OPEN (STYLE_BODY | STYLE_SHORT_BODY)                                         #styleElement
    ;

attribute
    :
      CP_INCLUDE    (CP_MIDDLE_WS)* ATTRIBUTE_EQUALS (CP_MIDDLE_WS)* DOUBLE_QUOTE_OPEN  (CP_WS)* objectChainedMembers   DQ(TAG_WS)*   #cp_includeAttribute
    | CP_PARAMETERS (CP_MIDDLE_WS)* ATTRIBUTE_EQUALS (CP_MIDDLE_WS)* DOUBLE_QUOTE_OPEN  (CP_WS)* objectChainedMembers   DQ (TAG_WS)* #cp_parametersAttribute
    | CHANGE        (CP_MIDDLE_WS)* ATTRIBUTE_EQUALS (CP_MIDDLE_WS)* DOUBLE_QUOTE_OPEN  (CP_WS)* objectChainedMembers   DQ (TAG_WS)* #changeAttribute
    | FOCUS         (CP_MIDDLE_WS)* ATTRIBUTE_EQUALS (CP_MIDDLE_WS)* DOUBLE_QUOTE_OPEN  (CP_WS)* objectChainedMembers   DQ (TAG_WS)* #focusAttribute
    | CP_APP        (CP_MIDDLE_WS)* ATTRIBUTE_EQUALS (CP_MIDDLE_WS)* DOUBLE_QUOTE_OPEN  (CP_WS)* IDENTIFIER             DQ (TAG_WS)* #cp_appAttribute
    | CP_SHOW       (CP_MIDDLE_WS)* ATTRIBUTE_EQUALS (CP_MIDDLE_WS)* DOUBLE_QUOTE_OPEN  (CP_WS)* booleanExpression      DQ (TAG_WS)* #cp_showAttribute
    | CP_HIDE       (CP_MIDDLE_WS)* ATTRIBUTE_EQUALS (CP_MIDDLE_WS)* DOUBLE_QUOTE_OPEN  (CP_WS)* booleanExpression      DQ (TAG_WS)* #cp_hideAttribute
    | CP_IF         (CP_MIDDLE_WS)* ATTRIBUTE_EQUALS (CP_MIDDLE_WS)* DOUBLE_QUOTE_OPEN  (CP_WS)* booleanExpression      DQ (TAG_WS)* #cp_ifAttribute
    | CP_FOR        (CP_MIDDLE_WS)* ATTRIBUTE_EQUALS (CP_MIDDLE_WS)* DOUBLE_QUOTE_OPEN  (CP_WS)* forLoop                DQ (TAG_WS)* #cp_forAttribute
    | CP_MODEL      (CP_MIDDLE_WS)* ATTRIBUTE_EQUALS (CP_MIDDLE_WS)* DOUBLE_QUOTE_OPEN  (CP_WS)* numericValue           DQ (TAG_WS)* #cp_modelAttribute
    | CLICK         (CP_MIDDLE_WS)* ATTRIBUTE_EQUALS (CP_MIDDLE_WS)* DOUBLE_QUOTE_OPEN  (CP_WS)* objectChainedMembers   DQ (TAG_WS)* #clickAttribute
    | MOUSEOVER     (CP_MIDDLE_WS)* ATTRIBUTE_EQUALS (CP_MIDDLE_WS)* DOUBLE_QUOTE_OPEN  (CP_WS)* objectChainedMembers   DQ (TAG_WS)* #mouseoverAttribute
    | TAG_NAME     ((TAG_WS)* TAG_EQUALS (TAG_WS)*ATTVALUE_VALUE)? (TAG_WS)*                                 #non_cpAttribute
    ;

booleanExpression
    : (CP_WS)* booleanValue (CP_WS)*                                                                  #primitiveBooleanExpression
    | (CP_WS)* comparisonExpression  (CP_WS)*                                                         #binaryComparisonExpression
    | booleanExpression  (CP_WS)* (booleanOperator | equalityOperator) booleanExpression (CP_WS)*      #binaryBooleanExpression
    | (CP_WS)* NOT booleanExpression                                                        #unaryBooleanExpression
    |  (CP_WS)* OPENING_PARENTHESES  (CP_WS)* booleanExpression  (CP_WS)* CLOSING_PARENTHESES (CP_WS)*                     #parenthesizedBooleanExpression
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
    : arithmeticExpression (CP_WS)*(comparisonOperator | equalityOperator) (CP_WS)*arithmeticExpression
    ;

comparisonOperator
    : GREATER
    | SMALLER
    | GREATER_EQUALS
    | SMALLER_EQUALS
    ;

arithmeticExpression
    : numericValue                                                    #primitiveArithmeticExpression
    | arithmeticExpression (CP_WS)* arithmeticOperator (CP_WS)*arithmeticExpression    #binaryArithmeticExpression
    | (PLUS | MINUS) arithmeticExpression                             #unaryArithmeticExpression
    | OPENING_PARENTHESES (CP_WS)*arithmeticExpression (CP_WS)*CLOSING_PARENTHESES    #parenthesizedArithmeticExpression
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

objectChainedMembers :(CP_WS)*objectMember (DOT objectMember)*(CP_WS)* ;

// IDENTIFIER, accessedArrayElement & functionCall Are Grouped In This Rule
// Because They're The Only Kind Of Expressions That Can
// Boil Down To A Boolean Value Or A Numeric Value
// (While TRUE, FALSE & NUMBER Cannot, So They're Separated In Their Own Rules)
objectMember
    : IDENTIFIER             #identifier
    | accessedArrayElement   #accessedElement
    | functionCall           #functionProvoke
    ;

accessedArrayElement : IDENTIFIER (CP_WS)*OPENING_BRACKET(CP_WS)* arithmeticExpression (CP_WS)*CLOSING_BRACKET ;

functionCall : IDENTIFIER (CP_WS)*OPENING_PARENTHESES (CP_WS)*functionArguments? (CP_WS)*CLOSING_PARENTHESES (CP_WS)*;

// The Function Argument Can Be Any Type Of Expression,
// Boolean Or Arithmetic, Simple Or Complex.
functionArguments : expression(CP_WS)* (COMMA expression)* ;

expression
    : STRING_LITERAL         #stringLiteral
    | booleanExpression      #booleanExpressionArgument
    | arithmeticExpression   #arithmeticExpressionArgument
    ;

forLoop
    : (CP_WS)*IDENTIFIER (CP_WS)+ IN (CP_WS)+(array | objectChainedMembers)(CP_WS)* (SEMICOLON (CP_WS)*IDENTIFIER (CP_WS)*ASSIGNMENT (CP_WS)*INDEX)?   #forLoop1
    | (CP_WS)*IDENTIFIER (CP_WS)*COMMA (CP_WS)*IDENTIFIER (CP_WS)+ IN (CP_WS)+objectChainedMembers                                     #forLoop2
    ;

array : OPENING_BRACKET(CP_WS)*
        (arrayElement ((CP_WS)*COMMA (CP_WS)*arrayElement)*)?(CP_WS)*
        CLOSING_BRACKET(CP_WS)*
      ;

arrayElement
    : expression   #expressionArrayElement
    | array        #nestedArrayElement
    ;

// Do Not Erase The 'E', switch is like a keyword to ANTLR, so things are getting mixed up ..
switchE
    : TAG_OPEN TAG_NAME (TAG_WS)*attribute* switchAttribute attribute* (TAG_WS)*TAG_CLOSE(SEA_WS)*
      (switchCase)*(SEA_WS)*
      TAG_OPEN TAG_SLASH TAG_NAME TAG_CLOSE(SEA_WS)*
    ;

switchAttribute : CP_SWITCH ATTRIBUTE_EQUALS DOUBLE_QUOTE_OPEN expression DQ ;

switchCase
    : TAG_OPEN TAG_NAME(TAG_WS)* attribute* switchCaseAttribute attribute* (TAG_WS)*TAG_CLOSE(SEA_WS)*
      (content*)(SEA_WS)*
      TAG_OPEN TAG_SLASH TAG_NAME TAG_CLOSE(SEA_WS)*
    ;

switchCaseAttribute
    : CP_SWITCH_CASE (CP_MIDDLE_WS)*ATTRIBUTE_EQUALS(CP_MIDDLE_WS)* DOUBLE_QUOTE_OPEN expression DQ
    | CP_SWITCH_DEFAULT
    ;

content
    : element   #elementContent
    | text      #textContent
    | curly     #curlyContent
    ;

text : ((SEA_WS)* HTML_TEXT (SEA_WS)* | (SEA_WS)*CURLY_OPEN (SEA_WS)*)+ ;

curly
    : DOUBLE_CURLY_OPEN (CP_WS)* pipedVariable (CP_WS)* DOUBLE_CURLY_CLOSE(SEA_WS)*       #curlyVariables
    | DOUBLE_CURLY_OPEN (CP_WS)* exprToExecute (CP_WS)* DOUBLE_CURLY_CLOSE(SEA_WS)*       #curlyExpression
    ;

ternaryOperator
    : booleanExpression (CP_WS)*QUESTION_MARK(CP_WS)* exprToExecute (CP_WS)*COLON(CP_WS)* exprToExecute   #basicTernaryOperator
    | (CP_WS)*OPENING_PARENTHESES (CP_WS)* ternaryOperator (CP_WS)*CLOSING_PARENTHESES (CP_WS)*            #parenthesizedTernaryOperator
    ;

exprToExecute
    : ternaryOperator                                         #ternaryExprToExecute
    | OPENING_PARENTHESES (CP_WS)*exprToExecute (CP_WS)*CLOSING_PARENTHESES   #parenthesizedExprToExecute
    | expression                                              #exp
    ;


pipes
    :(PIPE (CP_WS)*IDENTIFIER+ (CP_WS)*(COLON (CP_WS)*STRING_LITERAL)?)              #pipe
    ;
pipedVariable : variable pipes*;

variable : objectChainedMembers (COLON STRING_LITERAL)? ;
