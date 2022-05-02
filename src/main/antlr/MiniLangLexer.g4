lexer grammar MiniLangLexer ;

WHITESPACE : ('\t' | ' ' | '\n' | '\r' | '\u000C')+ ;
SEMICOLON : ';' ;
LET : 'let' ;
USE : 'use' ;
EQUALS : '=' ;
PLUS : '+' ;
IDENTIFIER : ALPHA+ ;
NUMBER : DIGIT+ ;
INVALID_CHARACTER: . -> channel(HIDDEN);

fragment
ALPHA : [A-Za-z] ;
fragment
DIGIT : [0-9] ;
