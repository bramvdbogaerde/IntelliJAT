package edu.vub.ideAT.configuration;

import com.intellij.openapi.options.CompositeSettingsEditor;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import edu.vub.ideAT.ui.ATRunConfigurationForm;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Created by flo on 26/08/16.
 */
public class ATSettingsEditor extends SettingsEditor<ATRunConfiguration> {
    private ATRunConfigurationForm form;
    private Project project;

    public ATSettingsEditor(Project project){
        this.project = project;
        form = new ATRunConfigurationForm(project);
    }

    @Override
    protected void resetEditorFrom(ATRunConfiguration atRunConfiguration){
        System.out.println("Scrip path in reset: " + atRunConfiguration.getScriptPath());
        form.setScriptPath(atRunConfiguration.getScriptPath());
        form.setScriptProject(project);
        if(!(atRunConfiguration.getScriptATLibPath().equals(ATConfigDefaults.getDefaultATLibPath()))){
            form.setScriptOtherATLibPath(atRunConfiguration.getScriptATLibPath());
        }
        form.setIATCommandLineArgs(atRunConfiguration.getScriptATCommandLineArgs());
    }

    @Override
    protected void applyEditorTo(ATRunConfiguration atRunConfiguration) throws ConfigurationException{
        atRunConfiguration.setScriptPath(form.getScriptPath());
        if(form.isOtherATLibPath()){
            atRunConfiguration.setScriptATLibPath(form.getOtherATLibPath());
        }
        atRunConfiguration.setScriptATCommandLineArgs(form.getIATCommandLineArgs());
    }

    @NotNull
    @Override
    protected JComponent createEditor(){
        return form.getRoot();
    }

    @Override
    protected void disposeEditor() {
        form = null;
    }

}
