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

public class ATKeywordImpl extends ASTWrapperPsiElement implements ATKeyword {

  public ATKeywordImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ATVisitor visitor) {
    visitor.visitKeyword(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ATVisitor) accept((ATVisitor)visitor);
    else super.accept(visitor);
  }

}
