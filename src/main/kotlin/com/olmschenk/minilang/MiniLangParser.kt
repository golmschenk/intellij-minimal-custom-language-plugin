package com.olmschenk.minilang

import com.intellij.lang.Language

class MiniLangParser : Language("MiniLang") {
    companion object {
        val INSTANCE: MiniLangParser = MiniLangParser()
    }
}