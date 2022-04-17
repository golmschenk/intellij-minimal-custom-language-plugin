package com.olmschenk.minilang

import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.siblings
import com.olmschenk.minilang.psi.MiniLangNameIdentifierOwner

class MiniLangPsiTreeSearcher {
    companion object {
        fun recursiveVariableDefinitionSearch(
            variableName: String,
            root: PsiElement?,
            variableDefinitions: MutableList<MiniLangNameIdentifierOwner>
        ) {
            if (root != null) {
                val nameIdentifierOwners = PsiTreeUtil.getChildrenOfType(
                    root,
                    MiniLangNameIdentifierOwner::class.java
                )
                if (nameIdentifierOwners != null) {
                    for (nameIdentifierOwner in nameIdentifierOwners) {
                        if (variableName == nameIdentifierOwner.text) {
                            variableDefinitions.add(nameIdentifierOwner)
                        }
                    }
                }
                for (child in root.children) {
                    recursiveVariableDefinitionSearch(variableName, child, variableDefinitions)
                }
            }
        }

        fun isVariableDefinedAtElement(
            variableName: String,
            element: PsiElement
        ) : Boolean {
            val backwardSiblings = element.siblings(forward = false, withSelf = false)
            for (backwardSibling in backwardSiblings){
                val variableDefinitions: MutableList<MiniLangNameIdentifierOwner> = ArrayList()
                recursiveVariableDefinitionSearch(variableName, backwardSibling, variableDefinitions)  // TODO: This does not need to be fully recursive, but can stop on the first declaration found.
                if (variableDefinitions.isNotEmpty()) {
                    return true
                }
            }
            if (element.parent !is MiniLangFile) {  // TODO: Consider if there's a better way to stop the tree search than just hitting the MiniLang file. Probably need to look into scopes or such.
                return isVariableDefinedAtElement(variableName, element.parent)
            }
            else {
                return false;
            }
        }
    }
}