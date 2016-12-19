package edu.vub.ideAT.highlighting;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.ColorKey;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import edu.vub.ideAT.psi.ATTypes;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

/**
 * Created by flo on 27/09/2016.
 */
public class ATHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey KEYWORD = createTextAttributesKey("KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey CONSTANT = createTextAttributesKey("CONSTANTS",DefaultLanguageHighlighterColors.CONSTANT);
    public static final TextAttributesKey OPERATOR = createTextAttributesKey("OPERATORS",DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey COMMENT  = createTextAttributesKey("COMMENTS",DefaultLanguageHighlighterColors.LINE_COMMENT);
    private static final TextAttributesKey[] KEYWORDS = new TextAttributesKey[]{KEYWORD};
    private static final TextAttributesKey[] CONSTANTS =  new TextAttributesKey[]{CONSTANT};
    private static final TextAttributesKey[] OPERATORS = new TextAttributesKey[]{OPERATOR};
    private static final TextAttributesKey[] COMMENTS = new TextAttributesKey[]{COMMENT};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new edu.vub.ideAT.parslex.ATLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType iElementType) {
        if(isKeyword(iElementType)){
            return KEYWORDS;
        }
        else if(isConstant(iElementType)){
            return CONSTANTS;
        }
        else if(isOperator(iElementType)){
            return OPERATORS;
        }
        else if(isComment(iElementType)){
            return COMMENTS;
        }
        else{
            return EMPTY_KEYS;
        }
    }

    private boolean isComment(IElementType token){
        return token.equals(ATTypes.COMMENT);
    }

    private boolean isOperator(IElementType token){
        return token.equals(ATTypes.GREAT_OP) ||
                token.equals(ATTypes.EQ_OP) ||
                token.equals(ATTypes.AND_OP) ||
                token.equals(ATTypes.DIV_OP) ||
                token.equals(ATTypes.OR_OP) ||
                token.equals(ATTypes.ASSIGN_OP) ||
                token.equals(ATTypes.AT_OP) ||
                token.equals(ATTypes.POW_OP) ||
                token.equals(ATTypes.PLUS_OP) ||
                token.equals(ATTypes.MIN_OP) ||
                token.equals(ATTypes.COL_OP) ||
                token.equals(ATTypes.TILD_OP) ||
                token.equals(ATTypes.MUL_OP);
    }

    private boolean isConstant(IElementType token){
        return token.equals(ATTypes.NIL_CONST) ||
                token.equals(ATTypes.TRUE_CONST) ||
                token.equals(ATTypes.FALSE_CONST);
    }

    private boolean isKeyword(IElementType token){
        return token.equals(ATTypes.DEF_KEY) ||
                token.equals(ATTypes.DEFTYPE_KEY) ||
                token.equals(ATTypes.IF_KEY) ||
                token.equals(ATTypes.THEN_KEY) ||
                token.equals(ATTypes.ELSE_KEY) ||
                token.equals(ATTypes.EACH_KEY) ||
                token.equals(ATTypes.WHILE_KEY) ||
                token.equals(ATTypes.DO_KEY) ||
                token.equals(ATTypes.UNLESS_KEY) ||
                token.equals(ATTypes.FOREACH_KEY) ||
                token.equals(ATTypes.IN_KEY) ||
                token.equals(ATTypes.RAISE_KEY) ||
                token.equals(ATTypes.TRY_KEY) ||
                token.equals(ATTypes.CATCH_KEY) ||
                token.equals(ATTypes.USING_KEY) ||
                token.equals(ATTypes.FINALLY_KEY) ||
                token.equals(ATTypes.USINGHANDLERS_KEY) ||
                token.equals(ATTypes.OBJECT_KEY) ||
                token.equals(ATTypes.ISOLATE_KEY) ||
                token.equals(ATTypes.NEW_KEY) ||
                token.equals(ATTypes.EXTEND_KEY) ||
                token.equals(ATTypes.WITH_KEY) ||
                token.equals(ATTypes.IS_KEY) ||
                token.equals(ATTypes.TAGGEDAS_KEY) ||
                token.equals(ATTypes.CHILDOF_KEY) ||
                token.equals(ATTypes.EXTENDS_KEY) ||
                token.equals(ATTypes.TAGSOF_KEY) ||
                token.equals(ATTypes.SELF_KEY) ||
                token.equals(ATTypes.SUPER_KEY) ||
                token.equals(ATTypes.IMPORT_KEY) ||
                token.equals(ATTypes.EXCLUDE_KEY) ||
                token.equals(ATTypes.ALIAS_KEY) ||
                token.equals(ATTypes.MIRROR_KEY) ||
                token.equals(ATTypes.MIRROREDBY_KEY) ||
                token.equals(ATTypes.BECOMEMIRROREDBY_KEY) ||
                token.equals(ATTypes.REFLECT_KEY) ||
                token.equals(ATTypes.REFLECTONACTOR_KEY) ||
                token.equals(ATTypes.PRINT_KEY) ||
                token.equals(ATTypes.EVAL_KEY) ||
                token.equals(ATTypes.READ_KEY) ||
                token.equals(ATTypes.ACTOR_KEY) ||
                token.equals(ATTypes.WHENEVER_KEY) ||
                token.equals(ATTypes.DISCOVERED_KEY) ||
                token.equals(ATTypes.DISCONNECTED_KEY) ||
                token.equals(ATTypes.RECONNECTED_KEY) ||
                token.equals(ATTypes.TAKENOFFLINE_KEY) ||
                token.equals(ATTypes.WHEN_KEY) ||
                token.equals(ATTypes.BECOMES_KEY) ||
                token.equals(ATTypes.RETRACT_KEY) ||
                token.equals(ATTypes.TAKEOFFLINE_KEY) ||
                token.equals(ATTypes.EXPORT_KEY) ||
                token.equals(ATTypes.AS_KEY) ||
                token.equals(ATTypes.ASYNCSEND_KEY) ||
                token.equals(ATTypes.SUBTYPE_KEY) ||
                token.equals(ATTypes.HOSEND_KEY)
                ;
    }
}
