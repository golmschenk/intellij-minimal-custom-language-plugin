package com.olmschenk.minilang

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import com.olmschenk.minilang.psi.MiniLangModuleIdentifier
import com.olmschenk.minilang.psi.MiniLangVariableReferrer

class MiniLangAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element is MiniLangVariableReferrer) {
            element.annotate(holder)
        }
        if (element is MiniLangModuleIdentifier) {
            element.annotate(holder)
        }
    }
}
