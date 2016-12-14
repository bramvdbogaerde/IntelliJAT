package edu.vub.ideAT.configuration;

import com.intellij.execution.configurations.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.sun.istack.internal.NotNull;
import edu.vub.ideAT.ATIcons;

import javax.swing.*;

/**
 * Created by flo on 26/08/16.
 */
public class ATRunConfigurationType extends ConfigurationTypeBase {

    public ATRunConfigurationType(){
        super("ATRunConfigurationType","AmbientTalk","AmbientTalk run configuration", ATIcons.ATIcon);
        addFactory(new ATRunConfigurationFactory(this));
    }


    @org.jetbrains.annotations.NotNull
    public static ATRunConfigurationType getInstance() {
        return ConfigurationTypeUtil.findConfigurationType(ATRunConfigurationType.class);
    }


}
