package edu.vub.ideAT.REPL;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by flo on 05/09/16.
 */
public class ATREPLOutput extends OutputStream {
    private JTextArea replContent;

    public ATREPLOutput(JTextArea replContent){
        this.replContent = replContent;
    }

    @Override
    public void write(int b) throws IOException {
        // redirects data to the text area
        replContent.append(String.valueOf((char)b));
        // scrolls the text area to the end of data
        replContent.setCaretPosition(replContent.getDocument().getLength());
    }
}
