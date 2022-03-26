package com.olmschenk.minilang

import com.intellij.lang.refactoring.RefactoringSupportProvider
import com.intellij.psi.PsiElement
import com.olmschenk.minilang.psi.MiniLangNameIdentifierOwner

class MiniLangRefactoringSupportProvider : RefactoringSupportProvider() {
    override fun isMemberInplaceRenameAvailable(elementToRename: PsiElement, context: PsiElement?): Boolean {
        return elementToRename is MiniLangNameIdentifierOwner
    }
}
