package com.olmschenk.minilang

import com.intellij.lang.refactoring.RefactoringSupportProvider
import com.intellij.psi.PsiElement
import com.olmschenk.minilang.psi.MiniLangVariableDeclaration

class MiniLangRefactoringSupportProvider : RefactoringSupportProvider() {
    override fun isMemberInplaceRenameAvailable(element: PsiElement, context: PsiElement?): Boolean {
        return element is MiniLangVariableDeclaration
    }
}
