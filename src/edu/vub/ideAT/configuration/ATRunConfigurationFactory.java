package edu.vub.ideAT.configuration;

import com.intellij.execution.BeforeRunTask;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;

/**
 * Created by flo on 26/08/16.
 */
public class ATRunConfigurationFactory extends ConfigurationFactory {
    private static final String FACTORY_NAME = "AmbientTalk configuration factory";

    protected ATRunConfigurationFactory(ConfigurationType type){
        super(type);
    }

    @Override
    public RunConfiguration createTemplateConfiguration(Project project){
        return new ATRunConfiguration(project,this,"AmbientTalk");
    }

    @Override
    public String getName(){
        return FACTORY_NAME;
    }


}
