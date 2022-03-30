package com.olmschenk.minilang.psi.MiniLangRenamableElement

import com.intellij.psi.PsiElement

interface MiniLangRenamableElement: PsiElement {
    fun rename(newName: String): PsiElement?
}
