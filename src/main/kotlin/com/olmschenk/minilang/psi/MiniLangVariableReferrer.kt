package com.olmschenk.minilang.psi

import com.intellij.lang.annotation.AnnotationHolder

interface MiniLangVariableReferrer: MiniLangVariableIdentifier {
    fun annotate(annotationHolder: AnnotationHolder)
}
