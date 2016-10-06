// This is a generated file. Not intended for manual editing.
package edu.vub.ideAT.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class ATVisitor extends PsiElementVisitor {

  public void visitConstant(@NotNull ATConstant o) {
    visitPsiElement(o);
  }

  public void visitExpression(@NotNull ATExpression o) {
    visitPsiElement(o);
  }

  public void visitKeyword(@NotNull ATKeyword o) {
    visitPsiElement(o);
  }

  public void visitOperator(@NotNull ATOperator o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
