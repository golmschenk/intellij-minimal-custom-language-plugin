package com.olmschenk.minilang.psi

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFileFactory
import com.olmschenk.minilang.MiniLangFile
import com.olmschenk.minilang.MiniLangFileType

object MiniLangVariableIdentifierFactory {
    @JvmStatic
    fun createVariableIdentifier(project: Project?, name: String?): MiniLangVariableIdentifier {
        val statementText = "let {$name} = 0;"
        val file = createFile(project, statementText)
        val variableIdentifierNode = file.firstChild.firstChild.children[1]  // TODO: This certainly doesn't seem like the appropriate way to do this.
        return variableIdentifierNode as MiniLangVariableIdentifier
    }

    fun createFile(project: Project?, text: String?): MiniLangFile {
        val name = "dummy.minilang"
        return PsiFileFactory.getInstance(project)
            .createFileFromText(name, MiniLangFileType.INSTANCE, text!!) as MiniLangFile
    }
}
