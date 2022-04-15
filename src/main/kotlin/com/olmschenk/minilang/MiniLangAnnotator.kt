package com.olmschenk.minilang

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import com.olmschenk.minilang.psi.MiniLangVariableUsage

class MiniLangAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element !is MiniLangVariableUsage) {
            return
        }

        val reference = (element.reference as MiniLangReference)
        if (reference.multiResolve(false).isEmpty()) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Unresolved property")
                .range(element.textRange)
                .highlightType(ProblemHighlightType.LIKE_UNKNOWN_SYMBOL)
                .create()
        }
    }
}