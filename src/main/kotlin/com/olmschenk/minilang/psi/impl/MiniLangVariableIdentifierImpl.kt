package com.olmschenk.minilang.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.siblings
import com.olmschenk.minilang.MiniLangFile
import com.olmschenk.minilang.MiniLangLanguage
import com.olmschenk.minilang.MiniLangReference
import com.olmschenk.minilang.parser.MiniLangLexer
import com.olmschenk.minilang.psi.MiniLangNameIdentifierOwner
import com.olmschenk.minilang.psi.MiniLangVariableIdentifier
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory

abstract class MiniLangVariableIdentifierImpl(node: ASTNode) : ASTWrapperPsiElement(node), MiniLangVariableIdentifier {
    override fun getName(): String? {
        val variableIdentifierNameNode: ASTNode? = this.node.findChildByType(
            PSIElementTypeFactory.createTokenSet(MiniLangLanguage.INSTANCE, MiniLangLexer.IDENTIFIER)
        )
        return variableIdentifierNameNode?.text
    }

    override fun rename(newName: String): PsiElement {
        val variableIdentifierNameNode: ASTNode? = this.node.findChildByType(
            PSIElementTypeFactory.createTokenSet(MiniLangLanguage.INSTANCE, MiniLangLexer.IDENTIFIER)
        )
        if (variableIdentifierNameNode != null) {
            val miniLangVariableIdentifier =
                MiniLangVariableReferrerImpl.create(
                    this.project,
                    newName
                )
            val newVariableIdentifierNameNode: ASTNode = miniLangVariableIdentifier.firstChild.node
            this.node.replaceChild(variableIdentifierNameNode, newVariableIdentifierNameNode)
        }
        return this
    }

    override fun getReference(): MiniLangReference = MiniLangReference(this, TextRange(0, this.text.length))

    override fun searchForDefinitionRecursively(
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
                    if (name!! == nameIdentifierOwner.text) {
                        variableDefinitions.add(nameIdentifierOwner)
                    }
                }
            }
            for (child in root.children) {
                searchForDefinitionRecursively(child, variableDefinitions)
            }
        }
    }

    override fun isDefinedAtElement(
        element: PsiElement
    ) : Boolean {
        val backwardSiblings = element.siblings(forward = false, withSelf = false)
        for (backwardSibling in backwardSiblings){
            val variableDefinitions: MutableList<MiniLangNameIdentifierOwner> = ArrayList()
            searchForDefinitionRecursively(backwardSibling, variableDefinitions)  // TODO: This does not need to be fully recursive, but can stop on the first declaration found.
            if (variableDefinitions.isNotEmpty()) {
                return true
            }
        }
        if (element.parent !is MiniLangFile) {  // TODO: Consider if there's a better way to stop the tree search than just hitting the MiniLang file. Probably need to look into scopes or such.
            return isDefinedAtElement(element.parent)
        }
        else {
            return false;
        }
    }
}