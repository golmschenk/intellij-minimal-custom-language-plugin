package com.olmschenk.minilang.psi.impl

import com.intellij.lang.ASTNode
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.olmschenk.minilang.MiniLangLanguage
import com.olmschenk.minilang.MiniLangReference
import com.olmschenk.minilang.parser.MiniLangLexer
import com.olmschenk.minilang.psi.MiniLangNameIdentifierOwner
import com.olmschenk.minilang.psi.MiniLangElementFactory
import com.olmschenk.minilang.psi.MiniLangVariableDeclaration
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory


class MiniLangVariableDeclarationImpl(node: ASTNode) : MiniLangNameIdentifierOwnerImpl(node),
    MiniLangVariableDeclaration {

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
                MiniLangElementFactory.createVariableIdentifier(this.project, name)
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

    override fun rename(newName: String): PsiElement {
        return this.setName(newName)
    }

    override fun getReference(): MiniLangReference = MiniLangReference(this, TextRange(0, this.text.length))
}