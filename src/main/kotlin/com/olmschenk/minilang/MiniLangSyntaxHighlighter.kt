package com.olmschenk.minilang

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import com.olmschenk.minilang.parser.MiniLangLexer
import org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory
import org.antlr.intellij.adaptor.lexer.TokenIElementType


class MiniLangSyntaxHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer(): Lexer {
        val lexer = MiniLangLexer(null)
        return ANTLRLexerAdaptor(MiniLangLanguage.INSTANCE, lexer)
    }

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey?> {
        if (tokenType !is TokenIElementType) return EMPTY_KEYS
        val ttype = tokenType.antlrTokenType
        val attrKey: TextAttributesKey
        attrKey = when (ttype) {
            MiniLangLexer.VARIABLE_IDENTIFIER -> ID
            MiniLangLexer.LET -> KEYWORD
            MiniLangLexer.NUMBER -> NUMBER
            else -> return EMPTY_KEYS
        }
        return arrayOf(attrKey)
    }

    companion object {
        private val EMPTY_KEYS = arrayOfNulls<TextAttributesKey>(0)
        val ID = TextAttributesKey.createTextAttributesKey("MINI_LANG_VARIABLE_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER)
        val KEYWORD =
            TextAttributesKey.createTextAttributesKey("MINI_LANG_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
        val NUMBER = TextAttributesKey.createTextAttributesKey("MINI_LANG_NUMBER", DefaultLanguageHighlighterColors.NUMBER)

        init {
            PSIElementTypeFactory.defineLanguageIElementTypes(
                MiniLangLanguage.INSTANCE,
                MiniLangParser.tokenNames,
                MiniLangParser.ruleNames
            )
        }
    }
}