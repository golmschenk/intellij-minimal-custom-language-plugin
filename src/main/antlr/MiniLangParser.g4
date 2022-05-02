parser grammar MiniLangParser ;

options { tokenVocab=MiniLangLexer; }

file_content : (statement)* EOF ;
statement : (import_statement | definition_statement) ;

import_statement : USE WHITESPACE? module_identifier SEMICOLON WHITESPACE? ;
module_identifier : IDENTIFIER;

definition_statement : LET WHITESPACE? variable_definition_identifier WHITESPACE? EQUALS WHITESPACE? expression SEMICOLON WHITESPACE? ;
variable_definition_identifier : IDENTIFIER ;
expression : value (WHITESPACE? PLUS WHITESPACE? value)*;
value : (variable_reference_identifier | NUMBER) ;
variable_reference_identifier : IDENTIFIER ;
