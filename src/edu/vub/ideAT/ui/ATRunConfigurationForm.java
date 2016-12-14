package edu.vub.ideAT.ui;

import com.intellij.openapi.project.Project;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import edu.vub.ideAT.configuration.ATConfigDefaults;
import edu.vub.ideAT.configuration.ATRunConfiguration;
import javafx.scene.control.RadioButton;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by flo on 29/08/16.
 */
public class ATRunConfigurationForm {
    private Project project;
    private JPanel panel1;
    private JTextField scriptPath;
    private JButton browseButton;
    private JTabbedPane tabbedPane1;
    private JPanel Main;
    private JPanel Arguments;
    private JRadioButton defaultATLibRadioButton;
    private JRadioButton otherATLibRadioButton;
    private JTextField defaultATLibPath;
    private JTextField otherATLibPath;
    private JTextField IATCommandLineArgs;
    private JRadioButton defaultATHomeRadioButton;
    private JTextField defaultATHomePath;
    private JRadioButton otherATHomeRadioButton;
    private JTextField otherATHomePath;
    private JRadioButton defaultATInitRadioButton;
    private JTextField defaultATInitPath;
    private JRadioButton otherATInitRadioButton;
    private JTextField otherATInitPath;

    public ATRunConfigurationForm(ATRunConfiguration config) {
        this.project = config.getScriptProject();
        $$$setupUI$$$();
    }

    public JPanel getRoot() {
        return panel1;
    }

    public void setScriptPath(String path) {
        scriptPath.setText(path);
    }

    public void setScriptProject(Project project) {
        this.project = project;
    }

    public String getScriptPath() {
        return scriptPath.getText();
    }

    public void setScriptOtherATLibPath(String path) {
        defaultATLibRadioButton.setSelected(false);
        defaultATLibPath.setText("");
        otherATLibRadioButton.setSelected(true);
        otherATLibPath.setText(path);
    }

    public boolean isOtherATLibPath() {
        return otherATLibRadioButton.isSelected();
    }

    public String getOtherATLibPath() {
        return otherATLibPath.getText();
    }

    public void setScriptOtherATHomePath(String path) {
        defaultATHomeRadioButton.setSelected(false);
        defaultATHomePath.setText("");
        otherATHomeRadioButton.setSelected(true);
        otherATHomePath.setText(path);
    }

    public boolean isOtherATHomePath() {
        return otherATHomeRadioButton.isSelected();
    }

    public String getOtherATHomePath() {
        return otherATHomePath.getText();
    }

    public void setScriptOtherATInitPath(String path) {
        defaultATInitRadioButton.setSelected(false);
        defaultATInitPath.setText("");
        otherATInitRadioButton.setSelected(true);
        otherATInitPath.setText(path);
    }

    public boolean isOtherATInitPath() {
        return otherATInitRadioButton.isSelected();
    }

    public String getOtherATInitPath() {
        return otherATInitPath.getText();
    }

    public String getIATCommandLineArgs() {
        return IATCommandLineArgs.getText();
    }

    public void setIATCommandLineArgs(String args) {
        IATCommandLineArgs.setText(args);
    }

    private void createFileChooser(JTextField toSet) {
        JFileChooser fc = new JFileChooser(project.getBasePath());
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setAcceptAllFileFilterUsed(false);
        int ret = fc.showOpenDialog(panel1);
        if (ret == JFileChooser.APPROVE_OPTION) {
            toSet.setText(fc.getCurrentDirectory().getAbsolutePath());
        }
    }

