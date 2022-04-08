package com.olmschenk.minilang

import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiTreeUtil
import com.olmschenk.minilang.psi.MiniLangNameIdentifierOwner
import com.olmschenk.minilang.psi.MiniLangRenamableElement.MiniLangRenamableElement


class MiniLangReference(element: PsiElement, textRange: TextRange) : PsiReferenceBase<PsiElement?>(element, textRange),
    PsiPolyVariantReference {
    override fun resolve(): PsiElement? {
        val resolveResults = multiResolve(false)
        return if (resolveResults.size == 1) resolveResults[0].element else null
    }

    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
        val project = myElement!!.project
        val variableDefinitions: MutableList<MiniLangNameIdentifierOwner> = ArrayList()
        val virtualFiles = FileTypeIndex.getFiles(
            MiniLangFileType.INSTANCE, GlobalSearchScope.allScope(
                project
            )
        )
        for (virtualFile in virtualFiles) {
            val miniLangFile = PsiManager.getInstance(project).findFile(virtualFile!!) as MiniLangFile?
            val root = miniLangFile
            recursiveVariableDefinitionSearch(root, variableDefinitions)
        }
        val results: MutableList<ResolveResult> = ArrayList()
        for (variableDefinition in variableDefinitions) {
            results.add(PsiElementResolveResult(variableDefinition))
        }
        return results.toTypedArray()
    }

    // TODO: Check if there's a built in recursive search.
    private fun recursiveVariableDefinitionSearch(
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
                    if ((myElement as PsiElement).text == nameIdentifierOwner.text) {
                        variableDefinitions.add(nameIdentifierOwner)
                    }
                }
            }
            for (child in root.children) {
                recursiveVariableDefinitionSearch(child, variableDefinitions)
            }
        }
    }

    // `setName` is called for the element being renamed, and this is called for all references of that element.
    override fun handleElementRename(newElementName: String): PsiElement? {
        return (myElement as MiniLangRenamableElement).rename(newElementName)
    }
}