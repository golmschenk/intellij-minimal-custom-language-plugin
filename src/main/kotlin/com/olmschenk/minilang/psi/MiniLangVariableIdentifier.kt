package com.olmschenk.minilang.psi

import com.intellij.psi.PsiElement

interface MiniLangVariableIdentifier : MiniLangRenamableElement {
    fun searchForDefinitionRecursively(root: PsiElement?, variableDefinitions: MutableList<MiniLangNameIdentifierOwner>)
    fun isDefinedAtElement(element: PsiElement): Boolean

}