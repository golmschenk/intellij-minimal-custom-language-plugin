lexer grammar MiniLangLexer ;

WHITESPACE : ('\t' | ' ' | '\n' | '\r' | '\u000C')+ ;
SEMICOLON : ';' ;
LET : 'let' ;
EQUALS : '=' ;
PLUS : '+' ;
VARIABLE_IDENTIFIER : ALPHA+ ;
NUMBER : DIGIT+ ;
INVALID_CHARACTER: . -> channel(HIDDEN);

fragment
ALPHA : [A-Za-z] ;
fragment
DIGIT : [0-9] ;
