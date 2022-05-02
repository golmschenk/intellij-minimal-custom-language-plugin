package com.olmschenk.minilang.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.olmschenk.minilang.psi.MiniLangVariableIdentifier

class MiniLangVariableIdentifierImpl(node: ASTNode) : ASTWrapperPsiElement(node), MiniLangVariableIdentifier {
    override fun rename(newName: String): PsiElement? {
        TODO("Not yet implemented")
    }

}