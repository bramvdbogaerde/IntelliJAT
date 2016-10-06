package edu.vub.ideAT;

import com.intellij.openapi.fileTypes.LanguageFileType;

import javax.swing.*;

/**
 * Created by flo on 26/08/16.
 */
public class ATFileType extends LanguageFileType {
    public static final ATFileType INSTANCE = new ATFileType();
    public static final String EXTENSION = "at";

    private ATFileType(){
        super(ATLanguage.INSTANCE);
    }

    public String getName(){
        return "AmbientTalk File";
    }

    public String getDescription(){
        return "AmbientTalk language file";
    }

    public String getDefaultExtension(){
        return EXTENSION;
    }

    public Icon getIcon(){
        return ATIcons.ATIcon;
    }
}
