package com.olmschenk.minilang.psi.impl

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.olmschenk.minilang.MiniLangFileType
import com.olmschenk.minilang.psi.MiniLangModuleIdentifier
import java.io.File

class MiniLangModuleIdentifierImpl(node: ASTNode) : ASTWrapperPsiElement(node), MiniLangModuleIdentifier {
    override fun annotate(annotationHolder: AnnotationHolder) {
        if (!this.fileExists()) {
            annotationHolder.newAnnotation(HighlightSeverity.ERROR, "Unresolved variable: ${this.text}")
                .range(this.textRange)
                .highlightType(ProblemHighlightType.LIKE_UNKNOWN_SYMBOL)
                .create()
        }
    }

    private fun fileExists(): Boolean {
        val virtualFiles = FileTypeIndex.getFiles(MiniLangFileType.INSTANCE, GlobalSearchScope.allScope(project))
        for (virtualFile in virtualFiles) {
            val virtualFileStem = File(virtualFile.path).nameWithoutExtension
            if (text == virtualFileStem) {
                return true
            }
        }
        return false
    }
}
