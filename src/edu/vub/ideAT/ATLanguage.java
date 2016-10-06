package edu.vub.ideAT;

import com.intellij.lang.Language;

/**
 * Created by flo on 26/08/16.
 */
public class ATLanguage extends Language {
    public static final ATLanguage INSTANCE = new ATLanguage();

    private ATLanguage(){
        super("AmbientTalk");
    }
}
