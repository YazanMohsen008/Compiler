lexer grammar  HTMLLexer ;

HTML_COMMENT : '<!--' .*? '-->' -> channel(HIDDEN) ;

HTML_CONDITIONAL_COMMENT : '<![' .*? ']>' -> channel(HIDDEN) ;

XML : '<?xml' .*? '>' ;

CDATA : '<![CDATA[' .*? ']]>' ;

DTD : '<!' .*? '>' ;

SCRIPTLET : '<?' .*? '?>' | '<%' .*? '%>';

SEA_WS : [ \t\r\n]  ;

CURLY_OPEN : '{' ;

HTML_TEXT : ~[<{ \t\r\n]+ ;

SCRIPT_OPEN : '<script' .*? '>' -> pushMode(SCRIPT) ;

STYLE_OPEN : '<style' .*? '>'  -> pushMode(STYLE) ;

TAG_OPEN : '<' -> pushMode(TAG) ;

DOUBLE_CURLY_OPEN : '{{' -> pushMode(CP_MODE) ;

mode SCRIPT ;

SCRIPT_BODY : .*? '</script>' -> popMode ;

SCRIPT_SHORT_BODY : .*? '</>' -> popMode ;

mode STYLE ;

STYLE_BODY : .*? '</style>' -> popMode ;

STYLE_SHORT_BODY : .*? '</>' -> popMode ;

mode TAG ;

CP_INCLUDE:'cp-include'-> pushMode(CP_MIDDLE) ;

CP_PARAMETERS:'cp-parameters'-> pushMode(CP_MIDDLE) ;

CHANGE:'@change'-> pushMode(CP_MIDDLE) ;

FOCUS:'@focus'-> pushMode(CP_MIDDLE) ;

CP_APP : 'cp-app' -> pushMode(CP_MIDDLE) ;

CP_SWITCH : 'cp-switch' -> pushMode(CP_MIDDLE) ;

CP_SWITCH_CASE :'cp-switch-case' -> pushMode(CP_MIDDLE) ;

CP_SWITCH_DEFAULT : 'cp-switchDefault' ;

CP_SHOW : 'cp-show' -> pushMode(CP_MIDDLE) ;

CP_HIDE : 'cp-hide' -> pushMode(CP_MIDDLE) ;

CP_IF : 'cp-if' -> pushMode(CP_MIDDLE) ;

CP_FOR : 'cp-for' -> pushMode(CP_MIDDLE) ;

CP_MODEL : 'cp-model' -> pushMode(CP_MIDDLE) ;

CLICK : '@click' -> pushMode(CP_MIDDLE) ;

MOUSEOVER : '@mouseover' -> pushMode(CP_MIDDLE) ;

MOUSEMOVE : '@mousemove' -> pushMode(CP_MIDDLE) ;

TAG_NAME : TAG_FIRST_CHAR TAG_CHAR* ;

TAG_EQUALS : '=' -> pushMode(ATTVALUE) ;

TAG_WS : [ \t\r\n]  ;

TAG_SLASH : '/' ;

TAG_SLASH_CLOSE : '/>' -> popMode ;

TAG_CLOSE : '>' -> popMode ;

fragment TAG_CHAR
    : TAG_FIRST_CHAR
    | '-'
    | '_'
    | '.'
    | DIGIT
    | '\u00B7'
    | '\u0300'..'\u036F'
    | '\u203F'..'\u2040'
    ;

fragment DIGIT : [0-9] ;

fragment HEXDIGIT : [a-fA-F0-9] ;

fragment TAG_FIRST_CHAR
    : [:a-zA-Z]
    | '\u2070'..'\u218F'
    | '\u2C00'..'\u2FEF'
    | '\u3001'..'\uD7FF'
    | '\uF900'..'\uFDCF'
    | '\uFDF0'..'\uFFFD'
    ;

mode CP_MIDDLE ;

ATTRIBUTE_EQUALS     : '=' ;
CP_MIDDLE_WS         : [ \t\r\n];
DOUBLE_QUOTE_OPEN    : '"' -> pushMode(CP_MODE) ;

mode CP_MODE ;

DOT                 : '.' ;
COMMA               : ',' ;
COLON               : ':' ;
SEMICOLON           : ';' ;
OPENING_PARENTHESES : '(' ;
CLOSING_PARENTHESES : ')' ;
OPENING_BRACKET     : '[' ;
CLOSING_BRACKET     : ']' ;
QUESTION_MARK       : '?' ;
PIPE                : '|' ;
ASSIGNMENT          : '=' ;
IN                  : 'in' ;
TRUE                : 'true' ;
FALSE               : 'false';
NULL                : 'null' ;
INDEX               : 'index' ;

 AND : 'AND' | 'and' | '&&' ;
 OR  : 'OR'  | 'or'  | '||' ;
 XOR : 'XOR' | 'xor' | '^' ;
 NOT : 'NOT' | 'not' | '!' ;

 PLUS           : '+' ;
 MINUS          : '-' ;
 MULTIPLICATION : '*' ;
 DIVISION       : '/' ;
 MODULUS        : '%' ;

 GREATER        : '>' ;
 SMALLER        : '<' ;
 GREATER_EQUALS : '>=' ;
 SMALLER_EQUALS : '<=' ;
 EQUALITY       : '==' ;
 INEQUALITY     : '!=' ;

IDENTIFIER : ID_FIRST_CHAR ID_CHAR* ;

CP_WS : [ \t\r\n]  ;

NUMBER : DIGIT+ (DOT DIGIT+)? ;


STRING_LITERAL
    : '\'' (ESCAPE | .)*? '\''
    | '"' (ESCAPE | ~(' ' | '{'))*? '"'
    ;

DQ                  : '"' -> popMode, popMode ;
DOUBLE_CURLY_CLOSE  : '}}' -> popMode ;

fragment ESCAPE
    : '\\\''
    | '\\"'
    | '\\\\'
    ;

fragment ID_CHAR
    : ID_FIRST_CHAR
    | DIGIT
    ;

fragment ID_FIRST_CHAR : [a-zA-Z$_] ;

mode ATTVALUE ;

// an attribute value may have spaces b/t the '=' and the value
ATTVALUE_VALUE : ' '* ATTRIBUTE -> popMode ;

ATTRIBUTE
    : DOUBLE_QUOTE_STRING
    | SINGLE_QUOTE_STRING
    | ATTCHARS
    | HEXCHARS
    | DECCHARS
    ;

fragment DOUBLE_QUOTE_STRING : '"' ~[<"]* '"' ;
fragment SINGLE_QUOTE_STRING : '\'' ~[<']* '\'' ;
fragment ATTCHARS : ATTCHAR+ ' '? ;

fragment ATTCHAR
    : '-'
    | '_'
    | '.'
    | '/'
    | '+'
    | ','
    | '?'
    | '='
    | ':'
    | ';'
    | '#'
    | [0-9a-zA-Z]
    ;

fragment HEXCHARS : '#' [0-9a-fA-F]+ ;

fragment DECCHARS : [0-9]+ '%'? ;
