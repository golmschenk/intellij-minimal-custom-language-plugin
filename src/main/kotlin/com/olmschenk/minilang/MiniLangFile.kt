package com.olmschenk.minilang

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider

class MiniLangFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, MiniLangLanguage.INSTANCE) {
    override fun getFileType(): FileType {
        return MiniLangFileType.INSTANCE
    }

    override fun toString(): String {
        return "MiniLang File"
    }
}