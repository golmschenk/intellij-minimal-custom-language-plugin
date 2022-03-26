package com.olmschenk.minilang.psi

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFileFactory
import com.olmschenk.minilang.MiniLangFile
import com.olmschenk.minilang.MiniLangFileType

object MiniLangNameIdentifierOwnerFactory {
    @JvmStatic
    fun create(project: Project?, name: String?): MiniLangNameIdentifierOwner {
        val file = createFile(project, name)
        return file.firstChild as MiniLangNameIdentifierOwner
    }

    fun createFile(project: Project?, text: String?): MiniLangFile {
        val name = "dummy.minilang"
        return PsiFileFactory.getInstance(project)
            .createFileFromText(name, MiniLangFileType.INSTANCE, text!!) as MiniLangFile
    }
}