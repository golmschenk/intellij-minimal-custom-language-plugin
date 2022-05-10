package com.olmschenk.minilang

import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase

@TestDataPath("\$CONTENT_ROOT/src/test/testData")
class MiniLangBasicRenameTest : BasePlatformTestCase() {
    override fun getTestDataPath(): String {
        return "src/test/testData/renameBasic"
    }

    fun testRename() {
        myFixture.testRename("fileBeforeRename.minilang", "fileAfterRename.minilang", "y")
    }
}
