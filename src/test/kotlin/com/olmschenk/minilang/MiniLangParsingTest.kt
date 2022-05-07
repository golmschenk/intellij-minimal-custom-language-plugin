package com.olmschenk.minilang

import com.intellij.testFramework.ParsingTestCase

class MiniLangParsingTest : ParsingTestCase("", "minilang", MiniLangParserDefinition()) {
    fun testParsingTestData() {
        doTest(true)
    }

    /**
     * @return path to test data file directory relative to root of this module.
     */
    override fun getTestDataPath(): String {
        return "src/test/testData"
    }

    override fun skipSpaces(): Boolean {
        return false
    }

    override fun includeRanges(): Boolean {
        return true
    }
}