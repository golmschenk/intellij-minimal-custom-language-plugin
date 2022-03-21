package com.olmschenk.minilang

import com.intellij.lang.Language

class MiniLangLanguage : Language("MiniLang") {
    companion object {
        val INSTANCE: MiniLangLanguage = MiniLangLanguage()
    }
}