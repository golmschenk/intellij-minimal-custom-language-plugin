package com.olmschenk.minilang.psi

import com.intellij.psi.PsiElement

interface MiniLangVariableIdentifier : MiniLangNameIdentifierOwner {
    override fun getName(): String?
    override fun setName(name: String): PsiElement
    override fun getNameIdentifier(): PsiElement?
}
