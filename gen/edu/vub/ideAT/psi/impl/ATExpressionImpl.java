// This is a generated file. Not intended for manual editing.
package edu.vub.ideAT.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static edu.vub.ideAT.psi.ATTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import edu.vub.ideAT.psi.*;

public class ATExpressionImpl extends ASTWrapperPsiElement implements ATExpression {

  public ATExpressionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ATVisitor visitor) {
    visitor.visitExpression(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ATVisitor) accept((ATVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ATConstant getConstant() {
    return findChildByClass(ATConstant.class);
  }

  @Override
  @Nullable
  public ATKeyword getKeyword() {
    return findChildByClass(ATKeyword.class);
  }

  @Override
  @Nullable
  public ATOperator getOperator() {
    return findChildByClass(ATOperator.class);
  }

}
