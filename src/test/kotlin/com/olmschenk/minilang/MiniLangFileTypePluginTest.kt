package com.olmschenk.minilang

import com.intellij.ide.highlighter.XmlFileType
import com.intellij.psi.xml.XmlFile
import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.intellij.util.PsiErrorElementUtil
import junit.framework.TestCase

@TestDataPath("\$CONTENT_ROOT/src/test/testData")
class MiniLangFileTypePluginTest : BasePlatformTestCase() {

    fun testMiniLangFile() {
        val psiFile = myFixture.configureByText(MiniLangFileType.INSTANCE, "let x = 5;")
        val miniLangFile = assertInstanceOf(psiFile, MiniLangFile::class.java)

        assertFalse(PsiErrorElementUtil.hasErrors(project, miniLangFile.virtualFile))

        val variableName = miniLangFile.children[0].children[0].children[0].children[0].text
        TestCase.assertEquals(variableName, "x")
    }

    override fun getTestDataPath() = "src/test/testData/rename"

    fun testRename() {
        myFixture.testRename("foo.xml", "foo_after.xml", "a2")
    }
}
