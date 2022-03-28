package com.olmschenk.minilang.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.olmschenk.minilang.psi.MiniLangNameIdentifierOwner

abstract class MiniLangNameIdentifierOwnerImpl(node: ASTNode) : ASTWrapperPsiElement(node), MiniLangNameIdentifierOwner
