// This is a generated file. Not intended for manual editing.
package edu.vub.ideAT.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import edu.vub.ideAT.psi.impl.*;

public interface ATTypes {

  IElementType CONSTANT = new ATElementType("CONSTANT");
  IElementType EXPRESSION = new ATElementType("EXPRESSION");
  IElementType KEYWORD = new ATElementType("KEYWORD");
  IElementType OPERATOR = new ATElementType("OPERATOR");

  IElementType ACTOR_KEY = new ATTokenType("actor:");
  IElementType ALIAS_KEY = new ATTokenType("alias");
  IElementType AND_OP = new ATTokenType("&");
  IElementType ASSIGN_OP = new ATTokenType(":=");
  IElementType ASYNCSEND_KEY = new ATTokenType("<-");
  IElementType AS_KEY = new ATTokenType("as:");
  IElementType AT_OP = new ATTokenType("@");
  IElementType BECOMEMIRROREDBY_KEY = new ATTokenType("becomeMirroredBy:");
  IElementType BECOMES_KEY = new ATTokenType("becomes:");
  IElementType CATCH_KEY = new ATTokenType("catch:");
  IElementType CHILDOF_KEY = new ATTokenType("childOf:");
  IElementType COL_OP = new ATTokenType(":");
  IElementType DEFTYPE_KEY = new ATTokenType("deftype");
  IElementType DEF_KEY = new ATTokenType("def");
  IElementType DISCONNECTED_KEY = new ATTokenType("disconnected:");
  IElementType DISCOVERED_KEY = new ATTokenType("discovered:");
  IElementType DIV_OP = new ATTokenType("/");
  IElementType DO_KEY = new ATTokenType("do:");
  IElementType EACH_KEY = new ATTokenType("each:");
  IElementType ELSE_KEY = new ATTokenType("else:");
  IElementType EQ_OP = new ATTokenType("==");
  IElementType EVAL_KEY = new ATTokenType("eval:");
  IElementType EXCLUDE_KEY = new ATTokenType("exclude");
  IElementType EXPORT_KEY = new ATTokenType("export:");
  IElementType EXTENDS_KEY = new ATTokenType("extends:");
  IElementType EXTEND_KEY = new ATTokenType("extend:");
  IElementType FALSE_CONST = new ATTokenType("false");
  IElementType FINALLY_KEY = new ATTokenType("finally:");
  IElementType FOREACH_KEY = new ATTokenType("foreach:");
  IElementType GREAT_OP = new ATTokenType(">");
  IElementType HOSEND_KEY = new ATTokenType("<+");
  IElementType IF_KEY = new ATTokenType("if:");
  IElementType IMPORT_KEY = new ATTokenType("import");
  IElementType IN_KEY = new ATTokenType("in:");
  IElementType ISOLATE_KEY = new ATTokenType("isolate:");
  IElementType IS_KEY = new ATTokenType("is:");
  IElementType MIN_OP = new ATTokenType("-");
  IElementType MIRROREDBY_KEY = new ATTokenType("mirroredBy:");
  IElementType MIRROR_KEY = new ATTokenType("mirror:");
  IElementType MUL_OP = new ATTokenType("*");
  IElementType NEW_KEY = new ATTokenType("new");
  IElementType NIL_CONST = new ATTokenType("nil");
  IElementType OBJECT_KEY = new ATTokenType("object:");
  IElementType OR_OP = new ATTokenType("|");
  IElementType PLUS_OP = new ATTokenType("+");
  IElementType POW_OP = new ATTokenType("^");
  IElementType PRINT_KEY = new ATTokenType("print:");
  IElementType RAISE_KEY = new ATTokenType("raise:");
  IElementType READ_KEY = new ATTokenType("read:");
  IElementType RECONNECTED_KEY = new ATTokenType("reconnected:");
  IElementType REFLECTONACTOR_KEY = new ATTokenType("reflectOnActor");
  IElementType REFLECT_KEY = new ATTokenType("reflect:");
  IElementType RETRACT_KEY = new ATTokenType("retract:");
  IElementType SELF_KEY = new ATTokenType("self");
  IElementType SUBTYPE_KEY = new ATTokenType("<:");
  IElementType SUPER_KEY = new ATTokenType("super");
  IElementType TAGGEDAS_KEY = new ATTokenType("taggedAs:");
  IElementType TAGSOF_KEY = new ATTokenType("tagsOf:");
  IElementType TAKENOFFLINE_KEY = new ATTokenType("takenOffline:");
  IElementType TAKEOFFLINE_KEY = new ATTokenType("takeOffline:");
  IElementType THEN_KEY = new ATTokenType("then:");
  IElementType TILD_OP = new ATTokenType("~");
  IElementType TRUE_CONST = new ATTokenType("true");
  IElementType TRY_KEY = new ATTokenType("try:");
  IElementType UNLESS_KEY = new ATTokenType("unless:");
  IElementType USINGHANDLERS_KEY = new ATTokenType("usingHandlers:");
  IElementType USING_KEY = new ATTokenType("using:");
  IElementType WHENEVER_KEY = new ATTokenType("whenever:");
  IElementType WHEN_KEY = new ATTokenType("when:");
  IElementType WHILE_KEY = new ATTokenType("while:");
  IElementType WITH_KEY = new ATTokenType("with:");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == CONSTANT) {
        return new ATConstantImpl(node);
      }
      else if (type == EXPRESSION) {
        return new ATExpressionImpl(node);
      }
      else if (type == KEYWORD) {
        return new ATKeywordImpl(node);
      }
      else if (type == OPERATOR) {
        return new ATOperatorImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
