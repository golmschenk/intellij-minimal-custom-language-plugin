package com.olmschenk.minilang.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.olmschenk.minilang.MiniLangLanguage
import com.olmschenk.minilang.MiniLangReference
import com.olmschenk.minilang.parser.MiniLangLexer
import com.olmschenk.minilang.psi.MiniLangElementFactory
import com.olmschenk.minilang.psi.MiniLangVariableUsage
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory

class MiniLangVariableUsageImpl(node: ASTNode) : ASTWrapperPsiElement(node), MiniLangVariableUsage {
    override fun getReference(): MiniLangReference = MiniLangReference(this, TextRange(0, this.text.length))
    override fun rename(newName: String): PsiElement {
        val variableIdentifierNameNode: ASTNode? = this.node.findChildByType(
            PSIElementTypeFactory.createTokenSet(MiniLangLanguage.INSTANCE, MiniLangLexer.VARIABLE_IDENTIFIER)
        )
        if (variableIdentifierNameNode != null) {
            val miniLangVariableIdentifier: MiniLangVariableUsage =
                MiniLangElementFactory.createVariableUsage(this.project, newName)
            val newVariableIdentifierNameNode: ASTNode = miniLangVariableIdentifier.firstChild.node
            this.node.replaceChild(variableIdentifierNameNode, newVariableIdentifierNameNode)
        }
        return this
    }
}
