package edu.vub.ideAT.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by flo on 26/09/2016.
 */
public class test extends JFrame {
    private JPanel root;
    private JTextArea consoleContent;

    public test(){
        root = new JPanel();
        consoleContent = new JTextArea();
        consoleContent.setFocusable(true);
        consoleContent.requestFocus();
        consoleContent.setColumns(10);
        consoleContent.setRows(10);
        root.add(consoleContent);
        consoleContent.getInputMap().put(KeyStroke.getKeyStroke("BACK_SPACE"),"none");
        this.add(root);
    }


    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new test().setVisible(true);
            }
        });
    }
}
