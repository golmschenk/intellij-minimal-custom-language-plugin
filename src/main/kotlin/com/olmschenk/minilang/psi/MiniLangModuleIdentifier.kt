package com.olmschenk.minilang.psi

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiElement

interface MiniLangModuleIdentifier : PsiElement {
    fun annotate(annotationHolder: AnnotationHolder)
    fun fileExists(): Boolean

    fun getFile(): VirtualFile?
}