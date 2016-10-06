package edu.vub.ideAT.psi;

import com.intellij.psi.tree.IElementType;
import edu.vub.ideAT.ATLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * Created by flo on 27/09/2016.
 */
public class ATTokenType extends IElementType {

    public ATTokenType(@NotNull @NonNls String debugName) {
        super(debugName, ATLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "ATTokenType." + super.toString();
    }
}
