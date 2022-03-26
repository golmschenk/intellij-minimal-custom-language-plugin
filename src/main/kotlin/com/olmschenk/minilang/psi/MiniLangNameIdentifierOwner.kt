package com.olmschenk.minilang.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.olmschenk.minilang.MiniLangLanguage
import com.olmschenk.minilang.parser.MiniLangLexer
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory


class MiniLangNameIdentifierOwner(node: ASTNode) : ASTWrapperPsiElement(node), PsiNameIdentifierOwner {
    override fun setName(name: String): PsiElement {
        // val childVariableIdentifierNode: ASTNode? = this.node.findChildByType(
        //     PSIElementTypeFactory.createTokenSet(MiniLangLanguage.INSTANCE, MiniLangLexer.VARIABLE_IDENTIFIER)
        // )
        // val tokenIElementTypes = PSIElementTypeFactory.getTokenIElementTypes(MiniLangLanguage.INSTANCE)
        // val variableIdentifierTokenType = tokenIElementTypes[MiniLangLexer.VARIABLE_IDENTIFIER]
        // if (childVariableIdentifierNode != null) {
        //     val variableIdentifier = MiniLangVariableIdentifierLeafPsiElement(variableIdentifierTokenType, name)
        //     val newChildVariableIdentifierNode: ASTNode = variableIdentifier.firstChild.node
        //     this.node.replaceChild(childVariableIdentifierNode, newChildVariableIdentifierNode)
        // }
        // return this
        val variableIdentifierNode: ASTNode? = this.node.findChildByType(
            PSIElementTypeFactory.createTokenSet(MiniLangLanguage.INSTANCE, MiniLangLexer.VARIABLE_IDENTIFIER))
        if (variableIdentifierNode != null) {
            val miniLangNameIdentifierOwner: MiniLangNameIdentifierOwner = MiniLangNameIdentifierOwnerFactory.create(this.project, name)
            val newVariableIdentifierNode: ASTNode = miniLangNameIdentifierOwner.firstChild.node
            this.node.replaceChild(variableIdentifierNode, newVariableIdentifierNode)
        }
        return this
    }

    override fun getNameIdentifier(): PsiElement? {
        val childVariableIdentifierNode: ASTNode? = this.node.findChildByType(
            PSIElementTypeFactory.createTokenSet(MiniLangLanguage.INSTANCE, MiniLangLexer.VARIABLE_IDENTIFIER)
        )
        return childVariableIdentifierNode?.psi
    }
}
