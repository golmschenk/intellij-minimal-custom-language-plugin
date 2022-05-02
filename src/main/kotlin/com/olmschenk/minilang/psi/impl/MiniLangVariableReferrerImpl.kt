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

class MiniLangVariableReferrerImpl(node: ASTNode) : MiniLangVariableIdentifierImpl(node), MiniLangVariableReferrer {
    companion object {
        fun create(project: Project?, name: String?): MiniLangVariableReferrer {
            val statementText = "let x = $name;"
            val file = MiniLangFile.create(project, statementText)
            val variableIdentifierNode = file.children[0].children[0].children[0].children[1].children[0].children[0]
            return variableIdentifierNode as MiniLangVariableReferrer
        }
    }
}
