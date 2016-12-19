package edu.vub.ideAT.parslex;
import static edu.vub.ideAT.psi.ATTypes.*;
import com.intellij.psi.TokenType;
import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;

%%
%{
 public ATLexer() {
     this((java.io.Reader)null);
 }
%}
%public
%class ATLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType

WHITE_SPACE=[\ \t\f]
Identifier = [A-Za-z_][A-Za-z_0-9]*
Comment = "//" [^\r\n]*

%%

<YYINITIAL> {
    "def" {return DEF_KEY;}
    "deftype" {return DEFTYPE_KEY;}
    "if:" {return IF_KEY;}
    "then:" {return THEN_KEY;}
    "else:" {return ELSE_KEY;}
    "each:" {return EACH_KEY;}
    "while:" {return WHILE_KEY;}
    "do:" {return DO_KEY;}
    "unless:" {return UNLESS_KEY;}
    "foreach:" {return FOREACH_KEY;}
    "in:" {return IN_KEY;}
    "raise:" {return RAISE_KEY;}
    "try:" {return TRY_KEY;}
    "catch:" {return CATCH_KEY;}
    "using:" {return USING_KEY;}
    "finally:" {return FINALLY_KEY;}
    "usingHandlers:" {return USINGHANDLERS_KEY;}
    "object:" {return OBJECT_KEY;}
    "isolate:" {return ISOLATE_KEY;}
    "new" {return NEW_KEY;}
    "extend:" {return EXTEND_KEY;}
    "with:" {return WITH_KEY;}
    "is:" {return IS_KEY;}
    "taggedAs:" {return TAGGEDAS_KEY;}
    "childOf:" {return CHILDOF_KEY;}
    "extends:" {return EXTENDS_KEY;}
    "tagsOf:" {return TAGSOF_KEY;}
    "self" {return SELF_KEY;}
    "super" {return SUPER_KEY;}
    "import" {return IMPORT_KEY;}
    "exclude" {return EXCLUDE_KEY;}
    "alias" {return ALIAS_KEY;}
    "mirror:" {return MIRROR_KEY;}
    "mirroredBy:" {return MIRROREDBY_KEY;}
    "becomeMirroredBy:" {return BECOMEMIRROREDBY_KEY;}
    "reflect:" {return REFLECT_KEY;}
    "reflectOnActor" {return REFLECTONACTOR_KEY;}
    "print:" {return PRINT_KEY;}
    "eval:" {return EVAL_KEY;}
    "read:" {return READ_KEY;}
    "actor:" {return ACTOR_KEY;}
    "whenever:" {return WHENEVER_KEY;}
    "discovered:" {return DISCOVERED_KEY;}
    "disconnected:" {return DISCONNECTED_KEY;}
    "reconnected:" {return RECONNECTED_KEY;}
    "takenOffline:" {return TAKENOFFLINE_KEY;}
    "when:" {return WHEN_KEY;}
    "becomes:" {return BECOMES_KEY;}
    "retract:" {return RETRACT_KEY;}
    "takeOffline:" {return TAKEOFFLINE_KEY;}
    "export:" {return EXPORT_KEY;}
    "as:" {return AS_KEY;}
    "<-" {return ASYNCSEND_KEY;}
    "<:" {return SUBTYPE_KEY;}
    "<+" {return HOSEND_KEY;}
    "nil" {return NIL_CONST;}
    "true" {return TRUE_CONST;}
    "false" {return FALSE_CONST;}
    ">" {return GREAT_OP;}
    "==" {return EQ_OP;}
    "&" {return AND_OP;}
    "/" {return DIV_OP;}
    "|" {return OR_OP;}
    ":=" {return ASSIGN_OP;}
    "@" {return AT_OP;}
    "^" {return POW_OP;}
    "+" {return PLUS_OP;}
    "-" {return MIN_OP;}
    ":" {return COL_OP;}
    "~" {return TILD_OP;}
    "*" {return MUL_OP;}
    {Comment} {return COMMENT;}
    {Identifier} {return TokenType.WHITE_SPACE; }
    [^((?!KEYWORD).)*$] {return TokenType.WHITE_SPACE;}



}
. {return TokenType.BAD_CHARACTER;}