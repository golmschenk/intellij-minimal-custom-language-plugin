package com.olmschenk.minilang.psi

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFileFactory
import com.olmschenk.minilang.MiniLangFile
import com.olmschenk.minilang.MiniLangFileType

object MiniLangElementFactory {
    @JvmStatic
    fun createVariableIdentifier(project: Project?, name: String?): MiniLangNameIdentifierOwner {
        val statementText = "let $name = 0;"
        val file = createFile(project, statementText)
        val variableIdentifierNode = file.firstChild.firstChild.children[0]  // TODO: This certainly doesn't seem like the appropriate way to do this.
        return variableIdentifierNode as MiniLangNameIdentifierOwner
    }

    @JvmStatic
    fun createVariableUsage(project: Project?, name: String?): MiniLangVariableUsage {
        val statementText = "let x = $name;"
        val file = createFile(project, statementText)
        val variableIdentifierNode = file.firstChild.firstChild.children[1].children[0]
        return variableIdentifierNode as MiniLangVariableUsage
    }

    fun createFile(project: Project?, text: String?): MiniLangFile {
        val name = "dummy.minilang"
        return PsiFileFactory.getInstance(project)
            .createFileFromText(name, MiniLangFileType.INSTANCE, text!!) as MiniLangFile
    }
}