    private void generateButtons(JRadioButton defaultButton, JRadioButton otherButton, JTextField defaultTextField, JTextField otherTextField, String defaultValue) {
        defaultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                defaultButton.setSelected(true);
                otherButton.setSelected(false);
                otherTextField.setText("");
                defaultTextField.setText(defaultValue);
            }
        });
        otherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                otherButton.setSelected(true);
                defaultButton.setSelected(false);
                defaultTextField.setText("");
                createFileChooser(otherTextField);
            }
        });
    }

    private void createUIComponents() {
        browseButton = new JButton();
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser(project.getBasePath());
                fc.setFileFilter(new FileFilter() {

                    public String getDescription() {
                        return "AmbientTalk Source Files (*.at)";
                    }

                    public boolean accept(File f) {
                        if (f.isDirectory()) {
                            return true;
                        } else {
                            String filename = f.getName().toLowerCase();
                            return filename.endsWith(".at");
                        }
                    }
                });
                int ret = fc.showOpenDialog(panel1);
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File f = fc.getSelectedFile();
                    scriptPath.setText(f.getAbsolutePath());
                }
            }
        });
        defaultATLibRadioButton = new JRadioButton();
        defaultATLibRadioButton.setSelected(true);
        otherATLibRadioButton = new JRadioButton();
        defaultATLibPath = new JTextField(ATConfigDefaults.getDefaultATLibPath());
        otherATLibPath = new JTextField();
        generateButtons(defaultATLibRadioButton, otherATLibRadioButton, defaultATLibPath, otherATLibPath, ATConfigDefaults.getDefaultATLibPath());
        defaultATHomeRadioButton = new JRadioButton();
        otherATHomeRadioButton = new JRadioButton();
        defaultATHomeRadioButton.setSelected(true);
        defaultATHomePath = new JTextField(ATConfigDefaults.getDefaultATJarPath());
        otherATHomePath = new JTextField();
        generateButtons(defaultATHomeRadioButton, otherATHomeRadioButton, defaultATHomePath, otherATHomePath, ATConfigDefaults.getDefaultATJarPath());
        defaultATInitRadioButton = new JRadioButton();
        defaultATInitRadioButton.setSelected(true);
        otherATInitRadioButton = new JRadioButton();
        defaultATInitPath = new JTextField(ATConfigDefaults.generateATInitPath(ATConfigDefaults.getDefaultATLibPath()));
        otherATInitPath = new JTextField();
        generateButtons(defaultATInitRadioButton, otherATInitRadioButton, defaultATInitPath, otherATInitPath, ATConfigDefaults.generateATInitPath(ATConfigDefaults.getDefaultATLibPath()));
        IATCommandLineArgs = new JTextField(ATConfigDefaults.getDefaultATCommandLineArgs());
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBorder(BorderFactory.createTitledBorder(""));
        tabbedPane1 = new JTabbedPane();
        panel1.add(tabbedPane1, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        Main = new JPanel();
        Main.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("Main", new ImageIcon(getClass().getResource("/edu/vub/ideAT/ui/atIcon.png")), Main);
        Main.setBorder(BorderFactory.createTitledBorder("Program:"));
        scriptPath = new JTextField();
        Main.add(scriptPath, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer1 = new Spacer();
        Main.add(spacer1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        browseButton.setText("Browse");
        Main.add(browseButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Arguments = new JPanel();
        Arguments.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("Arguments", new ImageIcon(getClass().getResource("/edu/vub/ideAT/ui/variable.gif")), Arguments);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        Arguments.add(panel2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel2.setBorder(BorderFactory.createTitledBorder("AmbientTalk VM Arguments:"));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel3.setBorder(BorderFactory.createTitledBorder("atlib:"));
        defaultATLibRadioButton.setText("Default:");
        panel3.add(defaultATLibRadioButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        otherATLibRadioButton.setText("Other:");
        panel3.add(otherATLibRadioButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel3.add(defaultATLibPath, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        panel3.add(otherATLibPath, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel4.setBorder(BorderFactory.createTitledBorder("IAT command line arguments:"));
        panel4.add(IATCommandLineArgs, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel5.setBorder(BorderFactory.createTitledBorder("AT_HOME:"));
        defaultATHomeRadioButton.setActionCommand("RadioButton");
        defaultATHomeRadioButton.setLabel("Default:");
        defaultATHomeRadioButton.setText("Default:");
        panel5.add(defaultATHomeRadioButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel5.add(defaultATHomePath, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        otherATHomeRadioButton.setActionCommand("RadioButton");
        otherATHomeRadioButton.setLabel("Other:");
        otherATHomeRadioButton.setText("Other:");
        panel5.add(otherATHomeRadioButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel5.add(otherATHomePath, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel6, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel6.setBorder(BorderFactory.createTitledBorder("AT_INIT:"));
        defaultATInitRadioButton.setActionCommand("RadioButton");
        defaultATInitRadioButton.setLabel("Default:");
        defaultATInitRadioButton.setText("Default:");
        panel6.add(defaultATInitRadioButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel6.add(defaultATInitPath, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        otherATInitRadioButton.setActionCommand("RadioButton");
        otherATInitRadioButton.setLabel("Other:");
        otherATInitRadioButton.setText("Other:");
        panel6.add(otherATInitRadioButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel6.add(otherATInitPath, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}
