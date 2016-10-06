package edu.vub.ideAT.psi;

import com.intellij.psi.tree.IElementType;
import edu.vub.ideAT.ATLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * Created by flo on 27/09/2016.
 */
public class ATElementType extends IElementType {

    public ATElementType(@NotNull @NonNls String debugName) {
        super(debugName, ATLanguage.INSTANCE);
    }

}
