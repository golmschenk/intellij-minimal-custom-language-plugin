package com.olmschenk.minilang.psi

import com.intellij.psi.impl.source.tree.LeafPsiElement
import com.intellij.psi.tree.IElementType
import com.olmschenk.minilang.MiniLangReference

class MiniLangVariableIdentifierNameLeafPsiElement(type: IElementType, text: CharSequence?) : LeafPsiElement(type, text) {
    // TODO: I feel like the get reference should be on the leaf element, but that doesn't seem to be working.
    // override fun getReference(): MiniLangReference = MiniLangReference(this, this.textRange)
}
