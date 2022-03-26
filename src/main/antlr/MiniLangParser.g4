parser grammar MiniLangParser ;

options { tokenVocab=MiniLangLexer; }

file_content : (statement)* EOF ;
statement : LET WHITESPACE? variable_definition_identifier WHITESPACE? EQUALS WHITESPACE? expression SEMICOLON WHITESPACE? ;
variable_definition_identifier : VARIABLE_IDENTIFIER ;
expression : value (WHITESPACE? PLUS WHITESPACE? value)*;
value : (VARIABLE_IDENTIFIER | NUMBER) ;
