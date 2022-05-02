package com.olmschenk.minilang.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.olmschenk.minilang.MiniLangFile
import com.olmschenk.minilang.MiniLangLanguage
import com.olmschenk.minilang.MiniLangReference
import com.olmschenk.minilang.parser.MiniLangLexer
import com.olmschenk.minilang.psi.MiniLangVariableReferrer
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory

class MiniLangVariableReferrerImpl(node: ASTNode) : ASTWrapperPsiElement(node), MiniLangVariableReferrer {

    override fun getReference(): MiniLangReference = MiniLangReference(this, TextRange(0, this.text.length))

    override fun rename(newName: String): PsiElement {
        val variableIdentifierNameNode: ASTNode? = this.node.findChildByType(
            PSIElementTypeFactory.createTokenSet(MiniLangLanguage.INSTANCE, MiniLangLexer.IDENTIFIER)
        )
        if (variableIdentifierNameNode != null) {
            val miniLangVariableIdentifier: MiniLangVariableReferrer = create(this.project, newName)
            val newVariableIdentifierNameNode: ASTNode = miniLangVariableIdentifier.firstChild.node
            this.node.replaceChild(variableIdentifierNameNode, newVariableIdentifierNameNode)
        }
        return this
    }

    companion object {
        fun create(project: Project?, name: String?): MiniLangVariableReferrer {
            val statementText = "let x = $name;"
            val file = MiniLangFile.create(project, statementText)
            val variableIdentifierNode = file.children[0].children[0].children[0].children[1].children[0].children[0]
            return variableIdentifierNode as MiniLangVariableReferrer
        }
    }
}
