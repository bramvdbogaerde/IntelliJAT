package edu.vub.ideAT.ui;

import javax.swing.*;

/**
 * Created by flo on 26/09/16.
 */
public class NewJFrame extends javax.swing.JFrame {

    public NewJFrame() {
        initComponents();
    }

    private void initComponents() {

        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTextArea jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextArea1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }

    private void jTextArea1KeyPressed(java.awt.event.KeyEvent evt) {

        if(evt.getKeyCode() == 8){
            System.err.println("evt "+evt.getKeyCode());
            System.out.println("Got event back space!");
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }



}
