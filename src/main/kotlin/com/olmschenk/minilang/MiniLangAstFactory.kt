package com.olmschenk.minilang

import com.intellij.lang.DefaultASTFactoryImpl
import com.intellij.psi.impl.source.tree.LeafElement
import com.intellij.psi.tree.IElementType
import com.olmschenk.minilang.parser.MiniLangLexer
import com.olmschenk.minilang.psi.MiniLangVariableIdentifierLeafPsiElement
import org.antlr.intellij.adaptor.lexer.TokenIElementType

class MiniLangAstFactory : DefaultASTFactoryImpl() {
    override fun createLeaf(type: IElementType, text: CharSequence): LeafElement {
        if (type is TokenIElementType && type.antlrTokenType == MiniLangLexer.VARIABLE_IDENTIFIER) {
            return MiniLangVariableIdentifierLeafPsiElement(type, text)
        } else {
            return super.createLeaf(type, text)
        }
    }
}
