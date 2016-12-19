// This is a generated file. Not intended for manual editing.
package edu.vub.ideAT.parslex;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static edu.vub.ideAT.psi.ATTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class ATParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    if (t == CONSTANT) {
      r = constant(b, 0);
    }
    else if (t == EXPRESSION) {
      r = expression(b, 0);
    }
    else if (t == KEYWORD) {
      r = keyword(b, 0);
    }
    else if (t == OPERATOR) {
      r = operator(b, 0);
    }
    else {
      r = parse_root_(t, b, 0);
    }
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return ATFile(b, l + 1);
  }

  /* ********************************************************** */
  // expression*
  static boolean ATFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ATFile")) return false;
    int c = current_position_(b);
    while (true) {
      if (!expression(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ATFile", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // "nil" | "true" | "false"
  public static boolean constant(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "constant")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CONSTANT, "<constant>");
    r = consumeToken(b, NIL_CONST);
    if (!r) r = consumeToken(b, TRUE_CONST);
    if (!r) r = consumeToken(b, FALSE_CONST);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // keyword | operator | constant | COMMENT
  public static boolean expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EXPRESSION, "<expression>");
    r = keyword(b, l + 1);
    if (!r) r = operator(b, l + 1);
    if (!r) r = constant(b, l + 1);
    if (!r) r = consumeToken(b, COMMENT);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "def" | "deftype" | "if:" | "then:" | "else:" | "each:" | "while:" | "do:" | "unless:" | "foreach:" | "in:" | "raise:" | "try:" | "catch:" | "using:" | "finally:" | "usingHandlers:" | "object:" | "isolate:" | "new" | "extend:" | "with:" | "is:" | "taggedAs:" | "childOf:" | "extends:" | "tagsOf:" | "self" | "super" | "import" | "exclude" | "alias" | "mirror:" | "mirroredBy:" | "becomeMirroredBy:" | "reflect:" | "reflectOnActor" | "print:" | "eval:" | "read:" | "actor:" | "whenever:" | "discovered:" | "disconnected:" | "reconnected:" | "takenOffline:" | "when:" | "becomes:" | "retract:" | "takeOffline:" | "export:" | "as:" | "<-" | "<:" | "<+"
  public static boolean keyword(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "keyword")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, KEYWORD, "<keyword>");
    r = consumeToken(b, DEF_KEY);
    if (!r) r = consumeToken(b, DEFTYPE_KEY);
    if (!r) r = consumeToken(b, IF_KEY);
    if (!r) r = consumeToken(b, THEN_KEY);
    if (!r) r = consumeToken(b, ELSE_KEY);
    if (!r) r = consumeToken(b, EACH_KEY);
    if (!r) r = consumeToken(b, WHILE_KEY);
    if (!r) r = consumeToken(b, DO_KEY);
    if (!r) r = consumeToken(b, UNLESS_KEY);
    if (!r) r = consumeToken(b, FOREACH_KEY);
    if (!r) r = consumeToken(b, IN_KEY);
    if (!r) r = consumeToken(b, RAISE_KEY);
    if (!r) r = consumeToken(b, TRY_KEY);
    if (!r) r = consumeToken(b, CATCH_KEY);
    if (!r) r = consumeToken(b, USING_KEY);
    if (!r) r = consumeToken(b, FINALLY_KEY);
    if (!r) r = consumeToken(b, USINGHANDLERS_KEY);
    if (!r) r = consumeToken(b, OBJECT_KEY);
    if (!r) r = consumeToken(b, ISOLATE_KEY);
    if (!r) r = consumeToken(b, NEW_KEY);
    if (!r) r = consumeToken(b, EXTEND_KEY);
    if (!r) r = consumeToken(b, WITH_KEY);
    if (!r) r = consumeToken(b, IS_KEY);
    if (!r) r = consumeToken(b, TAGGEDAS_KEY);
    if (!r) r = consumeToken(b, CHILDOF_KEY);
    if (!r) r = consumeToken(b, EXTENDS_KEY);
    if (!r) r = consumeToken(b, TAGSOF_KEY);
    if (!r) r = consumeToken(b, SELF_KEY);
    if (!r) r = consumeToken(b, SUPER_KEY);
    if (!r) r = consumeToken(b, IMPORT_KEY);
    if (!r) r = consumeToken(b, EXCLUDE_KEY);
    if (!r) r = consumeToken(b, ALIAS_KEY);
    if (!r) r = consumeToken(b, MIRROR_KEY);
    if (!r) r = consumeToken(b, MIRROREDBY_KEY);
    if (!r) r = consumeToken(b, BECOMEMIRROREDBY_KEY);
    if (!r) r = consumeToken(b, REFLECT_KEY);
    if (!r) r = consumeToken(b, REFLECTONACTOR_KEY);
    if (!r) r = consumeToken(b, PRINT_KEY);
    if (!r) r = consumeToken(b, EVAL_KEY);
    if (!r) r = consumeToken(b, READ_KEY);
    if (!r) r = consumeToken(b, ACTOR_KEY);
    if (!r) r = consumeToken(b, WHENEVER_KEY);
    if (!r) r = consumeToken(b, DISCOVERED_KEY);
    if (!r) r = consumeToken(b, DISCONNECTED_KEY);
    if (!r) r = consumeToken(b, RECONNECTED_KEY);
    if (!r) r = consumeToken(b, TAKENOFFLINE_KEY);
    if (!r) r = consumeToken(b, WHEN_KEY);
    if (!r) r = consumeToken(b, BECOMES_KEY);
    if (!r) r = consumeToken(b, RETRACT_KEY);
    if (!r) r = consumeToken(b, TAKEOFFLINE_KEY);
    if (!r) r = consumeToken(b, EXPORT_KEY);
    if (!r) r = consumeToken(b, AS_KEY);
    if (!r) r = consumeToken(b, ASYNCSEND_KEY);
    if (!r) r = consumeToken(b, SUBTYPE_KEY);
    if (!r) r = consumeToken(b, HOSEND_KEY);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ">" | "==" | "&" | "/" | "|" | ":=" | "@" | "^" | "+" | "-" | ":" | "~" | "*"
  public static boolean operator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "operator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OPERATOR, "<operator>");
    r = consumeToken(b, GREAT_OP);
    if (!r) r = consumeToken(b, EQ_OP);
    if (!r) r = consumeToken(b, AND_OP);
    if (!r) r = consumeToken(b, DIV_OP);
    if (!r) r = consumeToken(b, OR_OP);
    if (!r) r = consumeToken(b, ASSIGN_OP);
    if (!r) r = consumeToken(b, AT_OP);
    if (!r) r = consumeToken(b, POW_OP);
    if (!r) r = consumeToken(b, PLUS_OP);
    if (!r) r = consumeToken(b, MIN_OP);
    if (!r) r = consumeToken(b, COL_OP);
    if (!r) r = consumeToken(b, TILD_OP);
    if (!r) r = consumeToken(b, MUL_OP);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

}
