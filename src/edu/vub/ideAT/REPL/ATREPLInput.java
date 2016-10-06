package edu.vub.ideAT.REPL;

import edu.vub.ideAT.ui.ATREPL;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by flo on 06/09/16.
 */
public class ATREPLInput extends InputStream {
    private JTextArea   console;
    private ATREPL replInstance;
    private int         lastPos = 0;
    public  Action      originalBackSpace;

    public ATREPLInput(JTextArea console, ATREPL replInstance){
        this.console        = console;
        this.replInstance   = replInstance;
        /*Object backCode     = console.getInputMap().get(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0));
        originalBackSpace   = console.getActionMap().get(backCode);*/
        console.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("BACK_SPACE"),"none");
        console.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("BACK_SPACE"),"none");
        console.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("BACK_SPACE"),"none");
        console.getInputMap().put(KeyStroke.getKeyStroke("BACK_SPACE"),"none");

        console.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"myEnter");
        console.getActionMap().put("myEnter",new EnterAction());
        console.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE,0),"myBackSpace");
        console.getActionMap().put("myBackSpace",new BackAction());
        console.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                //System.out.println("Typed: " + e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //System.out.println("Pressed: " + e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //System.out.println("Released: " + e);
            }
        });
    }

    @Override
    public int read() throws IOException {
        //Issue with the way text is read, quick fix by directly invoking interpreter from on key callback
        /*if(pointer >= contents.size()) return -1;
        //return this.contents[pointer++];
        if(pointer >= contents.size()){
            System.out.println("Nothing to read");
            return -1;
        }
        else{
            System.out.println("Reading: " + contents.get(pointer));
            return contents.get(pointer++);
        }*/
        return -1;
    }

    class EnterAction extends AbstractAction {
        public void actionPerformed(ActionEvent e) {
            int offset= 0;
            try {
                offset = console.getLineOfOffset(console.getCaretPosition());
                int start=console.getLineStartOffset(offset);
                int end=console.getLineEndOffset(offset);
                //Given that we are overwriting ATIO we need to newline and prompt ourselves (ugly)
                replInstance.getInterpreter().getIatio().println();
                //+ 1 to ignore carret
                replInstance.getInterpreter().evalAndPrint(console.getText(start + 1, (end - start)));
                replInstance.getInterpreter().getIatio().print(">");
                lastPos = console.getCaretPosition();
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }
        }
    }

    class BackAction extends AbstractAction{
        public void actionPerformed(ActionEvent e){
            System.out.println("Custom backspace. Current: " + console.getCaretPosition() + " last: " + lastPos);
            if(console.getCaretPosition() > lastPos){
                originalBackSpace.actionPerformed(e);
            }
        }
    }

}
