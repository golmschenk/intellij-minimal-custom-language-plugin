parser grammar MiniLangParser ;

options { tokenVocab=MiniLangLexer; }

file_content : (statement)* EOF ;
statement : LET WHITESPACE VARIABLE_IDENTIFIER WHITESPACE EQUALS WHITESPACE expression NEWLINE ;
expression : value (WHITESPACE PLUS WHITESPACE value)*;
value : (VARIABLE_IDENTIFIER | NUMBER) ;
