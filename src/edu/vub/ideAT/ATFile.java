package edu.vub.ideAT;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Created by flo on 05/09/16.
 */
public class ATFile extends PsiFileBase {
    public ATFile(@NotNull FileViewProvider viewProvider){
        super(viewProvider,ATLanguage.INSTANCE);
    }
    @NotNull
    @Override
    public FileType getFileType() {
        return ATFileType.INSTANCE;
    }

    @Override
    public String toString(){
        return "AmbientTalk file";
    }

    @Override
    public Icon getIcon(int flags){
        return super.getIcon(flags);
    }
}
