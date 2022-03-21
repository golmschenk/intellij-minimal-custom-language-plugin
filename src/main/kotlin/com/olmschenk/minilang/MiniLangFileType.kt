package com.olmschenk.minilang

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class MiniLangFileType protected constructor() : LanguageFileType(MiniLangLanguage.INSTANCE) {
    override fun getName(): String {
        return "MiniLang File"
    }

    override fun getDescription(): String {
        return "MiniLang file"
    }

    override fun getDefaultExtension(): String {
        return FILE_EXTENSION
    }

    override fun getIcon(): Icon? {
        return MiniLangIcons.MINI_LANG_FILE_ICON
    }

    companion object {
        const val FILE_EXTENSION = "minilang"
        val INSTANCE = MiniLangFileType()
    }
}