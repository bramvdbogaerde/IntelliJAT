package edu.vub.ideAT.parslex;

import com.intellij.lexer.FlexAdapter;
import java.io.Reader;

/**
 * Created by flo on 27/09/2016.
 */
public class ATLexerAdapter extends FlexAdapter {
    public ATLexerAdapter() {
        super(new ATLexer((Reader) null));
    }
}