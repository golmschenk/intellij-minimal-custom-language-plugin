package com.olmschenk.minilang.psi

import com.intellij.psi.PsiElement

interface MiniLangRenamableElement: PsiElement {
    fun rename(newName: String): PsiElement?
}
