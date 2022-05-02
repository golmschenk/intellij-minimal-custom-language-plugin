package com.olmschenk.minilang.psi.impl

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
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

    override fun annotate(annotationHolder: AnnotationHolder) {
        if (!this.isDefinedAtElement(this)) {
            annotationHolder.newAnnotation(HighlightSeverity.ERROR, "Unresolved variable: ${this.text}")
                .range(this.textRange)
                .highlightType(ProblemHighlightType.LIKE_UNKNOWN_SYMBOL)
                .create()
        }
    }
}
