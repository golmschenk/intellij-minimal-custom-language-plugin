package com.olmschenk.minilang

import com.intellij.codeInsight.completion.CompletionType
import com.intellij.testFramework.fixtures.BasePlatformTestCase

class MiniLangCodeCompletionTest : BasePlatformTestCase() {
    override fun getTestDataPath(): String {
        return "src/test/testData"
    }

    fun testCompletion() {
        myFixture.configureByFiles("codeCompletionTestData.minilang")
        myFixture.complete(CompletionType.BASIC)
        val lookupElementStrings = myFixture.lookupElementStrings!!
        assertNotNull(lookupElementStrings)
        assertSameElements(lookupElementStrings, "aaaaa", "aaabbb")
    }
}
