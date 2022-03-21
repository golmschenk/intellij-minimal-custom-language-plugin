lexer grammar MiniLangLexer ;

WHITESPACE : NON_NEWLINE_WHITESPACE_CHARACTER+ ;
NEWLINE : '\n' ;
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
fragment
NON_NEWLINE_WHITESPACE_CHARACTER : ('\t' | ' ' | '\r' | '\u000C') ;
