package com.olmschenk.minilang.psi

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement

interface MiniLangVariableIdentifier : MiniLangRenamableElement {
    fun searchForDefinitionRecursively(root: PsiElement?, variableDefinitions: MutableList<MiniLangNameIdentifierOwner>)
    fun isDefinedAtElement(element: PsiElement): Boolean

    val static : Static
    interface Static {
        fun searchForDefinitionsRecursively(project: Project, root: PsiElement?, variableDefinitions: MutableList<MiniLangNameIdentifierOwner>)
    }
}
