package com.olmschenk.minilang

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.ParserDefinition.SpaceRequirements
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import com.olmschenk.minilang.parser.MiniLangLexer
import com.olmschenk.minilang.parser.MiniLangParser
import com.olmschenk.minilang.psi.impl.MiniLangVariableDeclarationImpl
import com.olmschenk.minilang.psi.impl.MiniLangVariableReferrerImpl
import org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory
import org.antlr.intellij.adaptor.lexer.RuleIElementType
import org.antlr.intellij.adaptor.parser.ANTLRParserAdaptor
import org.antlr.v4.runtime.Parser
import org.antlr.v4.runtime.tree.ParseTree


class MiniLangParserDefinition : ParserDefinition {
    override fun createLexer(project: Project): Lexer {
        val lexer = MiniLangLexer(null)
        return ANTLRLexerAdaptor(MiniLangLanguage.INSTANCE, lexer)
    }

    override fun getWhitespaceTokens(): TokenSet {
        return WHITESPACE
    }

    override fun getCommentTokens(): TokenSet {
        return TokenSet.EMPTY
    }

    override fun getStringLiteralElements(): TokenSet {
        return TokenSet.EMPTY
    }

    override fun createParser(project: Project): PsiParser {
        val parser = MiniLangParser(null)
        return object : ANTLRParserAdaptor(MiniLangLanguage.INSTANCE, parser) {
            override fun parse(parser: Parser, root: IElementType): ParseTree {
                return (parser as MiniLangParser).file_content()
            }
        }
    }

    override fun getFileNodeType(): IFileElementType {
        return FILE
    }

    override fun createFile(viewProvider: FileViewProvider): PsiFile {
        return MiniLangFile(viewProvider)
    }

    override fun spaceExistenceTypeBetweenTokens(left: ASTNode, right: ASTNode): SpaceRequirements {
        return SpaceRequirements.MAY
    }

    override fun createElement(node: ASTNode): PsiElement {
        val elementType = node.elementType
        if ((elementType as RuleIElementType).ruleIndex == MiniLangParser.RULE_variable_definition_identifier) {
            return MiniLangVariableDeclarationImpl(node)
        }
        if ((elementType as RuleIElementType).ruleIndex == MiniLangParser.RULE_variable_reference_identifier) {
            return MiniLangVariableReferrerImpl(node)
        }
        return ASTWrapperPsiElement(node)
    }

    companion object {
        val FILE = IFileElementType(MiniLangLanguage.INSTANCE)

        init {
            // TODO: Remove this suppression.
            @Suppress("DEPRECATION")
            PSIElementTypeFactory.defineLanguageIElementTypes(
                MiniLangLanguage.INSTANCE,
                MiniLangParser.tokenNames,
                MiniLangParser.ruleNames
            )
        }

        val WHITESPACE = PSIElementTypeFactory.createTokenSet(
            MiniLangLanguage.INSTANCE,
            MiniLangLexer.WHITESPACE
        )
    }
}
