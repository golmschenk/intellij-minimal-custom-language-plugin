package com.olmschenk.minilang

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiFileFactory

class MiniLangFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, MiniLangLanguage.INSTANCE) {
    override fun getFileType(): FileType {
        return MiniLangFileType.INSTANCE
    }

    override fun toString(): String {
        return "MiniLang File"
    }

    companion object {
        fun create(project: Project?, text: String?): MiniLangFile {
            val name = "dummy.minilang"
            return PsiFileFactory.getInstance(project)
                .createFileFromText(name, MiniLangFileType.INSTANCE, text!!) as MiniLangFile
        }
    }
}