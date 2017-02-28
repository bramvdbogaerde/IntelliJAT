package edu.vub.ideAT.configuration;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import edu.vub.ideAT.ui.ATRunConfigurationForm;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Created by flo on 26/08/16.
 */
public class ATSettingsEditor extends SettingsEditor<ATRunConfiguration> {
    private ATRunConfigurationForm form;
    ATRunConfiguration config;

    public ATSettingsEditor(ATRunConfiguration config){
        this.config = config;
        form = new ATRunConfigurationForm(config);
    }

    @Override
    protected void resetEditorFrom(ATRunConfiguration atRunConfiguration){
        form.setScriptPath(atRunConfiguration.getScriptPath());
        form.setScriptProject(atRunConfiguration.getScriptProject());
        if(!(atRunConfiguration.getScriptATLibPath().equals(ATConfigDefaults.getDefaultATLibPath()))){
            form.setScriptOtherATLibPath(atRunConfiguration.getScriptATLibPath());
        }
        if(!(atRunConfiguration.getScriptATCommandLineArgs().equals(ATConfigDefaults.getDefaultATCommandLineArgs()))){
            form.setIATCommandLineArgs(atRunConfiguration.getScriptATCommandLineArgs());
        }
        if(!(atRunConfiguration.getScriptATHomePath().equals(ATConfigDefaults.getDefaultATHomePath()))){
            form.setScriptOtherATHomePath(atRunConfiguration.getScriptATHomePath());
        }
        if(!(atRunConfiguration.getScriptATInitPath().equals(ATConfigDefaults.generateATInitPath(ATConfigDefaults.getDefaultATLibPath())))){
            form.setScriptOtherATInitPath(atRunConfiguration.getScriptATInitPath());
        }
    }

    @Override
    protected void applyEditorTo(ATRunConfiguration atRunConfiguration) throws ConfigurationException{
        atRunConfiguration.setScriptPath(form.getScriptPath());
        if(form.isOtherATLibPath()){
            atRunConfiguration.setScriptATLibPath(form.getOtherATLibPath());
        }
        if(form.isOtherATHomePath()){
            atRunConfiguration.setScriptATHomePath(form.getOtherATHomePath());
        }
        if(form.isOtherATInitPath()){
            atRunConfiguration.setScriptATInitPath(form.getOtherATInitPath());
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
