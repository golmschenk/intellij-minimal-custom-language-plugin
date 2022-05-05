package com.olmschenk.minilang

import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.olmschenk.minilang.psi.MiniLangNameIdentifierOwner
import com.olmschenk.minilang.psi.MiniLangRenamableElement
import com.olmschenk.minilang.psi.MiniLangVariableIdentifier


class MiniLangReference(element: PsiElement, textRange: TextRange) : PsiPolyVariantReferenceBase<PsiElement?>(element, textRange) {
    override fun resolve(): PsiElement? {
        val resolveResults = multiResolve(false)
        return if (resolveResults.size == 1) resolveResults[0].element else null
    }

    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
        val variableDefinitions: MutableList<MiniLangNameIdentifierOwner> = findIdentifierOwnerList()
        val results: MutableList<ResolveResult> = ArrayList()
        for (variableDefinition in variableDefinitions) {
            results.add(PsiElementResolveResult(variableDefinition))
        }
        return results.toTypedArray()
    }

    override fun getVariants(): Array<Any> {
        return findIdentifierOwnerList().toTypedArray()
    }

    private fun findIdentifierOwnerList(): MutableList<MiniLangNameIdentifierOwner> {
        val elementVirtualFile = myElement!!.containingFile
        val variableDefinitions: MutableList<MiniLangNameIdentifierOwner> = ArrayList()
        (myElement as MiniLangVariableIdentifier).searchForDefinitionRecursively(
            elementVirtualFile,
            variableDefinitions
        )
        return variableDefinitions
    }

    // `setName` is called for the element being renamed, and this is called for all references of that element.
    override fun handleElementRename(newElementName: String): PsiElement? {
        return (myElement as MiniLangRenamableElement).rename(newElementName)
    }
}