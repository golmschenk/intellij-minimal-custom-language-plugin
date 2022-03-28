package com.olmschenk.minilang.psi.impl

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.olmschenk.minilang.MiniLangLanguage
import com.olmschenk.minilang.parser.MiniLangLexer
import com.olmschenk.minilang.psi.MiniLangNameIdentifierOwner
import com.olmschenk.minilang.psi.MiniLangVariableIdentifierFactory
import com.olmschenk.minilang.psi.MiniLangVariableIdentifier
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory


class MiniLangVariableIdentifierImpl(node: ASTNode) : MiniLangNameIdentifierOwnerImpl(node),
    MiniLangVariableIdentifier {

    override fun getName(): String? {
        val variableIdentifierNameNode: ASTNode? = this.node.findChildByType(
            PSIElementTypeFactory.createTokenSet(MiniLangLanguage.INSTANCE, MiniLangLexer.VARIABLE_IDENTIFIER)
        )
        return variableIdentifierNameNode?.text
    }

    override fun setName(name: String): PsiElement {
        val variableIdentifierNameNode: ASTNode? = this.node.findChildByType(
            PSIElementTypeFactory.createTokenSet(MiniLangLanguage.INSTANCE, MiniLangLexer.VARIABLE_IDENTIFIER)
        )
        if (variableIdentifierNameNode != null) {
            val miniLangNameIdentifierOwner: MiniLangNameIdentifierOwner =
                MiniLangVariableIdentifierFactory.createVariableIdentifier(this.project, name)
            val newVariableIdentifierNameNode: ASTNode = miniLangNameIdentifierOwner.firstChild.node
            this.node.replaceChild(variableIdentifierNameNode, newVariableIdentifierNameNode)
        }
        return this
    }

    override fun getNameIdentifier(): PsiElement? {
        val childVariableIdentifierNameNode: ASTNode? = this.node.findChildByType(
            PSIElementTypeFactory.createTokenSet(MiniLangLanguage.INSTANCE, MiniLangLexer.VARIABLE_IDENTIFIER)
        )
        return childVariableIdentifierNameNode?.psi
    }
}
