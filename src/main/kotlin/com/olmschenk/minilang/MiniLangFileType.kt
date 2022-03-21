package com.olmschenk.minilang

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class MiniLangFileType protected constructor() : LanguageFileType(MiniLangParser.INSTANCE) {
    override fun getName(): String {
        return "Sile File"
    }

    override fun getDescription(): String {
        return "Sile file"
    }

    override fun getDefaultExtension(): String {
        return FILE_EXTENSION
    }

    override fun getIcon(): Icon? {
        return MiniLangIcons.MINI_LANG_FILE_ICON
    }

    companion object {
        const val FILE_EXTENSION = "sile"
        val INSTANCE = MiniLangFileType()
    }
}